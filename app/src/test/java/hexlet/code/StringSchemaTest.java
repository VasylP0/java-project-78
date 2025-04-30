package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {

    @Test
    void testRequiredPositiveCases() {
        StringSchema schema = new StringSchema();
        schema.required();

        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(" ")).isTrue(); // whitespace is considered non-empty
    }

    @Test
    void testRequiredNegativeCases() {
        StringSchema schema = new StringSchema();
        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testMinLengthPositiveCases() {
        StringSchema schema = new StringSchema();
        schema.minLength(4);

        assertThat(schema.isValid("word")).isTrue();
        assertThat(schema.isValid("longer")).isTrue();
    }

    @Test
    void testMinLengthNegativeCases() {
        StringSchema schema = new StringSchema();
        schema.minLength(5);

        assertThat(schema.isValid("cat")).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isTrue(); // null is valid unless required()
    }
}
