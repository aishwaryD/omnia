package com.testvagrant.tatests.tests.webtest.BootCamp.Trello.P1;

import com.testvagrant.core.RandomDataGenerator;
import com.testvagrant.core.ResourceValueParser;
import com.testvagrant.model.TestInfo;
import com.testvagrant.tatests.tests.webtest.BaseTest.WebBaseTest;
import org.testng.annotations.Test;
import java.util.Map;
import static com.testvagrant.core.BaseContext.getValuePath;


/**
 * Created by Aishwarya Dwivedi.
 */

public class TrelloAppTest extends WebBaseTest
{
        @Test(groups = {"Regression","P1","MobileWebRegression", "DesktopWebRegression"})
        @TestInfo(testCaseId = "tl-101",
                testCaseDescription = "Verifying able to move the cards in trello",
                module = "Trello",
                createdBy = "tester1@testvagrant.com")

        public void moveCardsInTrelloDT1() {
            Map<String, String> defaultValues = ResourceValueParser.getAllValues(getValuePath());
		    ResourceValueParser.setValueToParticularGroup(getValuePath(), defaultValues, "application", "trello");
		    start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
            let(client()).openPersonalBoard("TestVagrantSprint_1.0" + RandomDataGenerator.generateData(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"));
            and(client()).updateTasksInTrello("To Do", "Task 2", "Doing");
            then(client()).verifyUpdatedTask("Doing", "Task 2");
            EndTest();
        }

        @Test(groups = {"Regression","P1","MobileWebRegression", "DesktopWebRegression"})
        @TestInfo(testCaseId = "tl-102",
                testCaseDescription = "Verifying able to move the cards in trello",
                module = "Trello",
                createdBy = "tester2@testvagrant.com")

        public void moveCardsInTrelloDT2() {
            Map<String, String> defaultValues = ResourceValueParser.getAllValues(getValuePath());
            ResourceValueParser.setValueToParticularGroup(getValuePath(), defaultValues, "application", "trello");
            start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
            let(client()).openPersonalBoard("TestVagrantSprint_1.0" + RandomDataGenerator.generateData(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"));
            and(client()).updateTasksInTrello("To Do", "Task 2", "Doing");
            then(client()).verifyUpdatedTask("Doing", "Task 2");
            EndTest();
        }

        @Test(groups = {"Regression","P1","MobileWebRegression", "DesktopWebRegression"})
        @TestInfo(testCaseId = "tl-103",
                testCaseDescription = "Verifying able to move the cards in trello",
                module = "Trello",
                createdBy = "tester3@testvagrant.com")

        public void moveCardsInTrelloDT3() {
            Map<String, String> defaultValues = ResourceValueParser.getAllValues(getValuePath());
            ResourceValueParser.setValueToParticularGroup(getValuePath(), defaultValues, "application", "trello");
            start(client()).forTrelloAndLoginUsingCredentialsStoredIn(defaultValues);
            let(client()).openPersonalBoard("TestVagrantSprint_1.0" + RandomDataGenerator.generateData(5, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"));
            and(client()).updateTasksInTrello("To Do", "Task 2", "Doing");
            then(client()).verifyUpdatedTask("Doing", "Task 2");
            EndTest();
        }
}