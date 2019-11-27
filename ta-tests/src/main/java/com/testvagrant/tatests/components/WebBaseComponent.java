package com.testvagrant.tatests.components;

import com.testvagrant.core.*;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aishwarya
 */
public class WebBaseComponent extends BaseComponent
{
    protected WebBaseComponent(WebDriver driver)
    {
        super(driver);
        this.APP_URL = BaseContext.getEnvironmentURL();
    }

    public void loadHomePage()
    {
        wrapper.loadPage(this.APP_URL);
        wrapper.waitForPageLoadToComplete();
    }
}
