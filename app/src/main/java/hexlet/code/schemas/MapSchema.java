package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, ?>> {

    @Override
    public MapSchema required() {
        super.required(); // not-null (empty map still ok unless sizeof added)
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof_" + size, v -> v == null || v.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> fieldSchemas) {
        addCheck("shape", v -> {
            if (v == null) {
                return true; // handled by required()
            }
            for (var e : fieldSchemas.entrySet()) {
                var key = e.getKey();
                var schema = e.getValue();
                Object raw = v.get(key);
                String val = (raw == null) ? null : raw.toString();
                if (!schema.isValid(val)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
