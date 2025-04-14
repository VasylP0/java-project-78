package hexlet.code.schemas.string;

public class ContainsValidation implements ValidationStrategy<String> {
    private final String substring;

    public ContainsValidation(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean validate(String value) {
        return value != null && value.contains(substring);
    }
}
