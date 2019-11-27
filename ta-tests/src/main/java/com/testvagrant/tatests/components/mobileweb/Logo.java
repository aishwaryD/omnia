package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.ILogo;
import org.openqa.selenium.WebDriver;

public class Logo extends MWebBaseComponent implements ILogo {

    public Logo(WebDriver driver)
    {
        super(driver);
    }

    public void validateIsDisplayed(String item, String xpath) {
        Validator.validate(item, wrapper.isElementDisplayed(xpath), true, Enums.ValidationType.EQUALS, true);
    }
}
