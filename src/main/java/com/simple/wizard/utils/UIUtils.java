package com.simple.wizard.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Helper class used to create GUI elememts.
 * 
 */
public final class UIUtils {

    public static final int BUTTON_DEFAULT_WIDTH = 90;
    public static final int BUTTON_DEFAULT_HEIGHT = 25;
    public static final Font PAGE_TITLE_FONT = new Font("Franklin Gothic Medium", 
                                                        Font.PLAIN, 36);
    public static final Font TEXT_FIELD_FONT = new Font("Arial", Font.PLAIN, 13);
    public static final Border LIGHT_GRAY_BORDER = new LineBorder(Color.LIGHT_GRAY);

    /**
     * Create a new button.
     * 
     * @param text
     *            the button text.
     * @param x
     *            the x coordinate of the button.
     * @param y
     *            the y coordinate of the button.
     * @return A new {@link javax.swing.JButton} instance.
     */
    public static final JButton createButton(final String text, final int x,
            final int y) {
        return createButton(text, x, y, BUTTON_DEFAULT_WIDTH,
                BUTTON_DEFAULT_HEIGHT);
    }

    /**
     * Create a new button.
     * 
     * @param text
     *            the button text.
     * @param x
     *            the x coordinate of the button.
     * @param y
     *            the y coordinate of the button.
     * @param actionCommand
     *            the actionCommand of the button.
     * @return A new {@link javax.swing.JButton} instance.
     */
    public static final JButton createButton(final String text, final int x,
            final int y, final String actionCommand) {
        return createButton(text, x, y, BUTTON_DEFAULT_WIDTH,
                BUTTON_DEFAULT_HEIGHT, actionCommand);
    }

    /**
     * Create a new button.
     * 
     * @param text
     *            the button text..
     * @param x
     *            the x coordinate of the button.
     * @param y
     *            the y coordinate of the button.
     * @param width
     *            the width of the button.
     * @param height
     *            the height of the button.
     * @return A new {@link javax.swing.JButton} instance.
     */
    public static final JButton createButton(final String text, final int x,
            final int y, final int width, final int height) {
        final JButton button = new JButton(text);
        button.setName(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    /**
     * Create a new button.
     * 
     * @param text
     *            the button text.
     * @param x
     *            the x coordinate of the button.
     * @param y
     *            the y coordinate of the button.
     * @param width
     *            the width of the button.
     * @param height
     *            the height of the button.
     * @param actionCommand
     *            the actionCommand of the button.
     * @return A new {@link javax.swing.JButton} instance.
     */
    public static final JButton createButton(final String text, final int x,
            final int y, final int width, final int height,
            final String actionCommand) {
        final JButton button = new JButton(text);
        button.setName(text);
        button.setActionCommand(actionCommand);
        button.setBounds(x, y, width, height);
        return button;
    }

    /**
     * Create a new label.
     * 
     * @param text
     *            the label text.
     * @param x
     *            the x coordinate of the label.
     * @param y
     *            the y coordinate of the label.
     * @param width
     *            the width of the label.
     * @param height
     *            the height of the label.
     * @param font
     *            the label text font.
     * @return A new {@link javax.swing.JLabel} instance.
     */
    public static final JLabel createJLabel(final String text, final int x,
            final int y, final int width, final int height, final Font font) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;

    }

    /**
     * Create a new label.
     * 
     * @param text
     *            the label text.
     * @param x
     *            the x coordinate of the label.
     * @param y
     *            the y coordinate of the label.
     * @param width
     *            the width of the label.
     * @param height
     *            the height of the label.
     * @return A new {@link javax.swing.JLabel} instance.
     */
    public static final JLabel createJLabel(final String text, final int x,
            final int y, final int width, final int height) {
        return createJLabel(text, x, y, width, height, TEXT_FIELD_FONT);
    }

    /**
     * Create a new TextArea
     * 
     * @param x
     *            the x coordinate of the textarea.
     * @param y
     *            the y coordinate of the textarea.
     * @param width
     *            the width of the textarea.
     * @param height
     *            the height of the textarea.
     * @return A new {@link javax.swing.JTextArea} instance.
     */
    public static final JTextArea createTextArea(final int x, final int y,
            final int width, final int height) {
        final JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, width, height);
        return textArea;
    }

    /**
     * Create a new Checkbox
     * 
     * @param label
     *            the checkbox label.This is the text appearing next to the
     *            checkobox.
     * @param x
     *            the checkbox x coordinate
     * @param y
     *            the checkbox y coordinate.
     * @param width
     *            the checkbox width.
     * @param height
     *            the checkbox height.
     * @return A new {@link javax.swing.JCheckBox} instance.
     */
    public static final JCheckBox createCheckBox(final String label,
            final int x, final int y, final int width, final int height) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setBounds(x, y, width, height);
        return checkBox;
    }

    /**
     * Create a new Textfield
     * 
     * @param x
     *            the textfiled x coordinate.
     * @param y
     *            the textfiled y coordinate.
     * @param width
     *            the textfield width.
     * @param height
     *            the textfield height.
     * @return A new {@link javax.swing.JTextField} instance.
     */
    public static final JTextField createTextField(final int x, final int y,
            final int width, final int height) {
        final JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setColumns(10);
        return textField;
    }

    /**
     * Show an error dialog.
     * 
     * @param component
     *            the parent component of the dialog window to show.
     * @param message
     *            the error message to show.
     */
    public static final void showErrorDialog(final Component component,
            final String message) {
        JOptionPane.showMessageDialog(component, message, "Wizard Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
