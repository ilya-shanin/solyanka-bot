package dev.solyanka.solyankabot.telegram.service.handler;

import dev.solyanka.solyankabot.telegram.service.processor.callback.CallbackProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CallbackQueryHandlerImpl implements CallbackQueryHandler {
    private final List<CallbackProcessor> processors;

    @Override
    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        final var chatId = callbackQuery.getMessage().getChatId().toString();
        var data = callbackQuery.getData();

        return null;
    }

}
