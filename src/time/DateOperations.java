package time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class DateOperations {
    private static final DateTimeFormatter ISO_FMT = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US);
    private static final DateTimeFormatter SLASH_FMT = DateTimeFormatter.ofPattern("d/M/uuuu", Locale.US);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    public static String[] sortStringDates(String[] dates) {
        if (dates == null) return null;
        String[] copy = Arrays.copyOf(dates, dates.length);
        Arrays.sort(copy, Comparator.comparing(DateOperations::parseDate));
        return copy;
    }

    public static int getAge(String birthDate, String currentDate) {
        LocalDate birth = parseDate(birthDate);
        LocalDate current = (currentDate == null) ? LocalDate.now() : parseDate(currentDate);
        if (current.isBefore(birth)) {
            throw new IllegalArgumentException("currentDate is before birthDate");
        }
        return Period.between(birth, current).getYears();
    }

    public static void printCurrentTime(String zoneSubstring) {
        if (zoneSubstring == null) zoneSubstring = "";
        final String needle = zoneSubstring.toLowerCase(Locale.ROOT);

        ZoneId.getAvailableZoneIds().stream()
                .sorted()
                .filter(id -> id.toLowerCase(Locale.ROOT).contains(needle))
                .forEach(id -> {
                    ZonedDateTime now = ZonedDateTime.now(ZoneId.of(id));
                    System.out.println(id + " - " + now.format(TIME_FMT));
                });
    }

    private static LocalDate parseDate(String s) {
        if (s == null) throw new IllegalArgumentException("date is null");
        String str = s.trim();
        try {
            if (str.contains("-")) {
                return LocalDate.parse(str, ISO_FMT);
            } else if (str.contains("/")) {
                return LocalDate.parse(str, SLASH_FMT);
            } else {
                try { return LocalDate.parse(str, ISO_FMT); }
                catch (DateTimeParseException ex) { return LocalDate.parse(str, SLASH_FMT); }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unsupported date format: " + s, e);
        }
    }
}

