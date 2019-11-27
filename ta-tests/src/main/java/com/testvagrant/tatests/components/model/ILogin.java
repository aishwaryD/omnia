package com.testvagrant.tatests.components.model;

public interface ILogin {
    void validate_isDisplayed(String item);
    void enter_emailId(String emailId);
    void enter_password(String password);
    void click_signIn();
}
