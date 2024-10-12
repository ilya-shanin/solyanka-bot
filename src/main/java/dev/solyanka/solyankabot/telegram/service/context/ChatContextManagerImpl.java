package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.data.ChatContextRepository;
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
        return repository.findById(chatId).orElse(null);
    }

    @Override
    public void updateContext(ChatContext chatContext) {
        repository.save(chatContext);
    }

    @Override
    public boolean hasContext(String chatId) {
        return repository.existsById(chatId);
    }
}
