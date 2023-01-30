package view;

public class DungeonClearedData {
    int heroDamage = 0;
    int damageTaken = 0;
    int goldEarned = 0;
    int xpEarned = 0;
    int enemiesKilled = 0;
    int floorsCleared = 0;
    int tilesExplored = 0;
    int chestsOpened = 0;
    int levelsGained = 0;


    public int getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public void setXpEarned(int xpEarned) {
        this.xpEarned = xpEarned;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getFloorsCleared() {
        return floorsCleared;
    }

    public void setFloorsCleared(int floorsCleared) {
        this.floorsCleared = floorsCleared;
    }

    public int getTilesExplored() {
        return tilesExplored;
    }

    public void setTilesExplored(int tilesExplored) {
        this.tilesExplored = tilesExplored;
    }

    public int getChestsOpened() {
        return chestsOpened;
    }

    public void setChestsOpened(int chestsOpened) {
        this.chestsOpened = chestsOpened;
    }

    public int getLevelsGained() {
        return levelsGained;
    }

    public void setLevelsGained(int levelsGained) {
        this.levelsGained = levelsGained;
    }

    public void addGoldEarned(int goldEarned) {
        this.goldEarned += goldEarned;
    }

    public void addHeroDamage(int heroDamage) {
        this.heroDamage += heroDamage;
    }

    public void addDamageTaken(int damageTaken) {
        this.damageTaken += damageTaken;
    }

    public void addXpEarned(int xpEarned) {
        this.xpEarned += xpEarned;
    }


    public void addEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled += enemiesKilled;
    }

    public void addFloorsCleared(int floorsCleared) {
        this.floorsCleared += floorsCleared;
    }

    public void addTilesExplored(int tilesExplored) {
        this.tilesExplored += tilesExplored;
    }

    public void addChestsOpened(int chestsOpened) {
        this.chestsOpened += chestsOpened;
    }

    public void addLevelsGained(int levelsGained) {
        this.levelsGained += levelsGained;
    }
}
