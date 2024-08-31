package filemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileMaker {
    
   
    private static final int NUMBER_OF_ENTRIES = 50;

    public static void main(String[] args) {
       
        MakeGameGamerSession session = new MakeGameGamerSession();
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {
            session.createGameGamerEntry();
        }
        Map<Long, GameGamer> gameGamerMap = session.getGameGamerMap();

        try {
            saveToCsv(gameGamerMap);
            System.out.println("CSV files have been created successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to CSV files: " + e.getMessage());
        }
    }

    public static void saveToCsv(Map<Long, GameGamer> gameGamerMap) throws IOException {
     
        try (FileWriter gameWriter = new FileWriter("game.csv")) {
           
            for (GameGamer gameGamer : gameGamerMap.values()) {
                Game game = gameGamer.getGame();
                gameWriter.write(game.getGameID() + "," +
                        game.getDateTime() + "," +
                        game.getSequence() + "," +
                        game.isIs_finished() + "\n");
            }
        }

       
        writeUniqueGamerCsv("gamer.csv", gameGamerMap);

   
        try (FileWriter gameGamerWriter = new FileWriter("GameGamer.csv")) {
           
            for (Map.Entry<Long, GameGamer> entry : gameGamerMap.entrySet()) {
                Long gameGamerIdCounter = entry.getKey();
                GameGamer gameGamer = entry.getValue();
                Game game = gameGamer.getGame();
                Gamer gamer = gameGamer.getGamer();
                gameGamerWriter.write(gameGamerIdCounter + "," +
                        game.getGameID() + "," +
                        gamer.getName() + "," +
                        gameGamer.isWinner() + "\n");
            }
        }

      
        try (FileWriter moveWriter = new FileWriter("Move.csv")) {
         
            for (Map.Entry<Long, GameGamer> entry : gameGamerMap.entrySet()) {
                Long gameGamerIdCounter = entry.getKey();
                GameGamer gameGamer = entry.getValue();
                for (Move move : gameGamer.getMoves()) {
                    moveWriter.write( move.getMoveId() + "," +
                    		  move.getMoveSequence() + "," +
                              move.getBulls() + "," +
                              move.getCows() + "," +
                              gameGamerIdCounter +"\n");
                }
            }
        }
    }

    private static void writeUniqueGamerCsv(String fileName, Map<Long, GameGamer> gameGamerMap) throws IOException {
        Set<String> uniqueNames = new HashSet<>();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (GameGamer gameGamer : gameGamerMap.values()) {
                Gamer gamer = gameGamer.getGamer();
                String name = gamer.getName();
                
             
                if (uniqueNames.add(name)) {  
                    String gamerData = name + "," + gamer.getBirthdate();
                    writer.write(gamerData + "\n");
                }
            }
        }
       }
        
    }