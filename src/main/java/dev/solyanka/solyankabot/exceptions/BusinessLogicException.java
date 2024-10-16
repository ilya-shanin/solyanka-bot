package dev.solyanka.solyankabot.exceptions;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String reason) {
        super("Невозможно выполнить операцию: %s%s".formatted(
                reason,
                System.lineSeparator()
        ));
    }
}
