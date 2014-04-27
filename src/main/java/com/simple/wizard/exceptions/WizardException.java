package com.simple.wizard.exceptions;

/**
 * Wrapper class for all exception thrown inside the application.
 */
public class WizardException extends Exception {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new instance.
     * 
     * @param message
     *            the exception message.
     * @param cause
     *            the exception to be wrapped.
     */
    public WizardException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
