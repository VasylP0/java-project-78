package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A base schema class for creating validation rules.
 * Intended to be extended by specific schema types like StringSchema or NumberSchema.
 *
 * @param <T> the type of value to be validated
 */
public class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean isRequired = false;

    /**
     * Validates the input value against all the added checks.
     * Handles type casting and optional pre-validation steps.
     *
     * @param value the value to validate
     * @return true if all validations pass, false otherwise
     */
    public boolean isValid(Object value) {
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

    /**
     * Optional hook for subclasses to add pre-validation logic.
     *
     * @param value the value to pre-validate
     * @return true if pre-validation passes, false to skip further checks
     */
    protected boolean customPreValidation(T value) {
        return true;
    }

    /**
     * Adds a new check (predicate) to the validation list.
     *
     * @param predicate a predicate to validate the input
     * @return the current schema for chaining
     */
    protected BaseSchema<T> addCheck(Predicate<T> predicate) {
        checks.add(predicate);
        return this;
    }

    /**
     * Sets whether null values should be considered invalid.
     *
     * @param required true to mark the value as required
     */
    protected void setRequired(boolean required) {
        this.isRequired = required;
    }

    /**
     * Checks whether the schema is marked as required.
     *
     * @return true if required, false otherwise
     */
    protected boolean isRequired() {
        return isRequired;
    }

    /**
     * Removes all predicates that match the provided condition.
     * Intended for subclasses to remove redundant or outdated checks.
     *
     * @param condition a condition to match and remove predicates
     */
    protected void removeCheckIf(Predicate<Predicate<T>> condition) {
        checks.removeIf(condition);
    }
}
