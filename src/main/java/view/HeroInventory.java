package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import Character.*;
import Character.Equipment.Armor;
import Character.Equipment.Artifact;
import Character.Equipment.Potion;
import Character.Equipment.Weapon;
import Game.AssetPath;
import Game.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class HeroInventory {
    boolean returner =false;
    Hero hero;
    AssetPath ap = new AssetPath();
    JFrame f;
    LabelCreator lc = new LabelCreator();
    Sound sound = new Sound();

    int sizeX = 70;
    int sizeY = 70;
    int comboX = 190;
    public static void main(String[] args) {
        OpenInventory();
    }
    public static void OpenInventory(){
        JFrame f=new JFrame("Inventory");
        f.update(f.getGraphics());

//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        f.setSize(500,500);
        f.setLayout(null);


//        frame.setTitle("Add Image"); //Add the title to frame
//        frame.setLayout(null); //Terminates default flow layout
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
//        frame.setBounds(100, 200, 350, 300); //Sets the position of the frame

        Container c = f.getContentPane(); //Gets the content layer
        JLabel head = new JLabel(); //JLabel Creation
        head.setBounds(50, 30, 100, 100); //Sets the location of the image
        Image dimg =getImage("src/Assets/Armor/2.jpg").getScaledInstance(head.getWidth(), head.getHeight(),
                Image.SCALE_SMOOTH);
        head.setIcon(new ImageIcon(dimg)); //Sets the image to be displayed as an icon
        Dimension size = head.getPreferredSize(); //Gets the size of the image
        head.setToolTipText("Head Armor Piece"); //Sets the tooltip text

        JLabel chest = new JLabel(); //JLabel Creation
        chest.setBounds(50, 180, 100, 100); //Sets the location of the image
        Image dimg2 =getImage("src/Assets/Armor/4.jpg").getScaledInstance(chest.getWidth(), chest.getHeight(),
                Image.SCALE_SMOOTH);
        chest.setIcon(new ImageIcon(dimg2)); //Sets the image to be displayed as an icon
        Dimension size2 = chest.getPreferredSize(); //Gets the size of the image
        chest.setToolTipText("Chest Armor Piece"); //Sets the tooltip text

        JLabel legs = new JLabel(); //JLabel Creation
        legs.setBounds(50, 330, 100, 100); //Sets the location of the image
        Image dimg3 =getImage("src/Assets/Armor/6.jpg").getScaledInstance(legs.getWidth(), legs.getHeight(),
                Image.SCALE_SMOOTH);
        legs.setIcon(new ImageIcon(dimg3)); //Sets the image to be displayed as an icon
        Dimension size3 = legs.getPreferredSize(); //Gets the size of the image
        legs.setToolTipText("Leg Armor Piece"); //Sets the tooltip text

        c.add(head); //Adds objects to the container
        c.add(chest);
        c.add(legs);
        f.setVisible(true); // Exhibit the frame
    }


    public static BufferedImage getImage (String imagePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public Hero openInventory(Hero hero) throws Exception {
        this.hero = hero;
        if(returner) {
            //hero.wait = false;
            return hero;
        }

        //Make a new JFrame
        f=new JFrame("Inventory");
        f.update(f.getGraphics());
        // f.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(520,750);
        f.setLayout(null);
        Container container = f.getContentPane(); //Gets the content layer


        // Header:
        JLabel header = lc.createHugeText("Inventory", 100, 38, 300, 50);
        container.add(header);

        // Weapon
        int weaponx = 105;
        int weapony = 190;
        JLabel weapon = new JLabel(); //JLabel Creation
         //Sets the location of the image
        if(hero.getWeapon() == null) {
            weapon = lc.createLabel("src/Assets/Weapons/Swords/emptySword.tif", "No weapon equipped", weaponx, weapony, sizeX, sizeY);

        } else {
            weapon = lc.createLabel(hero.getWeapon().getIconPath(), hero.getWeapon().hoverString(), weaponx, weapony, sizeX, sizeY);
        }
            if(hero.getWeapons() != null){
                JComboBox bag = new JComboBox(hero.getWeapons().toArray());
                bag.setBounds(comboX, weapony - 15, 285, 35);
                container.add(bag);
                bag.addActionListener(e -> {
                    hero.equipWeapon((Weapon) bag.getSelectedItem());
                    f.dispose();
                    try {
                        openInventory(hero);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }


        // Armor
        int armorX = 105;
        int armorY = 305;
        JLabel armor = new JLabel(); //JLabel Creation
        armor.setBounds(50, 180, 100, 100); //Sets the location of the image
        if(hero.getArmor() == null) {
            armor = lc.createLabel("src/Assets/Armor/emptyArmor.tif", "No armor equipped", armorX, armorY, sizeX, sizeY);
        } else {
            //armor = lc.createLabel("src/Assets/Armor/armor4.png", hero.getArmor().hoverString(),50, 180, 100, 100);
            armor = lc.createLabel(hero.getArmor().getIconPath(), hero.getArmor().hoverString(),armorX, armorY, sizeX, sizeY);
        }
            if(hero.getArmors() != null){

                JComboBox bag = new JComboBox(hero.getArmors().toArray());
                bag.setBounds(comboX, armorY - 15, 285, 35);
                container.add(bag);
                bag.addActionListener(e -> {
                    hero.equipArmor((Armor) bag.getSelectedItem());
                    f.dispose();
                    try {
                        openInventory(hero);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }


        // Artifact
        int artifactX = 105;
        int artifactY = 415;
        JLabel artifact = new JLabel(); //JLabel Creation
        artifact.setBounds(50, 330, 100, 100); //Sets the location of the image
        if(hero.getArtifact() == null) {
            artifact = lc.createLabel("src/Assets/Artifact/emptyArtifact.tif", "No artifact equipped", artifactX, artifactY, sizeX, sizeY);
        } else {
            artifact = lc.createLabel(hero.getArtifact().getIconPath(), hero.getArtifact().hoverString(),artifactX, artifactY, sizeX, sizeY);
        }
            if(hero.getArtifacts() != null){
                JComboBox bag = new JComboBox(hero.getArtifacts().toArray());
                bag.setBounds(comboX, artifactY - 15, 285, 35);
                container.add(bag);
                bag.addActionListener(e -> {
                    hero.equipArtifact((Artifact) bag.getSelectedItem());
                    f.dispose();
                    try {
                        openInventory(hero);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

        // Gold
        JLabel gold = new JLabel(); //JLabel Creation
        gold = lc.createLabel(ap.Gold, "Gold: " + hero.getGold(), 80, 520, 45, 45);
        JTextField goldAmount = new JTextField();
        goldAmount.setBounds(140, 525, 150, 35);
        goldAmount.setText(hero.getGold() + "");
        goldAmount.setEditable(false);
        // Potions
        List<JLabel> potions = new ArrayList<>();
        int potionX = 387; // Gross number but lol
        int potionY = 400;
        for(int i = 0; i < 3; i++){
            potionY += 65;
            JLabel potion = new JLabel();
            Potion currentPotion = hero.getPotionBag().getPotion(i+1);
            potion.setBounds(potionX, potionY, 50, 50);
            if(currentPotion!=null) {
                String name = hero.getPotionBag().getPotion(i+1).toString();
                Image dimg5 = getImage(currentPotion.getIconPath()).getScaledInstance(potion.getWidth(), potion.getHeight(),
                        Image.SCALE_SMOOTH);
                potion.setIcon(new ImageIcon(dimg5)); //Sets the image to be displayed as an icon
                Dimension size5 = potion.getPreferredSize(); //Gets the size of the image
                potion.setToolTipText(currentPotion.toString()); //Sets the tooltip text
                potion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        hero.usePotion(hero.getPotionBag().getPotion(name));
                        f.dispose();
                        try {
                            openInventory(hero);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } else {
                //Add empty potion icon
                Image dimg5 = getImage(ap.emptyPotion).getScaledInstance(potion.getWidth(), potion.getHeight(),
                        Image.SCALE_SMOOTH);
                potion.setIcon(new ImageIcon(dimg5)); //Sets the image to be displayed as an icon
                Dimension size5 = potion.getPreferredSize(); //Gets the size of the image
                potion.setToolTipText("Empty potion"); //Sets the tooltip text
            }
            potions.add(potion);
        }

        JLabel back = lc.createLabel(ap.closeButton, "Close Inventory", 75, 600, 200, 50);
        //JButton back = new JButton("Close Inventory");

        //back.setBounds(75, 650, 150, 35);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sound.closeBagSound();
                returner = true;
                f.dispose();
                //hero.wait = false;
            }
        });
//        back.addActionListener(e -> {
//            sound.closeBagSound();
//            returner = true;
//            f.dispose();
//            try {
//                openInventory(hero);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });

        // ADD TO CONTAINER
        container.add(weapon); //Adds objects to the container
        container.add(armor);
        container.add(artifact);
        container.add(gold);
        container.add(goldAmount);
        container.add(back);
        for(JLabel potion : potions){
            container.add(potion);
        }

        JLabel background = lc.createLabelWithoutHover(ap.inventoryBackground,  10, 10, 500, 700);
        container.add(background);

        f.setVisible(true); // Exhibit the frame
        return hero;
    }

    public boolean isOpen(){
        if(f.isVisible()){
            return true;
        } else {
            return false;
        }
    }




    public void refresh(JFrame f){
        f.update(f.getGraphics());
    }

}
