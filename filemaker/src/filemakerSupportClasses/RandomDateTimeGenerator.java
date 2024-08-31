package filemakerSupportClasses;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import filemakerInterfaces.DateTimeGenerator;

public class RandomDateTimeGenerator implements DateTimeGenerator {

	 @Override
	    public LocalDateTime generateDateTime() {
	        long minDay = LocalDateTime.of(2020, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
	        long maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
	        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));
	        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
	    }
	}
