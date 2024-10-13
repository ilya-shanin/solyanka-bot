package dev.solyanka.solyankabot.data;

import dev.solyanka.solyankabot.telegram.model.InlineKeyboardItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@NoArgsConstructor
@RedisHash("quizgame")
public class QuizGame implements InlineKeyboardItem {

    @Id
    private String id;

    private String name;
    private String datetime;
    private String cost;
    private String location;
    private String maxPlayers;

    private List<QuizParticipant> participants;

    @Override
    public String getKeyboardText() {
        return name;
    }

    @Override
    public String getCallbackData() {
        return id;
    }
}
