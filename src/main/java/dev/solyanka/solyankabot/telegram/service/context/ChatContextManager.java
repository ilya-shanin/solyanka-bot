package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContext;

public interface ChatContextManager {

    void clearContext(String chatId);

    ChatContext getFullContext(String chatId);

    void updateContext(ChatContext chatContext);

    boolean hasContext(String chatId);

}
