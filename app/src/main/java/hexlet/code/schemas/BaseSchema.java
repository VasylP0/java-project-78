package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Registers a validation check under a given name.
     * Later checks do not overwrite previous ones unless the same name is reused.
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Marks the schema as required (non-null).
     * Subclasses may override to strengthen semantics (e.g., non-empty string).
     */
    public BaseSchema<T> required() {
        this.required = true;
        addCheck("__required_not_null__", v -> v != null);
        return this;
    }

    /**
     * Performs validation according to all accumulated checks.
     * If not required and the value is null, validation passes.
     */
    public final boolean isValid(T value) {
        if (!required && value == null) {
            return true;
        }
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
