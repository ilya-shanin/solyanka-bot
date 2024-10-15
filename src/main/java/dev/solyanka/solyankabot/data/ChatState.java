package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@Entity
public class ChatState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String chatId;

    @Column
    private BotState state;
}
