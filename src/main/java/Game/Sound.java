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

    public Sound() {
        //this.buy = getClass().getClassLoader().getResource("src/Sounds/buy.wav");
        //this.gainMoney = getClass().getClassLoader().getResource("src/Sounds/gain-money.wav");
        //death = getClass().getResource("src/Sounds/death.wav");
        //swordHit = getClass().getResource("src/Sounds/sword-hit.wav");
        //swordSheath = getClass().getResource("src/Sounds/sword-sheath.wav");
        //equipItem = getClass().getResource("src/Sounds/item-equip.wav");
        //victory = getClass().getResource("src/Sounds/victory.wav");
        //levelUp = getClass().getResource("src/Sounds/levelup.wav");
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
