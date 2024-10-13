package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackProcessor {

    BotApiMethod<?> answerCallback(CallbackQuery callbackQuery);

    boolean supports(BotState state);

}
