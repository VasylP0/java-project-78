package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        addCheck("string_required_non_empty", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int number) {
        addCheck("minLength", v -> v == null || v.isEmpty() || v.length() >= number);
        return this;
    }

    public StringSchema contains(String str) {
        addCheck("contains", v -> v == null || v.isEmpty() || v.contains(str));
        return this;
    }
}
