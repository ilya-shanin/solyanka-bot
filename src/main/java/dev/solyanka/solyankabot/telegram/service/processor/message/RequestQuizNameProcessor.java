package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class RequestQuizNameProcessor implements MessageProcessor {
    @Override
    public BotApiMethod<?> processMessage(Message message) {
        var chatId = message.getChatId().toString();
        var sendMessage = new SendMessage(chatId, BotMessage.ENTER_QUIZ_NAME.getMessage());
        return sendMessage;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.ADDING_QUIZ_STEP1_REQUEST_NAME.equals(state);
    }
}
