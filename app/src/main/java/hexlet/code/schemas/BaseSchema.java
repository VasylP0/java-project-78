package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean isRequired = false;

    // ✅ Made non-final so it can be overridden in subclasses like NumberSchema
    public boolean isValid(T value) {
        if (!isRequired && (value == null || value.toString().isEmpty())) {
            return true;
        }

        if (isRequired && (value == null || value.toString().isEmpty())) {
            return false;
        }

        for (Predicate<T> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }

    // ✅ Leave this final to protect base behavior
    protected final void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    // ✅ Leave this final — used in MapSchema shape logic
    @SuppressWarnings("unchecked")
    public final boolean isValidRaw(Object value) {
        return isValid((T) value);
    }
}
