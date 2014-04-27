package com.simple.wizard.controllers;

import java.beans.PropertyChangeEvent;
import java.io.File;

import javax.swing.SwingWorker;

import com.simple.wizard.utils.FileUtilis;
import com.simple.wizard.tasks.UnzipTask;
import com.simple.wizard.views.AbstractBaseView;
import com.simple.wizard.views.ProgressView;

/**
 * Progress view installer.
 */
public class ProgressViewController extends AbstractBaseController {

    private SwingWorker<?, ?> mUnzipTask;
    private String mSourceArtifactsPath;
    private String mDestinationFolderName;
    private String mInstallationFolderPath;

    /**
     * Create a new instance. Set the view and the configuration.
     * 
     * @param view
     *            the view to set.
     * @param configuration
     *            the configuration to set.
     */
    public ProgressViewController(final AbstractBaseView view,
                                  final boolean[] configuration) {
        super(view, configuration);
    }

    /**
     * Create a new instance. Set the view, the configuration,
     * sourceArtifactsPath and the destination folder name.
     * 
     * @param view
     *            the view to set.
     * @param sourceArtifactsPath
     *            the path to the source artifact. This is the path to the zip
     *            file to unzip/install.
     * @param destinationFolderName
     *            the name of the folder where the source artifact should be
     *            unzipped/installed
     * @param configuration
     *            the configuration view.
     */
    public ProgressViewController(final AbstractBaseView view,
                                  final String sourceArtifactsPath,
                                  final String destinationFolderName,
                                  final boolean[] configuration) {
        super(view, configuration);
        mSourceArtifactsPath = sourceArtifactsPath;
        mDestinationFolderName = destinationFolderName;
    }

    /**
     * @return true if the task is complete. Otherwise false.
     */
    public boolean isDone() {
        return mUnzipTask == null ? false : mUnzipTask.isDone();
    }

    @Override
    public void execute() {
        final File file = (File) mData.getValue();
        if (file != null) {
            String targetPath = file.getAbsolutePath();
            if (!FileUtilis.directoryExists(file)) {
                throw new IllegalStateException("The directory [" + 
                                                file.getAbsolutePath() + 
                                                " does not exist");
            }
            mInstallationFolderPath = targetPath + File.separator + 
                                      mDestinationFolderName;
            mUnzipTask = new UnzipTask(mSourceArtifactsPath, 
                                       mInstallationFolderPath);
            mUnzipTask.addPropertyChangeListener(this);
            mUnzipTask.execute();
        }
    }

    @Override
    public boolean cancel() {
        return mUnzipTask.cancel(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            ((ProgressView) mView).setProgressBarValue(progress);
            if (progress == 100) {
                mData = new Data<String>(mInstallationFolderPath);
            }
        }

    }
}
