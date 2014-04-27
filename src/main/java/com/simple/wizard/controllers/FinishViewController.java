package com.simple.wizard.controllers;

import java.io.IOException;

import com.simple.wizard.utils.DesktopUtils;
import com.simple.wizard.views.AbstractBaseView;
import com.simple.wizard.views.FinishView;

/**
 * Finish view's controller.
 */
public class FinishViewController extends AbstractBaseController {

    /**
     * Create a new instance. Set the view to control and controller view.
     * 
     * @param view
     *            the view to set.
     * @param configuration
     *            the configuration to set.
     */
    public FinishViewController(final AbstractBaseView view,
                                final boolean[] configuration) {
        super(view, configuration);
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean cancel() {
        return true;
    }

    /**
     * Open the installation folder if the corresponding option was selected.
     * 
     * @throws IOException
     */
    public void launchAppliaction() throws IOException {
        if (((FinishView) mView).launchApplicationSelected()) {
            // open the installation folder
            final String directory = (String) mData.getValue().toString();
            DesktopUtils.openDirectory(directory);
            System.out.println("Launching the APP ...");
        }
    }

}
