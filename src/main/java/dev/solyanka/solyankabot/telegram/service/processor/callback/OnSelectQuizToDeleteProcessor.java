package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.service.QuizValidationService;
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
public class OnSelectQuizToDeleteProcessor implements CallbackProcessor {
    private final QuizService quizService;
    private final QuizValidationService quizValidationService;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        final var callbackId = callbackQuery.getId();
        var callbackData = callbackQuery.getData();

        var quiz = quizService.getQuizById(callbackData);
        var errors = quizValidationService.validateDeletion(quiz);
        if (!errors.isEmpty()) {
            var answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackId);
            answer.setText(String.join(", ", errors));
            answer.setShowAlert(true);
            return answer;
        }

        quizService.delete(quiz);

        botStateManager.dropState(chatId);
        return new SendMessage(chatId, BotMessage.DELETION_FINISHED.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.DELETING_QUIZ_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
