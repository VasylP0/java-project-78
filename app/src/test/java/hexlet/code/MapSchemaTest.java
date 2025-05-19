package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.Validator;
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

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        final Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        schema.sizeof(2);
        assertThat(schema.isValid(map)).isTrue();
    }

    @Test
    void testMapSchemaShape() {
        final Validator validator = new Validator();
        final Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", validator.string().required());
        shape.put("age", validator.number().positive());

        final MapSchema schema = validator.map();
        schema.shape(shape);

        final Map<String, Object> human1 = Map.of("name", "Kolya", "age", 100);
        final Map<String, Object> human2 = Map.of("name", "Maya", "age", null);
        final Map<String, Object> human3 = Map.of("name", "", "age", null);
        final Map<String, Object> human4 = Map.of("name", "Valya", "age", -5);

        assertThat(schema.isValid(human1)).isTrue();
        assertThat(schema.isValid(human2)).isTrue();
        assertThat(schema.isValid(human3)).isFalse();
        assertThat(schema.isValid(human4)).isFalse();
    }
}
