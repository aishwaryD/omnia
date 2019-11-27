package com.testvagrant.tatests.components.web;

import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.IBoard;
import com.testvagrant.util.StringUtil;
import org.openqa.selenium.WebDriver;

public class Board extends WebBaseComponent implements IBoard {

    public Board(WebDriver driver) {
        super(driver);
    }

    private final String board = "//div[@class='content-all-boards']//h3[contains(@class,'boards-page-board-section-header-name') and contains(text(),'Boards')]//ancestor::div[@class='boards-page-board-section mod-no-sidebar']//a[contains(@class,'tile')]//div[@title='%s']";
    private final String createBoardLink = "//div[@class='board-tile mod-add']";
    private final String boardTitle = "//input[@data-test-id='create-board-title-input']";
    private final String createBoardBtn = "//button[@data-test-id='create-board-submit-button']";

    public boolean validate_isDisplayed(String boardName) {
        return wrapper.isElementDisplayed(StringUtil.fillParamValue(board,boardName));
    }

    public void open_board(String boardName){
        wrapper.clickElement(StringUtil.fillParamValue(board,boardName));
        wrapper.waitForElementToDisappear(board, 20);
    }

    public void create_board(String boardName){
        wrapper.clickElement(createBoardLink);
        wrapper.enterValue(boardTitle, boardName);
        wrapper.clickElement(createBoardBtn);
    }

    public BoardCanvas getBoardCanvas() {
        return new AllComponent().get(BoardCanvas.class);
    }
}
