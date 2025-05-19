package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    private final Map<String, BaseSchema<?>> shapeValidators = new HashMap<>();

    @Override
    public MapSchema required() {
        Predicate<Map<String, Object>> requiredCheck = value -> value != null;
        addCheck("required", requiredCheck);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<String, Object>> sizeCheck = value -> value != null && value.size() == size;
        addCheck("sizeof", sizeCheck);
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> validators) {
        shapeValidators.clear();
        shapeValidators.putAll(validators);

        Predicate<Map<String, Object>> shapeCheck = map -> {
            if (map == null) {
                return true; // skip shape check if map is null
            }
            return shapeValidators.entrySet().stream()
                    .allMatch(entry -> {
                        String key = entry.getKey();
                        BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue(); // safe cast
                        Object value = map.get(key);
                        return schema.isValid(value);
                    });
        };

        addCheck("shape", shapeCheck);
    }
}
