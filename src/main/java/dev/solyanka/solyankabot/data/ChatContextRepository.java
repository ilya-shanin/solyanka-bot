package dev.solyanka.solyankabot.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatContextRepository extends CrudRepository<ChatContext, String> {
    void deleteAllByChatId(String chatId);
    Optional<ChatContext> findFirstByChatId(String chatId);
}
