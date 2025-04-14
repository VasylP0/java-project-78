package hexlet.code;

import hexlet.code.schemas.map.MapSchema;
import hexlet.code.schemas.string.StringSchema;
import hexlet.code.schemas.numeric.NumberSchema;

public final class Validator {

    public final StringSchema string() {
        return new StringSchema();
    }

    public final NumberSchema number() {
        return new NumberSchema();
    }

    public final MapSchema map() {
        return new MapSchema();
    }
}
