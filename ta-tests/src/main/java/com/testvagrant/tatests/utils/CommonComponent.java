package com.testvagrant.tatests.utils;

import com.testvagrant.tatests.components.WebBaseComponent;
import org.openqa.selenium.WebDriver;
import com.testvagrant.ui.core.Driver;
import com.testvagrant.core.BaseContext;
import static com.testvagrant.model.Enums.*;

public class CommonComponent extends WebBaseComponent {
    private static CommonComponent commonComponent;

    public CommonComponent(WebDriver driver) {
        super(driver);
    }

    public static CommonComponent getInstance(WebDriver driver) {
        if(commonComponent == null)
            commonComponent = new CommonComponent(driver);
        return commonComponent;
    }

    public void switchFromAppToDesktop() {
        Driver.tearDown();
        BaseContext.setPlatform(Platform.DESKTOP);
    }

    public void switchFromDesktopToApp() {
        Driver.tearDown();
        BaseContext.setPlatform(Platform.ANDROID);
    }

}
