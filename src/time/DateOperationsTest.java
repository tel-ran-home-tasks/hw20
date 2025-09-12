package time;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateOperationsTest {

    private final PrintStream realOut = System.out;
    private ByteArrayOutputStream baos;

    String[] dates = {"2000-12-01", "10/12/2000", "1970-08-12", "2010-10-05" };
    String[] expectedDates = {"1970-08-12", "2000-12-01", "10/12/2000", "2010-10-05" };

    @BeforeEach
    void setUp() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @AfterEach
    void tearDown() {
        System.setOut(realOut);
    }


    @Test
    void datesSortTest() {
        String[] actual = DateOperations.sortStringDates(dates);
        assertArrayEquals(expectedDates, actual);
    }

    @Test
    void ageTest() {
        String birthDate = "12/04/1961";
        String currentDate = "11/11/2018";
        assertEquals(57, DateOperations.getAge(birthDate, currentDate));

        birthDate = "1961-12-12";
        currentDate = "2018-11-11";
        assertEquals(56, DateOperations.getAge(birthDate, currentDate));
    }

    @Test
    void printCurrentTimeForZonesTest() {
        DateOperations.printCurrentTime("Samara");
    }

    @Test
    void datesSort_emptyAndNull() {
        String[] empty = new String[0];
        assertArrayEquals(new String[0], DateOperations.sortStringDates(empty));
        assertNull(DateOperations.sortStringDates(null));
    }

    @Test
    void datesSort_mixedFormats_keepsOriginalStringsButSorted() {
        String[] in = {"01/01/2001", "2000-12-31", "02/01/2001"};
        String[] expected = {"2000-12-31", "01/01/2001", "02/01/2001"};
        assertArrayEquals(expected, DateOperations.sortStringDates(in));
    }

    @Test
    void age_withNullCurrent_usesNow() {
        LocalDate birth = LocalDate.of(2000, 1, 1);
        int expected = Period.between(birth, LocalDate.now()).getYears();
        assertEquals(expected, DateOperations.getAge("2000-01-01", null));
    }

    @Test
    void age_throwsIfCurrentBeforeBirth() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> DateOperations.getAge("2010-01-01", "2009-12-31"));
        assertTrue(ex.getMessage().toLowerCase().contains("before"));
    }

    @Test
    void printCurrentTime_filtersCaseInsensitive_andFormatsHHMM() {
        baos.reset();
        DateOperations.printCurrentTime("samara");
        String out = normalize(baos.toString());
        if (out.isBlank()) {
            baos.reset();
            DateOperations.printCurrentTime("america");
            out = normalize(baos.toString());
        }
        assertFalse(out.isBlank(), "at least one time zone in the output expected");

        List<String> lines = out.lines().toList();
        Pattern linePat = Pattern.compile(".+ - \\d{2}:\\d{2}");
        for (String line : lines) {
            assertTrue(linePat.matcher(line).matches(), "Invalid line format: " + line);
        }
    }

    @Test
    void printCurrentTime_noMatches_printsNothing() {
        baos.reset();
        DateOperations.printCurrentTime("__NO_SUCH_ZONE_SUBSTRING__");
        assertTrue(baos.toString().isBlank(), "Expected empty output");
    }


    private static String normalize(String s) {
        return s.replace("\r\n", "\n").trim();
    }
}