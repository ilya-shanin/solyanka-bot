package dev.solyanka.solyankabot.telegram.model;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.telegram.enumeration.ContextKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextModel {
    private String chatId;
    private Map<ContextKey, String> values;

    public void putValue(ContextKey key, String value) {
        if (Objects.isNull(values)) {
            values = new HashMap<>();
        }
        values.put(key, value);
    }

    public String getValue(ContextKey key) {
        if (Objects.isNull(values) || !values.containsKey(key)) {
            return null;
        }
        return values.get(key);
    }

    public static ContextModel of(ChatContext chatContextEntity) {
        var model = new ContextModel();
        model.setChatId(chatContextEntity.getChatId());
        return model;
    }
}
