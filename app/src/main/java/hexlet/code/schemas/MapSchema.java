package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private final Map<String, BaseSchema<?>> shape = new HashMap<>();

    public MapSchema required() {
        strategies.put("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        strategies.put("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemaMap) {
        shape.putAll(schemaMap);
        strategies.put("shape", this::validateShape);
        return this;
    }

    private boolean validateShape(Map<String, Object> dataToValidate) {
        for (Map.Entry<String, BaseSchema<?>> entry : shape.entrySet()) {
            String key = entry.getKey();
            Object value = dataToValidate.get(key);

            @SuppressWarnings("unchecked")
            BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
