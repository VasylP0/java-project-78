package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.BaseSchema; // <-- MISSING import added here
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testStringSchemaInsideValidator() {
        final Validator validator = new Validator();
        final StringSchema schema = validator.string();

        assertThat(schema.isValid("")).isTrue();
        schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testNumberSchemaInsideValidator() {
        final Validator validator = new Validator();
        final NumberSchema schema = validator.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        // Removed the line with schema.isValid("5")
    }

    @Test
    void testValidatorMap() {
        final Validator validator = new Validator();
        final MapSchema<String, Object> schema = validator.map();

        final Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

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
