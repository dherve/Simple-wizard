package com.simple.wizard.views;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.simple.wizard.interfaces.ViewListener;
import com.simple.wizard.utils.CommandKey;
import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.PropertyKeys;
import com.simple.wizard.utils.UIUtils;

/**
 * View to show for installation folder selection.
 */
public class FolderSelectionView extends AbstractBaseView {

    private static final long serialVersionUID = 1L;
    private JFileChooser mFileChooser;
    private JButton mBrowseButton;
    private JLabel mTextLabel;
    private JTextField mTextField;

    /**
     * Create a new instance and set up the UI.
     */
    public FolderSelectionView() {
        super(LocalizationUtils.getString("view.selectfolder.title"));
        setLayout(null);
        mFileChooser = new JFileChooser();
        mFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        mTextLabel = UIUtils.createJLabel(
                        LocalizationUtils.getString("view.selectfolder.text"),
                        10, 70, 460, 50);
        mTextLabel.setVerticalAlignment(SwingConstants.TOP);
        add(mTextLabel);

        mTextField = UIUtils.createTextField(10, 125, 350, 25);
        add(mTextField);
        mTextField.setColumns(10);
        mBrowseButton = UIUtils.createButton(
                        LocalizationUtils.getString("button.browse.label"),
                        360, 125, 100, 25, CommandKey.BROWSE_ACTION.toString());
        add(mBrowseButton);
    }

    @Override
    public void setViewListener(final ViewListener viewListener) {
        super.setViewListener(viewListener);
        mBrowseButton.addActionListener(viewListener);
        addPropertyChangeListener(viewListener);

    }

    /**
     * Open a new dialog window to browse and select a folder.
     */
    public void openDialog() {
        int retVal = mFileChooser.showOpenDialog(FolderSelectionView.this);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            mTextField.setText(mFileChooser.getSelectedFile().getAbsolutePath());
            firePropertyChange(PropertyKeys.FOLDER_SELECTED.toString(), false, true);
        }
    }

    /**
     * Set the selected path text field value.
     * 
     * @param filePath
     *            the path to set.
     */
    public void setSelectedFilePath(final String filePath) {
        mTextField.setText(filePath);
    }

    /**
     * @return the file selected by the file chooser.
     */
    public File getSelectedFile() {
        return mFileChooser.getSelectedFile();
    }
}
