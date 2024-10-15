package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.data.ChatContextRepository;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatContextManagerImpl implements ChatContextManager {
    private final ChatContextRepository repository;

    @Override
    public void clearContext(String chatId) {
        repository.deleteById(chatId);
    }

    @Override
    public ChatContext getFullContext(String chatId) {
        return repository.findById(chatId).orElseGet(ChatContext::new);
    }

    @Override
    public void updateContext(ChatContext chatContext) {
        repository.save(chatContext);
    }

    @Override
    public boolean hasContext(String chatId) {
        return repository.existsById(chatId);
    }

    @Override
    public void addValue(String chatId, ContextKey key, String value) {
        var context = getFullContext(chatId);
//        context.putValue(key, value);
    }
}
