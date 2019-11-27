package com.testvagrant.tatests.components;

import com.testvagrant.web.testchannel.Wrapper;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aishwarya Dwivedi
 */
public class BaseComponent {
    protected String APP_URL;
    protected final Wrapper wrapper;

    protected BaseComponent(WebDriver driver) {
        wrapper = new Wrapper(driver);
    }

}
