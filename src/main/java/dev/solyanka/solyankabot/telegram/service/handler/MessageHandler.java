package dev.solyanka.solyankabot.telegram.service.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {
    BotApiMethod<?> answerMessage(Message message);
}
