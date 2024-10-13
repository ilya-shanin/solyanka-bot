package dev.solyanka.solyankabot.exceptions;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String reason) {
        super("Обнаружена ошибка во введеных данных: %s%sПопробуйте еще раз.".formatted(
                reason,
                System.lineSeparator()
        ));
    }
}
