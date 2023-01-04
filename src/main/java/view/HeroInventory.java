package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import Character.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeroInventory {
    Hero hero;
    public static void main(String[] args) {
        OpenInventory();
    }
    public static void OpenInventory(){
        JFrame f=new JFrame("Inventory");
        f.update(f.getGraphics());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public Hero openInventory(Hero hero){
        this.hero = hero;
        JFrame f=new JFrame("Inventory");
        f.update(f.getGraphics());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        f.setSize(500,625);
        f.setLayout(null);

        Container c = f.getContentPane(); //Gets the content layer
        // Weapon
        JLabel weapon = new JLabel(); //JLabel Creation
        weapon.setBounds(50, 30, 100, 100); //Sets the location of the image
        if(hero.getWeapon() == null) {
            Image dimg = getImage("src/Assets/Weapons/Swords/emptySword.tif").getScaledInstance(weapon.getWidth(), weapon.getHeight(),
                    Image.SCALE_SMOOTH);
            weapon.setIcon(new ImageIcon(dimg)); //Sets the image to be displayed as an icon
            weapon.setToolTipText("No weapon equipped"); //Sets the tooltip text
        } else {
            Image dimg = getImage("src/Assets/Weapons/Daggers/daggers (1).jpg").getScaledInstance(weapon.getWidth(), weapon.getHeight(),
                    Image.SCALE_SMOOTH);
            weapon.setIcon(new ImageIcon(dimg)); //Sets the image to be displayed as an icon
            Dimension size = weapon.getPreferredSize(); //Gets the size of the image
            weapon.setToolTipText(hero.getWeapon().toString()); //Sets the tooltip text
            if(hero.getWeapons() != null){
                JComboBox bag = new JComboBox(hero.getWeapons().toArray());
                bag.setBounds(170, 30, 300, 35);
                c.add(bag);
            }
        }
        // Armor
        JLabel armor = new JLabel(); //JLabel Creation
        armor.setBounds(50, 180, 100, 100); //Sets the location of the image
        if(hero.getArmor() == null) {
            Image dimg = getImage("src/Assets/Armor/emptyArmor.tif").getScaledInstance(armor.getWidth(), armor.getHeight(),
                    Image.SCALE_SMOOTH);
            armor.setIcon(new ImageIcon(dimg)); //Sets the image to be displayed as an icon
            armor.setToolTipText("No armor equipped"); //Sets the tooltip text
        } else {
            Image dimg2 =getImage("src/Assets/Armor/4.jpg").getScaledInstance(armor.getWidth(), armor.getHeight(),
                    Image.SCALE_SMOOTH);
            armor.setIcon(new ImageIcon(dimg2)); //Sets the image to be displayed as an icon
            Dimension size2 = armor.getPreferredSize(); //Gets the size of the image
            armor.setToolTipText(hero.getArmor().toString()); //Sets the tooltip text
            if(hero.getArmors() != null){
                JComboBox bag = new JComboBox(hero.getArmors().toArray());
                bag.setBounds(170, 180, 300, 35);
                c.add(bag);
            }
        }

        // Artifact
        JLabel artifact = new JLabel(); //JLabel Creation
        artifact.setBounds(50, 330, 100, 100); //Sets the location of the image
        if(hero.getArtifact() == null) {
            Image dimg = getImage("src/Assets/Artifact/emptyArtifact.tif").getScaledInstance(artifact.getWidth(), artifact.getHeight(),
                    Image.SCALE_SMOOTH);
            artifact.setIcon(new ImageIcon(dimg)); //Sets the image to be displayed as an icon
            artifact.setToolTipText("No artifact equipped"); //Sets the tooltip text
        } else {
            Image dimg3 =getImage("src/Assets/Armor/6.jpg").getScaledInstance(artifact.getWidth(), artifact.getHeight(),
                    Image.SCALE_SMOOTH);
            artifact.setIcon(new ImageIcon(dimg3)); //Sets the image to be displayed as an icon
            Dimension size3 = artifact.getPreferredSize(); //Gets the size of the image
            artifact.setToolTipText(hero.getArtifact().toString()); //Sets the tooltip text
            if(hero.getArtifacts() != null){
                JComboBox bag = new JComboBox(hero.getArtifacts().toArray());
                bag.setBounds(170, 330, 300, 35);
                c.add(bag);
            }
        }

        // ADD TO CONTAINER
        c.add(weapon); //Adds objects to the container
        c.add(armor);
        c.add(artifact);
        f.setVisible(true); // Exhibit the frame


        return hero;
    }




    public void refresh(JFrame f){
        f.update(f.getGraphics());
    }

}
