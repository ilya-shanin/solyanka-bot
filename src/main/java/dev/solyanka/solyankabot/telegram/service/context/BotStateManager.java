package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;

public interface BotStateManager {

    BotState getChatState(String chatId);

    void updateState(String chatId, BotState botState);

    void dropState(String chatId);

}
