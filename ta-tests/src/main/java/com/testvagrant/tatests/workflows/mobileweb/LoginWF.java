package com.testvagrant.tatests.workflows.mobileweb;

import com.testvagrant.tatests.components.mobileweb.AllComponent;
import com.testvagrant.tatests.constants.web.Constants;
import com.testvagrant.tatests.workflows.model.mobileweb.ILoginWF;
import java.util.Map;

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
        AllComponent.Home().validateIsDisplayed("Home Screen");
        AllComponent.Home().getHeader().validateIsDisplayed("Header Section");
        AllComponent.Home().getHeader().clickLoginLink();
        AllComponent.Home().getHeader().getLogin().validateIsDisplayed("Login Form");
        AllComponent.Home().getHeader().getLogin().enterEmailId(defaultValues.get(Constants.email));
        AllComponent.Home().getHeader().getLogin().enterPassword(defaultValues.get(Constants.password));
        AllComponent.Home().getHeader().getLogin().clickSignIn();
    }
}
