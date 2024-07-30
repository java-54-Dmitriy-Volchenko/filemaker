package telran.game.BullsAndCows.net;

import telran.game.BullsAndCows.BullsCowsMapImpl;
import telran.game.BullsAndCows.BullsCowsService;
import telran.net.Protocol;
import telran.net.TcpServer;

public class BullsCowsServerAppl {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        BullsCowsService bullsCowsService = new BullsCowsMapImpl();
        Protocol protocol = new BullsCowsProtocol(bullsCowsService);
        TcpServer tcpServer = new TcpServer(protocol, PORT);
        tcpServer.run();
    }
}
