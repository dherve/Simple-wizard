package com.simple.wizard.controllers;

import com.simple.wizard.views.AbstractBaseView;

/**
 * Welcome view controller.
 */
public class WelcomeViewController extends AbstractBaseController {

    /**
     * Create a new instance. Set the view to control and controller view.
     * 
     * @param view
     *            the view to set.
     * @param configuration
     *            the configuration to set.
     */
    public WelcomeViewController(final AbstractBaseView view,
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

}
