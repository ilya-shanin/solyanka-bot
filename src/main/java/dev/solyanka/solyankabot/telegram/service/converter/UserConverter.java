package dev.solyanka.solyankabot.telegram.service.converter;

import dev.solyanka.solyankabot.data.QuizParticipant;
import org.telegram.telegrambots.meta.api.objects.User;

public class UserConverter {

    public static QuizParticipant fromUser(User user) {
        var participant = new QuizParticipant();

        participant.setTgId(user.getId());
        participant.setUsername(user.getUserName());
        participant.setFirstName(user.getFirstName());
        participant.setGuest(false);

        return participant;
    }

    public static QuizParticipant guestFromUser(User user) {
        var participant = new QuizParticipant();

        participant.setTgId(user.getId());
        participant.setUsername(user.getUserName());
        participant.setFirstName(user.getFirstName());
        participant.setGuest(true);

        return participant;
    }

}
