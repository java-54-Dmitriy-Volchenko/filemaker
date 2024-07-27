package telran.game.BullsAndCows.net;

import telran.game.BullsAndCows.BullsCowsApplItems;
import telran.net.TcpClient;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

import java.util.List;

public class BullsCowsClientAppl {
    private static final int PORT = 5001;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient(HOST, PORT);
        BullsCowsProxy bullsCowsProxy = new BullsCowsProxy(tcpClient);
        List<Item> items = BullsCowsApplItems.getItems(bullsCowsProxy);
        items.add(Item.of("Exit & connection close", io -> {
            try {
                tcpClient.close();
            } catch (Exception e) {
                io.writeLine("Error closing connection: " + e.getMessage());
            }
        }, true));
        Menu menu = new Menu("TCP Client Application", items.toArray(Item[]::new));
        menu.perform(new SystemInputOutput());
    }
}
