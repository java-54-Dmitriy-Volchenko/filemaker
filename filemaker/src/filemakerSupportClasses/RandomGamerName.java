package filemakerSupportClasses;

import filemakerInterfaces.GamerNameGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomGamerName implements GamerNameGenerator {

    private static final List<String> GAMER_NAMES = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    static {
       
        for (int i = 0; i < 10; i++) {
            GAMER_NAMES.add(generateGamerNameInternal());
        }
     
        Collections.shuffle(GAMER_NAMES, RANDOM);
    }

    @Override
    public String generateGamerName() {
   
        return GAMER_NAMES.get(RANDOM.nextInt(GAMER_NAMES.size()));
    }

    private static String generateGamerNameInternal() {
        return Stream.of(generateRandomWord(), generateRandomWord())
                     .collect(Collectors.joining(" "));
    }

    private static String generateRandomWord() {
        int wordLength = RANDOM.nextInt(8) + 1; 
        return IntStream.range(0, wordLength)
                        .mapToObj(i -> ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())))
                        .map(i -> i.toString())
                        .collect(Collectors.collectingAndThen(Collectors.joining(), word -> 
                            Character.toUpperCase(word.charAt(0)) + word.substring(1)));
    }
}