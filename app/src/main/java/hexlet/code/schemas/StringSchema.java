package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        final Predicate<String> requiredCheck = value -> value != null && !value.isEmpty();
        addCheck("required", requiredCheck);
        return this;
    }

    public StringSchema minLength(int length) {
        final Predicate<String> minLengthCheck = value ->
                value != null && value.length() >= length;
        addCheck("minLength", minLengthCheck);
        return this;
    }

    public StringSchema contains(String substring) {
        final Predicate<String> containsCheck = value ->
                value != null && value.contains(substring);
        addCheck("contains", containsCheck);
        return this;
    }
}
