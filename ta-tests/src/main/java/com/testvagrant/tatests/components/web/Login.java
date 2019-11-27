package com.testvagrant.tatests.components.web;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.ILogin;
import org.openqa.selenium.WebDriver;

public class Login extends WebBaseComponent implements ILogin {

    private final String loginForm = "//div[@class='login-password-container']";
    private final String emailID = "//input[contains(@id,'user') and @type='email']";
    private final String passwrd = "//input[contains(@id,'password') and @type='password']";
    private final String signInButton = "//input[contains(@id,'login') and @type='submit']";

    public Login(WebDriver driver)
    {
        super(driver);
    }

    public void validate_isDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(loginForm), true, Enums.ValidationType.EQUALS, true);
    }

    public void enter_emailId(String emailId){
        wrapper.enterValue(emailID, emailId,true,false,true);
    }

    public void enter_password(String password){
        wrapper.enterValueWithoutWait(passwrd, password);
    }

    public void click_signIn(){
        wrapper.clickElement(signInButton);
        wrapper.waitForElementToDisappear(signInButton, 3);
    }
}
