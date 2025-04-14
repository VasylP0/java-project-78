package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.map.MapSchema;
import hexlet.code.schemas.numeric.NumberSchema;
import hexlet.code.schemas.string.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
