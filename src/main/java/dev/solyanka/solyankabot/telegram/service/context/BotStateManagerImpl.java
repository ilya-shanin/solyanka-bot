package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContextRepository;
import dev.solyanka.solyankabot.data.ChatState;
import dev.solyanka.solyankabot.data.ChatStateRepository;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BotStateManagerImpl implements BotStateManager {
    private final ChatStateRepository repository;
    private final ChatContextManager chatContextManager;

    public void dropState(String chatId) {
        repository.deleteById(chatId);
        chatContextManager.clearContext(chatId);
    }

    public BotState getChatState(String chatId) {
        return repository.findById(chatId)
                .map(ChatState::getState)
                .orElse(BotState.START);
    }

    public void updateState(String chatId, BotState botState) {
        var chatState = repository.findById(chatId).orElseGet(ChatState::new);
        if (Objects.isNull(chatState.getId())) {
            chatState.setChatId(chatId);
        }
        chatState.setState(botState);
        repository.save(chatState);
    }

}
