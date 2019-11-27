package com.testvagrant.tatests.workflows.mobileweb;

import com.testvagrant.tatests.components.mobileweb.AllComponent;
import com.testvagrant.tatests.workflows.model.mobileweb.IBoardWF;


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
        AllComponent.DashBoard().validateIsDisplayed("Dashboard");
        if(AllComponent.DashBoard().getBoard().validateIsDisplayed(boardName))
            AllComponent.DashBoard().getBoard().openBoard(boardName);
        else
            AllComponent.DashBoard().getBoard().createBoard(boardName);
    }
}

