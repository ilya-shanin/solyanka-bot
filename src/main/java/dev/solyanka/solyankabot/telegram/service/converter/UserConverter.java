package dev.solyanka.solyankabot.telegram.service.converter;

import dev.solyanka.solyankabot.data.QuizParticipant;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

public class UserConverter {

    public static QuizParticipant fromUser(User user) {
        var participant = new QuizParticipant();

        participant.setName("%s (%s)".formatted(user.getFirstName(), user.getUserName()));

        return participant;
    }

    public static QuizParticipant guestFromUser(User user) {
        var participant = new QuizParticipant();

        participant.setName("%s (%s) +1".formatted(user.getFirstName(), user.getUserName()));

        return participant;
    }

}
