package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {

    @Test
    void testRequiredPositiveCases() {
        NumberSchema schema = new NumberSchema();
        schema.required();

        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
    }

    @Test
    void testRequiredNegativeCases() {
        NumberSchema schema = new NumberSchema();
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testPositiveOnly() {
        NumberSchema schema = new NumberSchema();
        schema.positive();

        assertThat(schema.isValid(null)).isTrue(); // null is OK unless required()
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
    }

    @Test
    void testRangePositiveCases() {
        NumberSchema schema = new NumberSchema();
        schema.range(1, 10);

        assertThat(schema.isValid(1)).isTrue();    // boundary
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();   // boundary
    }

    @Test
    void testRangeNegativeCases() {
        NumberSchema schema = new NumberSchema();
        schema.range(1, 10);

        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(null)).isTrue(); // null is OK unless required()
    }

    @Test
    void testCombinedValidatorsPositiveCase() {
        NumberSchema schema = new NumberSchema();
        schema.required().positive().range(1, 100);

        assertThat(schema.isValid(50)).isTrue();
        assertThat(schema.isValid(100)).isTrue();
    }

    @Test
    void testCombinedValidatorsNegativeCases() {
        NumberSchema schema = new NumberSchema();
        schema.required().positive().range(1, 100);

        assertThat(schema.isValid(null)).isFalse();  // required
        assertThat(schema.isValid(-1)).isFalse();    // not positive
        assertThat(schema.isValid(0)).isFalse();     // not positive
        assertThat(schema.isValid(101)).isFalse();   // out of range
    }
}
