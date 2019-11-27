package com.testvagrant.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Aishwarya Dwivedi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo
{
    String testCaseId() default "[unassigned]";
    String testCaseDescription() default "[unassigned]";
    String module() default "[unassigned]";
    String createdBy() default "[unassigned]";
}
