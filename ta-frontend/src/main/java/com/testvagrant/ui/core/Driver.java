package com.testvagrant.ui.core;

import com.testvagrant.core.*;
import com.testvagrant.util.Util;
import org.openqa.selenium.WebDriver;
import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class Driver {

    private static InheritableThreadLocal<WebDriver> driver = new InheritableThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        try {
            if (driver.get() == null) {
                setDriver();
            }
            return driver.get();
        } catch (Exception e) {
            SoutLogger.logError("Exception in getDriver " + Util.getStackTraceFromException(e));
            return null;
        }
    }

    private static void setDriver() {
        try {
            BrowserName browserName = BrowserName.CHROME;
            if (BaseContext.isDesktopWeb()) {
                if(BaseContext.getTestContext().get() != null && BaseContext.getTestContext().get().getBrowserName() != null){
                    browserName =  BaseContext.getTestContext().get().getBrowserName();
                }
                if (BaseContext.getDesktopBrowser() == BrowserType.CHROMEMOBILEEMULATION) {
                    browserName = BrowserName.CHROME;
                    driver.set(DriverFactory.initializeWebDriver(browserName, true));
                }
                else if (BaseContext.getDesktopBrowser() == BrowserType.FIREFOX) {
                    browserName = BrowserName.FIREFOX;
                    driver.set(DriverFactory.initializeWebDriver(browserName, true));
                }
                else {
                    driver.set(DriverFactory.initializeWebDriver(browserName, false));
                }
            } else if (BaseContext.isAndroidWeb()){
                driver.set(DriverFactory.initializeAndroidDriver(BrowserName.valueOf(BaseContext.getDeviceBrowser().toString().toUpperCase())));
            } else if (BaseContext.isAndroidApp()){
                driver.set(DriverFactory.initializeAndroidDriver());
            } else if (BaseContext.isIOSWeb()){
                driver.set(DriverFactory.initializeIOSDriver(BrowserName.valueOf(BaseContext.getDeviceBrowser().toString().toUpperCase())));
            } else if (BaseContext.isIOSApp()){
                driver.set(DriverFactory.initializeIOSDriver());
            }
            else LogManager.logFatal("Exception in set driver.");
        } catch (Exception ex) {
            LogManager.logFatal("Exception in set Driver", ex);
        }
    }

    public static void tearDown() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                LogManager.logInfo("Driver teardown successful");
            }
        }
        catch (Exception ex) {
            LogManager.logInfo("Unable to quit the driver " + Util.getStackTraceFromException(ex));
        }
        try {
            driver.set(null);
            driver.remove();
        }
        catch (Exception ex) {
            LogManager.logInfo("Thread" + Thread.currentThread().getId() + ": " + "Unable to remove the driver " + Util.getStackTraceFromException(ex));
        }
    }
}
