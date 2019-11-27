package com.testvagrant.tatests.components.web;
import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.IHome;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aishwarya Dwivedi
 */
public class Home extends WebBaseComponent implements IHome
{
    private final String homeScreen = "//body[contains(@data-analytics-screen,'homeScreen') and contains(@data-track-group,'Landing')]";

    public Home(WebDriver driver)
    {
        super(driver);
    }

    public void validate_isDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(homeScreen), true, Enums.ValidationType.EQUALS, true);
    }

    public Header get_Header(){
        return new AllComponent().get(Header.class);
    }

    public TopSection get_Top_Section(){
        return new AllComponent().get(TopSection.class);
    }

    public Footer get_Footer(){
        return new AllComponent().get(Footer.class);
    }

}
