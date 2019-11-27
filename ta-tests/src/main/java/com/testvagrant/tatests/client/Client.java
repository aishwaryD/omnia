package com.testvagrant.tatests.client;

import com.testvagrant.core.BaseContext;
import com.testvagrant.tatests.components.mobileweb.AllComponent;
import com.testvagrant.tatests.workflows.web.WorkFlows;
import java.util.Map;

public class Client {

    public void forTrelloAndLoginUsingCredentialsStoredIn(Map<String, String> defaultValues){
        if (BaseContext.isIOSWeb() || BaseContext.isAndroidWeb()){
            com.testvagrant.tatests.workflows.mobileweb.WorkFlows.LoginWF().launchAppAndLoginUsingCredentialsStoredIn(defaultValues);
        }
        else {
            WorkFlows.LoginWF().launchAppAndLoginUsingCredentialsStoredIn(defaultValues);
        }
    }

    public void openPersonalBoard(String boardName) {
        if (BaseContext.isIOSWeb() || BaseContext.isAndroidWeb()){
            com.testvagrant.tatests.workflows.mobileweb.WorkFlows.BoardWF().openPersonalBoard(boardName);
        }
        else{
            WorkFlows.BoardWF().openPersonalBoard(boardName);
        }
    }

    public void updateTasksInTrello(String source, String taskToMove, String destination) {
        if (BaseContext.isIOSWeb() || BaseContext.isAndroidWeb()){
            com.testvagrant.tatests.workflows.mobileweb.WorkFlows.BoardCanvasWF().updateTasksInTrello(source, taskToMove, destination);
        }
        else {
            WorkFlows.BoardCanvasWF().updateTasksInTrello(source, taskToMove, destination);
        }
    }

    public void verifyUpdatedTask(String source, String task) {
        if (BaseContext.isIOSWeb() || BaseContext.isAndroidWeb()) {
            com.testvagrant.tatests.workflows.mobileweb.WorkFlows.BoardCanvasWF().verifyMovedTaskInNewLocation(source, task);
        }
        else {
            WorkFlows.BoardCanvasWF().verifyMovedTaskInNewLocation(source, task);
        }
    }

    public void switchFromDesktopToApp(){
        AllComponent.CommonComponent().switchFromDesktopToApp();
    }

    public void switchFromAppToDesktop(){
        AllComponent.CommonComponent().switchFromAppToDesktop();
    }
}