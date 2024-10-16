package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.data.ChatContextRepository;
import dev.solyanka.solyankabot.data.ChatContextValue;
import dev.solyanka.solyankabot.data.ChatContextValueRepository;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import dev.solyanka.solyankabot.telegram.model.ContextModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ChatContextManagerImpl implements ChatContextManager {
    private final ChatContextRepository repository;
    private final ChatContextValueRepository valueRepository;

    @Override
    public void clearContext(String chatId) {
        repository.deleteAllByChatId(chatId);
    }

    @Override
    public ContextModel getFullContext(String chatId) {
        var context = repository.findFirstByChatId(chatId);
        if (context.isEmpty()) {
            return new ContextModel(chatId, new HashMap<ContextKey, String>());
        }
        var contextEntity = context.get();
        var result = ContextModel.of(contextEntity);
        valueRepository.findAllByChatContext(contextEntity)
                .forEach(contextValue -> result.putValue(contextValue.getKey(), contextValue.getValue()));
        return result;
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
        var context = ensureContext(chatId);
        var contextValue = valueRepository.findFirstByChatContextAndKey(context, key)
                .orElseGet(() -> {
                    var res = new ChatContextValue();
                    res.setChatContext(context);
                    res.setKey(key);
                    return res;
                });
        contextValue.setValue(value);
        valueRepository.save(contextValue);
    }

    private ChatContext ensureContext(String chatId) {
        var contextEntityOptional = repository.findFirstByChatId(chatId);
        ChatContext entity;
        if (contextEntityOptional.isEmpty()) {
            entity = new ChatContext();
            entity.setChatId(chatId);
            repository.save(entity);
        } else {
            entity = contextEntityOptional.get();
        }
        return entity;
    }
}
