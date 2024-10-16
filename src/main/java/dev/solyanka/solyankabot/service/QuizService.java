package dev.solyanka.solyankabot.service;

import dev.solyanka.solyankabot.data.QuizGame;
import dev.solyanka.solyankabot.data.QuizGameRepository;
import dev.solyanka.solyankabot.data.QuizParticipant;
import dev.solyanka.solyankabot.data.QuizParticipantRepository;
import dev.solyanka.solyankabot.exceptions.BusinessLogicException;
import dev.solyanka.solyankabot.exceptions.WorkflowInterruptedException;
import dev.solyanka.solyankabot.telegram.model.ContextModel;
import dev.solyanka.solyankabot.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dev.solyanka.solyankabot.telegram.enumeration.ContextKey.*;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizGameRepository quizGameRepository;
    private final QuizParticipantRepository playersRepository;

    // QUIZ MANAGEMENT

    public List<QuizGame> getActualGames() {
        var res = new ArrayList<QuizGame>();
        var currDate = LocalDate.now();
        quizGameRepository.findAll().forEach(res::add);
        return res.stream().filter(game ->
                        DateTimeUtils.dropTime(game.getDatetime()).isAfter(currDate)
                )
                .toList();
    }

    public List<QuizGame> getActualGamesByPlayer(Long telegramId, boolean isGuest) {
        var participants = playersRepository.findAllByTgIdAndGuest(telegramId, isGuest);
        var currDate = LocalDate.now();
        return participants.stream()
                .map(QuizParticipant::getQuiz)
                .filter(game ->
                        DateTimeUtils.dropTime(game.getDatetime()).isAfter(currDate)
                )
                .toList();
    }

    public QuizGame getQuizById(String id) {
        return quizGameRepository.findById(id).orElseThrow(() -> new WorkflowInterruptedException("Не удалось найти выбранный квиз!"));
    }

    public QuizGame createGame(ContextModel chatContext) {
        var quiz = new QuizGame();
        fillFromContext(quiz, chatContext);
        return quizGameRepository.save(quiz);
    }

    public void delete(QuizGame quiz) {
        quizGameRepository.delete(quiz);
    }

    // PLAYER MANAGEMENT

    public void addPlayer(QuizGame quiz, QuizParticipant quizParticipant) {
        if (Boolean.TRUE.equals(quizParticipant.isGuest())) {
            var player = playersRepository.findFirstByTgIdAndQuizAndGuestFalse(quizParticipant.getTgId(), quiz);
            if (player.isPresent()) {
                throw new BusinessLogicException("Игрок уже участвует!");
            }
        }
        var maxPlayers = quiz.getMaxPlayers();
        if (getCurrentPlayersCount(quiz) >= maxPlayers) {
            throw new BusinessLogicException("Достигнуто максимальное количество игроков!");
        }

        quizParticipant.setQuiz(quiz);
        playersRepository.save(quizParticipant);
    }

    public void removeGuestByTelegramId(QuizGame quiz, Long id) {
        playersRepository.deleteGuest(id, quiz);
    }

    public void removePlayerByTelegramId(QuizGame quiz, Long id) {
        playersRepository.deletePlayer(id, quiz);
    }

    public List<QuizParticipant> getPlayersByGame(QuizGame game) {
        return playersRepository.findAllByQuiz(game);
    }

    private Long getCurrentPlayersCount(QuizGame game) {
        return playersRepository.countAllByQuiz(game);
    }

    private void fillFromContext(QuizGame quiz, ContextModel chatContext) {
        validateIntegrity(chatContext);

        quiz.setName(chatContext.getValue(QUIZ_CREATION_NAME));
        quiz.setLocation(chatContext.getValue(QUIZ_LOCATION));
        quiz.setDatetime(DateTimeUtils.fromString(chatContext.getValue(QUIZ_DATETIME)));
        quiz.setCost(chatContext.getValue(QUIZ_COST));
        quiz.setMaxPlayers(Long.parseLong(chatContext.getValue(QUIZ_MAX_PLAYERS)));
    }

    private void validateIntegrity(ContextModel chatContext) {
        var errors = new ArrayList<String>();
        List.of(QUIZ_CREATION_NAME, QUIZ_LOCATION, QUIZ_DATETIME, QUIZ_COST, QUIZ_MAX_PLAYERS).forEach(key -> {
            if (Objects.isNull(chatContext.getValue(key))) {
                errors.add(key.name());
            }
        });
        if (!errors.isEmpty()) {
            throw new WorkflowInterruptedException("Не удалось добавить новую игру! Не хватает данных: %s.".formatted(
                    String.join(", ", errors)
            ));
        }
    }
}
