package dev.solyanka.solyankabot.telegram.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ButtonName {

    VIEW_GAMES("Зарегистрированные игры"),
    ADD_GAME("Добавить игру"),
    DELETE_GAME("Удалить игру"),
    ADD_PARTICIPANT("Участвовать"),
    DELETE_PARTICIPANT("Отменить участие"),
    ADD_GUEST("+ Гость"),
    REMOVE_GUEST("- Гость");

    private final String text;

    public static ButtonName resolve(String text) {
        return Arrays.stream(ButtonName.values())
                .filter(val -> val.getText().equals(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не удалось найти кнопку: %s".formatted(text)));
    }
}
