package com.testvagrant.ui.testchannel;

import com.testvagrant.core.BaseContext;
import com.testvagrant.core.LogManager;
import com.testvagrant.core.ExtentLogger;
import com.testvagrant.util.StringUtil;
import com.testvagrant.util.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static com.testvagrant.model.Enums.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Aishwarya
 */
public abstract class CommonChannel {

    private WebDriver baseDriver = null;
    protected int defaultWaitTime;

    public CommonChannel(WebDriver driver)
    {
        baseDriver = driver;
    }

    public void waitForElementToDisappear(String xpath) {
        waitForElementToDisappear(xpath, defaultWaitTime);
    }

    public void waitForElementPresence(String xpath)
    {
        waitForElementPresence(xpath, defaultWaitTime);
    }

    public void waitForElementVisibility(String xpath) {
        waitForElementVisibility(xpath, defaultWaitTime);
    }

    public void waitForElementVisibility(WebElement element, int timeInSeconds) {
        if(element == null)
            return;
        boolean found = false;
        int timeInSecCounter=0;
        int waitTime = timeInSeconds * 2;
        long startTime = new Date().getTime();
        while(timeInSecCounter++ < waitTime && !found){
            try {
                found = element.isDisplayed();
            }
            catch(TimeoutException e){
                BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: TimeoutException." + timeInSeconds + " seconds");
                BaseContext.getTestContext().get().setHasScriptIssue(true);
                LogManager.logInfo("TimeoutException in waitForElementVisibility");
                return;
            }
            catch (NoSuchElementException e) {
                found = false;
                System.out.println("Thread" + Thread.currentThread().getId() + ": " + "waitForElementVisibility: NoSuchElementException");
            }
            catch (Exception ex) {
                System.out.println("Thread" + Thread.currentThread().getId() + ": " + "waitForElementVisibility: not found in iteration " + timeInSecCounter + ". waitIteration = " + waitTime + " timeInSeconds = " + timeInSeconds);
            }
            if(!found)
                Util.sleep(500);
        }
        if(!found && timeInSeconds > 3) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: Could not find element on the page. Wait time " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementVisibility: Could not find element on the page with xpath. Wait time " + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 2) //if greater than 2 secs log it
            LogManager.logInfo("waitForElementVisibility: WAIT TIME:" + StringUtil.msToString(time));
    }

    public void waitForElementVisibility(WebElement element, String xpath, int timeInSeconds) {
        if(element == null)
            return;
        boolean found = false;
        int timeInSecCounter=0;
        int waitTime = timeInSeconds * 2;
        long startTime = new Date().getTime();
        while(timeInSecCounter++ < waitTime && !found){
            try {
                found = element.isDisplayed();
            }
            catch(TimeoutException e){
                BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: TimeoutException." + timeInSeconds + " seconds");
                BaseContext.getTestContext().get().setHasScriptIssue(true);
                LogManager.logInfo("TimeoutException in waitForElementVisibility");
                return;
            }
            catch (NoSuchElementException e) {
                found = false;
                System.out.println("Thread" + Thread.currentThread().getId() + ": " + "waitForElementVisibility: NoSuchElementException. Xpath: " + xpath);
            }
            catch (StaleElementReferenceException se)
            {
                try {
                    element = getVisibleElement(xpath);
                }
                catch (TimeoutException te) {
                    LogManager.logInfo("Timeout Exception in getVisibleElement waitForElementVisibility");
                }
                catch (Exception e) {
                    System.out.println("Thread" + Thread.currentThread().getId() + ": " + "waitForElementVisibility: not found in iteration " + timeInSecCounter + ". waitIteration = " + waitTime + " timeInSeconds = " + timeInSeconds);
                }
            }
            catch (Exception ex) {
            }
            if(!found)
                Util.sleep(500);
        }
        if(!found && timeInSeconds > 3) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: Could not find element on the page with xpath." + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementVisibility: Could not find element on the page with xpath." + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 2) //if greater than 2 secs log it
            LogManager.logInfo("waitForElementVisibility: WAIT TIME:" + StringUtil.msToString(time));
    }


    public void waitForElementEnabled(WebElement element, int timeInSeconds) {
        if(element == null)
            return;
        boolean found = false;
        int timeInSecCounter=0;
        int waitTime = timeInSeconds * 2;
        long startTime = new Date().getTime();
        while(timeInSecCounter++ < waitTime && !found){
            try {
                found = (element.isDisplayed() && element.isEnabled());
            }
            catch(TimeoutException e){
                BaseContext.getTestContext().get().setScriptIssue("waitForElementEnabled: TimeoutException. " + timeInSeconds + " seconds");
                BaseContext.getTestContext().get().setHasScriptIssue(true);
                LogManager.logInfo("TimeoutException in waitForElementEnabled");
                return;
            }
            catch (Exception ex) {
                System.out.println("Thread" + Thread.currentThread().getId() + ": " + "waitForElementEnabled: not found in iteration " + timeInSecCounter + ". waitIteration = " + waitTime + " timeInSeconds = " + timeInSeconds);
            }
            if(!found)
                Util.sleep(500);
        }
        if(!found && timeInSeconds > 3) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementEnabled: Could not find element on the page with xpath." + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementEnabled: Could not find element on the page with xpath." + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 2) //if greater than 2 secs log it
            LogManager.logInfo("waitForElementEnabled: WAIT TIME:" + StringUtil.msToString(time));
    }

    public boolean isElementEnabled(String xpath) {
        WebElement element = baseDriver.findElement(By.xpath(xpath));
        return isElementEnabled(element);
    }

    public boolean isElementDisplayedWithoutWait(String xpath) {
        return isDisplayed(xpath);
    }

    protected boolean isDisplayed(String xpath) {
        boolean result;
        try {
            result = baseDriver.findElement(By.xpath(xpath)).isDisplayed();
        }
        catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean isElementNotDisplayedWithoutWait(String xpath) {
        return !isDisplayed(xpath);
    }

    public boolean takeScreenShot(String filePath) {
        try {
            File srcFiler = ((TakesScreenshot) baseDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFiler, new File(filePath));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void takeScreenShot() {
        ExtentLogger.logScreenshot();
    }

    protected boolean isElementEnabled(WebElement element) {
        boolean returnValue = false;
        try{
            returnValue = element.isEnabled();
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in isElementEnabled");
        }
        catch (Exception e){}
        if(!returnValue)
            LogManager.logInfo("isElementEnabled returning False");
        return returnValue;
    }

    protected void waitForElement(WebElement element, ExpectedCondition expectedCondition) {
        try {
            switch (expectedCondition) {
                case elementToBeClickable:
                {
                    waitForElementEnabled(element, defaultWaitTime);
                    break;
                }
                case visibilityOfElement:
                {
                    waitForElementVisibility(element, defaultWaitTime);
                    break;
                }
                default:
                    new NotImplementedException();
            }
        }
        catch(Exception e){
            LogManager.logInfo("Exception in waitAndScrollToElement.");
        }
    }

    protected WebElement getElementDisplayedInternal(String xpath) {
        WebElement returnElement = null;
        try {
            for (WebElement element : baseDriver.findElements(By.xpath(xpath))) {
                if (element.isDisplayed()) {
                    returnElement = element;
                }
            }
        }
        catch (TimeoutException te) {
            throw te;
        }
        catch(Exception e){
        }
        return returnElement;
    }


    public boolean isElementPresentWithoutWait(String xpath) {
        try {
            baseDriver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected void pressArrowDown() {
        try {
            baseDriver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
        }
        catch (TimeoutException te) {
            throw te;
        }
        catch (Exception ex)
        {}
    }

    protected boolean validateValueEntered(String value, WebElement element) {
        boolean success;
        String valueEntered = null;
        try {
            valueEntered = element.getAttribute("value").replaceAll(",", "");
        } catch (InvalidElementStateException ex) {
        }
        if (StringUtil.isNullOrEmpty(valueEntered) || valueEntered.equalsIgnoreCase(value.replace(",", "")))
            success = true;
        else {
            success = false;
            LogManager.logInfo("Value:" + value + "Entered Value:" + valueEntered + " Value not entered correctly , clearing and entering again");
            LogManager.takeScreenshot();
        }
        return success;
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        waitForElementPresence(xpath);
        return baseDriver.findElements(By.xpath(xpath));
    }

    public int getCountOfMatchingElements(String xpath){
        int value = 0;
        waitForElementPresence(xpath);
        try {
            value = baseDriver.findElements(By.xpath(xpath)).size();
        }
        catch (TimeoutException te) {
            throw te;
        }
        catch (Exception ex) {
            LogManager.logInfo("Exception in getCountOfMatchingElements will return 0. XPath: " + xpath);
        }
        return value;
    }

    public String executeJavascript(String script) {
        String returnValue = "";
        try{
            JavascriptExecutor js=(JavascriptExecutor)baseDriver;
            returnValue = js.executeScript(script).toString();
        }
        catch (TimeoutException te) {
            throw te;
        }
        catch(Exception e) {
            returnValue = null;
        }
        return returnValue;
    }

    protected WebElement getVisibleElement(final String xpath) {
        WebElement returnElement;
        returnElement = getElementDisplayedInternal(xpath);
        if(returnElement == null) {
            waitForElementVisibility(xpath);
            returnElement = getElementDisplayedInternal(xpath);
        }
        return returnElement;
    }

    public void waitForElementVisibility(String xpath, int timeInSeconds) {
        long startTime = 0;
        boolean found = false;
        try{
            FluentWait<WebDriver> localWait = new FluentWait<WebDriver>(baseDriver)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .withTimeout(timeInSeconds, SECONDS)
                    .ignoring(NoSuchElementException.class);

            Function<WebDriver, Boolean> waitForElementVisibilityFunction = new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver arg0) {
                    WebElement element = null;
                    try {
                        element = arg0.findElement(By.xpath(xpath));
                    }
                    catch (Exception ex) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            };
            startTime = new Date().getTime();
            found = localWait.until(waitForElementVisibilityFunction);
        }
        catch(TimeoutException e){
            BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: TimeoutException Xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("TimeoutException in waitForElementVisibility");
        }
        catch(Exception e){
            LogManager.logInfo("Exception in waitForElementVisibility");
        }
        if(!found) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementVisibility: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementVisibility: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 0)
            LogManager.logInfo("waitForElementVisibility: WAIT TIME:" + StringUtil.msToString(time));
    }

    public void waitForElementPresence(String xpath, int timeInSeconds) {
        long startTime = 0;
        boolean found = false;
        try{
            FluentWait<WebDriver> localWait = new FluentWait<WebDriver>(baseDriver)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .withTimeout(timeInSeconds, SECONDS)
                    .ignoring(NoSuchElementException.class);

            Function<WebDriver, Boolean> waitForElementPresenceFunction = new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver arg0) {
                    WebElement element = null;
                    try {
                        element = arg0.findElement(By.xpath(xpath));
                        if(element != null) {
                            return true;
                        }
                    }
                    catch (Exception ex) {
                        return false;
                    }
                    return false;
                }
            };
            startTime = new Date().getTime();
            found = localWait.until(waitForElementPresenceFunction);
        }
        catch(TimeoutException e){
            BaseContext.getTestContext().get().setScriptIssue("waitForElementPresence: TimeoutException Xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("TimeoutException in waitForElementPresence");
        }
        catch(Exception e){
            LogManager.logInfo("Exception in waitForElementPresence");
        }
        if(!found) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementPresence: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementPresence: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 0)
            LogManager.logInfo("waitForElementPresence: WAIT TIME:" + StringUtil.msToString(time));
    }

    public void waitForElementToDisappear(String xpath, int timeInSeconds) {
        boolean disappeared = false;
        long startTime = 0;
        try{
            FluentWait<WebDriver> localWait = new FluentWait<WebDriver>(baseDriver)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .withTimeout(timeInSeconds, SECONDS)
                    .ignoring(NoSuchElementException.class);

            Function<WebDriver, Boolean> waitForElementToDisappearFunction = new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver arg0) {
                    WebElement element = null;
                    try {
                        element = arg0.findElement(By.xpath(xpath));
                    }
                    catch (Exception ex) {
                        return true;
                    }
                    return !element.isDisplayed();
                }
            };
            startTime = new Date().getTime();
            disappeared = localWait.until(waitForElementToDisappearFunction);
        }
        catch(TimeoutException e){
            BaseContext.getTestContext().get().setScriptIssue("waitForElementToDisappear: TimeoutException Xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("TimeoutException in waitForElementToDisappear");
        }
        catch(Exception e){
            LogManager.logInfo("Exception in waitForElementToDisappear");
        }
        if(!disappeared) {
            BaseContext.getTestContext().get().setScriptIssue("waitForElementToDisappear: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds");
            BaseContext.getTestContext().get().setHasScriptIssue(true);
            LogManager.logInfo("waitForElementToDisappear: Could not find element on the page with xpath " + xpath + " in " + timeInSeconds + " seconds", true);
        }
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        if (time / 1000 > 0)
            LogManager.logInfo("waitForElementToDisappear: WAIT TIME:" + StringUtil.msToString(time));
    }

}
