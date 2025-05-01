package hexlet.code.schemas;

import hexlet.code.strategies.ValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    private Map<K, BaseSchema<?>> shape = new HashMap<>();

    public MapSchema<K, V> required() {
        addStrategy("required", value -> value != null);
        return this;
    }

    public MapSchema<K, V> sizeof(int expectedSize) {
        addStrategy("sizeof", value -> value != null && value.size() == expectedSize);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<?>> schemas) {
        shape = schemas;
        addStrategy("shape", map -> {
            if (map == null) {
                return false;
            }
            for (Map.Entry<K, BaseSchema<?>> entry : shape.entrySet()) {
                final K key = entry.getKey();
                final Object value = map.get(key);
                final BaseSchema<?> schema = entry.getValue();
                final BaseSchema<Object> typedSchema = (BaseSchema<Object>) schema;
                if (!typedSchema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
