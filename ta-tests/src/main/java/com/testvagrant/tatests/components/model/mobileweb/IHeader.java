package com.testvagrant.tatests.components.model.mobileweb;

import com.testvagrant.tatests.components.mobileweb.Login;
import com.testvagrant.tatests.components.mobileweb.Logo;
import com.testvagrant.tatests.components.mobileweb.SignUp;

public interface IHeader {
    void validateIsDisplayed(String item);
    void clickLoginLink();
    Logo getLogo();
    Login getLogin();
    SignUp getSignUp();
}