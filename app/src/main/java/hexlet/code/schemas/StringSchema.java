package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        // required => non-null and non-empty
        addCheck("string_required", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int n) {
        // Empty is valid unless required() was set (that predicate will fail empties)
        addCheck("min_length_" + n, v -> v == null || v.isEmpty() || v.length() >= n);
        return this;
    }

    public StringSchema contains(String part) {
        // Empty is valid unless required() was set (that predicate will fail empties)
        addCheck("contains_" + part, v -> v == null || v.isEmpty() || v.contains(part));
        return this;
    }
}
