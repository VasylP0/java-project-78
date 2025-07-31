package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class NumberSchemaTest {

    @Test
    void testDefault() {
        NumberSchema schema = new NumberSchema();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(-10)).isTrue();
    }

    @Test
    void testPositive() {
        NumberSchema schema = new NumberSchema().positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testRequired() {
        NumberSchema schema = new NumberSchema().required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-5)).isTrue();
    }

    @Test
    void testRange() {
        NumberSchema schema = new NumberSchema().range(5, 10);
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(7)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testCombined() {
        NumberSchema schema = new NumberSchema().required().positive().range(1, 100);
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(101)).isFalse();
        assertThat(schema.isValid(50)).isTrue();
    }
}
