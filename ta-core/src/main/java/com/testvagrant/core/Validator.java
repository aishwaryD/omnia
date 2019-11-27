package com.testvagrant.core;

import com.testvagrant.model.Enums.ValidationType;

/**
 * Created by Aishwarya Dwivedi
 */
public class Validator
{
    public static boolean validate(String paramName, String actualValue, ValidationType validationType, boolean continueOnFailure)
    {
        return assertValue(paramName, actualValue, validationType, continueOnFailure);
    }

    public static boolean validate(String paramName, int actualValue, int expectedValue, ValidationType validationType, boolean continueOnFailure)
    {
        return assertValue(paramName, actualValue, expectedValue, validationType, continueOnFailure);
    }

    public static boolean validate(String paramName, boolean actualValue, boolean expectedValue, ValidationType validationType, boolean continueOnFailure)
    {
        return assertValue(paramName, actualValue, expectedValue, validationType, continueOnFailure);
    }

    public static boolean validate(String paramName, float actualValue, float expectedValue, ValidationType validationType, boolean continueOnFailure)
    {
        return assertValue(paramName, actualValue, expectedValue, validationType, continueOnFailure);
    }

    public static boolean validate(String paramName, double actualValue, double expectedValue, ValidationType validationType, boolean continueOnFailure)
    {
        return assertValue(paramName, actualValue, expectedValue, validationType, continueOnFailure);
    }

    private static boolean assertValue(String paramName, String actualValue, ValidationType validationType,boolean ContinueOnFailure)
    {
        switch (validationType)
        {
            case EMPTY:
                return CustomAssert.assertEmpty(actualValue, "Validation (EMPTY) for :'" + paramName + "'. Actual value = " + actualValue, ContinueOnFailure);
            case NOT_EMPTY:
                return CustomAssert.assertNotEmpty(actualValue, "Validation (NOT EMPTY) for :'" + paramName + "'. Actual value = " + actualValue, ContinueOnFailure);
            case IS_NUMERIC:
                return CustomAssert.assertIsNumeric(actualValue, "Validation (IS NUMERIC) for :'" + paramName + "'. Actual value = " + actualValue, ContinueOnFailure);
            case IS_DOUBLE:
                return CustomAssert.assertIsDouble(actualValue, "Validation (Is Double) for :'" + paramName + "'. Actual value = " + actualValue, ContinueOnFailure);
            case IS_BOOLEAN:
                return CustomAssert.assertIsBoolean(actualValue, "Validation (IS BOOLEAN) for :'" + paramName + "'. Actual value = " + actualValue, ContinueOnFailure);
            default:
                CustomAssert.fail("Invalid Validation type " + validationType.toString(), false);
                return false;
        }
    }

    private static boolean assertValue(String paramName, boolean actualValue, boolean expectedValue, ValidationType validationType,boolean ContinueOnFailure)
    {
        switch (validationType)
        {
            case EQUALS:
                return CustomAssert.assertEquals(actualValue, expectedValue, "Validation (Equals) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case NOT_EQUALS:
                return CustomAssert.assertNotEquals(actualValue, expectedValue, "Validation (Not Equals) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            default:
                CustomAssert.fail("Invalid Validation type " + validationType.toString(), false);
                return false;
        }
    }

    private static boolean assertValue(String paramName, int actualValue, int expectedValue, ValidationType validationType,boolean ContinueOnFailure)
    {
        switch (validationType)
        {
            case EQUALS:
                return CustomAssert.assertEquals(actualValue, expectedValue, "Validation (Equals) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case NOT_EQUALS:
                return CustomAssert.assertNotEquals(actualValue, expectedValue, "Validation (Not Equals) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_LESS_THAN:
                return CustomAssert.assertLessThan(actualValue, expectedValue, "Validation (LESS THAN) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_LESS_THAN_OR_EQUAL_TO:
                return CustomAssert.assertLessThanOREqualTo(actualValue, expectedValue, "Validation (LESS THAN OR EQUAL TO) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_GREATER_THAN:
                return CustomAssert.assertGreaterThan(actualValue, expectedValue, "Validation (GREATER THAN) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_GREATER_THAN_OR_EQUAL_TO:
                return CustomAssert.assertGreaterThanOREqualTo(actualValue, expectedValue, "Validation (GREATER THAN OR EQUAL TO) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            default:
                CustomAssert.fail("Invalid Validation type " + validationType.toString(), false);
                return false;
        }
    }

    private static boolean assertValue(String paramName, double actualValue, double expectedValue, ValidationType validationType, boolean ContinueOnFailure)
    {
        switch (validationType)
        {
            case EQUALS:
                return CustomAssert.assertEquals(actualValue, expectedValue, "Validation (EQUALS) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case NUMERIC_EQUALS:
                return CustomAssert.assertEquals(actualValue, expectedValue, "Validation (NUMERIC EQUALS) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_LESS_THAN:
                return CustomAssert.assertLessThan(actualValue, expectedValue, "Validation (LESS THAN) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_LESS_THAN_OR_EQUAL_TO:
                return CustomAssert.assertLessThanOREqualTo(actualValue, expectedValue, "Validation (LESS THAN OR EQUAL TO) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_GREATER_THAN:
                return CustomAssert.assertGreaterThan(actualValue, expectedValue, "Validation (GREATER THAN) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            case IS_GREATER_THAN_OR_EQUAL_TO:
                return CustomAssert.assertGreaterThanOREqualTo(actualValue, expectedValue, "Validation (GREATER THAN OR EQUAL TO) for :'" + paramName + "'. Actual value = '" + actualValue + "'  Expected value = '" + expectedValue + "'", ContinueOnFailure);
            default:
                CustomAssert.fail("Invalid Validation type " + validationType.toString(), false);
                return false;
        }
    }
}
