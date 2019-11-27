package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.IFooter;
import org.openqa.selenium.WebDriver;

public class Footer extends MWebBaseComponent implements IFooter {

    public Footer(WebDriver driver)
    {
        super(driver);
    }

    public void validateIsDisplayed(String item, String xpath) {
        Validator.validate(item, wrapper.isElementDisplayed(xpath), true, Enums.ValidationType.EQUALS, true);
    }
}
