package filemaker;

import java.util.List;

public class GameGamer {
	
	private Game game;
    private Gamer gamer;
    private List<Move> moves;
    private boolean isWinner;

    public GameGamer(Game game, Gamer gamer, List<Move> moves, boolean isWinner) {
        this.game = game;
        this.gamer = gamer;
        this.moves = moves;
        this.isWinner = isWinner;
    }

    public Game getGame() {
        return game;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
