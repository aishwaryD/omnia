package com.testvagrant.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Aishwarya Dwivedi
 */
public class SystemSettingsReader
{
    private static Properties properties = new Properties();
    static{
        try{
            String filePathLocation = new File(".").getCanonicalPath();
            if(filePathLocation.endsWith("test-automation"))
                filePathLocation = filePathLocation + "/ta-tests/src/main/resources";
            File filePath = new File(filePathLocation + "/input/systemsettings.properties");
            if(filePath.exists())
            {
                FileInputStream fileInputStream = new FileInputStream(filePath.getPath());
                properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
            }
            else
            {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("input/systemsettings.properties");
                properties = new Properties();
                properties.load(inputStream);
                inputStream.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String key)
    {
        if(properties.containsKey(key)) {
            return (String) properties.get(key);
        }
        return null;
    }
}
