package dev.solyanka.solyankabot.configuration;

import dev.solyanka.solyankabot.telegram.service.context.BotStateManager;
import dev.solyanka.solyankabot.telegram.service.handler.CallbackQueryHandler;
import dev.solyanka.solyankabot.telegram.service.handler.MessageHandler;
import dev.solyanka.solyankabot.telegram.SolyankaBot;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class BotConfiguration {
    private final TelegramConfiguration telegramConfiguration;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfiguration.getWebhookPath())
                .build();
    }

    @Bean
    public SolyankaBot springWebhookBot(
            SetWebhook setWebhook,
            MessageHandler messageHandler,
            CallbackQueryHandler callbackQueryHandler,
            BotStateManager botStateManager
    ) {
        var bot = new SolyankaBot(setWebhook, messageHandler, callbackQueryHandler, botStateManager);
        bot.setBotPath(telegramConfiguration.getWebhookPath());
        bot.setBotUsername(telegramConfiguration.getBotName());
        bot.setBotToken(telegramConfiguration.getBotToken());
        return bot;
    }
}
