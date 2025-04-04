package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {

    private Validator validator;
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testInitialValidation() {
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testPositiveConstraint() {
        schema.positive();

        assertThat(schema.isValid(null)).isTrue();  // still valid because `required()` not called yet
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse(); // 0 is not positive
    }

    @Test
    void testRequiredConstraint() {
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(0)).isTrue(); // no positive check yet
    }

    @Test
    void testPositiveAndRequiredTogether() {
        schema.required().positive();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testRangeConstraint() {
        schema.required().positive().range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }
}
