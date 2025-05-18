package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private Map<K, BaseSchema<?>> shape = null;

    public MapSchema<K, V> required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        final Predicate<Map<K, V>> sizeCheck = map ->
                map != null && map.size() == size;
        addCheck("sizeof", sizeCheck);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<?>> schemas) {
        this.shape = schemas;

        final Predicate<Map<K, V>> shapeCheck = map -> {
            if (map == null) {
                return false;
            }
            for (Map.Entry<K, BaseSchema<?>> entry : shape.entrySet()) {
                final K key = entry.getKey();
                final BaseSchema<Object> validator = (BaseSchema<Object>) entry.getValue();
                final Object value = map.get(key);
                if (!validator.isValid(value)) {
                    return false;
                }
            }
            return true;
        };

        addCheck("shape", shapeCheck);
        return this;
    }
}
