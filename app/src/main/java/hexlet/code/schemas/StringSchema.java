// File: src/main/java/hexlet/code/schemas/StringSchema.java
package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> minLengthCheck = value ->
                value == null || value.length() >= length;
        addCheck("minLength", minLengthCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsCheck = value ->
                value == null || value.contains(substring);
        addCheck("contains", containsCheck);
        return this;
    }
}
