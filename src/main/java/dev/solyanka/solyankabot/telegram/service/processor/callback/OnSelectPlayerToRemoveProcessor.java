package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.data.ChatContext;
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
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OnSelectPlayerToRemoveProcessor implements CallbackProcessor {
    private final BotStateManager botStateManager;
    private final ChatContextManager chatContextManager;
    private final QuizService quizService;

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        final var callbackId = callbackQuery.getId();
        var data = callbackQuery.getData();

        var ctx = chatContextManager.getFullContext(chatId);
        validateIntegrity(ctx);

        var quizId = ctx.getValue(ContextKey.QUIZ_TO_LEAVE.name());
        var playerId = ctx.getValue(ContextKey.PLAYER_TO_REMOVE_NAME.name());
        quizService.removePlayer(quizId, playerId);

        botStateManager.dropState(chatId);
        return new SendMessage(chatId, BotMessage.LEAVING_FINISHED.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_PLAYER_STEP3_CHOOSING_PLAYER.equals(state);
    }

    private void validateIntegrity(ChatContext chatContext) {
        var errors = new ArrayList<String>();
        List.of(ContextKey.PLAYER_TO_REMOVE_NAME, ContextKey.QUIZ_TO_LEAVE).forEach(key -> {
            if (Objects.isNull(chatContext.getValue(key.name()))) {
                errors.add(key.name());
            }
        });
        if (!errors.isEmpty()) {
            throw new RuntimeException("Не удалось удалить участника! Не хватает данных: %s.".formatted(
                    String.join(", ", errors)
            ));
        }
    }
}
