package dev.solyanka.solyankabot.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizParticipantRepository extends CrudRepository<QuizParticipant, String> {
    List<QuizParticipant> findAllByTgIdAndIsGuest(Long tgId, boolean isGuest);
    List<QuizParticipant> findAllByQuiz(QuizGame quiz);
    Integer countAllByQuiz(QuizGame quizGame);
    Optional<QuizParticipant> findFirstByTgIdAndQuizAndIsGuestFalse(Long tgId, QuizGame quizGame);
    void deleteByTgIdAndQuizAndIsGuest(Long tgId, QuizGame quizGame, Boolean isGuest);

    default void deletePlayer(Long tgId, QuizGame quizGame) {
        deleteByTgIdAndQuizAndIsGuest(tgId, quizGame, false);
    }

    default void deleteGuest(Long tgId, QuizGame quizGame) {
        deleteByTgIdAndQuizAndIsGuest(tgId, quizGame, true);
    }
}
