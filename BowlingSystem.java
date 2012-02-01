/*
* This is the bowling system, which handles frames and scoring
* By Emily Chen
*/

package emily.bowlingApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BowlingSystem
{
    /*
    * Takes in the player name and prints out a personalized welcome letter
    */
    public void welcomeMessage(String player) {
        System.out.println(String.format("Welcome to Bowlorama, %s!\nPlease choose one of the options below", player));
    }
    
    /*
    * Prints the menu options
    */
    public void mainMenu() {
        System.out.println("1. Enter a player name\n2. Begin bowling\n3. Quit");
        System.out.println("Enter a number between 1-3:");
    }
    
    /*
    * Prints out player information: the number of players and their names
    */
    public void printPlayerInfo(List<Player> playerList) {
        System.out.println("There are " + playerList.size() + " player(s) in the game.");
        System.out.println("The players are:");
        for (Player player:playerList) {
           System.out.println(player.getName());
       }
    } // end printPlayerInfo method
    
    /*
    * The system for bowling frames 1-9
    * 
    * Bonus points are calculated using bonusCounter. BonusCounter is incremented by 2 for every strike
    * and 1 for every spare. It is decremented by 1 for each bonus score added. If there are two bonus
    * scores added (e.g. the two previous balls were strikes) then the bonus score is decremented by 2. 
    */
    public void bowlFrame(Player player, int frame) throws IOException {
        int totalScore = 0;
        int bonusCounter = player.checkBonusCounter();
        System.out.println("\n" + player.getName() + " is up!\n");
        // Bowl first round
        System.out.println("Enter your score for ball 1:");
        int score = getScore(totalScore);
        totalScore = totalScore + score;
        player.setFirstBall(frame, score);
        
        // Update player score
        if (bonusCounter == 1 | bonusCounter == 2) {
            player.setPlayerScore(score + score);
            bonusCounter -= 1;
        }
        else if (bonusCounter == 3) {
            player.setPlayerScore(score + score + score);
            bonusCounter -= 2;
        }
        else {
            player.setPlayerScore(score);
        }
        
        if (totalScore == 10){ // Checks for strike 
            player.setSecondball(frame, 0);
            System.out.println("You have bowled a strike!!");
        }
        else {
            System.out.println("Enter your score for ball 2:"); // Gets score for ball 2 if there was no strike
            score = getScore(totalScore);
            player.setSecondball(frame, score);
            if (bonusCounter == 1) {
                player.setPlayerScore(score + score);
                bonusCounter -= 1;
            }
            else {
                player.setPlayerScore(score);
            }
            totalScore = totalScore + score;
            if (totalScore == 10) { // Checks for spare
                System.out.println("You have bowled a spare!!");
            }
        }
        
        if ((player.checkFirstBall(frame)) == 10) { // Updates bonus counter if the player got a strike
            bonusCounter += 2;
        }
        else if (totalScore == 10) { // Updates bonus counter if the player got a spare
            bonusCounter += 1;
        }
        player.setBonusCounter(bonusCounter);
        System.out.println(player.getName() + "'s total score is " + player.checkPlayerScore());
    } // end bowlFrame method
    
    /*
    * Special system for scoring the last frame.
    * Includes bonus ball if the player got a strike or spare.
    */
    public void bowlLastFrame(Player player, int frame) throws IOException {
        int totalScore = 0;
        int bonusCounter = player.checkBonusCounter();
        System.out.println("\n" + player.getName() + " is up!\n");
        
        // Bowl first ball
        System.out.println("Enter your score for ball 1:");
        int score = getScore(totalScore);
        totalScore = totalScore + score;
        player.setFirstBall(frame, score);
        
        // Update player score
        if (bonusCounter == 1 | bonusCounter == 2) {
            player.setPlayerScore(score + score);
            bonusCounter -= 1;
        }
        if (bonusCounter == 3) {
            player.setPlayerScore(score + score + score);
            bonusCounter -= 2;
        }
        else {
            player.setPlayerScore(score);
        }

        // Bowl second ball - system for when player bowled a strike on the first ball
        if (totalScore == 10){ 
            System.out.println("You have bowled a strike!!");
            bonusCounter += 2;
            System.out.println("Enter your score for ball 2:");
            score = getScore();
            player.setSecondball(frame, score);
            if (bonusCounter == 1 | bonusCounter == 2) {
                player.setPlayerScore(score);
                bonusCounter -= 1;
            }
            if (bonusCounter == 3) {
                player.setPlayerScore(score + score);
                bonusCounter -= 2;
            }
        }
        // Bowl second ball - system for when player did not bowl a strike on the first ball
        else {
            System.out.println("Enter your score for ball 2:");
            score = getScore(totalScore);
            player.setSecondball(frame, score);
            totalScore = totalScore + score;
            if (bonusCounter == 1 | bonusCounter == 2) {
                player.setPlayerScore(score + score);
                bonusCounter -= 1;
            }
            else {
                player.setPlayerScore(score);
            }
        }
        // Bowl bonus bowl if player bowled any strikes or a spare this frame  
        if (totalScore == 10) {
            System.out.println("Strike");
            System.out.println("You have earned a bonus ball! Enter your score for the bonus ball:");
            score = getScore();
            player.setBonusBall(score);
            if (score == 10) {
                System.out.println("Strike!!!");
            }
            player.setPlayerScore(score);
        }
        
        System.out.println(player.getName() + "'s total score is " + player.checkPlayerScore());

    }
    
    /*
    * Gets the bowling score from the player
    */
    public int getScore(int totalScore) throws IOException{
        boolean validScore = false;
        int tempScore = 0;
        while (validScore == false) {
            String temp = new BufferedReader(new InputStreamReader(System.in)).readLine();
            while (temp.equals("")) {
                System.out.println("Please enter a number.");
                temp = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            tempScore = Integer.parseInt(temp);
            if ((tempScore < 0) | (tempScore + totalScore > 10) ){
                System.out.println("You have entered an invalid number. Please try again.");
            }
            else {
                validScore = true;
            }            
        } 
       return tempScore;
    } // end GetScore method
    
    /*
    * Gets the bowling scores for the bonus round
    */
    public int getScore() throws IOException{
        boolean validScore = false;
        int tempScore = 0;
        while (validScore == false) {
            String temp = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (temp == null){
                System.out.println("Please enter a number.");
            }
            tempScore = Integer.parseInt(temp);
            if (tempScore < 0 | tempScore > 10){
                System.out.println("You have entered an invalid number. Please try again.");
            }
            else {
                validScore = true;
            }            
        } 
       return tempScore;
    } // end GetScore method for bonus round
}
