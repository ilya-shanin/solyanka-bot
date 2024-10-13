package dev.solyanka.solyankabot.exceptions;

/**
 * Используйте чтобы обработать некорректно введеные данные, требующие повторного ввода
 */
public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String reason) {
        super("Обнаружена ошибка во введеных данных: %s%sПопробуйте еще раз.".formatted(
                reason,
                System.lineSeparator()
        ));
    }
}
