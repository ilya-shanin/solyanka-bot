package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.model.InlineKeyboardItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "quiz", name = "quiz_game")
public class QuizGame implements InlineKeyboardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column
    private String cost;

    @Column(nullable = false)
    private String location;

    @Column
    private Long maxPlayers;

    @Override
    public String getKeyboardText() {
        return name;
    }

    @Override
    public String getCallbackData() {
        return id.toString();
    }
}
