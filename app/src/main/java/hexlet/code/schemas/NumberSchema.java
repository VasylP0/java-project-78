// NumberSchema.java
package hexlet.code.schemas;
public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        strategies.put("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        strategies.put("range", value -> value != null && value >= min && value <= max);
        return this;
    }

    @Override
    public NumberSchema required() {
        super.required();
        strategies.put("required", value -> value != null);
        return this;
    }
}
