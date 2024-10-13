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
    SELECT_QUIZ_TO_DELETE("Какую игру вы хотите удалить из списка?"),
    DELETION_FINISHED("Игра удалена из списка."),

    // Participate quiz
    SELECT_QUIZ_TO_PARTICIPATE("Выберите игру, в которой будете участвовать."),
    ENTER_PARTICIPANT_NAME("Ваше имя?"),
    PARTICIPATION_CONFIRMED("Участие подтверждено!"),

    // Leaving a quiz
    SELECT_QUIZ_TO_LEAVE("Выберите игру, чтобы отменить участие."),
    SELECT_PARTICIPANT_TO_EXCLUDE("Выберите участника."),
    LEAVING_FINISHED("Участие в игре отменено."),

    // Guests
    SELECT_QUIZ_TO_DELETE_GUEST("Выберите игру чтобы удалить гостя."),
    SELECT_QUIZ_TO_ADD_GUEST("Выберите игру чтобы добавить гостя."),
    GUEST_ADDED("Новый участник добавлен."),
    GUEST_REMOVED("Участник удален."),

    // Warnings
    MAX_PLAYERS_REACHED("Достигнуто максимальное количество игроков!"),
    NO_GAMES_AVAILABLE("Нет доступных игр!"),
    NO_PLAYERS("Не найдено ни одного участника!"),
    ACTIVE_PARTICIPANTS_ON_QUIZ("На данную игру уже зарегистрированы участники!"),

    // Errors
    COMMAND_NOT_RECOGNIZED("Я не смог распознать вашу команду."),
    EXCEPTION_ILLEGAL_MESSAGE("Кажется, вы отправили сообщение в некорректном формате."),
    UNEXPECTED_ERROR_MESSAGE("Во время выполнения произошла неизвестная ошибка.");

    private final String message;
}
