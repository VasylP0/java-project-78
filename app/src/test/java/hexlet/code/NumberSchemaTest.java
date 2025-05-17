package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {

    @Test
    void testRequired() {
        final NumberSchema schema = new NumberSchema().required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
    }

    @Test
    void testPositive() {
        final NumberSchema schema = new NumberSchema().positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    void testRange() {
        final NumberSchema schema = new NumberSchema().range(5, 10);
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testCombined() {
        final NumberSchema schema = new NumberSchema().required().positive().range(1, 100);
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(50)).isTrue();
        assertThat(schema.isValid(150)).isFalse();
    }
}
