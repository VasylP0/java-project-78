package hexlet.code.schemas;
import hexlet.code.schemas.string.ValidationStrategy;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {
    protected final Map<String, ValidationStrategy<T>> strategies = new HashMap<>();

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

    public Map<String, ValidationStrategy<T>> getStrategies() {
        return strategies;
    }
}
