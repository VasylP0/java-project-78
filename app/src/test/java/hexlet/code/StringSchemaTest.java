package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {

    private Validator validator;
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testValidString() {
        schema.required().minLength(5);

        assertThat(schema.isValid("Hello")).isTrue();
    }

    @Test
    void testInvalidString() {
        schema.required().minLength(5);

        assertThat(schema.isValid("Hi")).isFalse();
    }
}
