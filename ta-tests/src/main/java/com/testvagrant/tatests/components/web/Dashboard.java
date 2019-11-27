package com.testvagrant.tatests.components.web;

import com.testvagrant.core.Validator;
import com.testvagrant.model.Enums;
import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.IDashboard;
import org.openqa.selenium.WebDriver;

public class Dashboard extends WebBaseComponent implements IDashboard {

    private final String dashboard = "//div[contains(@class,'member-boards')]";
    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public void validate_isDisplayed(String item) {
        Validator.validate(item, wrapper.isElementDisplayed(dashboard), true, Enums.ValidationType.EQUALS, true);
    }

    public Board getBoard() {
        return new AllComponent().get(Board.class);
    }

    public Template getTemplate() {
        return new AllComponent().get(Template.class);
    }

}
