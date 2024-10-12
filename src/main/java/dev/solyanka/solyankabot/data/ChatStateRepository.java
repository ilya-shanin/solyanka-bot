package dev.solyanka.solyankabot.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatStateRepository extends CrudRepository<ChatState, String> {
}
