package filemakerSupportClasses;

import java.util.UUID;

import filemakerInterfaces.GameIDGenerator;

public class DefaultGameIDGenerator implements GameIDGenerator {
	 @Override
	    public long generateGameID() {
	        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	    }
	}

