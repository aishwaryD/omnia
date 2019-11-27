package com.testvagrant.util;

import com.testvagrant.core.LogManager;

import java.io.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class FileManager {

    public static String getAbsoluteFilePath(String filePath)
    {
        String filePathStr = null;
        try
        {
            File file = new File(new File(".").getCanonicalPath() + "/" + filePath);
            if (file.exists()) {
                filePathStr = file.getAbsolutePath();
            } else {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                filePathStr = classLoader.getResource(filePath).getPath();
            }
            if (System.getProperty("os.name").split(" ")[0].toUpperCase().equals("WINDOWS") && filePathStr.startsWith("/")) {
                filePathStr = filePathStr.replaceFirst("/", "");
            }
        }catch (Exception e)
        {
            LogManager.logError("Error in getting Absolute file path: ", e);
        } finally {
            System.out.println("getAbsoluteFilePath::" + filePathStr);
            return filePathStr;
        }
    }
}
