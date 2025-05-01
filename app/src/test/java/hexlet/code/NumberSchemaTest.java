package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {

    @Test
    void testRequiredValidation() {
        final NumberSchema schema = new NumberSchema();
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
    }

    @Test
    void testPositiveValidation() {
        final NumberSchema schema = new NumberSchema();
        schema.positive();

        assertThat(schema.isValid(null)).isTrue(); // null is valid unless required() is called
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    void testRangeValidation() {
        final NumberSchema schema = new NumberSchema();
        schema.range(5, 10);

        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testCombinedValidators() {
        final NumberSchema schema = new NumberSchema();
        schema.required().positive().range(1, 100);

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(50)).isTrue();
        assertThat(schema.isValid(150)).isFalse();
    }
}
