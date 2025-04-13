package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String mustContain = null;

    public StringSchema required() {
        setRequired(true);
        addCheck(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        addCheck(value -> value != null && value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        this.mustContain = substring;
        addCheck(value -> value != null && value.contains(mustContain));
        return this;
    }

    @Override
    protected boolean customPreValidation(String value) {
        if (isRequired() && (value == null || value.isEmpty())) {
            return false;
        }

        // Apply minLength if set
        if (minLength != null && value != null && value.length() < minLength) {
            return false;
        }

        // Apply contains if set
        if (mustContain != null && value != null && !value.contains(mustContain)) {
            return false;
        }

        return true;
    }
}
