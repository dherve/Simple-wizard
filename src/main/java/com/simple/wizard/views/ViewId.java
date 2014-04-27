package com.simple.wizard.views;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Wrapper class for the unique identifier of the view
 */
public final class ViewId {

    /**
     * Factory method to create new {@link com.afp.wizard.views.ViewId} from a
     * specific value.
     * 
     * @param value
     *            the value to use to create generate the unique identifier.
     * @return a new {@link com.afp.wizard.views.ViewId} instance.
     */
    public static final ViewId creatViewId(final String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(
                    "A value is required to create the id");
        }
        return new ViewId(StringUtils.deleteWhitespace(WordUtils.capitalize(value)));
    }

    private final String mValue;

    /**
     * Create a new instance and set the value of the unique identifier.
     * 
     * @param value
     */
    public ViewId(final String value) {
        mValue = value;
    }

    /**
     * @return the value of the identifier wrapped.
     */
    public String getValue() {
        return mValue;
    }
}
