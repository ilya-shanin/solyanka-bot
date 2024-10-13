package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class OnSelectQuizToRemoveGuest implements CallbackProcessor {
    private final QuizService quizService;
    private final BotStateManager botStateManager;

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

        quizService.removeGuestByTelegramId(quiz, user.getId());
        botStateManager.dropState(chatId);

        return new SendMessage(chatId, BotMessage.GUEST_REMOVED.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_GUEST_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
