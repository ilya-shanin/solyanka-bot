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
    VIEW_GAMES_STEP1_REQUEST_FOR_LIST("request_for_quiz_list_to_view"),
    VIEW_GAMES_STEP2_CHOOSE_FOR_DETAILS("choose_for_details"),

    // adding a new quiz
    ADDING_QUIZ_STEP1_REQUEST_NAME("request_new_quiz_name"),
    ADDING_QUIZ_STEP2_NAME_INPUT("new_quiz_name"),
    ADDING_QUIZ_STEP3_LOCATION_INPUT("new_quiz_location"),
    ADDING_QUIZ_STEP4_DATETIME_INPUT("new_quiz_datetime"),
    ADDING_QUIZ_STEP5_COST_INPUT("new_quiz_cost"),
    ADDING_QUIZ_STEP6_MAX_PLAYERS_INPUT("new_quiz_max_players"),

    // deleting a quiz
    DELETING_QUIZ_STEP1_REQUEST_SELECTION("request_selection_to_delete_quiz"),
    DELETING_QUIZ_STEP2_CHOOSING_QUIZ("choosing_quiz_to_delete"),

    // adding a new participant
    ADDING_PLAYER_STEP1_REQUEST_SELECTION("request_selection_to_add_player"),
    ADDING_PLAYER_STEP2_CHOOSING_QUIZ("choosing_quiz_to_participate"),

    // deleting a new participant
    REMOVING_PLAYER_STEP1_REQUEST_SELECTION("request_selection_to_delete_player"),
    REMOVING_PLAYER_STEP2_CHOOSING_QUIZ("choosing_quiz_to_leave");

    private final String state;

    public static BotState resolve(String state) {
        return Arrays.stream(BotState.values())
                .filter(botState -> botState.getState().equals(state))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("State not found: %s".formatted(state)));
    }
}
