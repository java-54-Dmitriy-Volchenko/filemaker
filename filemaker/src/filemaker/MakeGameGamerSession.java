package filemaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import filemakerInterfaces.*;
import filemakerSupportClasses.*;

public class MakeGameGamerSession {
    private static final int NUMBERS_OF_MOVES = 1000;
    private Map<Long, GameGamer> gameGamerMap = new HashMap<>();
    private long gameGamerIdCounter = 1;

    private GameIDGenerator idGenerator = new DefaultGameIDGenerator();
    private DateTimeGenerator dateTimeGenerator = new RandomDateTimeGenerator();
    private SequenceGenerator sequenceGenerator = new RandomSequenceGenerator();
    private GamerNameGenerator nameGenerator = new RandomGamerName();
    private GamerBirthdateGenerator birthdateGenerator = new RandomGamerBirthdate();

    public Map<Long, GameGamer> getGameGamerMap() {
        return gameGamerMap;
    }

    public void createGameGamerEntry() {
        Game game = new Game(idGenerator, dateTimeGenerator, sequenceGenerator, true);
        Gamer gamer = new Gamer(nameGenerator, birthdateGenerator);

        List<Move> moves = new ArrayList<>();
        boolean is_winner = false;

        int i = 0;
        while (i < NUMBERS_OF_MOVES && !is_winner) {
            Move move = new Move(idGenerator, sequenceGenerator, game);
            moves.add(move);

            if (move.getBulls() == 4) {
                is_winner = true;
            }
            i++;
        }

        GameGamer gameGamer = new GameGamer(game, gamer, moves, is_winner);
        gameGamerMap.put(gameGamerIdCounter++, gameGamer);
    }
}
