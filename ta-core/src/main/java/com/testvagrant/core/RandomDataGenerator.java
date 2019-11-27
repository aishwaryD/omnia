package com.testvagrant.core;

import com.testvagrant.model.RandomType;
import com.testvagrant.model.XMLAttributesConstants;
import com.testvagrant.util.StringUtil;
import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class RandomDataGenerator
{

    protected static String CreateRandomData(Map<String,String> valueAttributes)
    {
        String result, chars;
        int length=0;

        RandomType randomType = RandomType.valueOf(getValueFromValueMap(valueAttributes, XMLAttributesConstants.type).trim().toUpperCase());
        length = StringUtil.ConvertStringToInteger(getValueFromValueMap(valueAttributes, XMLAttributesConstants.length));
        switch (randomType)
        {
            case ALPHANUMERIC:
                chars = "A1B2C3D4E5F6G7H8I9JKLMNOPQRSTUVWXYZ0";
                break;
            case ALPHABETS:
                chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;
            case ALPHABETSLOWERCASE:
                chars = "abcdefghijklmnopqrstuvwxyz";
                break;
            case NUMERIC:
                chars = "0123456789";
                break;
            case NATURAL:
                chars = "123456789";
                break;
            case SPECIALCHARACTERS:
                chars = "~!@#$%^&*()_+`-={}[]\\ \":/;'<>?,./";
                break;
            case FROMVALUESET:
                Random rand = new Random();
                String valueSet = getValueFromValueMap(valueAttributes, XMLAttributesConstants.valueset);
                String[] values = valueSet.split(",");
                return values[rand.nextInt(values.length)];
            case FROMRANGE:
                String Range[] = getValueFromValueMap(valueAttributes, XMLAttributesConstants.range).split("-");
                int Start = Integer.parseInt(Range[0]);
                int End = Integer.parseInt(Range[1]);
                Random RandomRange = new Random();
                int RandomNumber = RandomRange.nextInt(End - Start) + Start;
                return String.valueOf(RandomNumber);

            default:
                chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
                break;
        }
        result = generateData(length, chars);
        return result;
    }

    public static String generateData(int length, String chars)
    {
        Random random = new Random();
        char[] text;
        text = new char[length];
        for (int i = 0; i < length; i++)
        {
            if(i==0) {
                do {
                    text[i] = chars.charAt(random.nextInt(chars.length()));
                } while (text[i] == '0');
            }
            else
            text[i] = chars.charAt(random.nextInt(chars.length()));
            //Multiple data generation check
            if(i>1)
            {
                while (text[i]==text[i-1] && text[i]==text[i-2]){
                    text[i] = chars.charAt(random.nextInt(chars.length()));
                }
            }
        }
        return new String(text);
    }

    private static String getValueFromValueMap(Map<String, String> valueAttributes, String valueName)
    {
        if (valueAttributes.containsKey(valueName))
            return valueAttributes.get(valueName);
        return null;
    }

}
