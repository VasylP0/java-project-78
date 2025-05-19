package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {

    @Test
    void testMapSchemaBasic() {
        final Validator validator = new Validator();
        final MapSchema schema = validator.map();

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
        final Validator validator = new Validator();
        final MapSchema schema = validator.map();

        final Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", validator.string().required());
        shape.put("age", validator.number().positive());

        schema.shape(shape);

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

        final Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schema.isValid(human4)).isFalse();
    }
}
