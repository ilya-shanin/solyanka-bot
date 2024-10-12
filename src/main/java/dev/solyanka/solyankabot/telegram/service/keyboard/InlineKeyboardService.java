package dev.solyanka.solyankabot.telegram.service.keyboard;

import dev.solyanka.solyankabot.telegram.core.InlineKeyboardItem;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class InlineKeyboardService {

    public InlineKeyboardMarkup buildInlineKeyboardOf(List<InlineKeyboardItem> items) {
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (var item : items) {
            var button = new InlineKeyboardButton();
            button.setText(item.getKeyboardText());
            button.setCallbackData(item.getCallbackData());
            rowList.add(List.of(button));
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

}
