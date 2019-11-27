package com.testvagrant.web.testchannel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AndroidApp extends CommonChannel implements ITool {

    private AppiumDriver<WebElement> driver = null;
    public AndroidApp(WebDriver driver)
    {
        super(driver);
        this.driver = (AppiumDriver<WebElement>)driver;
        defaultWaitTime = 10;
    }

    public boolean loadPage(String url)
    {
        throw new NotImplementedException();
    }

    public boolean loadPage(String url, int timeoutInSeconds)
    {
        throw new NotImplementedException();
    }

    public void clickElement(WebElement element, boolean waitForPageLoad)
    {
        throw new NotImplementedException();
    }

    public void clickElement(String xpath)
    {
        clickElement(xpath, true, true, false);
    }

    public void clickElement(String xpath, boolean scrollToElement)
    {
        clickElement(xpath, scrollToElement, true, false);
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility)
    {
        clickElement(xpath, scrollToElement, waitForVisibility, false);
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility, boolean waitForPageLoad)
    {
        clickElement(xpath, scrollToElement, waitForVisibility, false, waitForPageLoad);
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility, boolean waitForElementPresence, boolean waitForPageLoad)
    {
        throw new NotImplementedException();
    }

    public void enterValueWithoutWait(String xpath, String value) {
        throw new NotImplementedException();
    }

    public void enterValue(String xpath, String value)
    {
        enterValue(xpath, value, true, true, false, true, true);
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility) {
        enterValue(xpath, value, validateValueEntered, scrollToElement, waitForVisibility, true, true);
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility, boolean clickBeforeEnter, boolean clearBeforeEnter) {
        throw new NotImplementedException();
    }

    protected WebElement getWebElement(String xpath, boolean scrollToElement, boolean waitForElementVisibility, boolean waitForElementPresence) {
        throw new NotImplementedException();
    }

    public boolean isElementDisplayed(WebElement element)
    {
        throw new NotImplementedException();
    }

    public boolean isElementDisplayed(String xpath)
    {
        return isElementDisplayed(xpath,true,true);
    }

    public boolean isElementDisplayedWithoutWait(String xpath)
    {
        throw new NotImplementedException();
    }

    public WebElement findElementByXPath(String xpath) {
        throw new NotImplementedException();
    }

    public void scrollToElement(String xpath)
    {
        throw new NotImplementedException();
    }

    public void scrollToElement(WebElement element, String xpath)
    {
        throw new NotImplementedException();
    }

    public void scrollToElement(WebElement element)
    {
        throw new NotImplementedException();
    }

    protected int getCountOfElements(String xpath) {
        throw new NotImplementedException();
    }

    public void waitForPageLoadToComplete() {
        throw new NotImplementedException();
    }

    public boolean isElementDisplayed(String xpath, boolean scroll,boolean waitForVisibility) {
        throw new NotImplementedException();
    }

    public boolean isElementPresent(String xpath){
        throw new NotImplementedException();
    }

    public void takeFullScreenshot(String pageName){
        throw new NotImplementedException();
    }

    public void clickElementWithoutWait(String xpath)
    {
        throw new NotImplementedException();

    }

    public boolean isElementClickable(String xpath) {
        throw new NotImplementedException();
    }

    public void dragAndDrop(String from, String to)
    {
        throw new NotImplementedException();
    }

}
