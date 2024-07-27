package telran.game.BullsAndCows.net;

import telran.game.BullsAndCows.BullsCowsService;
import telran.game.BullsAndCows.Move;
import telran.game.BullsAndCows.MoveResult;
import telran.net.TcpClient;
import telran.net.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BullsCowsProxy implements BullsCowsService {
    private TcpClient tcpClient;

    public BullsCowsProxy(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public Long createNewGame() {
        String response = tcpClient.sendAndReceive(new Request("createNewGame", ""));
        return Long.parseLong(response);
    }

    @Override
    public List<MoveResult> getResults(long gameId, Move move) {
        String requestData = gameId + ";" + move.getClientSequence();
        String response = tcpClient.sendAndReceive(new Request("getResults", requestData));
        return Stream.of(response.split(";"))
                .map(MoveResult::fromJson)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isGameOver(long gameId) {
        String response = tcpClient.sendAndReceive(new Request("isGameOver", Long.toString(gameId)));
        return Boolean.parseBoolean(response);
    }
}
