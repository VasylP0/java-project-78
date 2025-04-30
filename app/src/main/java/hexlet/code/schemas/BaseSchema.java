// BaseSchema.java (updated)
package hexlet.code.schemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> strategies = new HashMap<>();
    private boolean isRequired = false;

    public boolean isValid(T data) {
        if (!isRequired && data == null) {
            return true;
        }
        if (isRequired && data == null) {
            return false;
        }
        return strategies.values().stream().allMatch(predicate -> predicate.test(data));
    }

    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }
}
