package hexlet.code.schemas.map;

import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private final Map<K, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema<K, V> required() {
        strategies.put("required", value -> value != null);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        strategies.put("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<?>> schemas) {
        shapeSchemas.putAll(schemas);
        strategies.put("shape", this::validateShape);
        return this;
    }

    @SuppressWarnings("unchecked")
    private boolean validateShape(Map<K, V> map) {
        for (Map.Entry<K, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            final K key = entry.getKey(); // ✅ made final
            final Object value = map.get(key); // ✅ made final

            final BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue(); // ✅ made final

            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
