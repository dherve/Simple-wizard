package com.simple.wizard.views;

import javax.swing.JCheckBox;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.ui.MultilineTextContainer;
import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.UIUtils;
import static com.simple.wizard.utils.LocalizationUtils.getString;

/**
 * View to show when the installation is complete
 */
public class FinishView extends AbstractBaseView {

    private static final long serialVersionUID = -7657078078617926955L;
    private JCheckBox mLaunchApplicationChecbox;
    private MultilineTextContainer mTextContainer;

    /**
     * Create a new instance and set the UI.
     */
    public FinishView() {
        super(LocalizationUtils.getString("view.finish.title"));
        setLayout(null);
        mTextContainer = new MultilineTextContainer(
                getString("view.finish.text", "<folder>"), 10, 70, 460, 150);
        mTextContainer.setBounds(10, 70, 460, 64);
        add(mTextContainer);
        mLaunchApplicationChecbox = UIUtils.createCheckBox(
                getString("checkbox.launch.label"),10, 160, 350, 25);
        mLaunchApplicationChecbox.setSelected(true);
        add(mLaunchApplicationChecbox);
    }

    @Override
    public void setViewListener(final ViewListener viewListener) {
        super.setViewListener(viewListener);
        mLaunchApplicationChecbox.addPropertyChangeListener(viewListener);
    }

    /**
     * @return true if the checkbox to launch the application after install is
     *         selected, false otherwise.
     */
    public boolean launchApplicationSelected() {
        return mLaunchApplicationChecbox.isSelected();
    }
}
