package com.testvagrant.tatests.components.model.mobileweb;

import com.testvagrant.tatests.components.mobileweb.Board;
import com.testvagrant.tatests.components.mobileweb.Template;

public interface IDashboard {
    void validateIsDisplayed(String item);
    Board getBoard();
    Template getTemplate();
}
