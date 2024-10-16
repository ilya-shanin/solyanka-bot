package dev.solyanka.solyankabot.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static LocalDateTime fromString(String dateTime) {
        String datetimePattern = getDateTimePattern();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datetimePattern);

        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDate dropTime(LocalDateTime dateTime) {
        return LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
    }

    public static String getDateTimePattern() {
        return "dd.MM.yyyy HH:mm";
    }
}
