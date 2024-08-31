package filemakerSupportClasses;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import filemakerInterfaces.SequenceGenerator;

public class RandomSequenceGenerator implements SequenceGenerator {

    private final Random random = new Random();

    @Override
    public String generateSequence() {
        String sequence = IntStream.range(0, 4)
                                   .mapToObj(i -> String.valueOf(random.nextInt(10)))
                                   .collect(Collectors.joining());

    
        while (sequence.length() < 4) {
            sequence += random.nextInt(10);
        }

        return sequence;
    }
}