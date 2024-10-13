package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.service.QuizDetailsService;
import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class OnSelectForDetailsProcessor implements CallbackProcessor {
    private final BotStateManager botStateManager;
    private final QuizDetailsService detailsService;
    private final QuizService quizService;

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        var callbackData = callbackQuery.getData();

        var quiz = quizService.getQuizById(callbackData);
        var details = detailsService.getQuizDetailsFormatted(quiz);

        botStateManager.dropState(chatId);

        return new SendMessage(chatId, details);
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.VIEW_GAMES_STEP2_CHOOSING_FOR_DETAILS.equals(state);
    }
}
