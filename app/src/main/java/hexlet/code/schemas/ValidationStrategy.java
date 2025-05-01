package hexlet.code.strategies;

@FunctionalInterface
public interface ValidationStrategy<T> {
    boolean validate(T data);
}
