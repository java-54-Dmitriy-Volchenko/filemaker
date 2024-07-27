package telran.game.BullsAndCows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import telran.game.BullsAndCows.BullsCowsService;
import telran.game.BullsAndCows.Move;
import telran.game.BullsAndCows.MoveResult;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class BullsCowsApplItems {
    public static List<Item> getItems(BullsCowsService bullsCows) {
        Item[] items = {
            Item.of("Start new Game", startGame(bullsCows)),
        };
        return new ArrayList<>(List.of(items));
    }

    private static Consumer<InputOutput> startGame(BullsCowsService bullsCows) {
        return io -> {
            Long gameId = bullsCows.createNewGame();
            io.writeLine("New game started. Game ID: " + gameId);
            AtomicBoolean exitToMainMenu = new AtomicBoolean(false);//solution found in Internet
            boolean gameFinished = false;
            while (!gameFinished && !exitToMainMenu.get()) {
                Menu menu = new Menu("Enter 4 non-repeated digits ['0'-'9']", getSubmenuItems(bullsCows, gameId, exitToMainMenu));
                menu.perform(io);
                gameFinished = bullsCows.isGameOver(gameId);
            }
            io.writeLine("=".repeat(40));
        };
    }

    private static Item[] getSubmenuItems(BullsCowsService bullsCows, Long gameId, AtomicBoolean exitToMainMenu) {
        Item[] items = {
            Item.of("Make Your Move", io -> makeMove(io, bullsCows, gameId)),
            Item.of("Exit to Main Menu", io -> {
                io.writeLine("Exiting to main menu...");
                exitToMainMenu.set(true);
            }, true)
        };
        return items;
    }

    private static void makeMove(InputOutput io, BullsCowsService bullsCows, Long gameId) {
        String clientMove = io.readString("Enter your guess (4 non-repeated digits): ");

        if (clientMove.length() != 4 || !areDigitsUnique(clientMove)) {
            io.writeLine("Invalid input. Please enter 4 non-repeated digits.");
            return;
        }

        Move move = new Move(gameId, clientMove);
        List<MoveResult> results = bullsCows.getResults(gameId, move);

        MoveResult lastResult = results.get(results.size() - 1);
        io.writeLine("Your guess: " + lastResult.getClientSequence() +
                " | Bulls: " + lastResult.getBulls() +
                " | Cows: " + lastResult.getCows());

        if (bullsCows.isGameOver(gameId)) {
            io.writeLine("Congratulations! You've guessed the sequence.");
        } else {
            io.writeLine("Try again.");
        }
    }

    private static boolean areDigitsUnique(String input) {
        Set<Character> digits = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || !digits.add(c)) {
                return false;
            }
        }
        return true;
    }
}

