package Character;

public class PotionBag {
    public Potion firstPotion;
    public Potion secondPotion;
    public Potion thirdPotion;

    public PotionBag(Potion firstPotion, Potion secondPotion, Potion thirdPotion) {
        this.firstPotion = firstPotion;
        this.secondPotion = secondPotion;
        this.thirdPotion = thirdPotion;
    }

    public PotionBag() {
        this.firstPotion = null;
        this.secondPotion = null;
        this.thirdPotion = null;
    }

    public void sortBag(){
        Potion tempPotion;
        if(firstPotion == null && secondPotion != null){
            tempPotion = secondPotion;
            firstPotion = tempPotion;
            secondPotion = null;
        }
        if(secondPotion == null && thirdPotion != null){
            tempPotion = thirdPotion;
            thirdPotion = null;
            secondPotion = tempPotion;
        }
    }
}
