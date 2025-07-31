package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    @Test
    void testRequired() {
        final StringSchema schema = new StringSchema();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testMinLength() {
        final StringSchema schema = new StringSchema();
        schema.required().minLength(3);

        assertThat(schema.isValid("hi")).isFalse();
        assertThat(schema.isValid("hey")).isTrue();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testContains() {
        final StringSchema schema = new StringSchema();
        schema.required().contains("wh");

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hello world")).isFalse();
    }

    @Test
    void testCombined() {
        final StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("wor");

        assertThat(schema.isValid("hello")).isFalse();
        assertThat(schema.isValid("world")).isTrue();
        assertThat(schema.isValid("wow")).isFalse();
        assertThat(schema.isValid("hello world")).isTrue();
    }
}
