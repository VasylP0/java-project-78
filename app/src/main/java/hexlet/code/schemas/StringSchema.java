// ==== FILE: StringSchema.java ====
package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        final Predicate<String> minLengthCheck = s -> s != null && s.length() >= minLength;
        addCheck("minLength", minLengthCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        final Predicate<String> containsCheck = s -> s != null && s.contains(substring);
        addCheck("contains", containsCheck);
        return this;
    }
}
