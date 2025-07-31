package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema required() {
        super.required();
        addCheck(value -> value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> shape) {
        addCheck(value -> {
            if (value == null) {
                return true;
            }
            for (Map.Entry<String, BaseSchema<?>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!schema.isValidRaw(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
