package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaShapeTest {

    private Validator validator;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();
    }

    @Test
    void testValidMap() {
        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", validator.string().required());
        shape.put("age", validator.number().required().positive());
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", "30");

        assertThat(schema.isValid(human)).isTrue();
    }

    @Test
    void testInvalidMap() {
        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("name", validator.string().required());
        shape.put("age", validator.number().required().positive());
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", "-5");

        assertThat(schema.isValid(human)).isFalse();
    }
}
