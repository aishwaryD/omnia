package com.testvagrant.tatests.tests.webtest.BootCamp.Trello.P1;

import com.testvagrant.core.RandomDataGenerator;
import com.testvagrant.core.ResourceValueParser;
import com.testvagrant.model.TestInfo;
import com.testvagrant.tatests.constants.web.Constants;
import com.testvagrant.tatests.tests.webtest.BaseTest.WebBaseTest;
import org.testng.annotations.Test;
import java.util.Map;
import static com.testvagrant.core.BaseContext.getValuePath;


/**
 * Created by Aishwarya Dwivedi.
 */

public class TrelloAppSwitchContextTest extends WebBaseTest
{
    @Test(groups = {"Regression","P1","MobileWebRegression", "DesktopWebRegression"})
    @TestInfo(testCaseId = "tl-104",
            testCaseDescription = "Verifying able to move the cards in trello",
            module = "Trello",
            createdBy = "tester4@testvagrant.com")

    public void moveCardsInTrelloSwitchContext() {
        Map<String, String> defaultValues = ResourceValueParser.getAllValues(getValuePath());
        ResourceValueParser.setValueToParticularGroup(getValuePath(), defaultValues, "application", "trello");
        defaultValues.put(Constants.board, "TestVagrantSprint_1.0_" + RandomDataGenerator.generateData(7, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"));
        start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
        let(client()).openPersonalBoard(defaultValues.get(Constants.board));
        and(client()).updateTasksInTrello("To Do", "Task 2", "Doing");
        then(client()).verifyUpdatedTask("Doing", "Task 2");
        let(client()).switchFromDesktopToApp();
        start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
        let(client()).openPersonalBoard(defaultValues.get(Constants.board));
        then(client()).verifyUpdatedTask("Doing", "Task 2");
        let(client()).switchFromAppToDesktop();
        start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
        let(client()).openPersonalBoard(defaultValues.get(Constants.board));
        then(client()).verifyUpdatedTask("Doing", "Task 2");
        EndTest();
    }
}