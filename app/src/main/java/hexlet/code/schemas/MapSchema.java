package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private final Map<String, BaseSchema<?>> shapeValidators = new HashMap<>();

    @Override
    public MapSchema required() {
        final Predicate<Map<String, Object>> requiredCheck = Objects::nonNull;
        addCheck("required", requiredCheck);
        return this;
    }

    public MapSchema sizeof(int size) {
        final Predicate<Map<String, Object>> sizeCheck = map ->
                map != null && map.size() == size;
        addCheck("sizeof", sizeCheck);
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> validators) {
        shapeValidators.clear();
        shapeValidators.putAll(validators);

        final Predicate<Map<String, Object>> shapeCheck = map -> {
            if (map == null) {
                return true;
            }

            for (Map.Entry<String, BaseSchema<?>> entry : shapeValidators.entrySet()) {
                final String key = entry.getKey();
                final BaseSchema<?> schema = entry.getValue();
                final Object fieldValue = map.get(key);

                if (!isValidWithCast(schema, fieldValue)) {
                    return false;
                }
            }
            return true;
        };

        addCheck("shape", shapeCheck);
    }

    @SuppressWarnings("unchecked")
    private static <T> boolean isValidWithCast(BaseSchema<T> schema, Object value) {
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
