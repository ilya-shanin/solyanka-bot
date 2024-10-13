package dev.solyanka.solyankabot.service;

import dev.solyanka.solyankabot.data.ChatContext;
import dev.solyanka.solyankabot.data.QuizGame;
import dev.solyanka.solyankabot.data.QuizGameRepository;
import dev.solyanka.solyankabot.data.QuizParticipant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static dev.solyanka.solyankabot.telegram.enumeration.ContextKey.*;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizGameRepository quizGameRepository;

    // QUIZ MANAGEMENT

    public List<QuizGame> getActualGames() {
        var res = new ArrayList<QuizGame>();
        quizGameRepository.findAll().forEach(res::add);
        return res;
    }

    public QuizGame getQuizById(String id) {
        return quizGameRepository.findById(id).orElseThrow(() -> new RuntimeException("Не удалось найти выбранный квиз!"));
    }

    public QuizGame createGame(ChatContext chatContext) {
        var quiz = new QuizGame();
        fillFromContext(quiz, chatContext);
        return quizGameRepository.save(quiz);
    }

    public void delete(QuizGame quiz) {
        quizGameRepository.delete(quiz);
    }

    // PLAYER MANAGEMENT

    public void addPlayer(QuizGame quiz, QuizParticipant quizParticipant) {

    }

    public void removePlayer(QuizGame quiz, QuizParticipant quizParticipant) {

    }

    public void removePlayer(String quizId, String playerId) {

    }

    public List<QuizParticipant> getPlayersByGame(QuizGame game) {

        return Collections.emptyList();
    }

    private void fillFromContext(QuizGame quiz, ChatContext chatContext) {
        validateIntegrity(chatContext);

        quiz.setName(chatContext.getValue(QUIZ_CREATION_NAME.name()));
        quiz.setLocation(chatContext.getValue(QUIZ_LOCATION.name()));
        quiz.setDatetime(chatContext.getValue(QUIZ_DATETIME.name()));
        quiz.setCost(chatContext.getValue(QUIZ_COST.name()));
        quiz.setMaxPlayers(chatContext.getValue(QUIZ_MAX_PLAYERS.name()));
    }

    private void validateIntegrity(ChatContext chatContext) {
        var errors = new ArrayList<String>();
        List.of(QUIZ_CREATION_NAME, QUIZ_LOCATION, QUIZ_DATETIME, QUIZ_COST, QUIZ_MAX_PLAYERS).forEach(key -> {
            if (Objects.isNull(chatContext.getValue(key.name()))) {
                errors.add(key.name());
            }
        });
        if (!errors.isEmpty()) {
            throw new RuntimeException("Не удалось добавить новую игру! Не хватает данных: %s.".formatted(
                    String.join(", ", errors)
            ));
        }
    }
}
