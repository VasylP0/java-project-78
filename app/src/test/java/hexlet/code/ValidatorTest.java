package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testStringSchema() {
        var v = new Validator();
        var schema = v.string();

        // До required — null и "" валидны
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("hello")).isTrue();

        // Добавляем required
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        // minLength
        schema.minLength(6);
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        // contains
        schema.contains("ex");
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("hellet")).isFalse();

        // повторный contains — все условия должны совпасть
        schema.contains("let");
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("hex")).isFalse();
    }
}
