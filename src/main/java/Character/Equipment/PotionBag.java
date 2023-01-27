package Character.Equipment;

import Character.Equipment.Potion;

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

    public boolean havePotions(){
        if(firstPotion != null || secondPotion != null || thirdPotion != null){
            return true;
        }
        return false;
    }

    public void printPotions(){
        System.out.println("[0]: return");
        if(firstPotion != null){
            System.out.println("[1]: " + firstPotion);
        }
        if(secondPotion != null){
            System.out.println("[2]: " + secondPotion);
        }
        if(thirdPotion != null){
            System.out.println("[3]: " + thirdPotion);
        }
    }

    public Potion getPotion(int i){
        if(i == 1 && firstPotion != null){
            return firstPotion;
        }
        else if(i == 2 && secondPotion != null){
            return secondPotion;
        }
        else if(i == 3 && thirdPotion != null){
            return thirdPotion;
        }
        else return null;
    }

    public Potion getPotion(String name){
        if(firstPotion != null && firstPotion.toString().equals(name)){
            return firstPotion;
        }
        else if(secondPotion != null && secondPotion.toString().equals(name)){
            return secondPotion;
        }
        else if(thirdPotion != null && thirdPotion.toString().equals(name)){
            return thirdPotion;
        }
        else return null;
    }

    public void addPotion(Potion potion){
        if(firstPotion == null){
            firstPotion = potion;
        }
        else if(secondPotion == null){
            secondPotion = potion;
        }
        else if(thirdPotion == null){
            thirdPotion = potion;
        }
        else {
            System.out.println("Potion bag is full");
        }
    }

    public int getPotionCount(){
        int count = 0;
        if(firstPotion != null){
            count++;
        }
        if(secondPotion != null){
            count++;
        }
        if(thirdPotion != null){
            count++;
        }
        return count;
    }

    public void removePotion(Potion potion){
        if(potion == firstPotion) firstPotion = null;
        else if(potion == secondPotion) secondPotion = null;
        else if(potion == thirdPotion) thirdPotion = null;
        else System.out.println("Potion not found");
        sortBag();
    }
}
