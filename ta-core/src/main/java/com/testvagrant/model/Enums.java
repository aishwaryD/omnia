package com.testvagrant.model;

/**
 * Created by Aishwarya Dwivedi
 */
public class Enums {
    public enum ExpectedCondition {
        elementToBeClickable,
        visibilityOfElement
    }

    public enum ValidationType
    {
        EQUALS,
        EQUALS_IGNORE_SPECIAL_CHAR,
        NOT_EQUALS,
        EMPTY,
        NOT_EMPTY,
        CONTAINS,
        NOT_CONTAINS,
        EXPECTED_IS_SUBSTRING_OF_ACTUAL,
        IS_CONTAINED,
        IS_NOT_CONTAINED,
        IS_NUMERIC,
        IS_BOOLEAN,
        NUMERIC_EQUALS,
        IS_GREATER_THAN,
        IS_GREATER_THAN_OR_EQUAL_TO,
        IS_LESS_THAN,
        IS_LESS_THAN_OR_EQUAL_TO,
        IS_DATE_AFTER,
        IS_DATE_AFTER_OR_EQUAL_TO,
        IS_DATE_BEFORE,
        IS_DATE_BEFORE_OR_EQUAL_TO,
        IS_DATE_EQUALS,
        REGEX_MATCH,
        EQUALS_WITHOUT_SPACE_LINE_BREAK,
        IS_DOUBLE,
        IN_VALUESET,
        NOT_IN_VALUESET;
    }

    public enum OSType {
        WINDOWS,
        LINUX,
        MAC
    }

    public enum ClientType {
        WEB,
        APP
    }

    public enum Platform {
        ANDROID,
        DESKTOP,
        IOS,
        WINDOWSMOBILE

    }

    public enum TestPlan {
        MOBILEWEB,
        DESKTOPWEB,
    }

    public enum BrowserType {
        FIREFOX,
        CHROME,
        SAFARI,
        CHROMEMOBILEEMULATION,
        IEXPLORER,
        RANDOM,
        NA
    }

    public enum TestType {
        WEB,
        APP,
        SERVICES,
        ANALYTICS
    }

    public enum BrowserName {
        FIREFOX,
        CHROME,
        SAFARI,
        IEXPLORER,
        PHANTOMJS,
        OPERA,
        HTML,
        NA
    }

    public enum TimeZone{
        UTC("UTC"),
        IST("IST"),
        SGT("Asia/Singapore"),
        MYT("Asia/Kuala_Lumpur");

        public final String zoneId;

        TimeZone(String zoneId)
        {
            this.zoneId = zoneId;
        }
    }

    public enum ExecutionLocale{
        IN("India", "IND", TimeZone.IST),
        SG("Singapore", "SGP", TimeZone.SGT),
        MY("Malaysia", "MY", TimeZone.MYT);

        public final String fullName;
        public final String shortName;
        public final TimeZone timeZone;

        ExecutionLocale(String fullName, String shortName, TimeZone timeZone)
        {
            this.fullName = fullName;
            this.shortName = shortName;
            this.timeZone = timeZone;
        }
    }
}
