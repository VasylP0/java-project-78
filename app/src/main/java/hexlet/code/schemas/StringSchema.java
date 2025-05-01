package hexlet.code.schemas;

import hexlet.code.strategies.ValidationStrategy;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addStrategy("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addStrategy("minLength", value -> value != null && value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addStrategy("contains", value -> value != null && value.contains(substring));
        return this;
    }
}
