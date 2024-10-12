package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface MessageProcessor {

    BotApiMethod<?> processMessage(String chatId, String messageText);

    boolean supports(BotState state);

}
