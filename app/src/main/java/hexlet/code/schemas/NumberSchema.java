package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        final Predicate<Integer> requiredCheck = value -> value != null;
        addCheck("required", requiredCheck);
        return this;
    }

    public NumberSchema positive() {
        final Predicate<Integer> positiveCheck = value -> value == null || value > 0;
        addCheck("positive", positiveCheck);
        return this;
    }

    public NumberSchema range(int min, int max) {
        final Predicate<Integer> rangeCheck = value ->
                value != null && value >= min && value <= max;
        addCheck("range", rangeCheck);
        return this;
    }
}
