package com.testvagrant.tatests.tests.webtest.BaseTest;

import com.testvagrant.tatests.client.Client;
import com.testvagrant.tatests.tests.BaseTest;
import org.testng.annotations.*;
import static com.testvagrant.core.ExecutionContext.*;


/**
 * Created by Aishwarya
 */
public class WebBaseTest extends BaseTest
{
    private Client client;

    @BeforeSuite(alwaysRun = true)
    public void suiteSetUp() {
        setWebContext();
        setTakeFullScreenshot(true);
        client = new Client();
    }

    protected <T extends Client> T start(T obj) {
        return obj;
    }

    protected <T extends Client> T let(T obj) {
        return obj;
    }

    protected <T extends Client> T and(T obj) {
        return obj;
    }

    protected <T extends Client> T then(T obj) {
        return obj;
    }

    protected Client client(){
        return client;
    }

}
