package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testValidatorCreatesIndependentSchemas() {
        Validator v = new Validator();

        StringSchema schema1 = v.string();
        StringSchema schema2 = v.string();

        assertThat(schema1).isNotSameAs(schema2);
    }

    @Test
    void testValidatorCreatesCorrectSchemaTypes() {
        Validator v = new Validator();

        assertThat(v.string()).isInstanceOf(StringSchema.class);
        assertThat(v.number()).isInstanceOf(NumberSchema.class);
        assertThat(v.map()).isInstanceOf(MapSchema.class);
    }
}
