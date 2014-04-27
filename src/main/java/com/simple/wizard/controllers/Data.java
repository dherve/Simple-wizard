package com.simple.wizard.controllers;

/**
 * Generic wrapper class for the controller data.
 * 
 * @param <T>
 *            the type of data value.
 */
public class Data<T> {

    private final T mValue;

    /**
     * Create a new instance and set the value.
     * 
     * @param value
     *            the value to set.
     */
    public Data(final T value) {
        mValue = value;
    }

    /**
     * @return the data's value
     */
    public T getValue() {
        return mValue;
    }
}
