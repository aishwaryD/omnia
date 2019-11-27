package com.testvagrant.tatests.components.model;

import com.testvagrant.tatests.components.web.Board;
import com.testvagrant.tatests.components.web.Template;

public interface IDashboard {
    void validate_isDisplayed(String item);
    Board getBoard();
    Template getTemplate();
}
