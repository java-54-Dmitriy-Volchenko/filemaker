package telran.game.BullsAndCows;
import telran.game.BullsAndCows.BullsCowsMapImpl;
import telran.game.BullsAndCows.BullsCowsService;
import telran.view.*;

import java.util.*;

public class BullsCowsAppl {

    public static void main(String[] args) {
        BullsCowsService service = new BullsCowsMapImpl();
        
        List<Item> gameItems = BullsCowsApplItems.getItems(service);
        gameItems.add(Item.ofExit());

        Menu menu = new Menu("Bulls and Cows Game", gameItems.toArray(Item[]::new));
        menu.perform(new SystemInputOutput());
    }
}
