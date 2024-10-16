package dev.solyanka.solyankabot.service;

import dev.solyanka.solyankabot.data.QuizGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizValidationService {

    private final QuizService quizService;

    public List<String> validateDeletion(QuizGame game) {
        var players = quizService.getPlayersByGame(game);
        if (Objects.nonNull(players) && !players.isEmpty()) {
            return List.of("Невозможно удалить игру, на которую уже зарегистрированые участники!");
        }
        return Collections.emptyList();
    }
}
