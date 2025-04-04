package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;

    public StringSchema required() {
        isRequired = true;
        addCheck(value -> value != null && value instanceof String && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(value -> value != null && value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(value -> value != null && value.contains(substring));
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!isRequired && value == null) {
            return true;
        }
        return super.isValid(value);
    }
}
