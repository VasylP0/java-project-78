package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Base schema that accumulates validation predicates and evaluates them
 * with logical AND semantics. Schemas call {@link #addCheck(String, Predicate)}
 * to register rules and may call {@link #required()} to mark a value as mandatory.
 *
 * @param <T> value type validated by the schema
 */
public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    /**
     * Register a validation predicate under a unique name. If the same name
     * is reused, the predicate is replaced (useful for idempotent rules).
     *
     * @param name     unique key for this check (in insertion order)
     * @param validate predicate that returns {@code true} when {@code value} is valid
     */
    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Mark this schema as required. In the base contract, "required" means
     the value must be non-{@code null}. Subclasses may add stronger rules
     (e.g., non-empty string) but should call {@code super.required()} first.
     *
     * @return this schema instance for fluent chaining
     */
    public BaseSchema<T> required() {
        this.required = true;
        // Base behavior: required ⇒ not null
        addCheck("__required_not_null__", v -> v != null);
        return this;
    }

    /**
     * Validate the given value against all registered checks.
     * If the schema is not required and {@code value} is {@code null}, validation passes.
     *
     * @param value value to validate (may be {@code null} unless required)
     * @return {@code true} if all checks pass; {@code false} otherwise
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
