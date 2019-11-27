package com.testvagrant.core;

import com.testvagrant.model.TestInfo;
import org.testng.ITestResult;
import java.util.*;

/**
 * Created by Aishwarya
 */
public class BaseTestInitializer {

    protected ArrayList<String> getTestCaseInfo(ITestResult tr, TestInfo testInfo) {
        ArrayList<String> testCaseInfo = new ArrayList<String>();
        testCaseInfo.add("Module: " + testInfo.module());
        testCaseInfo.add("TestCase Id: " + testInfo.testCaseId());
        testCaseInfo.add("Created By: " + testInfo.createdBy());
        testCaseInfo.addAll(Arrays.asList(tr.getMethod().getGroups()));
        return testCaseInfo;
    }

    protected void initializeAndStartExtentLogger() {
        ExtentLogger.startTest(BaseContext.getTestContext().get().getClassName());
    }
}
