package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.model.InlineKeyboardItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "quiz", name = "quiz_participant")
public class QuizParticipant implements InlineKeyboardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "tg_id")
    private Long tgId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "quiz_id")
    private QuizGame quiz;

    @Column(nullable = false, name = "is_guest")
    private boolean isGuest;

    @Column(nullable = false)
    private String username;

    @Column(name = "first_name")
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
