package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;
    private Integer minLength = null;
    private final List<String> mustContain = new ArrayList<>();

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        mustContain.add(substring);
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof String)) {
            return false;
        }

        if (isRequired && value.isEmpty()) {
            return false;
        }

        if (minLength != null && value.length() < minLength) {
            return false;
        }

        for (String substring : mustContain) {
            if (!value.contains(substring)) {
                return false;
            }
        }

        return true;
    }
}
