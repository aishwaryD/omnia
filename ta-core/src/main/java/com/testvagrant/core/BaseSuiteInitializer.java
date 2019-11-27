package com.testvagrant.core;

import com.testvagrant.util.*;
import org.testng.ISuite;
import static com.testvagrant.core.BaseContext.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class BaseSuiteInitializer {

    protected void onStartSetup(ISuite suite)
    {
        BaseContext.setSuiteName(suite.getName());
        setBaseContext();
        setRequestId();
        setOutputPath(suite);
    }

    private void setOutputPath(ISuite suite) {
        if(!InputReader.containsKey("request.outputdir") ||
                InputReader.getPropertyValue("request.outputdir").equalsIgnoreCase("default")) {
            BaseContext.setOutputPath(suite.getOutputDirectory() + "/tareports/" + getRequestId());
        }
        else {
            BaseContext.setOutputPath(InputReader.getPropertyValue("request.outputdir") + "/tareports/" + getRequestId());
        }
    }

    private void setRequestId() {
        BaseContext.setRequestId(DateTimeUtil.getCurrentDate("yyMMddhhmmssMs"));
    }
}
