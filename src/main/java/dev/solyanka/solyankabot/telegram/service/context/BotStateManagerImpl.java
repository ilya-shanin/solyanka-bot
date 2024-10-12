package dev.solyanka.solyankabot.telegram.service.context;

import dev.solyanka.solyankabot.data.ChatContextRepository;
import dev.solyanka.solyankabot.data.ChatState;
import dev.solyanka.solyankabot.data.ChatStateRepository;
import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .map(chatState -> BotState.resolve(chatState.getState()))
                .orElse(BotState.START);
    }

    public void updateState(String chatId, BotState botState) {
        var chatStateOpt = repository.findById(chatId);
        if (chatStateOpt.isPresent()) {
            var chatState = chatStateOpt.get();
            chatState.setState(botState.getState());
            repository.save(chatState);
        } else {
            repository.save(new ChatState(chatId, botState.getState()));
        }
    }

}
