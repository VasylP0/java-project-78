// ==== FILE: NumberSchema.java ====
package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        final Predicate<Integer> positiveCheck = n -> n == null || n > 0;
        addCheck("positive", positiveCheck);
        return this;
    }

    public NumberSchema range(int min, int max) {
        final Predicate<Integer> rangeCheck = n -> n != null && n >= min && n <= max;
        addCheck("range", rangeCheck);
        return this;
    }
}
