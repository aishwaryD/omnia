package com.testvagrant.core;

import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya
 */
public class ExecutionContext extends BaseContext {

    public static void setWebContext() {
        setTestType(TestType.WEB);
        setValuePath("values/web/DefaultValues.xml");
    }

    public static void setAnalyticsContext()
    {
        setTestType(TestType.ANALYTICS);
    }
}
