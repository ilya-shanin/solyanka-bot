package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.context.ChatContextManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizCreationLocationProcessor implements MessageProcessor {
    private final ChatContextManager chatContextManager;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> processMessage(Message message) {
        var chatId = message.getChatId().toString();
        var text = message.getText();

        validate(text);
        chatContextManager.addValue(chatId, ContextKey.QUIZ_LOCATION, text);

        botStateManager.updateState(chatId, BotState.ADDING_QUIZ_STEP4_DATETIME_INPUT);
        return new SendMessage(chatId, BotMessage.ENTER_QUIZ_DATETIME.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.ADDING_QUIZ_STEP3_LOCATION_INPUT.equals(state);
    }

    private void validate(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            throw new RuntimeException("Укажите информацию о месте проведения квиза!");
        }
    }
}
