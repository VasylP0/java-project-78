package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean notEmpty = false;
    private boolean shouldBePositive = false;
    private boolean shouldBeInRange = false;
    private int rangeMin;
    private int rangeMax;

    public NumberSchema required() {
        notEmpty = true;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (value == null) {
            return !notEmpty;
        }

        if (shouldBePositive && value <= 0) {
            return false;
        }

        if (shouldBeInRange && (value < rangeMin || value > rangeMax)) {
            return false;
        }

        return true;
    }

    public NumberSchema positive() {
        shouldBePositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        this.shouldBeInRange = true;
        return this;
    }
}
