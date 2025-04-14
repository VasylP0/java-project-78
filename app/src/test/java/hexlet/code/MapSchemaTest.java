package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {

    private Validator validator;
    private MapSchema schema;

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

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");

        assertThat(schema.isValid(data)).isFalse();

        data.put("key2", "value2");

        assertThat(schema.isValid(data)).isTrue();

        data.put("key3", "value3");

        assertThat(schema.isValid(data)).isFalse();
    }

    @Test
    void testEmptyMapWithSizeZero() {
        schema.required().sizeof(0);

        Map<String, Object> data = new HashMap<>();

        assertThat(schema.isValid(data)).isTrue();
    }
}
