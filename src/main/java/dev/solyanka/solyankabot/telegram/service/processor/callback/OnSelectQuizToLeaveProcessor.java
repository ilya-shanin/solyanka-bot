package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.context.ChatContextManager;
import dev.solyanka.solyankabot.telegram.service.keyboard.InlineKeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class OnSelectQuizToLeaveProcessor implements CallbackProcessor {
    private final QuizService quizService;
    private final BotStateManager botStateManager;
    private final ChatContextManager chatContextManager;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        final var callbackId = callbackQuery.getId();
        var callbackData = callbackQuery.getData();

        var user = callbackQuery.getFrom();
        var quiz = quizService.getQuizById(callbackData);
        var players = quizService.getPlayersByGame(quiz);

        if (players.isEmpty()) {
            var answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackId);
            answer.setText(BotMessage.NO_PLAYERS.getMessage());
            answer.setShowAlert(true);
            return answer;
        }

        quizService.removePlayerByTelegramId(quiz, user.getId());
        botStateManager.dropState(chatId);

        return new SendMessage(chatId, BotMessage.LEAVING_FINISHED.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_PLAYER_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
