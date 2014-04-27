package com.simple.wizard.controllers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.simple.wizard.utils.PropertyKeys;
import com.simple.wizard.views.AbstractBaseView;
import com.simple.wizard.views.FolderSelectionView;

/**
 * Folder selection view controller.
 * 
 */
public class FolderSelectionViewController extends AbstractBaseController {

    /**
     * Create a new instance. Set the view and the configuration.
     * 
     * @param view
     *            the view to set.
     * @param configuration
     *            the configuration to set.
     */
    public FolderSelectionViewController(final AbstractBaseView view,
                                         final boolean[] configuration) {
        super(view, configuration);
        ((FolderSelectionView) mView).setViewListener(this);

    }

    @Override
    public boolean isDone() {
        // the view is done if the data is set i.e if a folder has bee selected
        return mData != null;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean cancel() {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        ((FolderSelectionView) mView).openDialog();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        if (StringUtils.equals(propertyName, PropertyKeys.FOLDER_SELECTED)) {
            final FolderSelectionView view = (FolderSelectionView) mView;
            final File file = view.getSelectedFile();
            mData = new Data<File>(file);
        }
    }

}
