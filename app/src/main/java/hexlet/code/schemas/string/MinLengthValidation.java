package hexlet.code.schemas.string;

public final class MinLengthValidation implements ValidationStrategy<String> {

    private final int minLength;

    public MinLengthValidation(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String value) {
        return value != null && value.length() >= minLength;
    }
}
