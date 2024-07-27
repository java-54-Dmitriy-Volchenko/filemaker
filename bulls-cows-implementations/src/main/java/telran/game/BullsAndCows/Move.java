package telran.game.BullsAndCows;

public class Move {
    private long gameId;
    private String clientSequence;

    public Move(long gameId, String clientSequence) {
        this.gameId = gameId;
        this.clientSequence = clientSequence;
    }

    public long getGameId() {
        return gameId;
    }

    public String getClientSequence() {
        return clientSequence;
    }

    public String toJSON() {
        return String.format("{\"gameId\":%d,\"clientSequence\":\"%s\"}", gameId, clientSequence);
    }
    
}

