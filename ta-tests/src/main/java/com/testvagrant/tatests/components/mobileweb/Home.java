package com.testvagrant.tatests.components.mobileweb;
import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.IHome;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aishwarya Dwivedi
 */
public class Home extends MWebBaseComponent implements IHome
{
    private final String homeScreen = "//body[contains(@data-analytics-screen,'homeScreen') and contains(@data-track-group,'Landing')]";
    public Home(WebDriver driver)
    {
        super(driver);
    }


    public void validateIsDisplayed(String item) {
        Validator. validate(item, wrapper.isElementDisplayed(homeScreen), true, Enums.ValidationType.EQUALS, true);
    }

    public Header getHeader(){
        return new AllComponent().get(Header.class);
    }

    public TopSection getTopSection(){
        return new AllComponent().get(TopSection.class);
    }

    public Footer getFooter(){
        return new AllComponent().get(Footer.class);
    }

}
