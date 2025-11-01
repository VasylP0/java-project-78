package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected final String isRequired = "required";

    protected final void addCheck(String name, Predicate<T> predicate) {
        checks.put(name, predicate);
    }

    /**
     * Marks this schema as required (non-null).
     * <p>
     * <strong>Subclass contract:</strong> If you override this method, you must call
     * {@code super.required()} first to keep the non-null invariant. After that you may
     * add additional constraints specific to the subtype via {@link #addCheck(String, java.util.function.Predicate)}.
     * Implementations should return {@code this} to preserve the fluent API.
     * </p>
     *
     * @return this schema with the required (non-null) constraint applied
     */
    public BaseSchema<T> required() {
        addCheck(isRequired, v -> v != null);
        return this;
    }

    public final boolean isValid(T t) {
        if (checks.containsKey(isRequired)) {
            if (!checks.get(isRequired).test(t)) {
                return false;
            }
        } else {
            if (Objects.isNull(t)) {
                return true;
            }
        }
        for (var check : checks.keySet()) {
            var predicate = checks.get(check);
            if (!predicate.test(t)) {
                return false;
            }
        }
        return true;
    }
}
