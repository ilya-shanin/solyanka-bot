package dev.solyanka.solyankabot.telegram.service.handler;

import dev.solyanka.solyankabot.exceptions.ProcessorNotFoundException;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.processor.message.MessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Objects;

import static dev.solyanka.solyankabot.telegram.enumeration.ButtonName.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageHandlerImpl implements MessageHandler {

    private final List<MessageProcessor> processors;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> answerMessage(Message message) {
        var chatId = message.getChatId().toString();
        var inputText = message.getText();

        log.info("Processing message query for chat {}, user {}", chatId, message.getFrom().getUserName());

        if (Objects.isNull(inputText)) {
            throw new IllegalArgumentException();
        }

        if ("/start".equals(inputText) || "/stop".equals(inputText)) {
            botStateManager.dropState(chatId);
        } else if (ADD_GAME.getText().equals(inputText)) {
            botStateManager.updateState(chatId, BotState.ADDING_QUIZ_STEP1_REQUEST_NAME);
        } else if (VIEW_GAMES.getText().equals(inputText)) {
            botStateManager.updateState(chatId, BotState.VIEW_GAMES_STEP1_REQUEST_FOR_LIST);
        } else if (DELETE_GAME.getText().equals(inputText)) {
            botStateManager.updateState(chatId, BotState.DELETING_QUIZ_STEP1_REQUEST_SELECTION);
        } else if (ADD_PARTICIPANT.getText().equals(inputText)) {
            botStateManager.updateState(chatId, BotState.ADDING_PLAYER_STEP1_REQUEST_SELECTION);
        } else if (DELETE_PARTICIPANT.getText().equals(inputText)) {
            botStateManager.updateState(chatId, BotState.REMOVING_PLAYER_STEP1_REQUEST_SELECTION);
        }

        var currentState = botStateManager.getChatState(chatId);

        try {
            var processor = resolveProcessor(currentState);
            return processor.processMessage(message);
        } catch (ProcessorNotFoundException e) {
            return new SendMessage(chatId, BotMessage.COMMAND_NOT_RECOGNIZED.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessage.UNEXPECTED_ERROR_MESSAGE.getMessage());
        }
    }

    private MessageProcessor resolveProcessor(BotState state) {
        return processors.stream()
                .filter(messageProcessor -> messageProcessor.supports(state))
                .findFirst()
                .orElseThrow(ProcessorNotFoundException::new);
    }
}