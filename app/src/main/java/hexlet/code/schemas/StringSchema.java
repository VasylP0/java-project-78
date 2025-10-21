package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        // "Required" means non-null and non-empty string
        addCheck("string_required", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int n) {
        // Only apply if value is not null
        addCheck("min_length_" + n, v -> v == null || v.length() >= n);
        return this;
    }

    public StringSchema contains(String part) {
        // Only apply if value is not null
        addCheck("contains_" + part, v -> v == null || v.contains(part));
        return this;
    }
}
