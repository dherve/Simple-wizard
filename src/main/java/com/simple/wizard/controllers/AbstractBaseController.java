package com.simple.wizard.controllers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.views.AbstractBaseView;

/**
 * Base class for all view controllers. These controllers perform operations or
 * update the associated views depending of the view's actions. Each controller
 * is associated to a step in the wizard execution.
 */
public abstract class AbstractBaseController implements ViewListener {

    /**
     * Controller configuration. A configuration is an boolean array indicating
     * what should be the states (enabled or disabled) of the wizard navigation
     * buttons (cancel, next, previous, ...) when the view associated this
     * controller is shown. The order of the buttons is from the left to the
     * right where the state of the leftmost button correspond to the first
     * element in the array and the state of the rightmost button correspond to
     * the last element of the array.
     */
    protected boolean[] mConfiguration;
    protected Data<?> mData;
    protected AbstractBaseView mView;

    /**
     * Set the configuration of the controller.
     * 
     * @param configuration
     *            the configuration to set.
     */
    public AbstractBaseController(final boolean[] configuration) {
        this(null, configuration);
    }

    /**
     * Set the view and configuration of the controller.
     * 
     * @param view
     *            the view to set.
     * @param configuration
     *            the configuration to set.
     */
    public AbstractBaseController(final AbstractBaseView view,
                                  final boolean[] configuration) {
        mView = view;
        mConfiguration = configuration;
    }

    /**
     * @return true if the view has finished executing the main operation it is
     *         supposed to do.
     */
    public abstract boolean isDone();

    /**
     * Perform the controller's main operation.
     */
    public abstract void execute();

    /**
     * Cancel any operation being executed by the controller.
     * 
     * @return true if the operation has been successfully canceled, false
     *         otherwise.
     */
    public abstract boolean cancel();

    /**
     * @return the view being controlled and listener by this controller.
     */
    public AbstractBaseView getView() {
        return mView;
    }

    /**
     * @return the controller's configuration.
     */
    public boolean[] getConfiguration() {
        return mConfiguration;
    }

    /**
     * Set the controller's data.
     * 
     * @param data
     *            the data to set.
     */
    public void setData(final Data<?> data) {
        mData = data;
    }

    /**
     * @return the controller's data.
     */
    public Data<?> getData() {
        return mData;
    }

    public void actionPerformed(ActionEvent evt) {
    }

    public void propertyChange(PropertyChangeEvent evt) {
    }

}
