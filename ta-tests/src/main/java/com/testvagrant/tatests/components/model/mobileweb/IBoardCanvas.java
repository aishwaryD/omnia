package com.testvagrant.tatests.components.model.mobileweb;

public interface IBoardCanvas {
    boolean isDisplayedListTitle(String listTitle);
    void addListTitlesInTrello();
    boolean isDisplayedTask(String listTitle, String taskToMove);
    void addTasksInTrello(String boardName);
    int getAllTasks(String listTitle);
    void moveTaskInTrello(String listTitle, String taskToMove, String destination);
}
