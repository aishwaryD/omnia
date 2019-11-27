package com.testvagrant.core;

import com.testvagrant.util.Util;
import org.testng.Assert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aishwarya Dwivedi
 * Sout Logger
 */
public class SoutLogger
{
    protected static void logInfo(String message)
    {
        soutMessage(message);
    }

    private synchronized static void soutMessage(String message) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message = String.format("%s %s",sdf.format(new Date()), message);
        System.out.println(message);
    }

    public static void logError(String message)
    {
        soutMessage(message);
    }

    public static void logError(Exception e)
    {
        soutMessage("Exception " + e.getMessage() + "StackTrace" + Util.getStackTraceFromException(e));
        Assert.fail();
    }
}
