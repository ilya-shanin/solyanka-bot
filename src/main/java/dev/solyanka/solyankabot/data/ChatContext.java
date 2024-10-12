package dev.solyanka.solyankabot.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@RedisHash("chatcontext")
public class ChatContext {

    @Id
    private String chatId;

    private Map<String,String> values;

    public void putValue(String key, String value) {
        if (Objects.isNull(values)) {
            values = new HashMap<>();
        }
        values.put(key, value);
    }

    public String getValue(String key) {
        if (Objects.isNull(values) || !values.containsKey(key)) {
            return null;
        }
        return values.get(key);
    }
}
