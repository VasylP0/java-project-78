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
    void testRequired() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testSizeof() {
        schema.required().sizeof(2);
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        assertThat(schema.isValid(map)).isTrue();

        map.put("k3", "v3");
        assertThat(schema.isValid(map)).isFalse();
    }

    @Test
    void testEmptyMapWithSizeZero() {
        schema.required().sizeof(0);
        Map<String, Object> map = new HashMap<>();
        assertThat(schema.isValid(map)).isTrue();
    }
}
