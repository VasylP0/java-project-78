package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class ValidatorTest {

    @Test
    void testStringSchema() {
        final Validator v = new Validator();
        final StringSchema schema = v.string();

        assertThat(schema.isValid(""))
                .as("Empty string should be valid when not required")
                .isTrue();
        assertThat(schema.isValid(null))
                .as("Null should be valid when not required")
                .isTrue();

        schema.required();
        assertThat(schema.isValid("hexlet"))
                .as("Valid string")
                .isTrue();
        assertThat(schema.isValid(null))
                .as("Null should not be valid when required")
                .isFalse();
        assertThat(schema.isValid(""))
                .as("Empty string should not be valid when required")
                .isFalse();

        schema.minLength(4);
        assertThat(schema.isValid("hex"))
                .as("Too short string")
                .isFalse();
        assertThat(schema.isValid("hexlet"))
                .as("Valid string with sufficient length")
                .isTrue();

        schema.contains("ex");
        assertThat(schema.isValid("hexlet"))
                .as("Contains substring")
                .isTrue();
        assertThat(schema.isValid("hello"))
                .as("Does not contain substring")
                .isFalse();
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
        final MapSchema<String, Object> schema = v.map();

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
    void mapSchemaShapeValidation() {
        final Validator v = new Validator();
        final MapSchema<String, Object> schema = v.map();

        final Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", v.string().required());
        shape.put("age", v.number().positive());

        schema.shape(shape);

        final Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);

        final Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);

        final Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);

        final Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);

        assertThat(schema.isValid(human1)).isTrue();
        assertThat(schema.isValid(human2)).isTrue();
        assertThat(schema.isValid(human3)).isFalse();
        assertThat(schema.isValid(human4)).isFalse();
    }
}
