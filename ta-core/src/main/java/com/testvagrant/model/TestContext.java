package com.testvagrant.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import static com.testvagrant.model.Enums.*;

/**
 * Created by Aishwarya
 */

@Getter
@Setter

public class TestContext {

    private String testCaseName;
    private String deviceName;
    private String failurePage;
    private String currentPage;
    private String failureMethod;
    private String currentMethod;
    private String failureActivity;
    private String failureReason;
    private boolean skipScreenshot = false;
    private boolean testFailed = false;
    private int runCount;
    private String testCaseDescription;
    private String testCaseId;
    private String className;
    private ArrayList<String> testCaseInfo;
    private String logFilePath;
    private boolean hasScriptIssue;
    private String scriptIssue;
    private BrowserName browserName;
    private List<String> scriptIssueList = new ArrayList<String>();

}
