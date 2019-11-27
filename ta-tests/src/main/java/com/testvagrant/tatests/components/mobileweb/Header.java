package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums.ValidationType;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.IHeader;
import org.openqa.selenium.WebDriver;

public class Header extends MWebBaseComponent implements IHeader {

    private final String header = "//header[contains(@class,'fixed-top')]";
    private final String loginLink = "//a[contains(@href,'login')]";

    public Header(WebDriver driver)
    {
        super(driver);
    }

    public Logo getLogo(){
        return new AllComponent().get(Logo.class);
    }

    public Login getLogin(){
        return new AllComponent().get(Login.class);
    }

    public SignUp getSignUp(){
        return new AllComponent().get(SignUp.class);
    }

    public void validateIsDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(header), true, ValidationType.EQUALS, true);
    }

    public void clickLoginLink(){
        wrapper.clickElement(loginLink);
        wrapper.waitForElementToDisappear(loginLink, 3);
    }

}
