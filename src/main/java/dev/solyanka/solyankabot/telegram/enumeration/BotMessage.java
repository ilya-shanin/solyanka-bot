package dev.solyanka.solyankabot.telegram.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotMessage {

    // Help
    HELP("This is a help message template (WIP)"),

    // Viewing details
    SELECT_FOR_DETAILS("Выберите игру для просмотра информации."),

    // Adding a new quiz
    ENTER_QUIZ_NAME("Как называется игра?"),
    ENTER_QUIZ_LOCATION("Где будет проходить квиз?"),
    ENTER_QUIZ_DATETIME("Во сколько собираемся? Дата и время в формате dd-MM-yyyy HH:mm"),
    ENTER_QUIZ_COST("Какая информация о стоимости?"),
    ENTER_QUIZ_MAX_PLAYERS("Сколько человек максимально может быть в команде?"),
    QUIZ_CREATION_FINISHED("Игра успешно добавлена в список!"),

    // Removing a quiz
    CHOOSE_QUIZ_TO_DELETE("Какую игру вы хотите удалить из списка?"),
    DELETION_FINISH("Игра удалена из списка."),

    // Participate quiz
    CHOOSE_QUIZ_TO_PARTICIPATE("Выберите игру, в которой будете участвовать."),
    ENTER_PARTICIPANT_NAME("Ваше имя?"),
    PARTICIPATION_CONFIRMED("Участие подтверждено!"),

    // Leaving a quiz
    CHOOSE_QUIZ_TO_LEAVE("Выберите игру, чтобы отменить участие."),
    CHOOSE_PARTICIPANT_TO_EXCLUDE("Выберите участника."),

    // Guests
    ENTER_GUESTS_COUNT("Введите количество гостей"),

    // Warnings
    MAX_PLAYERS_REACHED("Достигнуто максимальное количество игроков!"),
    NO_GAMES_AVAILABLE("Нет доступных игр!"),
    NO_PLAYERS("Не найдено ни одного участника!"),
    ACTIVE_PARTICIPANTS_ON_QUIZ("На данную игру уже зарегистрированы участники!"),

    // Errors
    COMMAND_NOT_RECOGNIZED("Я не смог распознать вашу команду."),
    EXCEPTION_ILLEGAL_MESSAGE("Кажется, вы отправили некорректное сообщение."),
    UNEXPECTED_ERROR_MESSAGE("Во время выполнения произошла неизвестная ошибка.");

    private final String message;
}
