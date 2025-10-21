package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        // For strings, required means non-null and non-empty
        addCheck("string_required_non_empty", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int n) {
        addCheck("min_length_" + n, v -> {
            if (v == null || v.isEmpty()) {
                return !required;
            }
            return v.length() >= n;
        });
        return this;
    }

    public StringSchema contains(String part) {
        addCheck("contains_" + part, v -> {
            if (v == null || v.isEmpty()) {
                // Empty allowed only if not required
                return !required;
            }
            return v.contains(part);
        });
        return this;
    }
}
