package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ValidatorTest {

    @Test
    void testCreateSchemas() {
        Validator validator = new Validator();
        var stringSchema = validator.string();
        var numberSchema = validator.number();
        assertInstanceOf(StringSchema.class, stringSchema);
        assertInstanceOf(NumberSchema.class, numberSchema);
    }

    // Trigger GitHub sync
    // Confirm final GitHub sync
}
