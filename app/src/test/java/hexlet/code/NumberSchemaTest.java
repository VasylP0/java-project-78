package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {

    private Validator validator;
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testValidNumber() {
        schema.required().positive().range(5, 10);

        assertThat(schema.isValid(7)).isTrue();
    }

    @Test
    void testInvalidNumber() {
        schema.required().positive().range(5, 10);

        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
    }
}
