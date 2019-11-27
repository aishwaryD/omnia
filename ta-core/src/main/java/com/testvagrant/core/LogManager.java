package com.testvagrant.core;

import com.testvagrant.util.StringUtil;
import com.testvagrant.util.Util;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by Aishwarya Dwivedi
 * Log Manager
 */
public class LogManager
{

    public static void logInfo(String message)
    {
        logInfo(message, false);
    }

    public static void logInfo(String message, Exception e)
    {
        logInfo(message  + " with exception " +  e.getMessage() + " at " + Util.getStackTraceFromException(e), true);
    }

    public static void logInfo(String message, Exception e, boolean isScreenshotRequired)
    {
        logInfo(message  + " with exception " +  e.getMessage() + " at " + Util.getStackTraceFromException(e), isScreenshotRequired);
    }

    public static void logSuccess(String message)
    {
        logSuccess(message, false);
    }

    public static void logError(String message)
    {
        logError(message, false);
    }

    public static void logFatal(String message)
    {
        logFatal(message, false);

    }

    public static void takeScreenshot()
    {
            ExtentLogger.logScreenshot();
    }

    public static void takeFailureScreenshot(String pageName)
    {
        CustomLogger.takeFailureScreenshot(pageName);
    }

    public static void logError(Exception e)
    {
        logError(Util.getStackTraceFromException(e), false);
    }

    public static void logError(String message, Exception e)
    {
        logError(message  + " with exception " +  e.getMessage() + " at " + Util.getStackTraceFromException(e), false);
    }

    public static void logFatal(String message, Exception e)
    {
        logFatal(message  + " with exception " +  e.getMessage() + " at " + Util.getStackTraceFromException(e), false);
    }

    public static void logSuccess(String message, boolean isScreenshotRequired)
    {
        try {
            message = removeHTMLTag(message);
            ExtentLogger.log(LogStatus.PASS, message, isScreenshotRequired);
            SoutLogger.logInfo("Thread" + Thread.currentThread().getId() + ": " + message);
            CustomLogger.logDetailsInTestCaseLogFile(message);
        }
        catch (Exception ex) {
            ExtentLogger.log(LogStatus.ERROR, ex.getMessage(), false);
        }
    }

    public static void logError(String message, boolean isScreenshotRequired)
    {
        try {
            message = removeHTMLTag(message);
            ExtentLogger.log(LogStatus.ERROR, message, isScreenshotRequired);
            if(StringUtil.isNullOrEmpty(BaseContext.getTestContext().get().getFailureReason()))
                BaseContext.getTestContext().get().setFailureReason(message);
            SoutLogger.logError("Thread" + Thread.currentThread().getId() + ": " + message);
            CustomLogger.logDetailsInTestCaseLogFile(message);
            if(BaseContext.getTestContext() != null && BaseContext.getTestContext().get() != null)
                BaseContext.getTestContext().get().setTestFailed(true);
        }
        catch (Exception ex) {
            ExtentLogger.log(LogStatus.ERROR, ex.getMessage(), false);
        }
    }

    public static void logFatal(String message, boolean isScreenshotRequired)
    {
        try {
            message = removeHTMLTag(message);
            ExtentLogger.log(LogStatus.FATAL, message, isScreenshotRequired);
            if(StringUtil.isNullOrEmpty(BaseContext.getTestContext().get().getFailureReason()))
                BaseContext.getTestContext().get().setFailureReason(message);
            SoutLogger.logError("Thread" + Thread.currentThread().getId() + ": " + message);
            CustomLogger.logDetailsInTestCaseLogFile(message);
            if(BaseContext.getTestContext() != null && BaseContext.getTestContext().get() != null)
                BaseContext.getTestContext().get().setTestFailed(true);
        }
        catch (Exception ex) {
            ExtentLogger.log(LogStatus.ERROR, ex.getMessage(), false);
        }
    }

    public static void logInfo(String message, boolean isScreenshotRequired)
    {
        try {
            message = removeHTMLTag(message);
            ExtentLogger.log(LogStatus.INFO, message, isScreenshotRequired);
            SoutLogger.logInfo("Thread" + Thread.currentThread().getId() + ": " + message);
            CustomLogger.logDetailsInTestCaseLogFile(message);
        }
        catch (Exception ex) {
            ExtentLogger.log(LogStatus.ERROR, ex.getMessage(), false);
        }
    }

    private static String removeHTMLTag(String message) {
        message = message.replaceAll("<", "").replace(">", "");
        return message;
    }

}
