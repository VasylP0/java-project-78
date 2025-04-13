package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        setRequired(true);
        addCheck(value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value != null && value.size() == size);
        return this;
    }

    // ✅ Updated shape method with flexible generic parameter
    public void shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas); // copy to prevent mutation
        addCheck(this::validateShape);
    }

    private boolean validateShape(Map<?, ?> map) {
        for (var entry : shapeSchemas.entrySet()) {
            var key = entry.getKey();
            var schema = entry.getValue();

            Object value = map.get(key);

            // If key is missing, treat value as null
            if (!map.containsKey(key)) {
                value = null;
            }

            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }
}
