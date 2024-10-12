package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;

/**
 * Сервис для управления состоянием чата
 */
public interface BotStateManager {

    /**
     * Получить текущее состояние чата
     * @param chatId
     * @return
     */
    BotState getChatState(String chatId);

    /**
     * Обновить состояние чата
     * @param chatId
     * @param botState
     */
    void updateState(String chatId, BotState botState);

    /**
     * Сброс состояния
     * @param chatId
     */
    void dropState(String chatId);

}
