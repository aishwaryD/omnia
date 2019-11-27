package com.testvagrant.tatests.workflows.model;

import java.util.Map;

/**
 * Created by aishwarya
 */
public interface ILoginWF {
    void launchAppAndLoginUsingCredentialsStoredIn(Map<String, String> defaultValues);
}
