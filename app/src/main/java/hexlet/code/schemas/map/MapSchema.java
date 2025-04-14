package hexlet.code.schemas.map;

import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    private final Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        strategies.put("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        strategies.put("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        shapeSchemas.putAll(schemas);
        strategies.put("shape", this::validateShape);
        return this;
    }

    @SuppressWarnings("unchecked")
    private boolean validateShape(Map<String, Object> map) {
        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            Object value = map.get(key);

            BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
