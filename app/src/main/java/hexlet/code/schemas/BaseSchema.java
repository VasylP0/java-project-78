package hexlet.code.schemas;

import hexlet.code.schemas.string.ValidationStrategy; // ✅ Correct import

import java.util.HashMap;
import java.util.Map;

/**
 * BaseSchema is an abstract class for defining validation schemas.
 * Subclasses must define their own validation strategies.
 *
 * @param <T> the type of data to validate
 */
public abstract class BaseSchema<T> {
    protected final Map<String, ValidationStrategy<T>> strategies = new HashMap<>();

    /**
     * Validates the input data against all stored strategies.
     *
     * @param dataToValidate the data to validate
     * @return true if all strategies pass, false otherwise
     */
    public boolean isValid(T dataToValidate) {
        if (dataToValidate == null) {
            return !strategies.containsKey("required");
        }

        for (ValidationStrategy<T> strategy : strategies.values()) {
            if (!strategy.validate(dataToValidate)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the map of validation strategies.
     * Intended for internal or advanced usage.
     *
     * @return the map of strategies
     */
    public Map<String, ValidationStrategy<T>> getStrategies() {
        return strategies;
    }
}
