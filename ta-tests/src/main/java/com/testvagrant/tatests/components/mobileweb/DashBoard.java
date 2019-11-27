package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.IDashboard;
import org.openqa.selenium.WebDriver;

public class DashBoard extends MWebBaseComponent implements IDashboard {

    private final String dashboard = "//div[contains(@class,'member-boards')]";
    public DashBoard(WebDriver driver) {
        super(driver);
    }

    public void validateIsDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(dashboard), true, Enums.ValidationType.EQUALS, true);
    }

    public Board getBoard() {
        return new AllComponent().get(Board.class);
    }

    public Template getTemplate() {
        return new AllComponent().get(Template.class);
    }

}
