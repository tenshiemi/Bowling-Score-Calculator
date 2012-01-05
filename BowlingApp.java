package emily.bowlingApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BowlingApp
{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        final BowlingSystem bowlingSystem = new BowlingSystem();
        List<Player> playerList = new ArrayList<Player>(); // List of players in the game
        
        String selection = "";
        String name;
        
        System.out.println("Please enter your name:");
        name = new BufferedReader(new InputStreamReader(System.in)).readLine();
        playerList.add(new Player(name));
        
        while (!selection.equals("2")){
            
            bowlingSystem.welcomeMessage(playerList.get(0).getName());
            bowlingSystem.mainMenu();

            selection = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            switch(Integer.parseInt(selection)) {
            case 1:
                // Adds new players
                System.out.println("Please enter the player name");
                name = new BufferedReader(new InputStreamReader(System.in)).readLine();
                playerList.add(new Player(name));
                break;
            case 2:
               bowlingSystem.printPlayerInfo(playerList);
               break;
            case 3:
               System.out.println("Goodbye.");
               System.exit(0);
            default:
               System.out.println("You have entered an invalid selection. Please try again.");
               System.out.println("");
            } // end menu switch
        } // end menu loop

        // Start game!
        for (int i=0; i<9; i++) {
            System.out.println("\n>>> Frame " + (i + 1) + " begins now! <<<");
            for (Player player:playerList) {
                bowlingSystem.bowlFrame(player, i);
            }
        }
        
        // Play final frame
        System.out.println("\n>>> The Final Frame begins now! <<<");
        for (Player player:playerList) {
            bowlingSystem.bowlLastFrame(player, 9);
        }
        
        // Print scores
        final ScoreBoard scoreboard = new ScoreBoard();
        scoreboard.printScores(playerList);
    }

}
