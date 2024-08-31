package filemakerSupportClasses;

import java.time.LocalDate;

import java.util.Random;

import filemakerInterfaces.GamerBirthdateGenerator;

public class RandomGamerBirthdate implements GamerBirthdateGenerator {

    @Override
    public LocalDate generateBirthdate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long randomDay = minDay + (long) (new Random().nextDouble() * (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
        
    }
}