package telran.game.BullsAndCows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int QUANTITY_DIGITS = 4;
	private long id;
    private String serverSequence;
    private boolean isFinished;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private List<MoveResult> results;

    public Game(long id, String serverSequence) {
        this.id = id;
        this.serverSequence = serverSequence;
        this.isFinished = false;
        this.startTime = LocalDateTime.now();
        this.results = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getServerSequence() {
        return serverSequence;
    }

    public boolean isFinished() {
        return isFinished;
    }

   

    public List<MoveResult> moveProcess(Move move) {
    	String clientMove = move.getClientSequence();
        int[] bullsAndCows = calculateBullsAndCows(clientMove);
       
        int bulls = bullsAndCows[0];
        int cows = bullsAndCows[1];

        MoveResult result = new MoveResult(clientMove, bulls, cows);
        results.add(result);

        if (bulls == QUANTITY_DIGITS) {
            isFinished = true;
            finishTime = LocalDateTime.now();
        }

        return results;
    }

	public int[] calculateBullsAndCows(String clientMove) {
		 int bulls = 0, cows = 0;

	        for (int i = 0; i < QUANTITY_DIGITS; i++) {
	            if (clientMove.charAt(i) == serverSequence.charAt(i)) {
	                bulls++;
	            } else if (serverSequence.contains(String.valueOf(clientMove.charAt(i)))) {
	                cows++;
	            }
	        }

	        return new int[]{bulls, cows};
	}
}
