package view;

import Character.*;

import javax.swing.*;
import java.awt.*;

public class GameView {
    Hero hero;
    LabelCreator labelCreator = new LabelCreator();
    //Steps, Make the panel,
    //Make containers
    // - Need a main overview container for the bottom where you see all the heros stuff.
    // - Buttons will be there to save, open inventory, etc.

    JLabel weapon;
    JLabel armor;
    JLabel artifact;
    JLabel health;
    JProgressBar healthBar;
    JProgressBar XPBar;


    public GameView() {
    }

    public Hero startGameView(Hero hero){
        this.hero = hero;
        JFrame frame = new JFrame("BladeBoy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(1600, 1000);

        frame.setLayout(null);
        Container cont = frame.getContentPane();
        JLabel border = labelCreator.createLabel("src/Assets/UI/itemBorder.png","", 5, 725, 375, 250);
         weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
         armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 850, 75, 75);
         artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 850, 75, 75);

        healthBar = new JProgressBar(0, hero.getMaxHealth());
        healthBar.setValue(hero.getHealth());
        healthBar.setBounds(35, 775, 300, 25);
        healthBar.setStringPainted(true);
        healthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        healthBar.setForeground(Color.RED);
        healthBar.setString("Health: " + hero.getHealth() + "/" + hero.getMaxHealth());

        XPBar = new JProgressBar(0, hero.getNextLevelXp());
        XPBar.setValue(hero.getXp());
        XPBar.setBounds(35, 815, 300, 25);
        XPBar.setStringPainted(true);
        XPBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        XPBar.setForeground(Color.blue);
        XPBar.setString("XP: " + hero.getXp() + "/" + hero.getNextLevelXp());



        cont.add(weapon);
        cont.add(armor);
        cont.add(artifact);
        cont.add(healthBar);
        cont.add(XPBar);
        cont.add(border);
        frame.setVisible(true);
        return hero;
    }

    public void updateHealth(int health) {
        if(healthBar != null){
            healthBar.setValue(health);
            healthBar.setString("Health: " + health + "/" + hero.getMaxHealth());
        }
    }

    public void update(Hero hero){
        if(weapon != null){
            weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
        }
        if(armor != null){
            armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 850, 75, 75);
        }
        if(artifact != null){
            artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 850, 75, 75);
        }
        if(healthBar != null){
            healthBar.setValue(hero.getHealth());
            healthBar.setString("Health: " + hero.getHealth() + "/" + hero.getMaxHealth());
            healthBar.setMaximum(hero.getMaxHealth());
        }
        if(XPBar != null){
            XPBar.setValue(hero.getXp());
            XPBar.setString("XP: " + hero.getXp() + "/" + hero.getNextLevelXp());
            XPBar.setMaximum(hero.getNextLevelXp());
        }
    }


    class HeroPanel extends JPanel {
        public void MyCompenent() {
            setPreferredSize(new Dimension(100, 100));
        }
    }


}
