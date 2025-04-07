package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    private Validator validator;
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testInitialValidation() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("hello")).isTrue();
    }

    @Test
    void testRequiredConstraint() {
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("non-empty")).isTrue();
    }

    @Test
    void testMinLengthConstraint() {
        schema.required().minLength(5);

        assertThat(schema.isValid("12345")).isTrue();
        assertThat(schema.isValid("1234")).isFalse();
    }

    @Test
    void testContainsConstraint() {
        schema.required().contains("abc");

        assertThat(schema.isValid("xxabcxx")).isTrue();
        assertThat(schema.isValid("ab")).isFalse();
        assertThat(schema.isValid("xxabx")).isFalse();
    }

    @Test
    void testMultipleConstraintsTogether() {
        schema.required().minLength(5).contains("abc");

        assertThat(schema.isValid("xxabcxx")).isTrue();
        assertThat(schema.isValid("abc")).isFalse();         // too short
        assertThat(schema.isValid("xxdefxx")).isFalse();     // does not contain
    }
}
