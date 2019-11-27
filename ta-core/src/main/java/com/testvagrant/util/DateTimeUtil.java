package com.testvagrant.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class DateTimeUtil {

    public static String getCurrentDate(String format)
    {
        return convertCalendarToString(Calendar.getInstance(), format);
    }

    public static String convertCalendarToString(Calendar calendar, String format)
    {
        String formattedDateString = null;
        if(calendar != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern(format);
            formattedDateString = dateFormat.format(calendar.getTime());
        }
        return formattedDateString;
    }
}
