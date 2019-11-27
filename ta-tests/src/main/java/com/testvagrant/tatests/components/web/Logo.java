package com.testvagrant.tatests.components.web;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.ILogo;
import org.openqa.selenium.WebDriver;

public class Logo extends WebBaseComponent implements ILogo {

    public Logo(WebDriver driver)
    {
        super(driver);
    }

    public void validate_isDisplayed(String item, String xpath) {
        Validator.validate(item, wrapper.isElementDisplayed(xpath), true, Enums.ValidationType.EQUALS, true);
    }
}
