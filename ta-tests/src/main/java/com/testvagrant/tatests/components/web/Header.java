package com.testvagrant.tatests.components.web;

import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.IHeader;
import org.openqa.selenium.WebDriver;
import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums.ValidationType;

public class Header extends WebBaseComponent implements IHeader {

    private final String header = "//header[contains(@class,'fixed-top')]";
    private final String loginLink = "//a[contains(@href,'login')]";

    public Header(WebDriver driver)
    {
        super(driver);
    }

    public Logo get_Logo(){
        return new AllComponent().get(Logo.class);
    }

    public Login get_Login(){
        return new AllComponent().get(Login.class);
    }

    public SignUp get_SignUp(){
        return new AllComponent().get(SignUp.class);
    }

    public void validate_isDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(header), true, ValidationType.EQUALS, true);
    }

    public void click_loginLink(){
        wrapper.clickElement(loginLink);
        wrapper.waitForElementToDisappear(loginLink, 3);
    }

}
