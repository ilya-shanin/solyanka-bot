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
public class QuizToLeaveRequestProcessor implements MessageProcessor {
    private final QuizService quizService;
    private final InlineKeyboardService inlineKeyboardService;
    private final BotStateManager botStateManager;

    @Override
    public BotApiMethod<?> processMessage(Message message) {
        var chatId = message.getChatId().toString();
        final var tgId = message.getFrom().getId();
        var keyboard = inlineKeyboardService.buildInlineKeyboardOf(quizService.getActualGamesByPlayer(tgId, false));
        var answer = new SendMessage(chatId, BotMessage.SELECT_QUIZ_TO_LEAVE.getMessage());
        answer.setReplyMarkup(keyboard);
        answer.enableMarkdown(true);

        botStateManager.updateState(chatId, BotState.REMOVING_PLAYER_STEP2_CHOOSING_QUIZ);

        return answer;
    }

    @Override
    public boolean supports(BotState state) {
        return BotState.REMOVING_PLAYER_STEP1_REQUEST_SELECTION.equals(state);
    }
}
