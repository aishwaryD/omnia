package com.testvagrant.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya Dwivedi
 * Custom File Logger
 */
public class CustomLogger
{
    private static InheritableThreadLocal<Integer> testStepCount = new InheritableThreadLocal<Integer>();
    private static InheritableThreadLocal<WebDriver> driver = new InheritableThreadLocal<WebDriver>();

    protected static void setTestStepCount(int _testStepCount) {
        testStepCount.set(_testStepCount);
    }

    public static void setDriver(WebDriver _driver) {
        driver.set(_driver);
    }

    public static void logDetailsInTestCaseLogFile(String message)
    {
        if(message != null) {
            try {
                if (BaseContext.getTestContext().get() != null && BaseContext.getTestContext().get().getLogFilePath() != null) {
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    message = String.format("%s %s", sdf.format(new Date()), message);
                    Path path = Paths.get(BaseContext.getTestContext().get().getLogFilePath());
                    File dir = path.toAbsolutePath().toFile();
                    if (!dir.exists())
                        dir.mkdirs();
                    File file = new File(path.toAbsolutePath() + "/log.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(message);
                    bw.newLine();
                    bw.close();
                }
            } catch (Exception e) {
                SoutLogger.logError(e);
            }
        }
    }

    protected static String takeScreenShot()
    {
        Path path=null;
        String relativePath=null;
        if (BaseContext.getTestContext().get() != null && BaseContext.getTestContext().get().getClassName() != null && BaseContext.getTestContext().get().getTestCaseName() != null) {
            path = Paths.get(Paths.get(BaseContext.getOutputPath(), BaseContext.getTestContext().get().getClassName(), BaseContext.getTestContext().get().getTestCaseName()).toAbsolutePath().toString() + "_" + BaseContext.getTestContext().get().getRunCount());
            relativePath = "../" + BaseContext.getTestContext().get().getClassName() + "/" + BaseContext.getTestContext().get().getTestCaseName() + "_" + BaseContext.getTestContext().get().getRunCount();

            File dir = path.toAbsolutePath().toFile();
            if (!dir.exists())
                dir.mkdirs();
            String screenshotPath = dir.toString() + "/" + testStepCount.get() + ".png";
            relativePath = relativePath + "/" + testStepCount.get() + ".png";
            testStepCount.set((int) testStepCount.get() + 1);
            if (takeScreenShot(screenshotPath))
                return relativePath;
        }
        return relativePath;
    }

    public static boolean takeScreenShot(String filePath)
    {
        File srcFiler=null;
        try {
            switchWindow();
            if(driver.get() != null) {
                TakesScreenshot scrShot =((TakesScreenshot)driver.get());
                srcFiler = scrShot.getScreenshotAs(OutputType.FILE);
                if(srcFiler != null)
                    FileUtils.copyFile(srcFiler, new File(filePath));
            }
        }
        catch (UnhandledAlertException uae) {
            LogManager.logInfo("UnhandledAlertException in CustomFileLogger.takeScreenShot", false);
        }
        catch (TimeoutException te) {
            LogManager.logInfo("TimeoutException in CustomFileLogger.takeScreenShot", false);
        }
        catch (Exception e) {
            LogManager.logInfo("Exception in CustomFileLogger.takeScreenShot", e, false);
            return false;
        }
        return true;
    }

    private static void switchWindow() {
        try {
            if(BaseContext.getClientType() == ClientType.WEB) {
                Set<String> windows = driver.get().getWindowHandles();
                if (windows.size() == 1) {
                    if (!driver.get().getWindowHandle().equalsIgnoreCase(windows.toArray()[0].toString()))
                        driver.get().switchTo().window(windows.toArray()[0].toString());
                }
            }
        }
        catch (Exception ex) {
            LogManager.logInfo("Exception in CustomFileLogger.switchWindow", ex, false);
        }
    }

    protected static void takeFailureScreenshot(String pageName){
        try {
            switchWindow();
            scrollToTop();
            int domHeight = driver.get().findElement(By.xpath("/*")).getSize().getHeight();
            int viewPort = Integer.parseInt(executeJavascript("return document.documentElement.clientHeight"));
            int scrollSize = (viewPort * 70) / 100;
            int loopCounter = 0;
            if(scrollSize != 0)
                loopCounter = (domHeight / scrollSize) > 20 ? 20 : (domHeight / scrollSize);
            for (int loop = 0; loop <= loopCounter; loop++) {
                LogManager.logInfo(pageName + " : " + (loop + 1), false);
                LogManager.takeScreenshot();
                executeJavascript("scrollBy(0," + scrollSize + ");");
            }
            scrollToTop();
        }
        catch (TimeoutException te) {
            LogManager.logInfo("TimeoutException in CustomFileLogger.takeFailureScreenshot", false);
        }
        catch (UnhandledAlertException uae) {
            LogManager.logInfo("UnhandledAlertException in CustomFileLogger.takeFailureScreenshot", false);
        }
        catch (Exception ex) {
            LogManager.logInfo("Exception in CustomFileLogger.takeFailureScreenshot", ex, false);
        }
    }

    private static void scrollToTop()
    {
        scrollTo(0,0);
    }

    private static void scrollTo(int X, int Y)
    {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver.get();
            js.executeScript("window.scrollTo(" + X + "," + Y + ")");
        }
        catch (Exception ex) {
        }
    }

    private static String executeJavascript(String script) {
        String returnValue;
        JavascriptExecutor js=(JavascriptExecutor)driver.get();
        try{
            returnValue = js.executeScript(script).toString();
        }
        catch(Exception e) {
            returnValue = null;
        }
        return returnValue;
    }
}
