package telran.game.BullsAndCows.net;

import java.util.List;
import java.util.stream.Collectors;

import telran.game.BullsAndCows.BullsCowsService;
import telran.game.BullsAndCows.Move;
import telran.game.BullsAndCows.MoveResult;
import telran.net.Protocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class BullsCowsProtocol implements Protocol {
    private BullsCowsService bullsCowsService;

    public BullsCowsProtocol(BullsCowsService bullsCowsService) {
        this.bullsCowsService = bullsCowsService;
    }

    @Override
    public Response getResponse(Request request) {
        String requestType = request.requestType();
        String requestData = request.requestData();
        Response response;
        try {
            response = switch (requestType) {
                case "createNewGame" -> createNewGame();
                case "getResults" -> getResults(requestData);
                case "isGameOver" -> isGameOver(requestData);
                default -> wrongTypeResponse(requestType);
            };
        } catch (Exception e) {
            response = wrongDataResponse(e.getMessage());
        }
        return response;
    }

    private Response createNewGame() {
        Long gameId = bullsCowsService.createNewGame();
        return new Response(ResponseCode.OK, gameId.toString());
    }

    private Response getResults(String requestData) {
        String[] parts = requestData.split(";");
       long gameId = Long.parseLong(parts[0]);
        String clientMove = parts[1];
        Move move = new Move(gameId, clientMove);
        List<MoveResult> results = bullsCowsService.getResults(gameId, move);
        String resultsJson = resultsToJSON(results);
        return new Response(ResponseCode.OK, resultsJson);
    }

    private Response isGameOver(String requestData) {
        long gameId = Long.parseLong(requestData);
        boolean isOver = bullsCowsService.isGameOver(gameId);
        return new Response(ResponseCode.OK, Boolean.toString(isOver));
    }

    private Response wrongDataResponse(String message) {
        return new Response(ResponseCode.WRONG_REQUEST_DATA, message);
    }

    private Response wrongTypeResponse(String requestType) {
        return new Response(ResponseCode.WRONG_REQUEST_TYPE, requestType);
    }

    private String resultsToJSON(List<MoveResult> results) {
        return results.stream()
                .map(MoveResult::toJSON)
                .collect(Collectors.joining(";"));
    }
}

