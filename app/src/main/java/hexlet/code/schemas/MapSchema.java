package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean isRequired = false;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        isRequired = true;
        addCheck(value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(value -> value != null && value.size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchemas = schemas;
        addCheck(this::validateShape); // adds a custom check
    }

    private boolean validateShape(Map<?, ?> map) {
        for (var entry : shapeSchemas.entrySet()) {
            var key = entry.getKey();
            var schema = entry.getValue();

            // If the key is missing and required, fail
            if (!map.containsKey(key)) {
                if (schema instanceof StringSchema || schema instanceof NumberSchema || schema instanceof MapSchema) {
                    // Try validating `null`, will fail only if schema is `.required()`
                    if (!schema.isValid(null)) {
                        return false;
                    } else {
                        continue;
                    }
                }
            }

            // Validate the value if the key is present
            var value = map.get(key);
            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }



    @Override
    public boolean isValid(Object value) {
        if (!isRequired && value == null) {
            return true;
        }
        return super.isValid(value);
    }
}
