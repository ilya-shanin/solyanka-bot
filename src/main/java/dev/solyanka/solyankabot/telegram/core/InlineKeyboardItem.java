package dev.solyanka.solyankabot.telegram.core;

/**
 * Element which can be displayed on a keyboard
 */
public interface InlineKeyboardItem {
    String getKeyboardText();
    String getCallbackData();
}
