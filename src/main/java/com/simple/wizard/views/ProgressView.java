package com.simple.wizard.views;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.PropertyKeys;
import com.simple.wizard.utils.UIUtils;
import com.simple.wizard.utils.Constants;
import static com.simple.wizard.utils.LocalizationUtils.getString;

/**
 * View to be shown when the installation process is running.
 */
public class ProgressView extends AbstractBaseView {

    private static final long serialVersionUID = 1L;
    private JLabel mTextLabel;
    private JProgressBar mProgressBar;

    /**
     * Create a new instance and set up UI.
     */
    public ProgressView() {
        super(LocalizationUtils.getString("view.progress.title"));
        setLayout(null);
        mProgressBar = new JProgressBar(0, 100);
        mProgressBar.setBounds(10, 125, 455, 25);
        add(mProgressBar);
        mTextLabel = UIUtils.createJLabel(getString("view.progress.text"), 10, 70, 460, 50);
        mTextLabel.setVerticalAlignment(SwingConstants.TOP);
        add(mTextLabel);

    }

    /**
     * Set the value of the progress bar
     * 
     * @param value
     *            the value of the progress bar to set.
     */
    public void setProgressBarValue(final int value) {
        mProgressBar.setValue(value);
        if (value == Constants.PROGRESS_MAX_VALUE) {
            firePropertyChange(PropertyKeys.TASK_COMPLETE, false, true);
        }
    }
}
