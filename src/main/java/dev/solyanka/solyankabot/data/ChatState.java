package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.enumeration.BotState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "bot", name = "chat_state")
public class ChatState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column
    private BotState state;
}
