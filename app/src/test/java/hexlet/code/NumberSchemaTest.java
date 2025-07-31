package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class NumberSchemaTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testDefaultIsValid() {
        final NumberSchema schema = validator.number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testRequired() {
        final NumberSchema schema = validator.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testPositive() {
        final NumberSchema schema = validator.number().positive();
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(null)); // because not required
    }

    @Test
    void testRequiredAndPositive() {
        final NumberSchema schema = validator.number().required().positive();
        assertTrue(schema.isValid(100));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRange() {
        final NumberSchema schema = validator.number().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
