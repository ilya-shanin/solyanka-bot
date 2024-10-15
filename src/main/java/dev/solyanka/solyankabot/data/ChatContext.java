package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class ChatContext {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "chat_id")
    private String chatId;

    @Column(nullable = false)
    private ContextKey key;

    @Column
    private String value;
}
