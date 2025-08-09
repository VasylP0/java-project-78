package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required(); // not-null
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range_" + min + "_" + max, v -> v == null || (v >= min && v <= max));
        return this;
    }
}
