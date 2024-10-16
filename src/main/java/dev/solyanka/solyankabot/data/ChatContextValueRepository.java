package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatContextValueRepository extends CrudRepository<ChatContextValue, Long> {
    List<ChatContextValue> findAllByChatContext(ChatContext chatContext);
    Optional<ChatContextValue> findFirstByChatContextAndKey(ChatContext chatContext, ContextKey key);
}
