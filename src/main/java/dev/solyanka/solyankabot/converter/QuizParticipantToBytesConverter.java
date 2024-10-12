package dev.solyanka.solyankabot.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.solyanka.solyankabot.data.QuizGame;
import dev.solyanka.solyankabot.data.QuizParticipant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class QuizParticipantToBytesConverter implements Converter<QuizParticipant, byte[]> {
    private final Jackson2JsonRedisSerializer<QuizParticipant> serializer;

    public QuizParticipantToBytesConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), QuizParticipant.class);
    }

    @Override
    public byte[] convert(QuizParticipant source) {
        return serializer.serialize(source);
    }
}
