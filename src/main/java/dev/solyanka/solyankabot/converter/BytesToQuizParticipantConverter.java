package dev.solyanka.solyankabot.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.solyanka.solyankabot.data.QuizParticipant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class BytesToQuizParticipantConverter implements Converter<byte[], QuizParticipant> {
    private final Jackson2JsonRedisSerializer<QuizParticipant> serializer;

    public BytesToQuizParticipantConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), QuizParticipant.class);
    }

    @Override
    public QuizParticipant convert(byte[] source) {
        return serializer.deserialize(source);
    }
}
