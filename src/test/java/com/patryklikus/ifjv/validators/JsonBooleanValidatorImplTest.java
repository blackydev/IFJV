/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.ifjv.validators;

import static com.patryklikus.ifjv.validators.JsonValidatorTestCases.INVALIDATE_JSON_TEST;
import static com.patryklikus.ifjv.validators.JsonValidatorTestCases.VALIDATE_JSON_TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("JsonBooleanValidatorImpl")
public class JsonBooleanValidatorImplTest {
    private JsonBooleanValidator jsonBooleanValidator;

    @BeforeEach
    void setUp() {
        jsonBooleanValidator = new JsonBooleanValidatorImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {": true  ", ": false  "})
    @DisplayName(VALIDATE_JSON_TEST)
    void validateTest(String input) throws JsonValidationException {
        int expected = input.indexOf('e') + 1;

        int result = jsonBooleanValidator.validate(input.toCharArray(), 1);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ":true", ":false", "t rue", "f alse", "",
            "True", "tRue", "trUe", "truE",
            "False", "fAlse", "faLse", "falSe", "falsE"
    })
    @DisplayName(INVALIDATE_JSON_TEST)
    void invalidateTest(String input) {
        assertThrows(JsonValidationException.class, () -> jsonBooleanValidator.validate(input.toCharArray(), 0));
    }
}
