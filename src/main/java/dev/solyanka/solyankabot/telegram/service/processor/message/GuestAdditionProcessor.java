package dev.solyanka.solyankabot.telegram.service.processor.message;

import dev.solyanka.solyankabot.service.QuizService;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.keyboard.InlineKeyboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class GuestAdditionProcessor implements MessageProcessor {
    private final BotStateManager botStateManager;
    private final QuizService quizService;
    private final InlineKeyboardService inlineKeyboardService;

    @Override
    public BotApiMethod<?> processMessage(Message message) {
        var chatId = message.getChatId().toString();
        var keyboard = inlineKeyboardService.buildInlineKeyboardOf(quizService.getActualGames());
        var answer = new SendMessage(chatId, BotMessage.SELECT_QUIZ_TO_ADD_GUEST.getMessage());
        answer.setReplyMarkup(keyboard);
        answer.enableMarkdown(true);

        botStateManager.updateState(chatId, BotState.ADDING_GUEST_STEP2_CHOOSING_QUIZ);
        return answer;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.ADDING_GUEST_STEP1_REQUEST_SELECTION.equals(state);
    }
}
