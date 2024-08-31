package filemaker;

import filemakerInterfaces.GameIDGenerator;
import filemakerInterfaces.SequenceGenerator;


import java.util.HashSet;
import java.util.Set;

public class Move {

    private long moveId;
    private String moveSequence;
    private int bulls;
    private int cows;

    public Move(GameIDGenerator idGenerator, SequenceGenerator sequenceGenerator, Game game) {
        this.moveId = idGenerator.generateGameID();
        this.moveSequence = sequenceGenerator.generateSequence();
        calculateBullsAndCows(game.getSequence(), this.moveSequence);
    }

    public Move() {
       
    }

    private void calculateBullsAndCows(String gameSequence, String moveSequence) {
        int bullsCount = 0;
        int cowsCount = 0;

         Set<Character> bullsChars = new HashSet<>();

       
        for (int i = 0; i < gameSequence.length(); i++) {
            if (gameSequence.charAt(i) == moveSequence.charAt(i)) {
                bullsCount++;
                bullsChars.add(gameSequence.charAt(i));
            }
        }

        for (int i = 0; i < moveSequence.length(); i++) {
            if (gameSequence.indexOf(moveSequence.charAt(i)) != -1 &&
                gameSequence.charAt(i) != moveSequence.charAt(i) &&
                !bullsChars.contains(moveSequence.charAt(i))) {
                cowsCount++;
            }
        }

        this.bulls = bullsCount;
        this.cows = cowsCount;
    }

    public long getMoveId() {
        return moveId;
    }

    public String getMoveSequence() {
        return moveSequence;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    @Override
    public String toString() {
        return "Move{" +
                "moveId=" + moveId +
                ", moveSequence='" + moveSequence + '\'' +
                ", bulls=" + bulls +
                ", cows=" + cows +
                '}';
    }
}
