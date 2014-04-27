package com.simple.wizard.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Provide method for file access, creation, edit and deletion.
 */
public final class FileUtilis {

    private static final Logger LOGGER = Logger.getLogger(FileUtilis.class.getName());

    /**
     * private Constructor to prevent instantiation.
     */
    private FileUtilis() {
        throw new UnsupportedOperationException("FileUtilis instantiation" + 
                                                "not allowed !");
    }

    /**
     * Handle exception thrown caught by methods and logs them to the log file.
     * 
     * @param exception
     *            the exception thrown.
     * @param method
     *            the method from the exception was thrown and caught
     * @param args
     *            the arguments of the method from where the exception was
     *            thrown and caught.
     */
    private static void handleException(final String method,
                                        final Exception exception, 
                                        final Object... args) {
        final String stackTrace = ExceptionUtils.getStackTrace(exception);
        final String arguments = ArrayUtils.toString(args);
        LOGGER.log(Level.SEVERE, "EXCEPTION THROWN AT [{0]] \n\t {1} \n\t {2}",
                ArrayUtils.toArray(method, arguments, stackTrace));
        exception.printStackTrace();
    }

    /**
     * Check if a file exists.
     * 
     * @param filePath
     *            the full path to the file to check.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(final String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }
        final File file = new File(filePath);
        return file != null && file.exists() && (!file.isDirectory());
    }

    /**
     * Check if a directory exists.
     * 
     * @param filePath
     *            the full path to the directory to check.
     * @return true if the directory exists, false otherwise.
     */
    public static boolean directoryExists(final String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }
        final File directoryFile = new File(directoryPath);
        return directoryExists(directoryFile);
    }

    /**
     * Check if a directory exists.
     * 
     * @param directory
     *            the directory to check.
     * @return true if the directory exists, false otherwise.
     */
    public static boolean directoryExists(final File directory) {
        return directory != null && 
               directory.exists() && 
               directory.isDirectory();
    }

    /**
     * Create a new file.
     * 
     * @param filePath
     *            the full path of the file to create including the file name.
     * @return true if the file has been created successfully, false otherwise.
     * @throws IOException
     *             if an error occurs while creating the file.
     */
    public static boolean createFile(final String filePath) throws IOException {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("the file path can't be "
                    + "null or empty");
        }
        try {
            return new File(filePath).createNewFile();
        } catch (IOException exception) {
            handleException("createDirectory", exception, filePath);
            throw exception;
        }
    }

    /**
     * Create a new file in a given directory.
     * 
     * @param fileName
     *            the name of the file to create.
     * @param directory
     *            the full path to the directory (including the name) where to
     *            create the new file.
     * @return true if the file has been created successfully, false otherwise.
     * @throws Exception
     *             when an error occur while creating the file.
     */
    public static boolean createFile(final String fileName, final String directory)
    throws Exception {
        if (StringUtils.isBlank(fileName) || StringUtils.isBlank(directory)) {
            throw new IllegalArgumentException("the file name and the parent "
                    + "directory can't be null or empty");
        }

        // Try creating the directory if it doesn't exists.
        if (!(directoryExists(directory) || createDirectory(directory))) {
            return false;
        }

        return createFile(directory + File.separator + fileName);
    }

    /**
     * Create a new directory.
     * 
     * @param directoryPath
     *            the full path of the directory to create including the
     *            directory name.
     * @return true if the directory has created successfully, false otherwise.
     * @throws Exception
     *             if an error occurs while creating the directory.
     */
    public static boolean createDirectory(final String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            throw new IllegalArgumentException("The directory Path can't be "
                    + "null or empty");
        }
        return new File(directoryPath).mkdirs();
    }

    /**
     * Create a new directory in a given directory.
     * 
     * @param directoryName
     *            the name of the directory to create.
     * @param directory
     *            the full path to the parent directory (including the name)
     *            where to create the new directory.
     * @return true if the directory has been created successfully, false
     *         otherwise.
     * @throws Exception
     *             if an error occur while creating the directory.
     */
    public static boolean createDirectory(final String directoryName,
                                          final String parentDirectory) 
    throws Exception {

        if (StringUtils.isBlank(directoryName)
                || StringUtils.isBlank(parentDirectory)) {
            throw new IllegalArgumentException("the directory name and "
                    + "the parent directory can't be null or empty");
        }

        // Try to create the directory if it doesn't exists.
        if (!(directoryExists(parentDirectory) || createDirectory(parentDirectory))) {
            return false;
        }

        return createDirectory(parentDirectory + File.separator + directoryName);
    }

    /**
     * Delete a file from a directory.
     * 
     * @param fileName
     *            the name of the file to delete.
     * @param parentDirectory
     *            the parent directory of the file to delete.
     * @return true if the file has been successfully deleted, false otherwise.
     */
    public static boolean deleteFile(final String fileName,
                                     final File parentDirectory) {

        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException(
                    "The file name can't be null or empty");
        }

        if (parentDirectory == null) {
            throw new IllegalArgumentException(
                    "The parent directory can't be null");
        }

        final File file = new File(parentDirectory + File.separator + fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Delete files from a directory.
     * 
     * @param fileNames
     *            a list of names the files to delete.
     * @param parentDirectory
     *            the parent directory of the files to delete.
     * @return true if all the files were successfully deleted, otherwise false.
     */
    public static boolean deleteFiles(final List<String> fileNames,
                                      final File parentDirectory) {
        if (fileNames == null) {
            throw new IllegalArgumentException(
                    "The file name list can't be null!");
        }

        if (parentDirectory == null) {
            throw new IllegalArgumentException(
                    "The parent directory can't be null!");
        }

        boolean deleted = true;
        for (final String fileName : fileNames) {
            if (StringUtils.isBlank(fileName)) {
                throw new IllegalArgumentException("The file name to delete "
                        + "can't be null or empty");
            }
            deleted = deleted && deleteFile(fileName, parentDirectory);
        }
        return deleted;
    }

    /**
     * Delete all files in a directory.
     * 
     * @param directory
     *            the directory from which to delete files.
     * @return true if the provided directory was not null and all files inside
     *         have been successfully deleted. Otherwise return false.
     */
    public static boolean deleteDirectoryFiles(final File directory) {
        if (directory == null) {
            throw new IllegalArgumentException("the directory can't be null!");
        }

        // we can't delete files from an unexisting file nor from
        // a non-directory "file"
        if (!(directory.exists() && directory.isDirectory())) {
            return false;
        }

        boolean allDeleted = true;
        final File[] files = directory.listFiles();

        // some JVMs return null for empty dirs
        if (files != null) {
            for (File file : files) {
                allDeleted = allDeleted && file.delete();
            }
        }
        return allDeleted;
    }

}
