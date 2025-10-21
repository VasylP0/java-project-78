package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Registers (or replaces by name) a validation predicate.
     * Subclasses should use this method to accumulate checks; rules do not overwrite
     * each other unless the same name is reused deliberately.
     *
     * @param name   unique name of the check (used as key in insertion order)
     * @param validate predicate that returns true for valid values
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Marks the schema as required. The base contract enforces non-null.
     * Subclasses may override this method to add additional "required" semantics
     * (e.g., non-empty string) but should call {@code super.required()} first.
     *
     * @return this schema instance to allow fluent chaining
     */
    public BaseSchema<T> required() {
        this.required = true;
        // Base behavior: required => not null
        addCheck("__required_not_null__", v -> v != null);
        return this;
    }

    /**
     * Validates the given value against all registered checks.
     * If the schema is not required and the value is null, validation passes.
     *
     * @param value value to validate
     * @return true if all registered checks pass; false otherwise
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
