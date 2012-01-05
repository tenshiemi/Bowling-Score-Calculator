package emily.bowlingApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _playerName;
    private List<Integer> _firstBall = new ArrayList<Integer>();
    private List<Integer> _secondBall = new ArrayList<Integer>();
    private int _bonusBall;
    private int _playerScore;
    private int _bonusCounter;
    
    public Player(String name){
        _playerName = name;
    }

    public String getName(){
        return _playerName;
    }
    
    public void setFirstBall(int frame, int score) throws IOException{
        _firstBall.add(score);
    }
    
    public int checkFirstBall(int frame) {
        return _firstBall.get(frame);
    }
    
    public void setSecondball(int frame, int score) throws IOException{
        _secondBall.add(score);
    }
    
    public int checkSecondBall(int frame) {
        return _secondBall.get(frame);
    }
    
    public void setBonusBall(int score) {
        _bonusBall = score;
    }
    
    public int checkBonusBall() {
        return _bonusBall;
    }
    
    public void setPlayerScore(int score) {
        _playerScore = _playerScore + score;
    }
    
    public int checkPlayerScore() {
        return _playerScore;
    }

    public void setBonusCounter(int bonusCounter) {
        _bonusCounter = bonusCounter;
    }
    
    public int checkBonusCounter() {
        return _bonusCounter;
    }
}
