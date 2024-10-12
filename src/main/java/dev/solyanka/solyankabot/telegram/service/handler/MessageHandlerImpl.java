package dev.solyanka.solyankabot.telegram.service.handler;

import dev.solyanka.solyankabot.exceptions.CommandNotFoundException;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.processor.message.MessageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {

    private final List<MessageProcessor> processors;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> answerMessage(Message message) {
        var chatId = message.getChatId().toString();
        var inputText = message.getText();

        if (Objects.isNull(inputText)) {
            throw new IllegalArgumentException();
        }

        if ("/start".equals(inputText)) {
            botStateManager.dropState(chatId); // restart bot
        }
        var currentState = botStateManager.getChatState(chatId);

        try {
            var processor = resolveProcessor(currentState);
            return processor.processMessage(chatId, inputText);
        } catch (CommandNotFoundException e) {
            return new SendMessage(chatId, BotMessage.COMMAND_NOT_RECOGNIZED.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessage.UNEXPECTED_ERROR_MESSAGE.getMessage());
        }
    }

    private MessageProcessor resolveProcessor(BotState state) {
        return processors.stream()
                .filter(messageProcessor -> messageProcessor.supports(state))
                .findFirst()
                .orElseThrow(CommandNotFoundException::new);
    }
}
