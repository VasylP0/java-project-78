package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private final Map<K, BaseSchema<V>> shapeSchemas = new HashMap<>();

    public MapSchema<K, V> required() {
        strategies.put("required", value -> value != null);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        strategies.put("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        shapeSchemas.putAll(schemas);
        strategies.put("shape", this::validateShape);
        return this;
    }

    private boolean validateShape(Map<K, V> map) {
        for (Map.Entry<K, BaseSchema<V>> entry : shapeSchemas.entrySet()) {
            K key = entry.getKey();
            V value = map.get(key);

            BaseSchema<V> schema = entry.getValue();
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
