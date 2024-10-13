package dev.solyanka.solyankabot.service;

import com.vdurmont.emoji.EmojiParser;
import dev.solyanka.solyankabot.data.QuizGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizDetailsService {
    private final QuizService quizService;

    public String getQuizDetailsFormatted(QuizGame quizGame) {
        var players = quizService.getPlayersByGame(quizGame);

        var builder = new StringBuilder();
        // Наименование
        builder.append("Информация об игре %s:%s".formatted(quizGame.getName(), System.lineSeparator()));
        // Место проведения
        builder.append(EmojiParser.parseToUnicode(
                ":round_pushpin: %s%s".formatted(quizGame.getLocation(), System.lineSeparator()))
        );
        // Дата и время
        builder.append(EmojiParser.parseToUnicode(
                ":calendar: %s%s".formatted(quizGame.getDatetime(), System.lineSeparator()))
        );
        // Стоимость
        builder.append(EmojiParser.parseToUnicode(
                ":money_with_wings: %s%s".formatted(quizGame.getCost(), System.lineSeparator()))
        );
        // Макс количество участников
        builder.append(EmojiParser.parseToUnicode(
                ":man_raising_hand: %s%s%s".formatted(
                        quizGame.getMaxPlayers(),
                        System.lineSeparator(),
                        System.lineSeparator()
                ))
        );
        //Список участников
        if (players.isEmpty()) {
            builder.append(
                    "Пока нет ни одного участника \uD83E\uDD72"
            );
        } else {
            int index = 1;
            for (var player : players) {
                builder.append("%s. %s%s".formatted(index, player.getUsername(), System.lineSeparator()));
                index++;
            }
        }

        return builder.toString();
    }

}
