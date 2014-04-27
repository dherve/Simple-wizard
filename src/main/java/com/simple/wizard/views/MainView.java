package com.simple.wizard.views;

import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.utils.CommandKey;
import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.UIUtils;

/**
 * Wizard main view.
 */
public class MainView extends JFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2224962951615861814L;

    private JPanel mMainPanel;
    private JPanel mViewsPanel;
    private CardLayout mViewsCardLayout;
    private List<JButton> mNavigation = new ArrayList<JButton>();

    /**
     * Create a new instance, set up the ui and set the view controller.
     * 
     * @param viewListener
     *            the listener to set. This is the controller of this view.
     */
    public MainView(final ViewListener viewListener) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 325);
        setTitle(LocalizationUtils.getString("app.title"));
        mMainPanel = new JPanel();
        mMainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mMainPanel);
        mMainPanel.setLayout(null);

        mViewsCardLayout = new CardLayout(0, 0);

        mViewsPanel = new JPanel();
        mViewsPanel
                .setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        mViewsPanel.setBounds(10, 10, 480, 250);
        mMainPanel.add(mViewsPanel);
        mViewsPanel.setLayout(mViewsCardLayout);

        mNavigation = new ArrayList<JButton>();
        final int yButtons = 270;
        mNavigation.add(UIUtils.createButton(
                LocalizationUtils.getString("button.cancel.label"), 135,
                yButtons, CommandKey.CANCEL_ACTION.toString()));

        mNavigation.add(UIUtils.createButton(
                LocalizationUtils.getString("button.previous.label"), 225,
                yButtons, CommandKey.PREVIOUS_VIEW_ACTION.toString()));
        mNavigation.add(UIUtils.createButton(
                LocalizationUtils.getString("button.next.label"), 315,
                yButtons, CommandKey.NEXT_VIEW_ACTION.toString()));
        mNavigation.add(UIUtils.createButton(
                LocalizationUtils.getString("button.finish.label"), 405,
                yButtons, CommandKey.FINISH_ACTION.toString()));

        for (JButton button : mNavigation) {
            button.addActionListener(viewListener);
            mMainPanel.add(button);
        }
    }

    /**
     * Set a specific view as the current view (the one shown.
     * 
     * @param viewId
     *            the id of the view to show.
     * @param configuration
     *            the button configuration for this view.
     */
    public void showView(final ViewId viewId, final boolean[] configuration) {
        mViewsCardLayout.show(mViewsPanel, viewId.getValue());
        updateNavigation(configuration);
    }

    /**
     * Update the navigation button based on a configuration
     * 
     * @param configuration
     *            the new configuration of the buttons.
     */
    public void updateNavigation(final boolean[] configuration) {
        for (int i = 0; i < configuration.length; i++) {
            mNavigation.get(i).setEnabled(configuration[i]);
        }
    }

    /**
     * Add a child view.
     * 
     * @param subView
     *            the view to add.
     */
    public void addSubView(final AbstractBaseView subView) {
        mViewsPanel.add(subView, subView.getViewId().getValue());
    }

    /**
     * close the Wizard window.
     */
    public void closeWindow() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
