package com.simple.wizard.views;

import javax.swing.JTextArea;

import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.UIUtils;

/**
 * View to show upon the wizard launch.
 */
public class WelcomeView extends AbstractBaseView {
    private static final long serialVersionUID = 1L;
    private final JTextArea m_textArea;

    /**
     * Create a new instance and set up the UI.
     */
    public WelcomeView() {
        super(LocalizationUtils.getString("view.welcome.title"));
        setLayout(null);
        m_textArea = UIUtils.createTextArea(10, 80, 460, 150);
        m_textArea.setEditable(false);
        m_textArea.setBorder(UIUtils.LIGHT_GRAY_BORDER);
        m_textArea.setText(LocalizationUtils.getString("view.welcome.text"));
        add(m_textArea);
    }
}
