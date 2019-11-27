package com.testvagrant.core;

import com.testvagrant.model.Enums;
import com.testvagrant.util.StringUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class CustomAssert
{
    private static Map<String, SoftAssert> softAssertForTests = new HashMap<String, SoftAssert>();

    public static void fail()
    {
        fail(null, false);
    }

    public static void fail(String message)
    {
        fail(message, false);
    }

    public static void fail(String message, boolean continueOnFailure)
    {
        if(!StringUtil.isNullOrEmpty(message))
            LogManager.logError("FAILED: " + message);
        if(continueOnFailure)
            softAssertForTests.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount()).assertTrue(false, message);
        else
            failTheTestCase(message);
    }

    public static void failTheTestCase(String message, String methodName , String pageName , String activityName)
    {
        BaseContext.getTestContext().get().setFailureMethod(methodName);
        BaseContext.getTestContext().get().setFailurePage(pageName);
        BaseContext.getTestContext().get().setFailureActivity(activityName);
        assertAll();
        Assert.fail(message);
    }

    private static void failTheTestCase(String message)
    {
        assertAll();
        Assert.fail(message);
    }

    public static boolean assertEmpty(String actual, String message, boolean continueOnFailure)
    {
        if(!StringUtil.isNullOrEmpty(actual))
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertNotEmpty(String actual, String message, boolean continueOnFailure)
    {
        if(StringUtil.isNullOrEmpty(actual))
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertIsNumeric(String actual, String message, boolean continueOnFailure)
    {
        if(!NumberUtils.isCreatable(actual))
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertIsDouble(String actual, String message, boolean continueOnFailure)
    {
        String[] arrayNumbers= null;
        if(actual.contains(".")){
            arrayNumbers = actual.split("\\.");
        }
        if(!(NumberUtils.isCreatable(arrayNumbers[0].replace(",","")) && NumberUtils.isCreatable(arrayNumbers[1]) && arrayNumbers.length==2))
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertEquals(boolean actual, boolean expected, String message, boolean continueOnFailure)
    {
        if(actual != expected)
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertNotEquals(boolean actual, boolean expected, String message, boolean continueOnFailure)
    {
        if(actual == expected)
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertEquals(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual != expected)
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertLessThan(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual < expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertLessThanOREqualTo(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual <= expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertGreaterThan(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual > expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertGreaterThanOREqualTo(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual >= expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertEquals(double actual, double expected, String message, boolean continueOnFailure)
    {
        if(actual == expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertLessThan(double actual, double expected, String message, boolean continueOnFailure)
    {
        if(actual < expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertLessThanOREqualTo(double actual, double expected, String message, boolean continueOnFailure)
    {
        if(actual <= expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertGreaterThan(double actual, double expected, String message, boolean continueOnFailure)
    {
        if(actual > expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }

    public static boolean assertGreaterThanOREqualTo(double actual, double expected, String message, boolean continueOnFailure)
    {
        if(actual >= expected)
        {
            logSuccessMessage(message);
            return true;
        }
        else {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
    }


    public static boolean assertNotEquals(int actual, int expected, String message, boolean continueOnFailure)
    {
        if(actual == expected)
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    public static boolean assertIsBoolean(String actual, String message, boolean continueOnFailure)
    {
        if(actual == null || !(actual.equalsIgnoreCase("true") || actual.equalsIgnoreCase("false")))
        {
            logFailureMessage(message, continueOnFailure);
            return false;
        }
        else {
            logSuccessMessage(message);
            return true;
        }
    }

    private static void logFailureMessage(String message, boolean continueOnFailure)
    {
        LogManager.logError("FAILED: PageName: " + getCallingClassName() + " MethodName: " + getCallingMethodName() + " ActivityName: " + getCallingActivityName() + " Message: " + message);

        if((BaseContext.getTestType().equals(Enums.TestType.WEB) || BaseContext.getTestType().equals(Enums.TestType.APP)) && BaseContext.isTakeFullScreenshot()
                && !BaseContext.getTestContext().get().isSkipScreenshot())
            LogManager.takeFailureScreenshot("Failure Full Page Screenshot");
        if(continueOnFailure && softAssertForTests != null &&softAssertForTests.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount()) != null)
            softAssertForTests.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount()).assertTrue(false, message);
        else
            failTheTestCase(message);
    }

    private static void logSuccessMessage(String message)
    {
        LogManager.logSuccess("SUCCESS: " + message);
    }

    public static void assertAll()
    {
        SoftAssert softAssert = softAssertForTests.get(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount());
        if(softAssert != null) {
            softAssertForTests.remove(BaseContext.getTestContext().get().getTestCaseName() + "_Run_" + BaseContext.getTestContext().get().getRunCount());
            softAssert.assertAll();
        }
    }

    private static String getCallingMethodName()
    {
        String methodName = Thread.currentThread().getStackTrace()[6].getMethodName().trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed"))
            methodName = Thread.currentThread().getStackTrace()[7].getMethodName().trim();
        else
            methodName = Thread.currentThread().getStackTrace()[6].getMethodName().trim();
        BaseContext.getTestContext().get().setFailureMethod(methodName);
        return methodName;
    }

    private static String getCallingClassName()
    {
        String className = null;
        String methodName = Thread.currentThread().getStackTrace()[6].getMethodName().trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed")) {
            className = Thread.currentThread().getStackTrace()[7].getClassName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        else
        {
            className = Thread.currentThread().getStackTrace()[6].getClassName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        BaseContext.getTestContext().get().setFailurePage(className);
        return className;
    }

    private static String getCallingActivityName()
    {
        String className = null;
        String methodName = Thread.currentThread().getStackTrace()[6].getClassName().trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed")) {
            className = Thread.currentThread().getStackTrace()[7].getMethodName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        else {
            className = Thread.currentThread().getStackTrace()[6].getMethodName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        BaseContext.getTestContext().get().setFailureActivity(className);
        return className;
    }

}
