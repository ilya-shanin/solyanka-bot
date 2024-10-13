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

        var quiz = quizService.getQuizById(callbackData);
        var players = quizService.getPlayersByGame(quiz);

        if (players.isEmpty()) {
            var answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackId);
            answer.setText(BotMessage.NO_PLAYERS.getMessage());
            answer.setShowAlert(true);
            return answer;
        }

        chatContextManager.addValue(chatId, ContextKey.QUIZ_TO_LEAVE, callbackData);
        botStateManager.updateState(chatId, BotState.REMOVING_PLAYER_STEP3_CHOOSING_PLAYER);

        var message = new SendMessage(chatId, BotMessage.SELECT_PARTICIPANT_TO_EXCLUDE.getMessage());
        message.setReplyMarkup(inlineKeyboardService.buildInlineKeyboardOf(players));
        message.enableMarkdown(true);
        return message;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_PLAYER_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
