package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        super.required();
        addCheck("required_number", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value == null || (value >= min && value <= max));
        return this;
    }
}
