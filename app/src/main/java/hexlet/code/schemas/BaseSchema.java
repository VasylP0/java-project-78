package hexlet.code.schemas;

import hexlet.code.strategies.ValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {

    protected final Map<String, ValidationStrategy<T>> strategies = new HashMap<>();

    public final boolean isValid(T dataToValidate) {
        return validateData(dataToValidate, strategies);
    }

    protected boolean validateData(T dataToValidate, Map<String, ValidationStrategy<T>> strategiesForData) {
        for (ValidationStrategy<T> strategy : strategiesForData.values()) {
            if (!strategy.validate(dataToValidate)) {
                return false;
            }
        }
        return true;
    }

    protected void addStrategy(String name, ValidationStrategy<T> strategy) {
        strategies.put(name, strategy);
    }
}
