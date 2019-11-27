package com.testvagrant.web.testchannel;

import com.testvagrant.core.*;
import com.testvagrant.model.Enums;
import com.testvagrant.util.*;
import com.testvagrant.web.core.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.testvagrant.model.Enums.ExpectedCondition;

/**
 * Created by Aishwarya Dwivedi
 */
public class Web extends CommonChannel implements ITool {
    private WebDriver driver = null;

    public Web(WebDriver driver) {
        super(driver);
        this.driver = driver;
        if(!BaseContext.isProd()) {
            defaultWaitTime = 10;
        }
        else {
            defaultWaitTime = 20;
        }
    }

    public boolean loadPage(String url)
    {
        boolean success = false;
        try {
            driver.get(url);
            success = true;
        } catch (TimeoutException te) {
            success = false;
            LogManager.logInfo("Page Load Timeout");
        }
        waitForPageLoadToComplete();
        return success;
    }

    public boolean loadPage(String url, int timeoutInSeconds)
    {
        boolean success = false;
        try {
            driver.manage().timeouts().pageLoadTimeout(timeoutInSeconds, TimeUnit.SECONDS);
            driver.get(url);
            waitForPageLoadToComplete();
            success = true;
        } catch (TimeoutException te) {
            success = true;
        }
        catch (UnreachableBrowserException ube)
        {
            LogManager.logInfo("UnreachableBrowserException encountered");
            success = false;
        }
        return success;
    }

    public void clickElement(WebElement element, boolean waitForPageLoad)
    {
        boolean success = false;
        try {
            waitAndScrollToElement(element, ExpectedCondition.elementToBeClickable, true);
            element.click();
            success = true;
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in clickElement");
            return;
        }
        catch (Exception ex) {
            success = false;
            LogManager.logInfo("clickElement failed at first attempt");
            closePopUpIfAny();
        }
        if(!success) {
            try{
                waitAndScrollToElement(element, ExpectedCondition.elementToBeClickable, true);
                element.click();
                success = true;
            }
            catch (Exception e){
                executeJavascript("window.scrollBy(0,200);");
            }
        }
        if(!success)
            element.click();
        if(waitForPageLoad)
            waitForPageLoadToComplete();
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
        boolean success = false;
        WebElement element = getWebElement(xpath, scrollToElement, waitForVisibility, waitForElementPresence);
        try {
            element.click();
            success = true;
        } catch (TimeoutException te) {
            success = true;
            LogManager.logInfo("Timeout Exception in clickElement");
            return;
        } catch (Exception ex) {
            LogManager.logInfo("clickElement failed at first attempt for xpath " + xpath, true);
            closePopUpIfAny();
            LogManager.takeScreenshot();
        }
        if(!success) {
            try {
                closePopUpIfAny();
                element = getVisibleElement(xpath);
                element.click();
                success = true;
            }
            catch (TimeoutException te) {
                LogManager.logInfo("Timeout Exception in clickElement");
                return;
            }
            catch (Exception ex)
            {
                LogManager.logInfo("clickElement failed at second attempt for xpath " + xpath);
                closePopUpIfAny();
            }
        }
        if(!success) {
            try {
                pressArrowDown();
                LogManager.takeScreenshot();
                waitAndScrollToElement(element, ExpectedCondition.elementToBeClickable, true);
                element.click();
                success = true;
            }
            catch (TimeoutException te) {
                LogManager.logInfo("Timeout Exception in clickElement");
                return;
            }
            catch (Exception e) {
                LogManager.logInfo("clickElement failed at third attempt, will make one more final attempt for xpath " + xpath);
                closePopUpIfAny();
                executeJavascript("window.scrollBy(0,200);");
                LogManager.takeScreenshot();
            }
        }
        if (!success) {
            driver.findElement(By.xpath(xpath)).click();
        }
        if(waitForPageLoad)
            waitForPageLoadToComplete();
    }

    public void enterValueWithoutWait(String xpath, String value) {
        driver.findElement(By.xpath(xpath)).sendKeys(value);
    }

    public void enterValue(String xpath, String value)
    {
        enterValue(xpath, value, true, true, false, true, true);
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility) {
        enterValue(xpath, value, validateValueEntered, scrollToElement, waitForVisibility, true, true);
    }

    public void enterValue(String xpath, String value, boolean validateValueEntered, boolean scrollToElement, boolean waitForVisibility, boolean clickBeforeEnter, boolean clearBeforeEnter) {
        boolean success = false;
        boolean validationStatus = false;
        WebElement element = getWebElement(xpath, scrollToElement, waitForVisibility, false);
        if(value!=null)
        {
            if(element != null) {
                if(clearBeforeEnter) {
                    if(clickBeforeEnter) {
                        waitAndScrollToElement(element, ExpectedCondition.elementToBeClickable, false);
                        clickElement(xpath);
                    }
                    element.clear();
                }
                if(clickBeforeEnter) {
                    waitAndScrollToElement(element, ExpectedCondition.elementToBeClickable, false);
                    clickElement(xpath);
                }
                try {
                    element.sendKeys(value);
                    success = true;
                    if(validateValueEntered) {
                        validationStatus = validateValueEntered(value, element);
                    }
                }
                catch (TimeoutException te) {
                    throw te;
                }
                catch (Exception ex) {
                    LogManager.logInfo("enterValue failed at first attempt, making second attempt");
                    closePopUpIfAny();
                    scrollToElement(element, xpath);
                    LogManager.takeScreenshot();
                }
            }
            if(!success || (validateValueEntered && !validationStatus))
            {
                try {
                    waitForElementPresence(xpath);
                    element = getWebElement(xpath, true, true, false);
                    clickElement(xpath);
                    if(!validationStatus || clearBeforeEnter)
                        element.clear();
                    element.sendKeys(value);
                    success = true;
                    if(validateValueEntered) {
                        validationStatus = validateValueEntered(value, element);
                    }
                }
                catch (TimeoutException te) {
                    throw te;
                }
                catch (Exception ex) {
                    LogManager.logInfo("enterValue failed at second attempt, making third and final attempt");
                    closePopUpIfAny();
                    scrollToElement(element, xpath);
                    LogManager.takeScreenshot();
                    success = false;
                }
            }
            if(!success || (validateValueEntered && !validationStatus)) {
                if(!validationStatus || clearBeforeEnter)
                    driver.findElement(By.xpath(xpath)).clear();
                driver.findElement(By.xpath(xpath)).sendKeys(value);
            }
        }
        else
            LogManager.logError("enterValue: Value is NULL");
    }

    protected WebElement getWebElement(String xpath, boolean scrollToElement, boolean waitForElementVisibility, boolean waitForElementPresence) {
        WebElement element = null;
        if(waitForElementPresence) {
            waitForElementPresence(xpath);
        }
        int count = getCountOfElements(xpath);
        if(count > 1)
        {
            element = getVisibleElement(xpath);
            scrollToElement(element, xpath);
        }
        if(count <= 1 || element == null) {
            try {
                element = driver.findElement(By.xpath(xpath));
            } catch (Exception e) {
            }
        }

        if(element != null && scrollToElement && !isElementEnabled(element)) {
            scrollToElement(element, xpath);
        }
        if(element != null && waitForElementVisibility) {
            waitForElementVisibility(element, xpath, defaultWaitTime);
        }
        return element;
    }

    public boolean isElementDisplayed(WebElement element)
    {
        boolean result;
        result =  element.isDisplayed();
        if(!result) {
            waitAndScrollToElement(element, ExpectedCondition.visibilityOfElement, true);
            closePopUpIfAny();
            result = element.isDisplayed();
        }
        return result;
    }

    public boolean isElementDisplayed(String xpath)
    {
        return isElementDisplayed(xpath,true,true);
    }

    public boolean isElementDisplayedWithoutWait(String xpath)
    {
        boolean result = isDisplayed(xpath);
        if(!result) {
            try {
                WebElement element = driver.findElement(By.xpath(xpath));
                if(element != null)
                    scrollToElement(element, xpath);
                result = driver.findElement(By.xpath(xpath)).isDisplayed();
            } catch (Exception ex) {
                result = false;
            }
        }
        if(!result) {
            try {
                closePopUpIfAny();
                WebElement element = driver.findElement(By.xpath(xpath));
                if(element != null)
                    scrollToElement(element, xpath);
                result = driver.findElement(By.xpath(xpath)).isDisplayed();
            } catch (Exception ex) {
                result = false;
            }
        }
        return result;
    }

    public WebElement findElementByXPath(String xpath) {
        WebElement element = getWebElement(xpath, false, true, false);
        if(element != null)
        {
            return element;
        }
        return driver.findElement(By.xpath(xpath));
    }

    public void scrollToTop()
    {
        scrollTo(0,0);
    }


    private void scrollTo(int X, int Y)
    {
        try {
            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("window.scrollTo(" + X + "," + Y + ")");
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in scrollTo");
            return;
        }
        catch (Exception e)
        {}
    }

    public void scrollToElement(String xpath)
    {
        float screenHeight = 0, elementY = 0;
        try {
            WebElement element = getWebElement(xpath, false, false, false);
            if(element != null) {
                screenHeight = 1080;
                elementY = element.getLocation().getY();
                if (BaseContext.getClientType() == Enums.ClientType.WEB && BaseContext.getPlatform() == Enums.Platform.DESKTOP)
                    scrollTo(0, (int)(elementY - (screenHeight * 0.5)));
                else
                    scrollTo(0, (int)(elementY - screenHeight / 4));
            }
            else
                LogManager.logInfo("element is null. Skipping scroll to Element for xpath " + xpath);
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in scrollToElement");
            return;
        }
        catch (Exception ex){
            LogManager.logInfo("Exception in scrollToElement. elementY=" + elementY + " screenHeight=" + screenHeight, ex);
        }
    }

    public void scrollToElement(WebElement element, String xpath)
    {
        if(element != null) {
            int screenHeight = 0, elementY = 0;
            try {
                screenHeight = 1080;
                try {
                    elementY = element.getLocation().getY();
                }
                catch (StaleElementReferenceException sere) {
                    element = driver.findElement(By.xpath(xpath));
                    elementY = element.getLocation().getY();
                }
                if (BaseContext.getClientType() == Enums.ClientType.WEB && BaseContext.getPlatform() == Enums.Platform.DESKTOP)
                    scrollTo(0, elementY - screenHeight / 2);
                else
                    scrollTo(0, elementY - screenHeight / 4);
            } catch (TimeoutException te) {
                LogManager.logInfo("Timeout Exception in scrollToElement");
                return;
            } catch (Exception ex) {
                LogManager.logInfo("Exception in scrollToElement. elementY=" + elementY + " screenHeight=" + screenHeight, ex);
            }
        }
    }

    public void scrollToElement(WebElement element)
    {
        if(element != null) {
            int screenHeight = 0, elementY = 0;
            try {
                screenHeight = 1080;
                elementY = element.getLocation().getY();
                if (BaseContext.getClientType() == Enums.ClientType.WEB && BaseContext.getPlatform() == Enums.Platform.DESKTOP)
                    scrollTo(0, elementY - screenHeight / 2);
                else
                    scrollTo(0, elementY - screenHeight / 4);
            } catch (TimeoutException te) {
                LogManager.logInfo("Timeout Exception in scrollToElement");
                return;
            } catch (Exception ex) {
                LogManager.logInfo("Exception in scrollToElement. elementY=" + elementY + " screenHeight=" + screenHeight, ex);
            }
        }
    }

    private String getCurrentWindowHandleName() {
        String name = null;
        try {
            name = driver.getWindowHandle().toString();
        } catch (Exception e) {
            LogManager.logInfo("Exception in getting current window handle");
        }
        return name;
    }

    public void switchToWindow() {
        try{
            acceptAlertInternal();
            for (String windowHandle : driver.getWindowHandles()) {
                if(!windowHandle.toString().equalsIgnoreCase(getCurrentWindowHandleName()))
                    driver.switchTo().window(windowHandle);
            }
        }
        catch(TimeoutException e){
            LogManager.logInfo("Timeout Exception in switchToWindow");
            return;
        }
        catch(Exception e){
            LogManager.logInfo("Exception in switchToWindow", e);
        }
    }

    protected void waitAndScrollToElement(WebElement element, ExpectedCondition expectedCondition, boolean scrollTo) {  //REFACTOR - PRO
        waitForElement(element, expectedCondition);
        if(scrollTo)
            scrollToElement(element);
    }

    protected int getCountOfElements(String xpath) {
        int count=0;
        try{
            count = driver.findElements(By.xpath(xpath)).size();
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in getCountOfElements");
            return count;
        }
        catch(Exception e){
            LogManager.logInfo("Exception in getCountOfElements " + xpath, e);
        }
        return count;
    }

    protected void closePopUpIfAny() {
    }

    public void closeWindow(String title) {
        try{
            boolean found = false;
            String currentWindow = driver.getWindowHandle();
            String closedWindow = null;
            Set<String> windows = driver.getWindowHandles();
            if(windows.size() > 1) {
                for (String windowHandle : windows) {
                    String windowTitle = driver.switchTo().window(windowHandle).getTitle();
                    if (windowTitle.toLowerCase().contains(title.toLowerCase())) {
                        driver.close();
                        found = true;
                        closedWindow = windowHandle;
                        break;
                    }
                }
                if(found) {
                    windows.remove(closedWindow);
                    driver.switchTo().window(windows.toArray()[0].toString());
                }
                else
                    driver.switchTo().window(currentWindow);
            }
            else if(driver.getTitle().contains(title))
            {
                LogManager.logInfo("closeWindow: only 1 window found, quiting the browser");
                Driver.tearDown();
            }
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in closeWindow");
        }
        catch(Exception e){
            LogManager.logInfo("Exception in closeWindow", e);
        }
    }


    public void waitForPageLoadToComplete() {
        LogManager.logInfo("Inside waitForPageLoadToComplete");
        boolean multipleWindow = false;
        try {
            if (driver.getWindowHandles().size() == 1) {
                switchToWindow();
            }
            if (driver.getWindowHandles().size() > 1) {
                multipleWindow = true;
                closeWindow("Random TrelloPopUp");
            }
        } catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in waitForPageLoadToComplete Part1");
            return;
        } catch (Exception e) {
            String exMessage = e.toString().trim();
            if (exMessage.contains("Session info:")) {
                exMessage = exMessage.split("Session info:")[0];
                exMessage = exMessage.substring(0, exMessage.length() - 2);
            }
            if (exMessage.contains("chrome not reachable")) {
                CustomAssert.failTheTestCase("chrome not reachable","waitForPageLoadToComplete","Selenium","waitForPageLoadToComplete");
            } else
                LogManager.logInfo("Exception in WaitForPageLoadToComplete1. Exception: ", e);
            acceptAlertInternal();
            switchToWindow();
        }
        try {
            boolean found = false;
            int timeInSecCounter=0;
            String jQueryActive;
            while(timeInSecCounter++ < defaultWaitTime * 2 && !found){
                try {
                    String readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
                    try {
                        jQueryActive = ((JavascriptExecutor) driver).executeScript("return (jQuery.active == 0)").toString();
                    }
                    catch (Exception ex) {
                        jQueryActive = "true";
                    }
                    found = readyState.equals("complete") && jQueryActive.equals("true");
                }
                catch(TimeoutException e){
                    LogManager.logInfo("TimeoutException in waitForPageLoadToComplete document.readyState");
                    return;
                }
                catch (Exception ex) {
                }
                if(!found)
                    Util.sleep(500);
            }
        } catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in waitForPageLoadToComplete Part2");
            return;
        } catch (Exception ex) {
            if (driver.getWindowHandles().size() == 1 & multipleWindow)
                switchToWindow();
            else {
                LogManager.logInfo("Exception in WaitForPageLoadToComplete2. Exception: ", ex);
                acceptAlertInternal();
            }
        }
        LogManager.logInfo("Exiting waitForPageLoadToComplete");
    }

    public boolean isElementDisplayed(String xpath, boolean scroll,boolean waitForVisibility) {
        boolean result = false;
        if(waitForVisibility)
            waitForElementPresence(xpath);
        WebElement ele = getWebElement(xpath, scroll, true, false);
        if(ele != null)
            try {
                result = ele.isDisplayed();
            }
            catch (StaleElementReferenceException sere) {
                ele = getWebElement(xpath, false, false, false);
            }
        if(ele != null && result == false) {
            closePopUpIfAny();
            result = ele.isDisplayed();
        }
        return result;
    }

    public boolean isElementPresent(String xpath){
        boolean result = false;
        WebElement element = getWebElement(xpath, false, false, false);
        if(element != null)
            result =true;
        return result;
    }

    public void takeFullScreenshot(String pageName){
        try {
            closePopUpIfAny();
            scrollToTop();
            int domHeight = driver.findElement(By.xpath("/*")).getSize().getHeight();
            int viewPort = Integer.parseInt(executeJavascript("return document.documentElement.clientHeight"));
            int scrollSize = (viewPort * 70) / 100;
            int loopCounter = (domHeight / scrollSize) > 20 ? 20 : (domHeight / scrollSize);
            for (int loop = 0; loop <= loopCounter; loop++) {
                LogManager.logInfo(pageName + " : " + (loop + 1), false);
                ExtentLogger.logScreenshot();
                executeJavascript("scrollBy(0," + scrollSize + ");");
            }
            scrollToTop();
        }
        catch (UnhandledAlertException uae) {
            LogManager.logInfo("UnhandledAlertException in Selenium.takeFullScreenshot", false);
        }
        catch (Exception ex)
        {
            LogManager.logInfo("Exception in Selenium.takeFullScreenshot", ex);
        }
    }

    private void acceptAlertInternal(){
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (NoAlertPresentException Ex) {
        }
        catch (NoSuchWindowException nswe) {
        }
        catch (TimeoutException te) {
            LogManager.logInfo("Timeout Exception in acceptAlertInternal");
            return;
        }
        catch(Exception e){
            LogManager.logInfo("Exception in acceptAlertInternal", e);
        }
    }

    public void clickElementWithoutWait(String xpath)
    {
        boolean success = false;
            try {
                driver.findElement(By.xpath(xpath)).click();
                success = true;
            }
            catch (Exception ex) {
                LogManager.logInfo("clickElementWithoutWait at first attempt failed");
            }
        if(!success) {
            try {
                driver.findElement(By.xpath(xpath)).click();
                success = true;
            }
            catch (Exception ex)
            {
                LogManager.logInfo("clickElementWithoutWait at second attempt failed");
            }
        }
        if(!success) {
            driver.findElement(By.xpath(xpath)).click();
        }

    }

    public boolean isElementClickable(String xpath) {
        boolean status = false;
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public void dragAndDrop(String from, String to)
    {
        boolean success = false;
        Actions action = new Actions(driver);
        WebElement From=driver.findElement(By.xpath(from));
        WebElement To=driver.findElement(By.xpath(to));
        try {
            action.dragAndDrop(From, To).build().perform();
            success = true;
        }
        catch (Exception ex)
        {
            LogManager.logInfo("dragAndDrop at first attempt failed");
        }
        if(!success) {
            try {
                action.dragAndDrop(From, To).build().perform();
            }
            catch (Exception ex)
            {
                LogManager.logInfo("dragAndDrop at second attempt failed");
            }
        }
    }
}
