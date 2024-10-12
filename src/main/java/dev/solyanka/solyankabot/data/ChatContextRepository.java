package dev.solyanka.solyankabot.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatContextRepository extends CrudRepository<ChatContext, String> {

}
