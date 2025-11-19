import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class Game {
    
    private String[] aiNames = {"null", "John Gaming", "Erik", "Andreas", "Dawg", "Bodil", "Hjalmar", "Gertrud"};
    private ArrayList<Player> playerArray = new ArrayList<Player>();
    public Magazine magazine;
    private GameWindow gui;
    

    public Game(int playerAmount, int aiAmount, String[] nameArray) {
        
        ArrayList<Image> doomguy = new ArrayList<>();
        doomguy.add(new ImageIcon("graphics/STFDEAD0BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFST01BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFST31BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFST40BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFEVL0BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFEVL3BIG.png").getImage());
        doomguy.add(new ImageIcon("graphics/STFEVL4BIG.png").getImage());                      
        Player playerClass1 = new Player("Jan Doom", 0, 0, false, doomguy);
        playerArray.add(playerClass1);

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

    public Player calculateWinner(ArrayList<Player> playerList) {
        ArrayList<Player> alivePlayers = new ArrayList<>();

        for (int i = playerList.size()-1; i >= 0; i--) {
            if (playerList.get(i).dead() == false) {
                alivePlayers.add(playerList.get(i));
            }
        }

        if (alivePlayers.size() == 1) {
            System.out.println("Game Finished. Winner is :" + alivePlayers.get(0).getName());
            return alivePlayers.get(0);
        } else {
            return null;
        }
    }

    public void generateMag(int totalAmount, int blankNR) {
        Magazine magazine = new Magazine(totalAmount,blankNR);
        this.magazine = magazine;
    }

    public void shoot(Player player, Player playerShot) {

        if (this.magazine.getMagazine().size() == 0) {System.out.println("Need to reload");} else {
            System.out.println(player.getName() + " shoots at " + playerShot.getName());
            if (this.magazine.getNextRound().getLive() == true) {
                playerShot.damage(1);
            } else {
                System.out.println("CLICK*** " + playerShot.getName() + " didn't die");
            }   
            if (player == playerShot) { }
        }
    }

    public void showActivePlayers() {
        Iterator<Player> playeritr = playerArray.iterator();
        
        while (playeritr.hasNext()) {
            System.out.println(playeritr.next().getName());
        }
    }

    public ArrayList<Player> getPlayerArray () {return this.playerArray;}
    public GameWindow gui () {return this.gui;}

    public void startUi() {
        GameWindow gui = new GameWindow(1270,720,this);
        gui.ui().setGameState("joe");
        gui.ui().setPlayers(playerArray);
    }
}
