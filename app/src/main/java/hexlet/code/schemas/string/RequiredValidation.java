package hexlet.code.schemas.string;

public class RequiredValidation implements ValidationStrategy<String> {
    @Override
    public boolean validate(String value) {
        return value != null && !value.isEmpty();
    }
}
