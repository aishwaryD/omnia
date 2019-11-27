package com.testvagrant.util;

import com.testvagrant.core.LogManager;

/**
 * Created by Aishwarya Dwivedi
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String value)
    {
        return value == null || value.equals("");
    }

    public static boolean convertStringToBoolean(String str) {
        if (str == null) {
            return false;
        } else {
            return Boolean.parseBoolean(str);
        }
    }

    public static String fillParamValue(String original, String toReplace)
    {
        if(toReplace == null)
            LogManager.logInfo("toReplace value is NULL for string " + original);
        String returnValue = original.replaceAll("%s",toReplace);
        return returnValue;
    }

    public static String msToString(long ms) {
        long totalSecs = ms/1000;
        long hours = (totalSecs / 3600);
        long mins = (totalSecs / 60) % 60;
        long secs = totalSecs % 60;
        String minsString = (mins == 0)
                ? "00"
                : ((mins < 10)
                ? "0" + mins
                : "" + mins);
        String secsString = (secs == 0)
                ? "00"
                : ((secs < 10)
                ? "0" + secs
                : "" + secs);
        if (hours > 0)
            return hours + " Hours " + minsString + " Mins " + secsString + " Secs";
        else if (mins > 0)
            return mins + " Mins " + secsString + " Secs";
        else return secs + " Secs";
    }

    public static int convertStringToInteger(String input)
    {
        int value = -1;
        try
        {
            value = Integer.parseInt(input.trim().replace(",",""));
        }
        catch (Exception ex)
        {
            LogManager.logError("Unable to convert String " + input + " to Integer value ", ex);
        }
        return value;
    }

    public static Integer ConvertStringToInteger(String str) {
        if (str == null) {
            return new Integer(0);
        } else {
            return Integer.parseInt(str);
        }
    }

}
