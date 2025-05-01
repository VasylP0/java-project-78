package hexlet.code.schemas;

import hexlet.code.strategies.ValidationStrategy;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addStrategy("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addStrategy("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addStrategy("range", value -> value != null && value >= min && value <= max);
        return this;
    }
}
