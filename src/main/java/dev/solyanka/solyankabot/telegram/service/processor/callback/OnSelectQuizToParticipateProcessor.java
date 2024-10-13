package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.service.QuizValidationService;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class OnSelectQuizToParticipateProcessor implements CallbackProcessor {
    private final QuizService quizService;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        var callbackData = callbackQuery.getData();
        var user = callbackQuery.getFrom();

        var quiz = quizService.getQuizById(callbackData);

        quizService.addPlayer(quiz, UserConverter.fromUser(user));

        botStateManager.dropState(chatId);
        return new SendMessage(chatId, BotMessage.PARTICIPATION_CONFIRMED.getMessage());
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.ADDING_PLAYER_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
