package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isRequired = false;

    public NumberSchema required() {
        isRequired = true;
        addCheck(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(value -> value != null && value >= min && value <= max);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        if (!isRequired && value == null) {
            return true;
        }
        return super.isValid(value);
    }
}
