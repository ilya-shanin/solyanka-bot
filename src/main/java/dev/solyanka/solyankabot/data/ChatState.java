package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("chatstate")
public class ChatState {

    @Id
    private String chatId;
    private BotState state;

}
