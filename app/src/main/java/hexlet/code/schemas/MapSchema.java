package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        super.required();
        addCheck("required_map", value -> value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addCheck("shape", map -> {
            if (map == null) {
                return true;
            }
            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                if (!schema.isValidRaw(map.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
