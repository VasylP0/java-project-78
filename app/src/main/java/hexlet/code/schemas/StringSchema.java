package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required(); // marks null as invalid in BaseSchema
        // additionally forbid empty string ("")
        addCheck("string_required_non_empty", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int n) {
        // empty/null are OK unless required(); length must be >= n otherwise
        addCheck("min_length_" + n, v -> v == null || v.isEmpty() || v.length() >= n);
        return this;
    }

    public StringSchema contains(String part) {
        // empty/null are OK unless required(); otherwise must contain `part`
        addCheck("contains_" + part, v -> v == null || v.isEmpty() || v.contains(part));
        return this;
    }
}
