// File: src/main/java/hexlet/code/schemas/NumberSchema.java
package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positiveCheck = value ->
                value == null || value > 0;
        addCheck("positive", positiveCheck);
        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> rangeCheck = value ->
                value != null && value >= min && value <= max;
        addCheck("range", rangeCheck);
        return this;
    }
}
