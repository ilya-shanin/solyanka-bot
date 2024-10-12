package dev.solyanka.solyankabot.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.solyanka.solyankabot.data.QuizGame;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class QuizGameToBytesConverter implements Converter<QuizGame, byte[]> {
    private final Jackson2JsonRedisSerializer<QuizGame> serializer;

    public QuizGameToBytesConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), QuizGame.class);
    }

    @Override
    public byte[] convert(QuizGame source) {
        return serializer.serialize(source);
    }
}
