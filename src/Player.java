public class Player {
    private String name;
    private int health;
    private boolean ai;
    private boolean dead;

    public Player(String name,boolean ai) {
        this.name = name;
        this.health = 3;
        this.ai = ai;
        this.dead = false;
    }

    public Player(String name) {
        this.name = name;
        this.health = 3;
        this.ai = false;
        this.dead = false;
    }

    public String getName() {return name;}

    public int getHealth() {return health;}

    public boolean getAi() {return ai;}

    public void damage(int amount) {
        System.out.println("BANG*** " + this.name + " got shot in the head");
        health -= amount;
        if (health <= 0) { System.out.println(this.name + " has died for the last time!");}
    }

    public boolean alive() {return dead;}

    public void heal(int amount) {
        health += amount;
    }
}
