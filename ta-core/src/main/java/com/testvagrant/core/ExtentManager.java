package com.testvagrant.core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aishwarya Dwivedi
 */
public class ExtentManager {
    private static Map<String, ExtentReports> extent = new HashMap<String, ExtentReports>();

    protected synchronized static ExtentReports initializeReporter(String testScripName) {
        if(extent.containsKey(testScripName))
            return extent.get(testScripName);
        ExtentReports ext = null;
        Path path = Paths.get(BaseContext.getOutputPath(), "ExtentReport" + "/" + testScripName + ".html");
        String reportFilePath = path.toAbsolutePath().toString();
        ext = new ExtentReports(reportFilePath, true, NetworkMode.OFFLINE);
        ext.addSystemInfo("Environment Name", BaseContext.getEnvironmentName());
        ext.addSystemInfo("Environment URL", BaseContext.getEnvironmentURL());
        extent.put(testScripName, ext);
        return ext;
    }

    public static void closeAllReporters() {
        if(extent != null && extent.size() > 0)
        {
            for(Map.Entry<String, ExtentReports> report : extent.entrySet())
            {
                try {
                    System.out.println("Closing ExtentReport for " + report.getKey());
                    report.getValue().close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}

