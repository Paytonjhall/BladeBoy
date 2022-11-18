package Character;

public class Hero {
    Armor armor;
    Weapon weapon;
    Artifact artifact;
    int health;

    public Hero(Armor armor, Weapon weapon, Artifact artifact, int health) {
        this.armor = armor;
        this.weapon = weapon;
        this.health = health;
        this.artifact = artifact;
    }


    public void takeDamage(int damage){
        health -= damage;
        if(health<=0){
            System.out.println("You have died");
        }
    }



    //GETTERS AND SETTERS


    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
