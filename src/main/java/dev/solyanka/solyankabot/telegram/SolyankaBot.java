package dev.solyanka.solyankabot.telegram;

import dev.solyanka.solyankabot.exceptions.BusinessLogicException;
import dev.solyanka.solyankabot.exceptions.IncorrectInputException;
import dev.solyanka.solyankabot.exceptions.WorkflowInterruptedException;
import dev.solyanka.solyankabot.telegram.enumeration.BotMessage;
import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.handler.CallbackQueryHandler;
import dev.solyanka.solyankabot.telegram.service.handler.MessageHandler;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.io.IOException;

@Getter
@Setter
@Slf4j
public class SolyankaBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private String botToken;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final BotStateManager botStateManager;

    public SolyankaBot(
            SetWebhook setWebhook,
            MessageHandler messageHandler,
            CallbackQueryHandler callbackQueryHandler,
            BotStateManager botStateManager
    ) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.botStateManager = botStateManager;
    }

    @Override
    @Transactional
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessage.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
        } catch (IncorrectInputException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    e.getMessage());
        } catch (WorkflowInterruptedException | BusinessLogicException e) {
            botStateManager.dropState(update.getMessage().getChatId().toString());
            return new SendMessage(update.getMessage().getChatId().toString(),
                    e.getMessage());
        } catch (Exception e) {
            botStateManager.dropState(update.getMessage().getChatId().toString());
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessage.UNEXPECTED_ERROR_MESSAGE.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            var callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                return messageHandler.answerMessage(update.getMessage());
            }
        }
        return null;
    }

}
