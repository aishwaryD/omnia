package com.testvagrant.web.core;

import com.testvagrant.core.*;
import com.testvagrant.util.*;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import static com.testvagrant.core.BaseContext.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class SuiteInitializer extends BaseSuiteInitializer implements ISuiteListener {

    private static String chromeDriverPath;

    public void onStart(ISuite suite) {
        readSystemSettingsFile();
        onStartSetup(suite);
    }


    public void onFinish(ISuite suite)
    {
        System.out.println("Thread" + Thread.currentThread().getId() + ": " + "Inside Suite Initializer OnFinish");
        try {
            ExtentLogger.endAllTests();
            ExtentManager.closeAllReporters();
            System.out.println("REQUEST ID:" + getRequestId() + " Completed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readSystemSettingsFile()
    {
        try {

            chromeDriverPath = SystemSettingsReader.getPropertyValue("chrome.driver.path");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}