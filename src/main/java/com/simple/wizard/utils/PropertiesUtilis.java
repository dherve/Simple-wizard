package com.simple.wizard.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

/**
 * Provide helper methods to handle property files.
 */
public final class PropertiesUtilis {

    /**
     * private Constructor to prevent instantiation.
     */
    private PropertiesUtilis() {
        throw new UnsupportedOperationException("PropertiesUtilis instantiation" + 
                                                "not allowed !");
    }

    /**
     * Read and load an external properties file.
     * 
     * @param path
     *            path to the file to read and load.
     * @return the corresponding {@link java.util.Properties} object with the
     *         properties read from the external file.
     * @throws FileNotFoundException
     *             the file can't be found.
     * @throws IOException
     *             if an error occurred while reading the file.
     */
    public static Properties loadProperties(final String path)
    throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("The properties file path "
                    + "can't be null or empty!");
        }

        final InputStream inputStream =
                PropertiesUtilis.class.getResourceAsStream(path);
        if (inputStream == null) {

            throw new FileNotFoundException("Can't find the properties "
                    + "file from the path [" + path + "]");
        }
        properties.load(inputStream);
        return properties;
    }

    /**
     * Read and load an external properties file.
     * 
     * @param filePath
     *            path to the file to read and load.
     * @return the corresponding {@link java.util.Properties} object with the
     *         properties read from the external file.
     * @throws FileNotFoundException
     *             the file can't be found.
     * @throws IOException
     *             if an error occurred while reading the file.
     */
    public static Properties loadPropertiesFromFile(final String filePath)
    throws FileNotFoundException, IOException {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException(
                    "The properties file path can't be null or empty");
        }
        final Properties properties = new Properties();
        final InputStream in = new FileInputStream(filePath);
        properties.load(in);
        return properties;
    }

    /**
     * Save a property file
     * 
     * @param properties
     *            the properties file to save.
     * @param path
     *            the path to the location where to save the path.
     * @return true if the properties files have been successfully saved, false
     *         otherwise or if an error or an exception did occurs while saving
     *         the file.
     * @throws IOException
     * @throws ConfigurationException
     */
    public static boolean saveProperties(final Properties properties, final String path)
    throws IOException {
        if (properties == null) {
            throw new IllegalArgumentException(
                    "The properties to save can't be null");
        }

        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException(
                    "The property path can't be null or empty");
        }

        final FileOutputStream outPutStream = new FileOutputStream(path);
        properties.store(outPutStream, null);
        return true;
    }

    /**
     * Save properties to a file, using <code>java.util.Properties.store</code>
     * method
     * 
     * @param path
     *            the path to the file where to save the properties
     * @param properties
     *            the properties to save.
     * @throws IOException
     *             thrown when an error occurs while saving the properties to
     *             the file.
     */
    public static void storeProperties(final Properties properties, final String path)
    throws IOException {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException(
                    "The properties file path can't be null or empty");
        }

        if (properties == null) {
            throw new IllegalArgumentException(
                    "The properties to save can't be null");
        }

        final FileOutputStream outPutStream = new FileOutputStream(path);
        properties.store(outPutStream, null);
    }

    /**
     * Load a properties file as a HashMap.
     * 
     * @param filePath
     *            path to the properties file to load.
     * @return a hash map containing the values read from the properties file,
     *         along with the corresponding keys.
     * @throws FileNotFoundException
     *             thrown if the specified file can't be found.
     * @throws IOException
     *             if an error while accessing the file.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, String>loadPropertiesAsMap(final String filePath)
    throws FileNotFoundException, IOException {
        final Properties properties = loadProperties(filePath);
        return new HashMap<String, String>((Map) properties);
    }

    /**
     * Save a map as a properties file.
     * 
     * @param map
     *            the map to save.
     * @param propertyFilePath
     *            path to the properties file where to save map values.
     * @return true if the map has been successfully saved, false otherwise.
     * @throws IOException
     *             if an error occurs while saving the properties file.
     */
    public static boolean saveMapToPropertiesFile(final Map<String, String> map, 
                                                  final String propertyFilePath)
    throws IOException {
        // Remove the / at the start, with it the config seems to have
        // problem finding the specified file :(
        if (map == null) {
            throw new IllegalArgumentException("The properties map can't be null");
        }

        if (StringUtils.isBlank(propertyFilePath)) {
            throw new IllegalArgumentException("The property file path can't be null or empty");
        }

        final Properties properties = new Properties();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            properties.setProperty(key, value);
        }
        final File propertiesFile = new File(propertyFilePath);
        final FileOutputStream outPutStream = new FileOutputStream(propertiesFile);
        properties.store(outPutStream, null);
        return true;
    }

    /**
     * Get a {@link java.util.Map} with the values and key of a properties
     * object.
     * 
     * @param properties
     *            the properties from where to get the keys and values
     * @return A {@link java.util.Map} with the properties values and keys.
     */
    public static Map<String, String> getPropertiesMap(final Properties properties) {
        final Map<String, String> propertiesMap = new HashMap<String, String>();
        if (properties == null) {
            throw new IllegalArgumentException("The properties can't be null");
        }
        final Set<String> propertiesNames = properties.stringPropertyNames();
        for (final String name : propertiesNames) {
            propertiesMap.put(name, properties.getProperty(name));
        }
        return propertiesMap;
    }
}
