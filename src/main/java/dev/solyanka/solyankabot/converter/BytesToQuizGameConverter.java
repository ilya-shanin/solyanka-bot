package dev.solyanka.solyankabot.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.solyanka.solyankabot.data.QuizGame;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class BytesToQuizGameConverter implements Converter<byte[], QuizGame> {
    private final Jackson2JsonRedisSerializer<QuizGame> serializer;

    public BytesToQuizGameConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), QuizGame.class);
    }

    @Override
    public QuizGame convert(byte[] source) {
        return serializer.deserialize(source);
    }
}
