package com.testvagrant.tatests.components.model;

import com.testvagrant.tatests.components.web.BoardCanvas;

public interface IBoard {
    boolean validate_isDisplayed(String board);
    void open_board(String boardName);
    void create_board(String boardName);
    BoardCanvas getBoardCanvas();
}
