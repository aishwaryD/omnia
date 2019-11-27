package com.testvagrant.tatests.components.web;

import com.testvagrant.core.BaseContext;
import com.testvagrant.tatests.components.model.*;
import com.testvagrant.tatests.utils.CommonComponent;
import com.testvagrant.ui.core.Driver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class AllComponent {
    List<Object> components;
    public AllComponent() {
        components = new ArrayList<Object>();
    }

    public <T> T get(Class<T> classes) {
        for (Object page : components) {
            if (page.getClass() == classes)
                return (T) page;
        }
        T component = PageFactory.initElements(Driver.getDriver(), classes);
        components.add(component);
        return component;
    }

    public static CommonComponent CommonComponent() {
        return PageFactory.initElements(Driver.getDriver(), CommonComponent.class);
    }

    public static IHome Home() {
        if (BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getDesktopBrowser() != BrowserType.CHROMEMOBILEEMULATION) {
            return PageFactory.initElements(Driver.getDriver(), Home.class);
        }
        if (BaseContext.getPlatform() == Platform.ANDROID || BaseContext.getPlatform() == Platform.IOS || (BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getDesktopBrowser() == BrowserType.CHROMEMOBILEEMULATION)) {
//            if (BaseContext.clientType == ClientType.WEB) {
//                return PageFactory.initElements(Driver.getDriver(), MWebHomePage.class);
//            }
        }
        return PageFactory.initElements(Driver.getDriver(), Home.class);
    }

    public static IDashboard DashBoard() {
        if (BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getDesktopBrowser() != BrowserType.CHROMEMOBILEEMULATION) {
            return new AllComponent().get(Dashboard.class);
        }
        if (BaseContext.getPlatform() == Platform.ANDROID || BaseContext.getPlatform() == Platform.IOS || (BaseContext.getPlatform() == Platform.DESKTOP && BaseContext.getDesktopBrowser() == BrowserType.CHROMEMOBILEEMULATION)) {
            if (BaseContext.getClientType() == ClientType.WEB) {
//                return PageFactory.initElements(Driver.getDriver(), MWebLoginPage.class);
            }
        }
        return new AllComponent().get(Dashboard.class);
    }
}

