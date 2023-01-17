package view;

import Character.*;
import Game.AssetPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView {
    Hero hero;
    LabelCreator labelCreator = new LabelCreator();
    AssetPath ap = new AssetPath();
    //Steps, Make the panel,
    //Make containers
    // - Need a main overview container for the bottom where you see all the heros stuff.
    // - Buttons will be there to save, open inventory, etc.

    JLabel weapon;
    JLabel armor;
    JLabel artifact;
    JLabel gold;
    JLabel inventory;

    JProgressBar healthBar;
    JProgressBar XPBar;
    Boolean inventoryOpen = false;


    public GameView() {
    }

    public Hero startGameView(Hero hero){
        this.hero = hero;
        JFrame frame = new JFrame("BladeBoy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(1300, 800);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyInput(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        frame.setLayout(null);
        Container cont = frame.getContentPane();
        JLabel border = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 5, 525, 475, 250);
         weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 650, 75, 75);
         armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 650, 75, 75);
         artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 650, 75, 75);
         gold = labelCreator.createLabel(ap.Gold,hero.getGold()+"", 350, 570, 65, 65);
         inventory = labelCreator.createLabel(ap.backpack, "Inventory", 350, 650, 75, 75);
        healthBar = new JProgressBar(0, hero.getMaxHealth());
        healthBar.setValue(hero.getHealth());
        healthBar.setBounds(35, 575, 300, 25);
        healthBar.setStringPainted(true);
        healthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        healthBar.setForeground(Color.RED);
        healthBar.setString("Health: " + hero.getHealth() + "/" + hero.getMaxHealth());

        XPBar = new JProgressBar(0, hero.getNextLevelXp());
        XPBar.setValue(hero.getXp());
        XPBar.setBounds(35, 615, 300, 25);
        XPBar.setStringPainted(true);
        XPBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        XPBar.setForeground(Color.blue);
        XPBar.setString("XP: " + hero.getXp() + "/" + hero.getNextLevelXp());


        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openInventory();

            }
        });

        cont.add(weapon);
        cont.add(armor);
        cont.add(artifact);
        cont.add(healthBar);
        cont.add(XPBar);
        cont.add(gold);
        cont.add(inventory);
        cont.add(border);

        frame.setVisible(true);
        return hero;
    }


    public void update(Hero hero){
        this.hero = hero;
        if(weapon != null){
            labelCreator.update(hero.getWeapon().getIconPath(), weapon, hero.getWeapon().toString());
            //weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
        }
        if(armor != null){
            labelCreator.update("src/Assets/Armor/platemail.png", armor, hero.getArmor().toString());
            //armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 850, 75, 75);
        }
        if(artifact != null){
            labelCreator.update(hero.getArtifact().getIconPath(), artifact, hero.getArtifact().toString());
            //artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 850, 75, 75);
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
        if(gold != null){
            gold.setText(hero.getGold()+"");
            gold.setToolTipText(hero.getGold()+"");
        }
    }

    public void openInventory()  {
        HeroInventory heroInventory = new HeroInventory();
        try {
            update(heroInventory.openInventory(hero));
            inventoryOpen = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void progressChat(){

    }

    public void keyInput(char e) {
        switch (e) {
            case 'i' -> openInventory();
            case ' ' -> progressChat();
        }
    }



}
