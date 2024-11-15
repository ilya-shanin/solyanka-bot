package dev.solyanka.solyankabot.telegram.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
public enum BotState {
    START,

    // viewing details
    VIEW_GAMES_STEP1_REQUEST_FOR_LIST,
    VIEW_GAMES_STEP2_CHOOSING_FOR_DETAILS,

    // adding a new quiz
    ADDING_QUIZ_STEP1_REQUEST_NAME,
    ADDING_QUIZ_STEP2_NAME_INPUT,
    ADDING_QUIZ_STEP3_LOCATION_INPUT,
    ADDING_QUIZ_STEP4_DATETIME_INPUT,
    ADDING_QUIZ_STEP5_COST_INPUT,
    ADDING_QUIZ_STEP6_MAX_PLAYERS_INPUT,

    // deleting a quiz
    DELETING_QUIZ_STEP1_REQUEST_SELECTION,
    DELETING_QUIZ_STEP2_CHOOSING_QUIZ,

    // adding a new participant
    ADDING_PLAYER_STEP1_REQUEST_SELECTION,
    ADDING_PLAYER_STEP2_CHOOSING_QUIZ,

    // deleting a participant
    REMOVING_PLAYER_STEP1_REQUEST_SELECTION,
    REMOVING_PLAYER_STEP2_CHOOSING_QUIZ,

    // adding a guest
    ADDING_GUEST_STEP1_REQUEST_SELECTION,
    ADDING_GUEST_STEP2_CHOOSING_QUIZ,

    // removing a guest
    REMOVING_GUEST_STEP1_REQUEST_SELECTION,
    REMOVING_GUEST_STEP2_CHOOSING_QUIZ;
}
