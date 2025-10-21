package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {

    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    void testDefaultIsValid() {
        // No rules yet: null is valid; any integer is valid
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(-10)).isTrue();
        assertThat(schema.isValid(42)).isTrue();
    }

    @Test
    void testRequired() {
        schema.required();
        // Required: null is NOT valid; integers are still fine
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    void testPositive() {
        schema.positive();
        // Not required: null stays valid; positive numbers valid; zero/negative invalid
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
    }

    @Test
    void testRange() {
        schema.range(5, 10);
        // Inclusive bounds
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testRequiredAndPositive() {
        schema.required().positive();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
    }
}
