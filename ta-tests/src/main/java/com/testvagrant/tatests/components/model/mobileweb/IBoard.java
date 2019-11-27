package com.testvagrant.tatests.components.model.mobileweb;

import com.testvagrant.tatests.components.mobileweb.BoardCanvas;

public interface IBoard {
    boolean validateIsDisplayed(String board);
    void openBoard(String boardName);
    void createBoard(String boardName);
    BoardCanvas getBoardCanvas();
}
