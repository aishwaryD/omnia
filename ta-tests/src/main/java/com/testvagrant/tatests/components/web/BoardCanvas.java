package com.testvagrant.tatests.components.web;

import com.testvagrant.tatests.components.WebBaseComponent;
import com.testvagrant.tatests.components.model.IBoardCanvas;
import com.testvagrant.util.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BoardCanvas extends WebBaseComponent implements IBoardCanvas {

    public BoardCanvas(WebDriver driver) {
        super(driver);
    }

    private final String lstTitle = "//div[contains(@class,'list js-list-content')]//h2[contains(@class,'js-list-name-assist') and contains(text(),'%s')]//ancestor::div[contains(@class,'js-list-header')]";
    private final String allTasks = "//div[contains(@class,'list js-list-content')]//h2[contains(@class,'js-list-name-assist') and contains(text(),'%s')]//ancestor::div[contains(@class,'is-menu-shown')]//following-sibling::div[contains(@class,'js-list-cards')]//a[contains(@class,'ui-droppable')]";
    private final String task = "//div[contains(@class,'list js-list-content')]//h2[contains(@class,'js-list-name-assist') and contains(text(),'%s')]//ancestor::div[contains(@class,'is-menu-shown')]//following-sibling::div[contains(@class,'js-list-cards')]//a[contains(@class,'ui-droppable')]//span[contains(@class,'js-card-name') and text()='%s']";
    private final String addListIcon = "//div[contains(@class,'js-add-list')]";
    private final String listTitleInput = "//input[contains(@class,'list-name-input') and @type='text']";
    private final String addListBtn = "//input[contains(@class,'js-save-edit') and @type='submit']";
    private final String cancelListAddBtn = "//a[contains(@class,'js-cancel-edit')]";
    private final String addCardBtn = "//input[contains(@class,'js-add-card') and @type='submit']";
    private final String cardNameInput = "//textarea[contains(@class,'js-card-title')]";
    private final String cardComposer = "//div[contains(@class,'list js-list-content')]//h2[contains(@class,'js-list-name-assist') and contains(text(),'%s')]//ancestor::div[contains(@class,'is-menu-shown')]//following-sibling::div[contains(@class,'card-composer-container js-card-composer-container')]";


    public boolean isDisplayedListTitle(String listTitle) {
        return wrapper.isElementDisplayedWithoutWait(StringUtil.fillParamValue(lstTitle,listTitle));
    }

    public void moveTaskInTrello(String source, String taskToMove, String destination){
        wrapper.dragAndDrop(String.format(task,source, taskToMove), StringUtil.fillParamValue(lstTitle, destination));
    }

    public boolean isDisplayedTask(String source, String taskToMove) {
        return wrapper.isElementDisplayed(String.format(task, source, taskToMove));
    }

    public void addListTitlesInTrello(){
        String[] listTitles = {"To Do", "Doing", "Done"};
        for (String str : listTitles){
            wrapper.clickElement(addListIcon);
            wrapper.enterValue(listTitleInput, str);
            wrapper.clickElement(addListBtn);
        }
        wrapper.clickElement(cancelListAddBtn);
    }

    public void addTasksInTrello(String boardName){
        String[] listTasks = {"Task 1", "Task 2", "Task 3"};
        wrapper.clickElement(StringUtil.fillParamValue(cardComposer,boardName));
        for (String str : listTasks){
            wrapper.enterValue(cardNameInput, str);
            wrapper.clickElement(addCardBtn);
        }
    }

    public int get_allTasks(String listTitle)
    {
        List<WebElement> links = wrapper.findElementsByXPath(StringUtil.fillParamValue(allTasks,listTitle));
        List<WebElement> linkDisplayed = new ArrayList<>();
        for(WebElement element : links)
        {
            if(element.isDisplayed())
            {
                linkDisplayed.add(element);
            }
        }
        return linkDisplayed.size();
    }



}