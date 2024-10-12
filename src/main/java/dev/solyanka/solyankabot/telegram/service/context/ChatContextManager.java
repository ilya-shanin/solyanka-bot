package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKeys;

/**
 * Сервис для управления контекстом чата, сохранения промежуточных данных
 */
public interface ChatContextManager {

    /**
     * Очистка контекста для чата
     * @param chatId
     */
    void clearContext(String chatId);

    /**
     * Получить весь контекст чата
     * @param chatId
     * @return
     */
    ChatContext getFullContext(String chatId);

    /**
     * Перезаписать контекст
     * @param chatContext
     */
    void updateContext(ChatContext chatContext);

    /**
     * Проверить есть ли контекст для указанного чата
     * @param chatId
     * @return
     */
    boolean hasContext(String chatId);

    /**
     * Добавить новое значение в контекст чата по ключу
     * @param chatId
     * @param key
     * @param text
     */
    void addValue(String chatId, ContextKeys key, String text);
}
