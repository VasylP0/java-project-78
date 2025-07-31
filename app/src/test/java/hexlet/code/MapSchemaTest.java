package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class MapSchemaTest {

    @Test
    void testRequired() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testSizeof() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        schema.required().sizeof(2);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isFalse();

        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();

        data.put("key3", "value3");
        assertThat(schema.isValid(data)).isFalse();
    }

    @Test
    void testShape() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", validator.string().required());
        shape.put("age", validator.number().positive());

        schema.shape(shape);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "Kolya");
        human.put("age", 100);
        assertThat(schema.isValid(human)).isTrue();

        human.put("age", null);
        assertThat(schema.isValid(human)).isTrue();

        human.put("name", "");
        assertThat(schema.isValid(human)).isFalse();

        human.put("name", "Valya");
        human.put("age", -5);
        assertThat(schema.isValid(human)).isFalse();
    }
}
