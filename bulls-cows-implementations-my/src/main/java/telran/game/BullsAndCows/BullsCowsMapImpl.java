package telran.game.BullsAndCows;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


public class BullsCowsMapImpl implements BullsCowsService {
    private Map<Long, Game> games = new HashMap<>();
    private long gameIdCounter = 0;

    @Override
    public Long createNewGame() {
        long gameId = ++gameIdCounter;
        String serverSequence = generateRandomSequence();
        games.put(gameId, new Game(gameId, serverSequence));
        return gameId;
    }

    @Override
    public List<MoveResult> getResults(long gameId, Move move) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Invalid game ID");
        }
        return game.moveProcess(move);
    }

    @Override
    public Boolean isGameOver(long gameId) {
        Game game = games.get(gameId);
        return game != null && game.isFinished();
    }

    private String generateRandomSequence() {
        Random random = new Random();
        return random.ints(0, 10)
                     .distinct()
                     .limit(4)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.joining());
    }
    
    public Map<Long, Game> getGames() {
        return games;
    }
    
}

