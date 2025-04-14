package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        strategies.put("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        strategies.put("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        strategies.put("range", value -> value != null && value >= min && value <= max);
        return this;
    }
}
