public class Main {
    public static void main(String[] args) {
        final String[] NAMES = {"Joe", "shmoe"};
        final int BULLET_AMMOUNT = 10;
        final int BLANK_AMMOUNT = 3;
        final int PLAYERS = 2;
        final int AI = 4;

        Game game = new Game(PLAYERS, AI, NAMES);
        game.generateMag(BULLET_AMMOUNT, BLANK_AMMOUNT);
        System.out.println("--- List before shuffled ---");
        game.magazine.showBulletArray();
        System.out.println("--- List after shuffled ---");
        game.magazine.shuffleMag();
        game.magazine.showBulletArray();
        System.out.println("--- Names of active players ---");
        game.showActivePlayers();
    }
}
