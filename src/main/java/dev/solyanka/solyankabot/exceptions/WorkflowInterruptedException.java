package dev.solyanka.solyankabot.exceptions;

/**
 * Используйте при ошибках, требующих сброса бота до начального состояния
 */
public class WorkflowInterruptedException extends RuntimeException {
    public WorkflowInterruptedException(String reason) {
        super("Произошла ошибка во время выполнения: %s%sПожалуйста начните сначала.".formatted(
                reason,
                System.lineSeparator()
        ));
    }
}
