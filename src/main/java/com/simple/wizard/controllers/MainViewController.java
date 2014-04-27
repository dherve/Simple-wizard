package com.simple.wizard.controllers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.lang3.StringUtils;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.utils.CommandKey;
import com.simple.wizard.utils.PropertyKeys;
import com.simple.wizard.utils.UIUtils;
import com.simple.wizard.utils.WizardProperties;
import com.simple.wizard.views.FinishView;
import com.simple.wizard.views.FolderSelectionView;
import com.simple.wizard.views.MainView;
import com.simple.wizard.views.ProgressView;
import com.simple.wizard.views.ViewId;
import com.simple.wizard.views.WelcomeView;

/**
 * Wizard main controller. Set up the main view and coordinate the different
 * controllers.
 */
public class MainViewController implements ViewListener {

    private MainView mMainView;
    private List<AbstractBaseController> mControllers;
    private int mActiveControllerIndex;
    private Data<?> mData;

    /**
     * Create a new instance and set up the main view.
     */
    public MainViewController() {
        mActiveControllerIndex = 0;

        // Create views
        mMainView = new MainView(this);

        final FolderSelectionView folderSelectionView = new FolderSelectionView();
        final ProgressView progressView = new ProgressView();
        folderSelectionView.addPropertyChangeListener(PropertyKeys.FOLDER_SELECTED, this);
        progressView.addPropertyChangeListener(PropertyKeys.TASK_COMPLETE, this);

        // Controllers
        mControllers = new ArrayList<AbstractBaseController>();
        mControllers.add(new WelcomeViewController(new WelcomeView(),
                         new boolean[] { true, false, true, false }));

        final String artifactPath = WizardProperties.getArtifactPath();
        final String defaultName = WizardProperties.getArtifactName();
        mControllers.add(new FolderSelectionViewController(folderSelectionView,
                         new boolean[] { true, true, false, false }));
        mControllers.add(new ProgressViewController(progressView, artifactPath,
                defaultName, new boolean[] { true, false, false, false }));
        mControllers.add(new FinishViewController(new FinishView(),
                         new boolean[] { false, false, false, true }));

        for (AbstractBaseController controller : mControllers) {
            mMainView.addSubView(controller.getView());
        }

    }

    /**
     * Show the main view.
     */
    public void execute() {
        mMainView.setVisible(true);
    }

    /**
     * Update the main view i.e and display the view of the controller being
     * executed.
     */
    public void updateMainView() {
        final AbstractBaseController activeController = mControllers.get(mActiveControllerIndex);
        final ViewId viewId = activeController.getView().getViewId();
        final boolean[] configuration = activeController.getConfiguration();
        mMainView.showView(viewId, configuration);
        activeController.setData(mData);
        activeController.execute();
    }

    /**
     * Show the view of the step coming after the current step.
     */
    public void showNextView() {
        if (mActiveControllerIndex < mControllers.size()) {
            mActiveControllerIndex++;
        }
        updateMainView();
    }

    /**
     * Show the view of the previous step.
     */
    public void showPreviousView() {
        if (mActiveControllerIndex > 0) {
            mActiveControllerIndex--;
        }
        updateMainView();
    }

    /**
     * Called when an action in action is performed in view that previous
     * registered to.
     */
    public void actionPerformed(ActionEvent evt) {
        final CommandKey command = CommandKey.getCommand(evt.getActionCommand());
        final AbstractBaseController controller = mControllers.get(mActiveControllerIndex);
        switch (command) {
            case NEXT_VIEW_ACTION:
                showNextView();
                break;

            case PREVIOUS_VIEW_ACTION:
                showPreviousView();
                break;

            case CANCEL_ACTION:

                if (!controller.isDone()) {
                    controller.cancel();
                }
                mMainView.closeWindow();
                break;

            case FINISH_ACTION:
                if (controller instanceof FinishViewController) {
                    try {
                        ((FinishViewController) controller).launchAppliaction();
                    } catch (IOException e) {
                        UIUtils.showErrorDialog(mMainView, "An error occured " + 
                                                           "why trying to launch " +
                                                           "the application");
                    }
                }
                mMainView.closeWindow();
                break;

            default:
                break;
        }
    }

    /**
     * Called when a property change in a view previously registered to.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        if (StringUtils.equals(PropertyKeys.FOLDER_SELECTED, propertyName) ||
            StringUtils.equals(PropertyKeys.TASK_COMPLETE, propertyName)) {
            final AbstractBaseController controller =
                    mControllers.get(mActiveControllerIndex);
            final boolean[] configuration = controller.getConfiguration();

            // enable the button "next".
            configuration[2] = true;
            mData = controller.getData();
            mMainView.updateNavigation(configuration);
        }
    }

    /**
     * @return the main view.
     */
    public JFrame getView() {
        return mMainView;
    }

}
