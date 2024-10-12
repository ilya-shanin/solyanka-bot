package dev.solyanka.solyankabot.telegram.service.handler;

import dev.solyanka.solyankabot.exceptions.ProcessorNotFoundException;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.processor.callback.CallbackProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CallbackQueryHandlerImpl implements CallbackQueryHandler {
    private final List<CallbackProcessor> processors;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        final var callbackId = callbackQuery.getId();
        var data = callbackQuery.getData();

        log.info("Processing callback query for chat {}, user {}", chatId, callbackQuery.getFrom().getUserName());

        var currentState = botStateManager.getChatState(chatId);

        try {
            var processor = resolveProcessor(currentState);
            return processor.answerCallback(chatId, callbackId, data);
        } catch (ProcessorNotFoundException e) {
            return new SendMessage(chatId, BotMessage.COMMAND_NOT_RECOGNIZED.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessage.UNEXPECTED_ERROR_MESSAGE.getMessage());
        }
    }

    private CallbackProcessor resolveProcessor(BotState state) {
        return processors.stream()
                .filter(callbackProcessor -> callbackProcessor.supports(state))
                .findFirst()
                .orElseThrow(ProcessorNotFoundException::new);
    }
}
