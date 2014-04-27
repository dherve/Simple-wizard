package com.simple.wizard.ui;

import javax.swing.JTextArea;

/**
 * An TextArea not editable.
 */
public final class MultilineTextContainer extends JTextArea {
    
    private static final long serialVersionUID = 1L;

    /**
     * Create a new MultilineTextContainer instance.
     * 
     * @param text
     *            the text to be shown in the MultilineTextContainer
     * @param x
     *            the x coordinate of the MultilineTextContainer
     * @param y
     *            the y cooridinate of the MultilineTextContainer
     * @param width
     *            the width of the MultilineTextContainer
     * @param height
     *            the height of the MultilineTextContainer
     */
    public MultilineTextContainer(final String text, final int x, final int y,
            final int width, final int height) {
        super(text);
        setBackground(null);
        setEditable(false);
        setBorder(null);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFocusable(false);
        setBounds(x, y, width, height);
    }
}
