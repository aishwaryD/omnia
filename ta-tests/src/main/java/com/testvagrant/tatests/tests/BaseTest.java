package com.testvagrant.tatests.tests;

import com.testvagrant.core.BaseContext;
import com.testvagrant.core.LogManager;
import org.testng.Assert;


/**
 * Created by Aishwarya Dwivedi
 */
public class BaseTest
{
    protected void EndTest()
    {
        try {
            if(BaseContext.getTestContext().get().isTestFailed())
                Assert.fail();
        }
        catch (Exception ex)
        {
            LogManager.logFatal("Thread" + Thread.currentThread().getId() + ": " + "Exception in EndTest ", ex);
        }
    }
}
