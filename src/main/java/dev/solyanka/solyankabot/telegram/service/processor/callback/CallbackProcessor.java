package dev.solyanka.solyankabot.telegram.service.processor.callback;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface CallbackProcessor {

    BotApiMethod<?> answerCallback(String chatId, String callbackId, String callbackData);

    boolean supports(BotState state);

}
