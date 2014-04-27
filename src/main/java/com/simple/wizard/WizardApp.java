package com.simple.wizard;

import java.awt.EventQueue;

import com.simple.wizard.controllers.MainViewController;
import com.simple.wizard.utils.LocalizationUtils;
import com.simple.wizard.utils.WizardProperties;
import com.simple.wizard.utils.UIUtils;

/**
 * Main application class
 */
public class WizardApp {

    private MainViewController mMainController;

    /**
     * Load the application properties, create and execute the main controller.
     */
    public void run() {
        try {
            WizardProperties.init();
        } catch (Exception e) {
            UIUtils.showErrorDialog(mMainController.getView(),
                    LocalizationUtils.getString("error.application.start"));
            // we should stop but for some reason it doesn't seem to happen ?
            return;
        }
        mMainController = new MainViewController();
        mMainController.execute();
    }

    /**
     * Initialize and start the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final WizardApp wizardApp = new WizardApp();
                    wizardApp.run();
                } catch (Exception e) {
                }
            }
        });
    }
}
