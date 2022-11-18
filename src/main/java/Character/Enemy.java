package Character;

public class Enemy {
    int health;
    int damage;
    double armorRating;
    String name;

    public Enemy(String name, int health, int damage, double armorRating) {
        this.health = health;
        this.damage = damage;
        this.armorRating = armorRating;
        this.name = name;
    }

    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println(name + " has died");
        }
    }
}
