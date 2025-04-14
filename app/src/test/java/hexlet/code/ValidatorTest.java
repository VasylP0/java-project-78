package hexlet.code;

import hexlet.code.schemas.string.StringSchema;
import hexlet.code.schemas.numeric.NumberSchema;
import hexlet.code.schemas.map.MapSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ValidatorTest {

    @Test
    void testCreateSchemas() {
        Validator validator = new Validator();
        var stringSchema = validator.string();
        var numberSchema = validator.number();
        var mapSchema = validator.map();

        assertInstanceOf(StringSchema.class, stringSchema);
        assertInstanceOf(NumberSchema.class, numberSchema);
        assertInstanceOf(MapSchema.class, mapSchema);
    }

    @Test
    void testMapSchemaValidation() {
        MapSchema schema = new Validator().map();

        Map<String, Object> actual1 = new HashMap<>();
        actual1.put("key", "value");

        assertThat(schema.isValid(actual1)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(actual1)).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();
    }

    @Test
    void testMapShapeValidation() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().minLength(3));
        schemas.put("lastName", validator.string());

        schema.shape(schemas);

        Map<String, Object> actual1 = new HashMap<>();
        actual1.put("firstName", "Bob");
        actual1.put("lastName", null);
        assertThat(schema.isValid(actual1)).isTrue();

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("firstName", "Al"); // too short
        actual2.put("lastName", "Smith");
        assertThat(schema.isValid(actual2)).isFalse();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("firstName", "John");
        actual3.put("lastName", "Doe");
        assertThat(schema.isValid(actual3)).isTrue();
    }
}
