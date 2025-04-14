package hexlet.code.schemas.string;

public final class ContainsValidation implements ValidationStrategy<String> {

    private final String substring;

    public ContainsValidation(String str) {
        this.substring = str;
    }

    @Override
    public boolean validate(String value) {
        return value != null && value.contains(substring);
    }
}
