package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class OnSelectQuizToRemoveGuest implements CallbackProcessor {

    @Override
    public BotApiMethod<?> answerCallback(CallbackQuery callbackQuery) {
        return null;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_GUEST_STEP2_CHOOSING_QUIZ.equals(state);
    }
}
