package com.testvagrant.tatests.components.model;

import com.testvagrant.tatests.components.web.Login;
import com.testvagrant.tatests.components.web.Logo;
import com.testvagrant.tatests.components.web.SignUp;

public interface IHeader {
    void validate_isDisplayed(String item);
    void click_loginLink();
    Logo get_Logo();
    Login get_Login();
    SignUp get_SignUp();
}