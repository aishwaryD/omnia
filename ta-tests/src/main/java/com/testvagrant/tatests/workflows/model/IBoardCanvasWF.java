package com.testvagrant.tatests.workflows.model;

public interface IBoardCanvasWF {
    void updateTasksInTrello(String boardHeaderName, String taskToMove, String currentStatus);
    void verifyMovedTaskInNewLocation(String source, String task);
}
