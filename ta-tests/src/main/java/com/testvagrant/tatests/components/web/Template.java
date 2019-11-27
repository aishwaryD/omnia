package com.testvagrant.tatests.components.web;

import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.ITemplate;
import org.openqa.selenium.WebDriver;

public class Template extends WebBaseComponent implements ITemplate {
    public Template(WebDriver driver)
    {
        super(driver);
    }
}
