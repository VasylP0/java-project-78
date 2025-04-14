package hexlet.code.schemas.string;

import hexlet.code.schemas.ValidationStrategy;

public class RequiredValidation implements ValidationStrategy<String> {
    @Override
    public boolean validate(String value) {
        return value != null && !value.isEmpty();
    }
}
