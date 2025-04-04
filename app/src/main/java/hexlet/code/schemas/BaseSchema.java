package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();

    public boolean isValid(Object value) {
        try {
            T castedValue = (T) value;

            // Allow schema-specific early filtering
            if (!customPreValidation(castedValue)) {
                return false;
            }

            for (Predicate<T> check : checks) {
                if (!check.test(castedValue)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    protected boolean customPreValidation(T value) {
        return true; // By default, no extra logic
    }

    protected void addCheck(Predicate<T> predicate) {
        checks.add(predicate);
    }
}
