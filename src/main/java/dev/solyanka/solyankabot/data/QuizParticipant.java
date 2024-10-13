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

    private String name;

    @Override
    public String getKeyboardText() {
        return name;
    }

    @Override
    public String getCallbackData() {
        return id;
    }
}
