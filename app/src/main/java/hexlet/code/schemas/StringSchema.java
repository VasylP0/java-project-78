package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        setRequired(true);
        addCheck(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        // Remove any previous minLength check
        removeCheckIf(predicate -> predicate.toString().contains("length() >= "));
        addCheck(value -> value != null && value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(value -> value != null && value.contains(substring));
        return this;
    }

    @Override
    protected boolean customPreValidation(String value) {
        return !isRequired() || (value != null && !value.isEmpty());
    }
}
