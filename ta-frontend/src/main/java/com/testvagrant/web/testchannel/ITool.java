package com.testvagrant.web.testchannel;

import org.openqa.selenium.WebElement;
import java.util.List;


/**
 * Created by Aishwarya
 */
public interface ITool
{
    boolean loadPage(String url);

    boolean loadPage(String url, int timeoutInSeconds);

    void clickElement(WebElement element, boolean waitForPageLoad);

    void clickElement(String xpath);

    void clickElement(String xpath, boolean scrollToElement);

    void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility);

    void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility, boolean waitForPageLoad);

    void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility,boolean waitForElementPresence, boolean waitForPageLoad);

    void scrollToElement(String xpath);

    void waitForElementPresence(String xpath);

    void waitForElementPresence(String xpath, int timeInSeconds);

    void waitForElementVisibility(String xpath);

    void waitForElementVisibility(String xpath, int timeInSeconds);

    boolean isElementDisplayed(WebElement element);

    boolean isElementDisplayed(String xpath);

    boolean isElementEnabled(String xpath);

    boolean isElementNotDisplayedWithoutWait(String xpath);

    boolean isElementDisplayedWithoutWait(String xpath);

    boolean isElementPresentWithoutWait(String xpath);

    void waitForElementToDisappear(String xpath, int timeInSeconds);

    void waitForElementToDisappear(String xpath);

    void enterValue(String xpath, String Value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility);

    void enterValue(String xpath, String Value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility, boolean clickBeforeEnter, boolean clearBeforeEnter);

    void enterValue(String xpath, String value);

    void enterValueWithoutWait(String xpath, String value);

    WebElement findElementByXPath(String xpath);

    List<WebElement> findElementsByXPath(String xpath);

    int getCountOfMatchingElements(String xpath);

    boolean takeScreenShot(String filePath);

    void takeScreenShot();

    void waitForPageLoadToComplete();

    boolean isElementPresent(String xpath);

    void takeFullScreenshot(String pageName);

    void clickElementWithoutWait(String xpath);

    boolean isElementClickable(String xpath);

    void dragAndDrop(String from, String to);
}
