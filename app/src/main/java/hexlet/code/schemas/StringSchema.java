package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        // For strings, "required" also means non-empty
        addCheck("string_non_empty", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int n) {
        // If value is null or empty AND not required, it's considered valid
        addCheck("min_len_" + n, v -> v == null || v.isEmpty() || v.length() >= n);
        return this;
    }

    public StringSchema contains(String part) {
        // If value is null or empty AND not required, it's considered valid
        addCheck("contains_" + part, v -> v == null || v.isEmpty() || v.contains(part));
        return this;
    }
}
