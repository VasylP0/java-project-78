package hexlet.code.schemas;

import hexlet.code.schemas.string.RequiredValidation;
import hexlet.code.schemas.string.MinLengthValidation;
import hexlet.code.schemas.string.ContainsValidation;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        strategies.put("required", new RequiredValidation());
        return this;
    }

    public StringSchema minLength(int minLength) {
        strategies.put("minLength", new MinLengthValidation(minLength));
        return this;
    }

    public StringSchema contains(String substring) {
        strategies.put("contains", new ContainsValidation(substring));
        return this;
    }
}
