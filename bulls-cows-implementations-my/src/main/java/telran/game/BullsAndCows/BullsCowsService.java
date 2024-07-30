package telran.game.BullsAndCows;

import java.util.List;

public interface BullsCowsService {
    Long createNewGame();
    List<MoveResult> getResults(long gameId, Move move);
    Boolean isGameOver(long gameId);
}
