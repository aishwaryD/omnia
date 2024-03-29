package com.testvagrant.ui.testchannel;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IOSWeb extends Web implements ITool {

    private AppiumDriver<WebElement> driver = null;
    public IOSWeb(WebDriver driver)
    {
        super(driver);
        this.driver = (AppiumDriver<WebElement>)driver;
        defaultWaitTime = 10;
    }
}
