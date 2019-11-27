package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.ILogin;
import org.openqa.selenium.WebDriver;

public class Login extends MWebBaseComponent implements ILogin {

    private final String loginForm = "//div[@class='login-password-container']";
    private final String emailID = "//input[contains(@id,'user') and @type='email']";
    private final String passwrd = "//input[contains(@id,'password') and @type='password']";
    private final String signInButton = "//input[contains(@id,'login') and @type='submit']";

    public Login(WebDriver driver)
    {
        super(driver);
    }

    public void validateIsDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(loginForm), true, Enums.ValidationType.EQUALS, true);
    }

    public void enterEmailId(String emailId){
        wrapper.enterValue(emailID, emailId,true,false,true);
    }

    public void enterPassword(String password){
        wrapper.enterValue(passwrd, password);
    }

    public void clickSignIn(){
        wrapper.clickElement(signInButton);
        wrapper.waitForElementToDisappear(signInButton, 3);
    }
}
