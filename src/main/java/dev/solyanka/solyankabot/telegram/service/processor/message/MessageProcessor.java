package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import jakarta.transaction.Transactional;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageProcessor {

    BotApiMethod<?> processMessage(Message message);

    boolean supports(BotState state);

}
