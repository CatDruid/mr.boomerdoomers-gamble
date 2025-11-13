import java.util.ArrayList;

public class TurnLogic {
    private ArrayList<Player> mainArray;
    private ArrayList<Player> turnArray;
    private Player currentPlayer;
    private int turnCounter;
    private int roundCounter;

    public TurnLogic(ArrayList<Player> mainArray) {
        this.mainArray = mainArray;
        this.turnCounter = 0;
        this.roundCounter = 0;
    }

    public Player getPlayersTurn() {return currentPlayer;}
    public int getRoundCount() {return roundCounter;}

    public void nextPlayer() {

        if (turnCounter < mainArray.size()){
            currentPlayer = mainArray.get(turnCounter);
            turnCounter++;
        } else {
            turnCounter = 0;
            roundCounter++;
            currentPlayer = mainArray.get(turnCounter);
        }
    }
}
