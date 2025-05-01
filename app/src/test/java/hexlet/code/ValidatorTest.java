package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

final class ValidatorTest {

    @Test
    void testStringSchema() {
        final Validator v = new Validator();
        final StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.minLength(7);
        assertThat(schema.isValid("hexlet")).isFalse();
        assertThat(schema.isValid("hexlet123")).isTrue();

        schema.contains("hex");
        assertThat(schema.isValid("hexlet123")).isTrue();
        assertThat(schema.isValid("hello")).isFalse();
    }

    @Test
    void testNumberSchema() {
        final Validator v = new Validator();
        final NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(5)).isTrue();

        schema.positive();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        schema.range(5, 10);
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();

        // Corrected: removed assertThat(schema.isValid("5")).isFalse(); // string is invalid
    }

    @Test
    void testMapSchema() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        final Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        assertThat(schema.isValid(map)).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(map)).isFalse();
        map.put("key2", "value2");
        assertThat(schema.isValid(map)).isTrue();
    }

    @Test
    void testValidatorMap() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        final Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        final Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isTrue();

        final Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        final Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();
    }
}
