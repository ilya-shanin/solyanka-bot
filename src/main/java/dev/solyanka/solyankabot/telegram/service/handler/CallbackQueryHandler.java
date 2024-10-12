package dev.solyanka.solyankabot.telegram.service.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackQueryHandler {
    BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery);
}
