package telran.game.BullsAndCows;

import org.json.JSONObject;

public class MoveResult {
    private String clientSequence;
    private int bulls;
    private int cows;

    public MoveResult(String clientSequence, int bulls, int cows) {
        this.clientSequence = clientSequence;
        this.bulls = bulls;
        this.cows = cows;
    }

    public String getClientSequence() {
        return clientSequence;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    public void setClientSequence(String clientSequence) {
        this.clientSequence = clientSequence;
    }

    public String toJSON() {
        return String.format("{\"clientSequence\":\"%s\",\"bulls\":%d,\"cows\":%d}", clientSequence, bulls, cows);
    }

    public static MoveResult fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String clientSequence = jsonObject.getString("clientSequence");
        int bulls = jsonObject.getInt("bulls");
        int cows = jsonObject.getInt("cows");
        return new MoveResult(clientSequence, bulls, cows);
    }
}

