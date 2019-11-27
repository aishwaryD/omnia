package com.testvagrant.core;

import com.testvagrant.model.*;
import com.testvagrant.util.*;
import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya
 */

public class BaseContext {

    private static String valuePath = "";
    private static boolean gridRun = false;
    private static String environmentURL;
    private static String outputPath;
    private static ExecutionLocale locale;
    private static boolean runHeadless = false;
    private static String environmentName;
    private static boolean isProd;
    private static boolean isUAT;
    private static boolean isSTG;
    private static TestType testType;
    private static ClientType clientType;
    private static Platform platform;
    private static TestPlan testPlan;
    private static String requestId;
    private static BrowserType desktopBrowser;
    private static String suiteName;
    private static boolean turnOffScreenShot = false;
    private static boolean takeFullScreenshot;
    private static BrowserType deviceBrowser;
    private static String appSeleniumServerIP;
    private static int appSeleniumPort;
    private static InheritableThreadLocal<TestContext> testContext = new InheritableThreadLocal<TestContext>();

    protected static void setBaseContext() {
        testType = TestType.valueOf(InputReader.getPropertyValue("test.type").toUpperCase());
        platform = Platform.valueOf(InputReader.getPropertyValue("device.platform").toUpperCase());
        clientType = ClientType.valueOf(InputReader.getPropertyValue("device.clienttype").toUpperCase());
        desktopBrowser = BrowserType.valueOf(InputReader.getPropertyValue("desktop.browser").toUpperCase());
        deviceBrowser = BrowserType.valueOf(InputReader.getPropertyValue("device.browser").toUpperCase());
        environmentName = InputReader.getPropertyValue("environment.name");
        if(environmentName.equalsIgnoreCase("production"))
            isProd = true;
        if(environmentName.toLowerCase().contains("uat"))
            isUAT = true;
        if(environmentName.toLowerCase().contains("stg"))
            isSTG = true;
        runHeadless = StringUtil.convertStringToBoolean(InputReader.getPropertyValue("execution.headless"));
        locale = getLocal();
        environmentURL = InputReader.getPropertyValue("environment.url");
        testPlan = getTestPlan();
        validateTestPlanAndSuite();
        requestId = InputReader.getPropertyValue("request.id");
        turnOffScreenShot = StringUtil.convertStringToBoolean(InputReader.getPropertyValue("execution.turnoffscreenshot"));
        gridRun = StringUtil.convertStringToBoolean(SystemSettingsReader.getPropertyValue("grid.run"));
        appSeleniumServerIP = SystemSettingsReader.getPropertyValue("app.selenium.server.ip");
        appSeleniumPort = StringUtil.convertStringToInteger(SystemSettingsReader.getPropertyValue("app.selenium.port"));
        SoutLogger.logInfo("Suite Name :: " + BaseContext.suiteName);
    }

    public static void setTestContext(TestContext testContext) {
        BaseContext.testContext.set(testContext);
    }

    private static void validateTestPlanAndSuite() {
        if(BaseContext.suiteName.toLowerCase().contains("mobileweb")) {
            if(!(testPlan == TestPlan.MOBILEWEB)) {
                SoutLogger.logInfo("Test Plan and Suite miss-match detected");
                System.exit(0);
            }
        }
        if(BaseContext.suiteName.toLowerCase().contains("desktopweb")) {
            if(!(testPlan == TestPlan.DESKTOPWEB)) {
                SoutLogger.logInfo("Test Plan and Suite miss-match detected");
                System.exit(0);
            }

        }
    }

    private static Enums.ExecutionLocale getLocal() {
        String localeString = InputReader.getPropertyValue("environment.locale");
        localeString = StringUtil.isNullOrEmpty(localeString) ? "IN":localeString;
        if(Util.isValidEnumValue(ExecutionLocale.class, localeString))
        {
            return ExecutionLocale.valueOf(localeString);
        }
        return ExecutionLocale.valueOf(localeString);
    }

    private static TestPlan getTestPlan() {
        TestPlan plan = null;
        if(platform.equals(Platform.DESKTOP) && !desktopBrowser.equals(BrowserType.CHROMEMOBILEEMULATION))
            plan = TestPlan.DESKTOPWEB;
        else if((platform.equals(Platform.DESKTOP) && desktopBrowser.equals(BrowserType.CHROMEMOBILEEMULATION))
                || (!platform.equals(Platform.DESKTOP)  && clientType.equals(ClientType.WEB)))
            plan = TestPlan.MOBILEWEB;
        return plan;
    }

    public static String getValuePath() {
        return valuePath;
    }

    public static void setValuePath(String valuePath) {
        BaseContext.valuePath = valuePath;
    }

    public static boolean isGridRun() {
        return gridRun;
    }

    public static String getEnvironmentURL() {
        return environmentURL;
    }

    public static String getOutputPath() {
        return outputPath;
    }

    public static void setOutputPath(String outputPath) {
        BaseContext.outputPath = outputPath;
    }

    public static boolean isRunHeadless() {
        return runHeadless;
    }

    public static String getEnvironmentName() {
        return environmentName;
    }

    public static boolean isProd() {
        return isProd;
    }

    public static TestType getTestType() {
        return testType;
    }

    public static void setTestType(TestType testType) {
        BaseContext.testType = testType;
    }

    public static ClientType getClientType() {
        return clientType;
    }

    public static Platform getPlatform() {
        return platform;
    }

    public static void setPlatform(Platform platform) {
        BaseContext.platform = platform;
    }

    public static String getRequestId() {
        return requestId;
    }

    public static void setRequestId(String requestId) {
        BaseContext.requestId = requestId;
    }

    public static BrowserType getDesktopBrowser() {
        return desktopBrowser;
    }

    public static void setSuiteName(String suiteName) {
        BaseContext.suiteName = suiteName;
    }

    public static boolean isTurnOffScreenShot() {
        return turnOffScreenShot;
    }

    public static boolean isTakeFullScreenshot() {
        return takeFullScreenshot;
    }

    public static void setTakeFullScreenshot(boolean takeFullScreenshot) {
        BaseContext.takeFullScreenshot = takeFullScreenshot;
    }

    public static BrowserType getDeviceBrowser() {
        return deviceBrowser;
    }

    public static String getAppSeleniumServerIP() {
        return appSeleniumServerIP;
    }

    public static int getAppSeleniumPort() {
        return appSeleniumPort;
    }

    public static InheritableThreadLocal<TestContext> getTestContext() {
        return testContext;
    }

    public static ExecutionLocale getLocale() {
        return locale;
    }

    public static boolean isAndroidWeb(){
        if(BaseContext.getPlatform() == Platform.ANDROID && BaseContext.getClientType() == ClientType.WEB)
            return true;
        return false;
    }

    public static boolean isIOSWeb(){
        if(BaseContext.getPlatform() == Platform.IOS && BaseContext.getClientType() == ClientType.WEB)
            return true;
        return false;
    }

    public static boolean isDesktopMobileEmulation(){
        if(BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getDesktopBrowser() == BrowserType.CHROMEMOBILEEMULATION)
            return true;
        return false;
    }

    public static boolean isAndroidApp(){
        if(BaseContext.getPlatform() == Platform.ANDROID && BaseContext.getClientType() == ClientType.APP)
            return true;
        return false;
    }

    public static boolean isIOSApp(){
        if(BaseContext.getPlatform() == Platform.IOS && BaseContext.getClientType() == ClientType.APP)
            return true;
        return false;
    }

    public static boolean isDesktopWeb(){
        if(BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getClientType() == ClientType.WEB)
            return true;
        return false;
    }
}
