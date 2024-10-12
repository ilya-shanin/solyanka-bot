package dev.solyanka.solyankabot.telegram.service.keyboard;

import dev.solyanka.solyankabot.telegram.enumeration.ButtonName;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Service
public class ReplyKeyboardService {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow(List.of(
                new KeyboardButton(ButtonName.VIEW_GAMES.getText())
        ));
        KeyboardRow row2 = new KeyboardRow(List.of(
                new KeyboardButton(ButtonName.ADD_PARTICIPANT.getText()),
                new KeyboardButton(ButtonName.DELETE_PARTICIPANT.getText())
        ));
        KeyboardRow row3 = new KeyboardRow(List.of(
                new KeyboardButton(ButtonName.ADD_GAME.getText()),
                new KeyboardButton(ButtonName.DELETE_GAME.getText())
        ));

        final var replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(row1, row2, row3));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true); // hide keyboard after selection?
        return replyKeyboardMarkup;
    }

}
