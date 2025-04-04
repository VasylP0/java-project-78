package hexlet.code.schemas;

import hexlet.code.Validator;
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
        shape.put("firstName", validator.string().required());
        shape.put("lastName", validator.string().required().minLength(2));
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertThat(schema.isValid(human)).isTrue();
    }

    @Test
    void testNullLastNameFails() {
        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("firstName", validator.string().required());
        shape.put("lastName", validator.string().required().minLength(2));
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);

        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testShortLastNameFails() {
        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("firstName", validator.string().required());
        shape.put("lastName", validator.string().required().minLength(2));
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Anna");
        human.put("lastName", "B");

        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testMissingKeyStillValidIfNotRequired() {
        Map<String, BaseSchema<?>> shape = new HashMap<>();
        shape.put("firstName", validator.string().required());
        shape.put("middleName", validator.string()); // not required
        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Peter");

        assertThat(schema.isValid(human)).isTrue();
    }
}
