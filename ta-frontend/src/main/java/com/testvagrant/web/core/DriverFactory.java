package com.testvagrant.web.core;

import com.testvagrant.core.*;
import com.testvagrant.util.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class DriverFactory
{

    protected static WebDriver initializeWebDriver(BrowserName browserName, boolean emulation) {
        if(BaseContext.isGridRun())
            return initializeRemoteWebDriver(browserName, emulation);
        return initializeLocalWebDriver(browserName, emulation);
    }

    private static WebDriver initializeLocalWebDriver(BrowserName browserName, boolean emulation)
    {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browserName) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
                capabilities = DesiredCapabilities.chrome();
                if(emulation)
                    setChromeMobileEmulationCapabilities(capabilities, BaseContext.isRunHeadless());
                else {
                    final ChromeOptions chromeOptions = new ChromeOptions();
                    if (BaseContext.isRunHeadless()) {
                        chromeOptions.addArguments("--headless");
                        chromeOptions.addArguments("--disable-gpu");
                    }
                    chromeOptions.addArguments("window-size=1920,1080");
                    chromeOptions.addArguments("enable-automation");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--start-maximized");
                    Map prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.geolocation", 2); // 1:allow 2:block
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                }
                driver = new ChromeDriver(capabilities);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
                driver = new ChromeDriver(capabilities);
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initializeRemoteWebDriver(BrowserName browserName, boolean emulation)
    {
        RemoteWebDriver driver = null;
        return driver;
    }


    private static void setChromeMobileEmulationCapabilities(DesiredCapabilities capabilities, boolean headless)
    {
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        String device = "Google Nexus 5";
        if(!StringUtil.isNullOrEmpty(SystemSettingsReader.getPropertyValue("mobile.emulation.device")))
            device = SystemSettingsReader.getPropertyValue("mobile.emulation.device");
        mobileEmulation.put("deviceName", device);
        final ChromeOptions chromeOptions = new ChromeOptions();
        if(headless) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
        }
        chromeOptions.addArguments("window-size=1920,1080");
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        Map prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // 1:allow 2:block
//        prefs.put("download.default_directory", BaseContext.filedownloadpath);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability("browserName", "chrome");
    }

    private static String getChromeDriverPath() {
        String driverPath = SystemSettingsReader.getPropertyValue("chrome.driver.path");
        File file = new File(driverPath);
        if(file.exists())
            return driverPath;

        String path = Util.getResourcePath("drivers");
        switch(Util.getCurrentOSType())
        {
            case LINUX:
                driverPath = path + "/chromedriver_linux64/" + "chromedriver";
                break;
            case WINDOWS:
                driverPath = path + "/chromedriver_win32/" + "chromedriver.exe";
                break;
            case MAC:
                driverPath = path + "/chromedriver_mac64/" + "chromedriver";
                break;
        }
        return driverPath;
    }

    protected static AppiumDriver<WebElement> initializeAndroidDriver(BrowserName browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions opt = new ChromeOptions();
        opt.setExperimentalOption("w3c", false);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("skipUnlock", "true");
        capabilities.setCapability("chromedriverExecutable", getChromeDriverPath());
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
        AppiumDriver<WebElement> driver = startDriver(capabilities);
//        BaseContext.testContext.get().deviceName = driver.getCapabilities().getCapability("deviceName").toString().trim();
        LogManager.logInfo("DeviceId: " + BaseContext.getTestContext().get().getDeviceName());
        LogManager.logInfo("Device Model: " + driver.getCapabilities().getCapability("deviceModel").toString().trim());
        LogManager.logInfo("Device Manufacturer: " + driver.getCapabilities().getCapability("deviceManufacturer").toString().trim());
        return driver;
    }

    private static AppiumDriver<WebElement> startDriver(DesiredCapabilities capabilities) {
        AppiumDriver<WebElement> driver = null;
        try{
            String url = "http://" + BaseContext.getAppSeleniumServerIP() + ":" + BaseContext.getAppSeleniumPort() + "/wd/hub";
            driver = new AndroidDriver<WebElement>(new URL(url), capabilities);
            LogManager.logInfo("startDriver Success");
            Util.sleep(3000);
            return driver;
        } catch (Exception e){
           e.printStackTrace();
        }
        return driver;
    }

    protected static AppiumDriver<WebElement> initializeAndroidDriver() {
        AppiumDriver<WebElement> driver = null;
        return driver;
    }

    protected static AppiumDriver<WebElement> initializeIOSDriver(BrowserName browserName) {
        AppiumDriver<WebElement> driver = null;
        return driver;
    }

    protected static AppiumDriver<WebElement> initializeIOSDriver() {
        AppiumDriver<WebElement> driver = null;
        return driver;
    }
}
