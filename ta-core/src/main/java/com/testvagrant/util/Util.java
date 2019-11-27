package com.testvagrant.util;

import com.testvagrant.core.*;
import org.apache.commons.lang3.EnumUtils;
import java.io.*;
import static com.testvagrant.model.Enums.*;


/**
 * Created by Aishwarya
 */
public class Util
{
    public static String getResourcePath(String folderName)
    {
        String path = null;
        try {
            File filePath = new File(new File(".").getCanonicalPath() + "/" + folderName);
            if (filePath.exists()) {
                path = filePath.getPath();
            }
            else
            {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                path = classLoader.getResource("").getPath() + folderName;
            }
        }
        catch (Exception e)
        {
            LogManager.logError("Exception in Util.getResourcePath", e);
        }
        return path;
    }

    public static OSType getCurrentOSType()
    {
        return OSType.valueOf(System.getProperty("os.name").split(" ")[0].toUpperCase());
    }


    public static void sleep(int milliseconds){
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (Exception e)
        {
            LogManager.logError("Exception in Util.sleep", e);
        }
    }


    public static String getStackTraceFromException(Throwable throwable)
    {
        StringBuffer stackTrace = new StringBuffer("");
        try {
            if (throwable != null) {
                if(throwable.getCause() != null)
                    stackTrace.append(throwable.getCause().toString() + ":");
                stackTrace.append(throwable.toString().trim() + "\n");
                StackTraceElement[] stackTraceElements = throwable.getStackTrace();
                if (stackTraceElements != null && stackTraceElements.length > 0) {
                    for (StackTraceElement stackTraceElement : stackTraceElements) {
                        stackTrace.append(stackTraceElement.toString() + "\n");
                    }
                }
            }
        }
        catch (Exception ex)
        {
            LogManager.logError("Error in Util.getStackTraceFromException ", ex);
        }
        return stackTrace.toString();
    }

    public static <E extends Enum<E>> boolean isValidEnumValue(Class<E> enumClass, String value) {
        return EnumUtils.isValidEnum(enumClass, value);
    }
}
