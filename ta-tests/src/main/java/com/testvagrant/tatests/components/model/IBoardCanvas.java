package com.testvagrant.tatests.components.model;

public interface IBoardCanvas {
    boolean isDisplayedListTitle(String listTitle);
    void addListTitlesInTrello();
    boolean isDisplayedTask(String listTitle, String taskToMove);
    void addTasksInTrello(String boardName);
    int get_allTasks(String listTitle);
    void moveTaskInTrello(String listTitle, String taskToMove, String destination);
}
