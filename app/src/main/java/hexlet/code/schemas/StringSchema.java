package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        super.required();
        addCheck(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck(value -> value == null || value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(value -> value == null || value.contains(substring));
        return this;
    }
}
