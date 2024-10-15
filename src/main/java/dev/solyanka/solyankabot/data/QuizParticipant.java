package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.model.InlineKeyboardItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@Entity
public class QuizParticipant implements InlineKeyboardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tgId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private QuizGame quizId;

    @Column(nullable = false)
    private Boolean isGuest;

    @Column(nullable = false)
    private String username;

    @Column
    private String firstName;

    public String getShortName() {
        if (Boolean.TRUE.equals(isGuest)) {
            return "%s (%s) +1".formatted(firstName, username);
        }
        return "%s (%s)".formatted(firstName, username);
    }

    @Override
    public String getKeyboardText() {
        return getShortName();
    }

    @Override
    public String getCallbackData() {
        return id.toString();
    }
}
