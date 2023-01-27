package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;

public class Sound {
    private final String buy;
    private final String gainMoney;
    private final String death;
    private final String swordHit;
    private final String swordSheath;
    private final String equipItem;
    private final String victory;
    private final String levelUp;
    private final String drink;
    private final String openBag;
    private final String closeBag;
    private final String openChest;
    private final String enterDoor;
    private final String step1;
    private final String step2;

    public Sound() {
        buy = "src/Sounds/buy.wav";
        gainMoney = "src/Sounds/gain-money.wav";
        death = "src/Sounds/death.wav";
        swordHit = "src/Sounds/sword-hit.wav";
        swordSheath = "src/Sounds/sword-sheath.wav";
        equipItem = "src/Sounds/item-equip.wav";
        victory = "src/Sounds/victory.wav";
        levelUp = "src/Sounds/levelup.wav";
        drink = "src/Sounds/drink.wav";
        openBag = "src/Sounds/open-bag.wav";
        closeBag = "src/Sounds/close-bag.wav";
        openChest = "src/Sounds/chest.wav";
        enterDoor = "src/Sounds/enter-Door.wav";
        step1 = "src/Sounds/step1.wav";
        step2 = "src/Sounds/step2.wav";
    }

    public void buySound(){
        play(new File(buy));
    }

    public void gainMoneySound(){
        play(new File(gainMoney));
    }

    public void deathSound(){
        play(new File(death));
    }

    public void swordHitSound(){
        play(new File(swordHit));
    }

    public void swordSheathSound(){
        play(new File(swordSheath));
    }

    public void equipItemSound(){
        play(new File(new File(equipItem).getAbsolutePath()));
    }

    public void victorySound(){
        play(new File(victory));
    }

    public void levelUpSound(){
        play(new File(levelUp));
    }

    public void drinkSound(){
        play(new File(drink));
    }

    public void openBagSound(){
        play(new File(openBag));
    }

    public void closeBagSound(){
        play(new File(closeBag));
    }

    public void openChestSound(){play(new File(openChest));}

    public void enterDoorSound(){play(new File(enterDoor));}

    public void stepSound(){
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            play(new File(step1));
        } else {
            play(new File(step2));
        }
    }


    private void play(File file){
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            audioIn.close();
            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
