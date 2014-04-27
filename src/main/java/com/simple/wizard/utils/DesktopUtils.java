package com.simple.wizard.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

public final class DesktopUtils {

    /**
     * private Constructor to prevent instantiation.
     */
    private DesktopUtils(){
        throw new UnsupportedOperationException("DesktopUtils instantiation" +
                                                "not allowed !");
    }
    
    /**
     * Open the default browser and load the page specified.
     * 
     * @param url
     *            the url of the page to be opened in the browser.
     * @throws URISyntaxException
     *             if the provided url is not correct.
     * @throws IOException
     *             if an error occurs while opening the directory.
     */
    public static final void openWebPage(final URL url)
    throws IOException, URISyntaxException {
        if (url == null) {
            throw new IllegalArgumentException("The url can't be null !");
        }

        final Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new UnsupportedOperationException("Can't open the browser "
                    + "with the url " + url);
        }
        desktop.browse(url.toURI());

    }

    /**
     * Open a directory.
     * 
     * @param directoryPath
     *            the path to the directory to open.
     * @throws IOException
     *             if an error occurs while opening the directory.
     */
    public static final void openDirectory(final String directoryPath)
    throws IOException {
        if (StringUtils.isBlank(directoryPath)) {
            throw new IllegalArgumentException("The directory path can't be " + 
                                               "null or empty !");
        }

        final Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new UnsupportedOperationException("Can't open the directory " +
                                                    directoryPath);
        }
        final File directory = new File(directoryPath);
        desktop.open(directory);
    }
}
