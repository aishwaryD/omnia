package com.testvagrant.tatests.workflows.web;

import com.testvagrant.tatests.components.web.AllComponent;
import com.testvagrant.tatests.workflows.model.IBoardWF;


/**w
 * Created by Aishwarya Dwivedi
 */
public class BoardWF implements IBoardWF {
    private static BoardWF webEligibilityWF;

    public static BoardWF getInstance() {
        if (webEligibilityWF == null)
            webEligibilityWF = new BoardWF();
        return webEligibilityWF;
    }

    public void openPersonalBoard(String boardName) {
        AllComponent.DashBoard().validate_isDisplayed("Dashboard");
        if(AllComponent.DashBoard().getBoard().validate_isDisplayed(boardName))
            AllComponent.DashBoard().getBoard().open_board(boardName);
        else
            AllComponent.DashBoard().getBoard().create_board(boardName);
    }
}

