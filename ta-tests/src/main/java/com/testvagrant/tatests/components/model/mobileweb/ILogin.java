package com.testvagrant.tatests.components.model.mobileweb;

public interface ILogin {
    void validateIsDisplayed(String item);
    void enterEmailId(String emailId);
    void enterPassword(String password);
    void clickSignIn();
}
