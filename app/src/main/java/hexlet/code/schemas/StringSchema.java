// StringSchema.java
package hexlet.code.schemas;
public class StringSchema extends BaseSchema<String> {
    public StringSchema contains(String substring) {
        strategies.put("contains", value -> value != null && value.contains(substring));
        return this;
    }

    public StringSchema minLength(int length) {
        strategies.put("minLength", value -> value != null && value.length() >= length);
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        strategies.put("required", value -> value != null && !value.isEmpty());
        return this;
    }
}
