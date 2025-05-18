// ==== FILE: MapSchemaTest.java ====
package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {

    private Validator validator;
    private MapSchema<String, Object> schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testNullIsValidBeforeRequired() {
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testRequiredValidation() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testSizeofValidation() {
        schema.required().sizeof(2);

        final Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        assertThat(schema.isValid(map)).isFalse();

        map.put("key2", "value2");
        assertThat(schema.isValid(map)).isTrue();

        map.put("key3", "value3");
        assertThat(schema.isValid(map)).isFalse();
    }

    @Test
    void testEmptyMapWithSizeZero() {
        schema.required().sizeof(0);
        final Map<String, Object> map = new HashMap<>();
        assertThat(schema.isValid(map)).isTrue();
    }
}
