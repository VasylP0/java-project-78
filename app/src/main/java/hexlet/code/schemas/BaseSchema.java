// File: src/main/java/hexlet/code/schemas/BaseSchema.java
package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public final boolean isValid(T value) {
        return checks.values().stream().allMatch(p -> p.test(value));
    }

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public abstract BaseSchema<T> required();
}
