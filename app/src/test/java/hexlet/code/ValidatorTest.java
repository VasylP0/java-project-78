package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testStringSchema() {
        final Validator v = new Validator();
        final StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        schema.minLength(4);
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.contains("ex");
        assertThat(schema.isValid("hexlet")).isTrue();
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
        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(10)).isTrue();

        schema.range(5, 10);
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testMapSchemaBasic() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.sizeof(2);
        final Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertThat(schema.isValid(map)).isTrue();
    }

    @Test
    void testMapSchemaShape() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        final Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        final Map<String, Object> actual1 = new HashMap<>();
        actual1.put("name", "Kolya");
        actual1.put("age", 100);
        assertThat(schema.isValid(actual1)).isTrue();

        final Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Maya");
        actual2.put("age", null);
        assertThat(schema.isValid(actual2)).isTrue();

        final Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isFalse();

        final Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "Valya");
        actual4.put("age", -5);
        assertThat(schema.isValid(actual4)).isFalse();
    }
}
