package com.testvagrant.tatests.components;

import com.testvagrant.core.BaseContext;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aishwarya
 */
public class MWebBaseComponent extends BaseComponent
{
    protected MWebBaseComponent(WebDriver driver)
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
