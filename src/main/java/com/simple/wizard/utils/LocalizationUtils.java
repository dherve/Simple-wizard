package com.simple.wizard.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

/**
 * Localization helper class
 */
public class LocalizationUtils {
    public static final String APPLICATION_MESSAGES_FILE = "Messages";
    public static final ResourceBundle RESSOURCE_BUNDLE = 
                        ResourceBundle.getBundle(APPLICATION_MESSAGES_FILE);

    /**
     * Get message string from the resource bundle.
     * 
     * @param key
     *            the key of the message to get.
     * @return A string associated with the provided key.
     */
    public static String getString(final String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("The locale key can't be null");
        }
        return RESSOURCE_BUNDLE.getString(key);
    }

    /**
     * Get message string from the resource bundle.
     * 
     * @param key
     *            the key of the message to get.
     * @param values
     *            the values of the message parameters
     * @return A string associated with the provided key and the parameters
     *         replaced with the values provided.
     */
    public static String getString(String key, Object... values) {
        return MessageFormat.format(RESSOURCE_BUNDLE.getString(key), values);

    }
}
