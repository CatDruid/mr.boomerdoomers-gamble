import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    
    private String[] aiNames = {"null", "John Gaming", "Erik", "Andreas", "Dawg", "Bodil", "Hjalmar", "Gertrud"};
    private ArrayList<Player> playerArray = new ArrayList<Player>();
    public Magazine magazine;
    

    public Game(int playerAmount, int aiAmount, String[] nameArray) {

        if (playerAmount <= nameArray.length) {
            for (int i = 0; i < playerAmount; i++) {
                Player playerClass = new Player(nameArray[i], false);
                playerArray.add(playerClass);
            }

            for (int i = 0; i < aiAmount; i++) {
                Player playerClass = new Player(aiNames[(int) ((Math.random() * (aiNames.length - 1)) + 1)], true);
                playerArray.add(playerClass);
            }

        }


    }

    public void generateMag(int totalAmount, int blankNR) {
        Magazine magazine = new Magazine(totalAmount,blankNR);
        this.magazine = magazine;
    }

    public void shoot(Player player, Player playerShot) {

        System.out.println(player.getName() + " shoots at " + playerShot.getName());

        if (this.magazine.getNextRound().getLive() == true) {
            playerShot.damage(1);
        } else {
            System.out.println("CLICK*** " + playerShot.getName() + " didn't die");
        }   
        if (player == playerShot) { }

    }

    public void showActivePlayers() {
        Iterator<Player> playeritr = playerArray.iterator();
        
        while (playeritr.hasNext()) {
            System.out.println(playeritr.next().getName());
        }
    }
}
