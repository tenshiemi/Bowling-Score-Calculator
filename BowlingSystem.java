package emily.bowlingApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BowlingSystem
{

    public void welcomeMessage(String player) {
        System.out.println(String.format("Welcome to Bowlorama, %s!\nPlease choose one of the options below", player));
    }
    
    public void mainMenu() {
        System.out.println("1. Enter a player name\n2. Begin bowling\n3. Quit");
        System.out.println("Enter a number between 1-3:");
    }
    
    public void printPlayerInfo(List<Player> playerList) {
        // Prints out the number of players
        System.out.println("There are " + playerList.size() + " player(s) in the game.");
       // Prints out each player's name
       System.out.println("The players are:");
       for (Player player:playerList) {
           System.out.println(player.getName());
       }
    } // end printPlayerInfo method
    
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
        
        // Bowl second round (skipped if player got a strike)
        if (totalScore == 10){
            player.setSecondball(frame, 0);
            System.out.println("You have bowled a strike!!");
        }
        else {
            System.out.println("Enter your score for ball 2:");
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
            if (totalScore == 10) {
                System.out.println("You have bowled a spare!!");
            }
        }
        
        if ((player.checkFirstBall(frame)) == 10) {
            bonusCounter += 2;
        }
        else if (totalScore == 10) {
            bonusCounter += 1;
        }
        player.setBonusCounter(bonusCounter);
        System.out.println(player.getName() + "'s total score is " + player.checkPlayerScore());
    } // end bowlFrame method
    
    public void bowlLastFrame(Player player, int frame) throws IOException {
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
        if (bonusCounter == 3) {
            player.setPlayerScore(score + score + score);
            bonusCounter -= 2;
        }
        else {
            player.setPlayerScore(score);
        }

        // Bowl second round (skipped if player got a strike)
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
