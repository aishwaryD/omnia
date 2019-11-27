package com.testvagrant.tatests.components.mobileweb;

import static com.testvagrant.core.BaseContext.getPlatform;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.model.mobileweb.IDashboard;
import com.testvagrant.tatests.utils.CommonComponent;
import com.testvagrant.web.core.Driver;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;
import com.testvagrant.tatests.components.model.mobileweb.IHome;

/**
 * Created by Aishwarya Dwivedi
 */
public class AllComponent {
    private List<Object> components;
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
        if (getPlatform() == Enums.Platform.IOS) {
            return PageFactory.initElements(Driver.getDriver(), Home.class);
        }
        return PageFactory.initElements(Driver.getDriver(), Home.class);
    }

    public static IDashboard DashBoard() {
        if (getPlatform() == Enums.Platform.IOS) {
            return PageFactory.initElements(Driver.getDriver(), DashBoard.class);
        }
        return PageFactory.initElements(Driver.getDriver(), DashBoard.class);
    }

}


