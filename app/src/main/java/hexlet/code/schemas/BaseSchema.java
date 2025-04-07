package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean isRequired = false;

    public boolean isValid(Object value) {
        // If not required and value is null — consider it valid
        if (!isRequired && value == null) {
            return true;
        }

        try {
            @SuppressWarnings("unchecked")
            T castedValue = (T) value;

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
        return true;
    }

    protected BaseSchema<T> addCheck(Predicate<T> predicate) {
        checks.add(predicate);
        return this;
    }

    protected void setRequired(boolean required) {
        this.isRequired = required;
    }

    protected boolean isRequired() {
        return isRequired;
    }
}
