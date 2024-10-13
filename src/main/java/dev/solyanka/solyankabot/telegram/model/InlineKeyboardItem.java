package dev.solyanka.solyankabot.telegram.model;

/**
 * Element which can be displayed on a keyboard
 */
public interface InlineKeyboardItem {
    String getKeyboardText();
    String getCallbackData();
}
