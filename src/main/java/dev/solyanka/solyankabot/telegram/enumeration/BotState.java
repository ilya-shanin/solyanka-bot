package dev.solyanka.solyankabot.telegram.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BotState {
    START("start"),
    MAIN_MENU("main_menu"),

    // viewing details
    CHOOSING_QUIZ_FOR_DETAILS("choose_for_details"),

    // adding a new quiz
    WAITING_FOR_A_QUIZ_NAME_TO_ADD("new_quiz_name"),
    WAITING_FOR_A_QUIZ_LOCATION("new_quiz_location"),
    WAITING_FOR_A_QUIZ_DATETIME("new_quiz_datetime"),
    WAITING_FOR_A_QUIZ_COST("new_quiz_cost"),

    // deleting a quiz
    CHOOSING_QUIZ_TO_DELETE("choosing_quiz_to_delete"),

    // adding a new participant
    CHOOSING_QUIZ_TO_PARTICIPATE("choosing_quiz_to_participate"),
    WAITING_FOR_THE_PARTICIPANT_NAME("new_participant_name"),

    // deleting a new participant
    CHOOSING_A_QUIZ_TO_LEAVE("choosing_quiz_to_leave"),
    CHOOSING_A_PARTICIPANT_TO_REMOVE("choosing_participant_to_remove");

    private final String state;

    public static BotState resolve(String state) {
        return Arrays.stream(BotState.values())
                .filter(botState -> botState.getState().equals(state))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("State not found: %s".formatted(state)));
    }
}
