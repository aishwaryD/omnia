package com.testvagrant.tatests.workflows.web;

import com.testvagrant.core.BaseContext;
import com.testvagrant.tatests.components.web.AllComponent;
import com.testvagrant.tatests.workflows.model.IBoardCanvasWF;
import com.testvagrant.core.LogManager;

public class BoardCanvasWF implements IBoardCanvasWF {


    private static BoardCanvasWF webEligibilityWF;

    public static BoardCanvasWF getInstance() {
        if (webEligibilityWF == null)
            webEligibilityWF = new BoardCanvasWF();
        return webEligibilityWF;
    }

    public void updateTasksInTrello(String source, String taskToMove, String destination) {
        if (AllComponent.DashBoard().getBoard().getBoardCanvas().isDisplayedListTitle(source)){
            int tasks = AllComponent.DashBoard().getBoard().getBoardCanvas().get_allTasks(source);
            if (tasks > 0) {
                AllComponent.DashBoard().getBoard().getBoardCanvas().moveTaskInTrello(source, taskToMove, destination);
            } else {
                LogManager.logInfo("No Task Is Added In The Given Board. Adding The Task From Code.");
                AllComponent.DashBoard().getBoard().getBoardCanvas().addTasksInTrello(source);
                AllComponent.DashBoard().getBoard().getBoardCanvas().moveTaskInTrello(source, taskToMove, destination);
            }
        }
        else{
            LogManager.logInfo("No List Titles Are Added In The Given Board. Adding From Code.");
            AllComponent.DashBoard().getBoard().getBoardCanvas().addListTitlesInTrello();
            AllComponent.DashBoard().getBoard().getBoardCanvas().addTasksInTrello(source);
            AllComponent.DashBoard().getBoard().getBoardCanvas().moveTaskInTrello(source, taskToMove, destination);
        }
    }

    public void verifyMovedTaskInNewLocation(String source, String task) {
        if (AllComponent.DashBoard().getBoard().getBoardCanvas().isDisplayedTask(source, task)){
            LogManager.logInfo("Task has been moved successfully. Current location of task: " + task + " is in " + source + " status" + " displayed effectively in platform: " + BaseContext.getPlatform());
        }
        else{
            LogManager.logInfo("Task has not been moved successfully");
        }
    }
}
