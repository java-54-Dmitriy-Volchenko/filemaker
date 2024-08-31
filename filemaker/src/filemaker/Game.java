package filemaker;

import java.time.LocalDateTime;

import filemakerInterfaces.DateTimeGenerator;
import filemakerInterfaces.GameIDGenerator;
import filemakerInterfaces.SequenceGenerator;

public class Game {
    private long gameID;
    private LocalDateTime dateTime;
    private String sequence;
    private boolean is_finished = true;

    public Game(GameIDGenerator idGenerator, DateTimeGenerator dateTimeGenerator, SequenceGenerator sequenceGenerator, boolean is_finished) {
        this.gameID = idGenerator.generateGameID();
        this.dateTime = dateTimeGenerator.generateDateTime();
        this.sequence = sequenceGenerator.generateSequence();
        this.is_finished = is_finished;
    }

    public Game() {
    }

    public long getGameID() {
        return gameID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getSequence() {
        return sequence;
    }

    public boolean isIs_finished() {
        return is_finished;
    }
    
    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", dateTime=" + dateTime +
                ", sequence='" + sequence + '\'' +
                ", is_finished=" + is_finished +
                '}';
    }
}