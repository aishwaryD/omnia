package com.testvagrant.web.testchannel;

import com.testvagrant.core.*;
import com.testvagrant.util.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class Wrapper implements ITool
{
    private final ITool _tool;

    public Wrapper(WebDriver driver) {
            if (BaseContext.isAndroidWeb())
                _tool = new AndroidWeb(driver);
            else if (BaseContext.isIOSWeb())
                _tool = new IOSWeb(driver);
            else if (BaseContext.isAndroidApp())
                _tool = new AndroidApp(driver);
            else if (BaseContext.isIOSApp())
                _tool = new IOSApp(driver);
            else
                _tool = new Web(driver);
            CustomLogger.setDriver(driver);
    }

    public boolean loadPage(String url)
    {
        boolean result = false;
        try {
            result = _tool.loadPage(url);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " URL: " + url, true);
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " URL: " + url + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return result;
    }

    public boolean loadPage(String url, int timeoutInSeconds)
    {
        boolean result = false;
        try {
            result = _tool.loadPage(url, timeoutInSeconds);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " URL: " + url, true);
        }
        catch (Exception e) {
            LogManager.logInfo("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " URL: " + url + " failed with Exception: "+ getException(e), true);
        }
        return result;
    }


    public void clickElement(WebElement element, boolean waitForPageLoad) {
        try {
            _tool.clickElement(element, waitForPageLoad);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " waitForPageLoad: " + waitForPageLoad, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " waitForPageLoad: " + waitForPageLoad + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void clickElement(String xpath) {
        try {
            _tool.clickElement(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: " + getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
           _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void clickElement(String xpath, boolean scrollToElement) {
        try {
            _tool.clickElement(xpath, scrollToElement);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " scrollToElement: " + scrollToElement, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }

        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " failed with Exception: " + getException(e), true);

            AssertFail(e);
        }
    }

    public void scrollToElement(String xpath) {
        try {
            _tool.scrollToElement(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }

        catch (Exception e) {
            LogManager.logInfo("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: " + getException(e), true);

        }
    }

    private String getException(Exception e)
    {
        String exMessage = e.toString().trim();
        if(e instanceof NoSuchElementException) {
            exMessage = "'[PotentialBug]: Element is not present in the page [Page Screenshot Below]'";
        }
        if(e instanceof ElementNotVisibleException) {
            exMessage = "'[PotentialScriptIssue]: Element is not visible in the page [Page Screenshot Below]'";
        }
        if(e instanceof InvalidElementStateException) {
            exMessage = "'[PotentialScriptIssue]: Seems like element is no more present on the current page [Page Screenshot Below]'";
        }
        if(e instanceof WebDriverException && exMessage.contains("Element is not clickable at point")
                && exMessage.contains("Other element would receive the click")) {
            exMessage = "'[PotentialScriptIssue]: Seems like some other element is overlapping the intended element to be clicked. [Page Screenshot Below]'";
        }
        if(exMessage.contains("Session info:")) {
            exMessage = exMessage.split("Session info:")[0];
            exMessage =exMessage.substring(0, exMessage.length()-2);
        }
        exMessage = exMessage.replace("org.openqa.selenium.WebDriverException:","").replace("unknown error:","").trim();
        if(e instanceof TimeoutException) {
            exMessage = "'[PotentialSeleniumIssue]: Selenium timed out performing the action [Page Screenshot Below]'";
            BaseContext.getTestContext().get().setFailurePage("SeleniumException");
            BaseContext.getTestContext().get().setFailureMethod("TimeoutError");
            BaseContext.getTestContext().get().setFailureActivity("SeleniumTimeOutException");
            Util.sleep(30000);
        }
        BaseContext.getTestContext().get().setFailureReason(exMessage);
        return exMessage;
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility) {
        try {
            _tool.clickElement(xpath, scrollToElement, waitForVisibility);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility, boolean waitForPageLoad) {
        try {
            _tool.clickElement(xpath, scrollToElement, waitForVisibility, waitForPageLoad);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " waitForVisibility: " + waitForVisibility + " waitForPageLoad: " + waitForPageLoad, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " waitForVisibility: " + waitForVisibility + " waitForPageLoad: " + waitForPageLoad + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void clickElement(String xpath, boolean scrollToElement, boolean waitForVisibility,boolean waitForElementPresence, boolean waitForPageLoad) {
        try {
            _tool.clickElement(xpath,scrollToElement,waitForVisibility,waitForElementPresence,waitForPageLoad);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " waitForVisibility: " + waitForVisibility + " waitForPageLoad: " + waitForPageLoad, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " waitForVisibility: " + waitForVisibility + " waitForPageLoad: " + waitForPageLoad + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    private void AssertFail(Exception e) {
        try {
            CustomAssert.fail();
        }
        catch (Exception ex) {
            LogManager.logFatal("Thread" + Thread.currentThread().getId() + ": " + "Exception in AssertFail", ex);
        }
    }

    public void waitForElementPresence(String xpath) {
        try {
            _tool.waitForElementPresence(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public void waitForElementPresence(String xpath, int timeInSeconds) {
        try {
            _tool.waitForElementPresence(xpath, timeInSeconds);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public void waitForElementVisibility(String xpath) {
        try {
            _tool.waitForElementVisibility(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public void waitForElementVisibility(String xpath, int timeInSeconds) {
        try {
            _tool.waitForElementVisibility(xpath, timeInSeconds);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            boolean status = _tool.isElementDisplayed(element);
            LogManager.logInfo("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " IsDisplayed:"+status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public boolean isElementDisplayed(String xpath) {
        try {
            boolean status = _tool.isElementDisplayed(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " Result: " + status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public boolean isElementEnabled(String xpath) {
        try {
            boolean status = _tool.isElementEnabled(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " Result: " + status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public boolean isElementDisplayedWithoutWait(String xpath) {
        try {
            boolean status = _tool.isElementDisplayedWithoutWait(xpath);
            LogManager.logInfo("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " IsDisplayed: " + status + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public boolean isElementNotDisplayedWithoutWait(String xpath) {
        try {
            boolean status = _tool.isElementNotDisplayedWithoutWait(xpath);
            LogManager.logInfo("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " IsDisplayed: " + status + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public boolean isElementPresentWithoutWait(String xpath) {
        try {
            boolean status = _tool.isElementPresentWithoutWait(xpath);
            LogManager.logInfo("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " IsPresent: " + status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public void waitForPageLoadToComplete() {
        try {
            _tool.waitForPageLoadToComplete();
        }
        catch (Exception e) {
            LogManager.logInfo("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);
        }
    }


    public void waitForElementToDisappear(String xpath, int timeInSeconds){
        try {
            _tool.waitForElementToDisappear(xpath, timeInSeconds);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: " + getCallingMethodName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public void waitForElementToDisappear(String xpath){
        try {
            _tool.waitForElementToDisappear(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: " + getCallingMethodName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
    }

    public WebElement findElementByXPath(String xpath) {
        WebElement element = null;
        try {
            element = _tool.findElementByXPath(xpath);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return element;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
        return element;
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        WebElement element = null;
        try {
            List<WebElement> elements =  _tool.findElementsByXPath(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingMethodName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, false);
            return elements;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
        return null;
    }

    public int getCountOfMatchingElements(String xpath) {
        int count =0;
        try {
            count= _tool.getCountOfMatchingElements(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + "Count: " + count +" For xpath " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return count;
    }

    public void enterValueWithoutWait(String xpath, String value){
        try {
            _tool.enterValueWithoutWait(xpath, value);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " Entered Value: " + value + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " Value: " + value + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void enterValue(String xpath, String value) {
        try {
            _tool.enterValue(xpath, value);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " Entered Value: " + value + " XPath: " + xpath, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " Value: " + value + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility) {
        try {
            _tool.enterValue(xpath, value, validateValueEntered, scrollToElement, waitForVisibility);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " Entered Value: " + value + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " Value: " + value + " XPath: " + xpath  + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility, boolean clickBeforeEnter, boolean clearBeforeEnter) {
        try {
            _tool.enterValue(xpath, value, validateValueEntered, scrollToElement, waitForVisibility, clickBeforeEnter, clearBeforeEnter);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " Entered Value: " + value + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility + " clickBeforeEnter: " + clickBeforeEnter + " clearBeforeEnter: " + clearBeforeEnter, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " Value: " + value + " XPath: " + xpath + " scrollToElement: " + scrollToElement + " waitForVisibility: " + waitForVisibility + " clickBeforeEnter: " + clickBeforeEnter + " clearBeforeEnter: " + clearBeforeEnter + " failed with Exception: "+ getException(e), true);
            LogManager.logInfo("Taking full page screenshot");
            _tool.takeFullScreenshot("Full Page Failure Screenshot");

            AssertFail(e);
        }
    }

    public boolean takeScreenShot(String filePath) {
        try {
            _tool.takeScreenShot(filePath);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public void takeScreenShot() {
        try {
            _tool.takeScreenShot();
        }
        catch (Exception e) {
        }
    }

    private String getCallingMethodName(boolean isFailed)
    {
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName().trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed"))
            methodName = Thread.currentThread().getStackTrace()[4].getMethodName().trim();
        else
            methodName = Thread.currentThread().getStackTrace()[3].getMethodName().trim();
        if(isFailed) {
            BaseContext.getTestContext().get().setFailureMethod(methodName);
        }
        BaseContext.getTestContext().get().setCurrentMethod(methodName);
        return methodName;
    }

    private String getCallingClassName(boolean isFailed)
    {
        String className = null;
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName().trim();
        className = Thread.currentThread().getStackTrace()[3].getClassName();
        className = className.substring(className.lastIndexOf(".") + 1).trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed")) {
            className = Thread.currentThread().getStackTrace()[4].getClassName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        if(isFailed) {
            BaseContext.getTestContext().get().setFailurePage(className);
        }
        BaseContext.getTestContext().get().setCurrentPage(className);
        return className;
    }

    private String getCallingActivityName(boolean isFailed)
    {
        String className = null;
        String methodName = Thread.currentThread().getStackTrace()[2].getClassName().trim();
        if(methodName.equalsIgnoreCase("validate_isDisplayed")) {
            className = Thread.currentThread().getStackTrace()[3].getMethodName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        else
        {
            className = Thread.currentThread().getStackTrace()[2].getMethodName();
            className = className.substring(className.lastIndexOf(".") + 1).trim();
        }
        if(isFailed) {
            BaseContext.getTestContext().get().setFailureActivity(className);
        }
        return className;
    }

    public boolean isElementPresent(String xpath){
        try{
            boolean status =  _tool.isElementPresent(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath + " Result: " + status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }catch(Exception e){
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public void takeFullScreenshot(String pageName) {
        try {
            _tool.takeFullScreenshot(pageName);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false), false);
        }
        catch (Exception e) {
            LogManager.logInfo("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: " + getException(e), true);
        }
    }


    public void logScriptIssue(String page, String method) {
        if (BaseContext.getTestContext().get().isHasScriptIssue()) {
            BaseContext.getTestContext().get().getScriptIssueList().add("<br><b>" + BaseContext.getTestContext().get().getTestCaseName() + "</b><br> " +
                    "Page : " + BaseContext.getTestContext().get().getCurrentPage() +
                    " Method: " + BaseContext.getTestContext().get().getCurrentMethod() +
                    "<br>" + BaseContext.getTestContext().get().getScriptIssue());
        }
        BaseContext.getTestContext().get().setHasScriptIssue(false);
        BaseContext.getTestContext().get().setScriptIssue("");
    }

    public void clickElementWithoutWait(String xpath) {
        try {
            _tool.clickElementWithoutWait(xpath);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " XPath: " + xpath, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " XPath: " + xpath + " failed with Exception: " + getException(e), true);

            AssertFail(e);
        }
    }

    public boolean isElementClickable(String xpath) {
        try {
            boolean status = _tool.isElementClickable(xpath);
            LogManager.logInfo("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " IsClickable:"+status, false);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
            return status;
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " failed with Exception: "+ getException(e), true);

            AssertFail(e);
        }
        return false;
    }

    public void dragAndDrop(String from, String to) {
        try {
            _tool.dragAndDrop(from, to);
            LogManager.logSuccess("SUCCESS: PageName: " + getCallingClassName(false) + " MethodName: " + getCallingMethodName(false) + " ActivityName: " + getCallingActivityName(false) + " FROM_XPath: " + from + " TO_XPath: " + to, true);
            logScriptIssue(getCallingClassName(false),getCallingMethodName(false));
        }
        catch (Exception e) {
            LogManager.logError("FAILED: PageName: " + getCallingClassName(true) + " MethodName: " + getCallingMethodName(true) + " ActivityName: " + getCallingActivityName(true) + " FROM_XPath: " + from + " TO_XPath: " + to + " failed with Exception: " + getException(e), true);

            AssertFail(e);
        }
    }
}
