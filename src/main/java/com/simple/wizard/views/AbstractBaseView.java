package com.simple.wizard.views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.utils.UIUtils;

/**
 * Base class for the different views used in the different sections of the
 * wizard.
 */
public class AbstractBaseView extends JPanel {

    private static final long serialVersionUID = 1L;
    private final String mTitle;
    private final ViewId mViewId;
    private final JLabel mTitleLabel;
    private ViewListener mViewListener;

    /**
     * Called when a child instance is created and set the title of the view and
     * initialize the view's unique identifier.
     * 
     * @param title
     *            the view's title to set.
     */
    public AbstractBaseView(final String title) {
        this(title, null);
    }

    /**
     * Called when a child instance is created. Set the title, the listener of
     * the view and initialize the view's unique identifier.
     * 
     * @param title
     *            the view's title to set.
     * @param viewListener
     *            the view's listener to set.
     */
    public AbstractBaseView(final String title, final ViewListener viewListener) {
        setBounds(0, 0, 480, 260);
        mTitle = title;
        mViewId = ViewId.creatViewId(title);
        mViewListener = viewListener;
        mTitleLabel = UIUtils.createJLabel(title, 10, 10, 460, 50,
                                           UIUtils.PAGE_TITLE_FONT);
        add(mTitleLabel);
    }

    /**
     * Set the listener of the view.
     * 
     * @param viewListener
     *            the listener to set.
     */
    public void setViewListener(final ViewListener viewListener) {
        mViewListener = viewListener;
    }

    /**
     * @return the view's listener.
     */
    public ViewListener getViewListener() {
        return mViewListener;
    }

    /**
     * @return the title of the view.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @return the unique identifier of the view.
     */
    public ViewId getViewId() {
        return mViewId;
    }

}
