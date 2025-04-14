package hexlet.code.schemas;

@FunctionalInterface
public interface ValidationStrategy<T> {
    boolean validate(T value);
}
