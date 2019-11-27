package com.testvagrant.tatests.components.mobileweb;

import com.testvagrant.tatests.components.MWebBaseComponent;
import com.testvagrant.tatests.components.model.mobileweb.IBoard;
import com.testvagrant.util.StringUtil;
import org.openqa.selenium.WebDriver;

public class Board extends MWebBaseComponent implements IBoard {

    public Board(WebDriver driver) {
        super(driver);
    }

    private final String board = "//div[@class='content-all-boards']//h3[contains(@class,'boards-page-board-section-header-name') and contains(text(),'Boards')]//ancestor::div[@class='boards-page-board-section mod-no-sidebar']//a[contains(@class,'tile')]//div[@title='%s']";
    private final String createBoardLink = "//div[@class='board-tile mod-add']";
    private final String boardTitle = "//input[@data-test-id='create-board-title-input']";
    private final String createBoardBtn = "//button[@data-test-id='create-board-submit-button']";

    public boolean validateIsDisplayed(String boardName) {
        return wrapper.isElementDisplayed(StringUtil.fillParamValue(board,boardName));
    }

    public void openBoard(String boardName){
        wrapper.clickElement(StringUtil.fillParamValue(board,boardName));
        wrapper.waitForElementToDisappear(board, 20);
    }

    public void createBoard(String boardName){
        wrapper.clickElement(createBoardLink);
        wrapper.enterValue(boardTitle, boardName);
        wrapper.clickElement(createBoardBtn);
    }

    public BoardCanvas getBoardCanvas() {
        return new AllComponent().get(BoardCanvas.class);
    }
}
