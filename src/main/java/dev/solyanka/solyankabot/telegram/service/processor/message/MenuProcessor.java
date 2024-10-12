package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.enumeration.ButtonName;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuProcessor implements MessageProcessor{
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> processMessage(String chatId, String messageText) {
        var button = ButtonName.resolve(messageText);
        switch (button) {
            case ADD_GAME -> ;
            case VIEW_GAMES -> ;
            case DELETE_GAME -> ;
            case ADD_PARTICIPANT -> ;
            case DELETE_PARTICIPANT -> ;
            default -> {
                log.error("Menu item not found");
                throw new RuntimeException("Menu item not found");
            }
        }
        return null;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.MAIN_MENU.equals(state);
    }

    private SendMessage initQuizCreation(String chatId) {
        botStateManager.updateState(chatId, BotState.WAITING_FOR_A_QUIZ_NAME_TO_ADD);

        return null;
    }

    private SendMessage initQuizDeletion(String chatId) {
        botStateManager.updateState(chatId, BotState.CHOOSING_QUIZ_TO_DELETE);

        // return quiz list

        return null;
    }

    private SendMessage viewAllGames(String chatId) {
        botStateManager.updateState(chatId, BotState.CHOOSING_QUIZ_FOR_DETAILS);

        // return quiz list

        return null;
    }

    private SendMessage initParticipantAddition(String chatId) {
        botStateManager.updateState(chatId, BotState.CHOOSING_QUIZ_TO_PARTICIPATE);

        // return quiz list
    }

    private SendMessage initParticipantDeletion(String chatId) {
        botStateManager.updateState(chatId, BotState.CHOOSING_A_QUIZ_TO_LEAVE);

        // return quiz list
    }

}
