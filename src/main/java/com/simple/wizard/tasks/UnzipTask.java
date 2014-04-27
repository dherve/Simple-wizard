package com.simple.wizard.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;

import com.simple.wizard.exceptions.WizardException;

/**
 * Helper class to unzip compressed files (Here the application to install)
 */
public class UnzipTask extends SwingWorker<Void, Void> {

    private final String mZipFileName;
    private final String mOutputFolderPath;

    /**
     * Create a new instance
     * 
     * @param zipFileName
     *            the name of the file to unzip
     * @param outputFolderPath
     *            the path to the folder where to unzip the files.
     */
    public UnzipTask(final String zipFileName, final String outputFolderPath) {
        mZipFileName = zipFileName;
        mOutputFolderPath = outputFolderPath;
    }

    @Override
    protected Void doInBackground() throws Exception {
        if (mZipFileName == null) {
            throw new IllegalStateException("No File provide to unzip");
        }

        if (mOutputFolderPath == null) {
            throw new IllegalStateException("No destination folder found");
        }
        // Code adapted from :
        // http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
        // with slight changes to get the progress and handle intermediate
        // directory creation.
        byte[] buffer = new byte[1024];

        try {

            // create output directory is not exists
            final File outputFolder = new File(mOutputFolderPath);
            if (!outputFolder.exists()) {
                outputFolder.mkdir();
            }
            double totalRead = 0.0;

            final InputStream inpuStream =
                    UnzipTask.class.getResourceAsStream(mZipFileName);
            final ZipInputStream zipInputStream =
                    new ZipInputStream(inpuStream);

            final double totalSize = (double) inpuStream.available();

            // get the first zipped file list entry
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {

                final String fileName = zipEntry.getName();

                totalRead += zipEntry.getCompressedSize();
                final File newFile =
                        new File(mOutputFolderPath + File.separator + fileName);
                if (zipEntry.isDirectory()) {
                    FileUtils.forceMkdir(newFile.getParentFile());
                } else {
                    new File(newFile.getParent()).mkdirs();

                    // workaround for jar =>
                    final FileOutputStream fileOutputStream =
                            new FileOutputStream(newFile);

                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }

                    double percentage = (totalRead / totalSize) * 100.0;
                    setProgress((int) percentage);
                    fileOutputStream.close();
                }
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();
            setProgress(100);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new WizardException("An error occured while porcessing", ex);
        }
        return null;
    }
}
