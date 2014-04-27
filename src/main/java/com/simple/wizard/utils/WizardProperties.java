package com.simple.wizard.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Wrapper class for the wizard properties.
 */
public class WizardProperties {

    private static Properties _applicationProperties = null;

    public static final String MESSAGE_FILE_PATH = "";

    // Resources location properties
    private static final String PROPERTIES_FILE_PATH = "/config.properties";

    // App properties keys
    public static final String ARTIFACT_NAME_KEY = "artifcat.name";
    public static final String ARTIFACT_LOCATION_KEY = "artifact.location";
    public static final String ARTIFACT_TYPE = "artifact.type";

    /**
     * Load the wizard properties from the properties file.
     * 
     * @throws FileNotFoundException
     *             if the properties file can't be found.
     * @throws IOException
     *             when an error occurs while reading the properties file.
     */
    public static final void init() throws FileNotFoundException, IOException {
        if (_applicationProperties == null) {
            _applicationProperties = PropertiesUtilis.loadProperties(PROPERTIES_FILE_PATH);
        }
    }

    /**
     * Get a property value.
     * 
     * @param key
     *            the key of the property to get.
     * @return the value of the property read.
     */
    public static final String getProperty(final String key) {
        return (String) _applicationProperties.get(key);
    }

    /**
     * @return the location (path to the folder) of artifacts.
     */
    public static final String getArtifactLocation() {
        return (String) _applicationProperties.get(ARTIFACT_LOCATION_KEY);
    }

    /**
     * @return the name of the artifacts to install.
     */
    public static final String getArtifactName() {
        return (String) _applicationProperties.get(ARTIFACT_NAME_KEY);
    }

    /**
     * @return the type of the artifact to install.
     */
    public static final String getArtifactType() {
        return (String) _applicationProperties.get(ARTIFACT_TYPE);
    }

    /**
     * @return the path to the artifacts to install.
     */
    public static final String getArtifactPath() {
        return (String) _applicationProperties.get(ARTIFACT_LOCATION_KEY)
                + File.separator
                + _applicationProperties.get(ARTIFACT_NAME_KEY) + "."
                + _applicationProperties.get(ARTIFACT_TYPE);
    }
}
