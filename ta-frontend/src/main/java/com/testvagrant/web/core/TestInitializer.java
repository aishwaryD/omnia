package com.testvagrant.web.core;

import com.testvagrant.core.*;
import com.testvagrant.model.TestContext;
import com.testvagrant.model.TestInfo;
import com.testvagrant.util.InputReader;
import com.testvagrant.util.StringUtil;
import com.testvagrant.util.Util;
import org.testng.*;
import static com.testvagrant.model.Enums.*;


/**
 * Created by Aishwarya
 */
public class TestInitializer extends BaseTestInitializer implements ITestListener {

    public void onTestStart(ITestResult tr) {
        try {
            TestInfo testInfo = tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestInfo.class);
            setTestContext(tr, testInfo);
            initializeAndStartExtentLogger();
        }
        catch (Exception ex)
        {
            LogManager.logFatal("Exception in TestInitializer.onTestStart", ex);
        }
    }

    private void setTestContext(ITestResult tr, TestInfo testInfo) {
        String testClassName = tr.getMethod().getTestClass().getName();
        TestContext testContext = new TestContext();
        testContext.setTestCaseName(tr.getName());
        testContext.setTestCaseDescription(testInfo.testCaseDescription());
        testContext.setTestCaseId(testInfo.testCaseId());
        testContext.setClassName(testClassName.substring(testClassName.lastIndexOf(".") + 1));
        try {
            testContext.setRunCount(1);
            System.out.println("BaseContext.outputPath: " + BaseContext.getOutputPath());
            System.out.println("testContext.className: " + testContext.getClassName());
            System.out.println("testContext.testCaseName: " + testContext.getTestCaseName());
            System.out.println("testContext.runCount: " + testContext.getRunCount());
            testContext.setTestCaseInfo(getTestCaseInfo(tr, testInfo));
        }
        catch (Exception ex) {
            System.out.println("Exception in setTestContext: " + Util.getStackTraceFromException(ex));
        }
        BaseContext.setTestContext(testContext);
    }

    public void onTestSuccess(ITestResult tr) {
        try {
            if (StringUtil.convertStringToBoolean(InputReader.getPropertyValue("execution.leaveSessionActive"))) {

            }
            else {
                Driver.tearDown();
            }
            ExtentLogger.endTest();
        }
        catch (Exception ex)
        {
            LogManager.logFatal("Exception in TestInitializer.onTestSuccess", ex);
        }
    }

    public void onTestFailure(ITestResult tr) {
        try {
            if(BaseContext.getClientType()==ClientType.WEB && BaseContext.getPlatform()==Platform.ANDROID) {
                if (!StringUtil.isNullOrEmpty(BaseContext.getTestContext().get().getDeviceName())) {
                }
            }

            if (StringUtil.convertStringToBoolean(InputReader.getPropertyValue("execution.leaveSessionActive"))) {

            }
            else {
                Driver.tearDown();
            }
            ExtentLogger.endTest();
        }
        catch (Exception ex)
        {
            LogManager.logFatal("Exception in TestInitializer.onTestFailure", ex);
        }
    }

    public void onTestSkipped(ITestResult tr) {
        try {
            if (StringUtil.convertStringToBoolean(InputReader.getPropertyValue("execution.leaveSessionActive"))) {

            }
            else {
                Driver.tearDown();
            }
            ExtentLogger.endTest();
        }
        catch (Exception ex)
        {
            LogManager.logFatal("Exception in TestInitializer.onTestSkipped", ex);
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

}
