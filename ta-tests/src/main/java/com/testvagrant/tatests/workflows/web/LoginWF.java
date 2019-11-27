package com.testvagrant.tatests.workflows.web;

import com.testvagrant.tatests.constants.web.Constants;
import com.testvagrant.tatests.components.web.AllComponent;
import com.testvagrant.tatests.workflows.model.ILoginWF;

import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class LoginWF implements ILoginWF {
    private static LoginWF webLoginWF;

    public static LoginWF getInstance() {
        if (webLoginWF == null)
            webLoginWF = new LoginWF();
        return webLoginWF;
    }

    public void launchAppAndLoginUsingCredentialsStoredIn(Map<String, String> defaultValues)
    {
        AllComponent.Home().loadHomePage();
        AllComponent.Home().validate_isDisplayed("Home Screen");
        AllComponent.Home().get_Header().validate_isDisplayed("Header Section");
        AllComponent.Home().get_Header().click_loginLink();
        AllComponent.Home().get_Header().get_Login().validate_isDisplayed("Login Form");
        AllComponent.Home().get_Header().get_Login().enter_emailId(defaultValues.get(Constants.email));
        AllComponent.Home().get_Header().get_Login().enter_password(defaultValues.get(Constants.password));
        AllComponent.Home().get_Header().get_Login().click_signIn();
    }
}
