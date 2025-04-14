package hexlet.code.schemas.string;

@FunctionalInterface
public interface ValidationStrategy<T> {
    boolean validate(T value);
}
