package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {

    @Test
    void testRequiredValidation() {
        final StringSchema schema = new StringSchema();
        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testMinLengthValidation() {
        final StringSchema schema = new StringSchema();
        schema.minLength(3);

        assertThat(schema.isValid("hi")).isFalse();
        assertThat(schema.isValid("hex")).isTrue();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testContainsValidation() {
        final StringSchema schema = new StringSchema();
        schema.contains("abc");

        assertThat(schema.isValid("xxabcxx")).isTrue();
        assertThat(schema.isValid("abx")).isFalse();
    }

    @Test
    void testCombinedValidators() {
        final StringSchema schema = new StringSchema();
        schema.required().minLength(5).contains("ab");

        assertThat(schema.isValid("abcdeab")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("abc")).isFalse();
        assertThat(schema.isValid("hello world")).isFalse();
    }
}
