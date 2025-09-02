package streamApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumbersUtil {

    public static void displayShuffledArray(int[] ar) {
        Arrays.stream(ar).boxed()
              .collect(Collectors.collectingAndThen(Collectors.toList(), list -> { 
                   Collections.shuffle(list); list.forEach(n -> System.out.print(n + " ")); return null; 
              }));
        System.out.println();
    }

    public static void sportLoto(int min, int max, int numberDigits) {
        if (min > max || numberDigits < 1 || numberDigits > (max - min + 1)) {
            throw new IllegalArgumentException("Bad range or count");
        }
        IntStream.rangeClosed(min, max).boxed()
                 .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                     Collections.shuffle(list);
                     return list.stream().limit(numberDigits).sorted().toList();
                 }))
                 .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
