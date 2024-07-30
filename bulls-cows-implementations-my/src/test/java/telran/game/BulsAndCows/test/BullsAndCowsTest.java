package telran.game.BulsAndCows.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.game.BullsAndCows.BullsCowsMapImpl;
import telran.game.BullsAndCows.BullsCowsService;
import telran.game.BullsAndCows.Game;
import telran.game.BullsAndCows.Move;
import telran.game.BullsAndCows.MoveResult;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BullsAndCowsTest {

    private BullsCowsService service;

    @BeforeEach
    public void setUp() {
        service = new BullsCowsMapImpl();
    }

    @Test
    public void CreateNewGameTest() {
        Long gameId = service.createNewGame();
        assertNotNull(gameId);
    }

    @Test
    public void GenerateRandomSequenceTest() {
        Long gameId = service.createNewGame();
        String sequence = ((BullsCowsMapImpl) service).getGames().get(gameId).getServerSequence();
        assertNotNull(sequence);
        assertEquals(4, sequence.length());
        assertTrue(sequence.chars().allMatch(Character::isDigit));
        assertEquals(4, sequence.chars().distinct().count());
    }

    @Test
    public void GetResultsTest() {
        Long gameId = service.createNewGame();
        Move move = new Move(gameId, "1234");
        List<MoveResult> results = service.getResults(gameId, move);
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    public void IsGameOverTest() {
        Long gameId = service.createNewGame();
        assertFalse(service.isGameOver(gameId));
        Move move = new Move(gameId, ((BullsCowsMapImpl) service).getGames().get(gameId).getServerSequence());
        service.getResults(gameId, move);
        assertTrue(service.isGameOver(gameId));
    }
    
    @Test
    public void testCalculateBullsAndCows() {
        Game game = new Game(1, "1234");
        int[] result1 = game.calculateBullsAndCows("5678");
        assertEquals(0, result1[0]);
        assertEquals(0, result1[1]);
        int[] result2 = game.calculateBullsAndCows("1678");
        assertEquals(1, result2[0]);
        assertEquals(0, result2[1]);
        int[] result3 = game.calculateBullsAndCows("5178");
        assertEquals(0, result3[0]);
        assertEquals(1, result3[1]);
        int[] result4 = game.calculateBullsAndCows("5134");
        assertEquals(2, result4[0]);
        assertEquals(1, result4[1]);
    }
}
