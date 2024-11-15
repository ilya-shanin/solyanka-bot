package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.exceptions.IncorrectInputException;
import dev.solyanka.solyankabot.service.QuizService;
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
public class QuizCreationMaxPlayersProcessor implements MessageProcessor {
    private final ChatContextManager chatContextManager;
    private final BotStateManager botStateManager;
    private final QuizService quizService;

    @Override
    public BotApiMethod<?> processMessage(Message message) {
        var chatId = message.getChatId().toString();
        var text = message.getText();

        validate(text);
        chatContextManager.addValue(chatId, ContextKey.QUIZ_MAX_PLAYERS, text);

        var created = quizService.createGame(chatContextManager.getFullContext(chatId));

        botStateManager.dropState(chatId);
        return new SendMessage(chatId, BotMessage.QUIZ_CREATION_FINISHED.getMessage().formatted(created.getName()));
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.ADDING_QUIZ_STEP6_MAX_PLAYERS_INPUT.equals(state);
    }

    private void validate(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            throw new IncorrectInputException("Укажите максимальное количество участников!");
        }
    }
}
