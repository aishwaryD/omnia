package com.testvagrant.core;

import com.testvagrant.util.StringUtil;
import com.testvagrant.util.Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aishwarya Dwivedi
 */
public class ExtentLogger
{
    private static ExtentReports extent = null;
    private static Map<String, ExtentTest> extentTest = new HashMap<String, ExtentTest>();

    private static ExtentTest getTest() {
        try {
            return extentTest.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount());
        } catch (Exception e) {
            SoutLogger.logError("Error in ExtentLogger.getTest" + e);
            return null;
        }
    }

    protected synchronized static void startTest(String module)
    {
        String testName = BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount();
        String testDescription = BaseContext.getTestContext().get().getTestCaseDescription();
        CustomLogger.setTestStepCount(0);
        extent = ExtentManager.initializeReporter(module);
        ExtentTest test = extent.startTest(testName, testDescription);
        if(BaseContext.getTestContext().get().getTestCaseInfo() != null && BaseContext.getTestContext().get().getTestCaseInfo().size() != 0)
        {
            for (String str : BaseContext.getTestContext().get().getTestCaseInfo())
                test.assignCategory(str);
        }
        extentTest.put(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount(), test);
    }

    public static void endTest() {

            try {
                SoutLogger.logInfo("EndTest " + BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount());
                extent.endTest(extentTest.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount()));
                extentTest.remove(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount());
            } catch (Exception ex) {
                SoutLogger.logError("Error in ExtentLogger.endTest" + ex);
            }

    }

    public static void endAllTests() {
        try {
            for(Map.Entry<String, ExtentTest> test : extentTest.entrySet())
            {
                System.out.println("Thread" + Thread.currentThread().getId() + ": " + "Ending extent test for " + test.getKey());
                extent.endTest(test.getValue());
            }
        }
        catch (Exception ex)
        {
            SoutLogger.logError("Error in ExtentLogger.endAllTests" + ex);
        }
    }

    protected static void log(LogStatus status, String message, boolean  isScreenshotRequired)
    {
        ExtentTest extendLogger= null;
        extendLogger = getTest();
        logToExtentTest(status, message, isScreenshotRequired, extendLogger);
    }

    private static void logToExtentTest(LogStatus status, String message, boolean isScreenshotRequired, ExtentTest extendLogger) {
        extendLogger.log(status, message);
        if(isScreenshotRequired)
        {
            if(!BaseContext.isTurnOffScreenShot())
            {
                    String path = CustomLogger.takeScreenShot();
                    if(!StringUtil.isNullOrEmpty(path)) {
                        String image = extendLogger.addScreenCapture(path);
                        extendLogger.log(status, message, image);
                    }
            }
        }
    }

    public static void logScreenshot()
    {
        ExtentTest extendLogger= null;
        try {
            extendLogger = getTest();
            String path = CustomLogger.takeScreenShot();
            if(!StringUtil.isNullOrEmpty(path)) {
                String image = extendLogger.addScreenCapture(path);
                extendLogger.log(LogStatus.PASS, "Screenshot", image);
            }
        }
        catch (Exception ex)
        {
            SoutLogger.logError("Error in ExtentLogger.logScreenshot " + Util.getStackTraceFromException(ex));
        }
    }
}
