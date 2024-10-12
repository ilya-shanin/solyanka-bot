package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.keyboard.ReplyKeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class StartMessageProcessor implements MessageProcessor {

    private final ReplyKeyboardService replyKeyboardService;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> processMessage(String chatId, String messageText) {
        botStateManager.updateState(chatId, BotState.MAIN_MENU);

        var sendMessage = new SendMessage(chatId, BotMessage.HELP.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardService.getMainMenuKeyboard());
        return sendMessage;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.START.equals(state);
    }
}
