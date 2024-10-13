package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.model.InlineKeyboardItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@RedisHash("quizparticipant")
public class QuizParticipant implements InlineKeyboardItem {

    @Id
    private String id;
    private String username;
    private String firstName;
    private Boolean isGuest;
    private Long tgId;


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
        return id;
    }
}
