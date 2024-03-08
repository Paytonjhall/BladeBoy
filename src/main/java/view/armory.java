package view;
import javax.swing.*;
import Character.*;
import Character.Equipment.Armor;
import Character.Town.Armory;
import Game.AssetPath;
import Game.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class armory {
    boolean returner =false;
    Hero hero;
    AssetPath ap = new AssetPath();
    JFrame f;
    LabelCreator lc = new LabelCreator();
    Armory armory = new Armory();
    Sound sound = new Sound();
    public armory() {
    }

    public Hero visitArmory(Hero hero) {
        List<Armor> armors = new ArrayList<>();
        this.hero = hero;
        if (returner) return hero;
        f = new JFrame("BlackSmith");
        f.update(f.getGraphics());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(500, 800);
        f.setLayout(null);
        Container container = f.getContentPane(); //Gets the content layer
        JLabel heroGold = lc.createLabel(ap.Gold, hero.getGold() + "", 400, 25, 50, 50);
        container.add(heroGold);
        JLabel heroGoldText = lc.createText(hero.getGold() + "", 450, 10, 200, 100);
        container.add(heroGoldText);
        JLabel blacksmithLabel = lc.createLabelWithoutHover(ap.barbarianIcon, 40, 20, 75, 75);
        container.add(blacksmithLabel);
        JLabel blacksmithText = lc.createText("Welcome to my shop, check out my stock:", 125, 20, 375, 75);
        container.add(blacksmithText);
        if (armors.isEmpty()) armors = armory.getItems(hero);
        int i = 0;
        for (Armor armor : armors) {
            JLabel label = lc.createLabel(armor.getIconPath(), "Buy?", 50, 100 + i * 125, 100, 100);
            JLabel armorName = lc.createText(armor.getName(), 175, 105 + i * 125, 250, 35);
            JLabel gold = lc.createLabel(ap.Gold, armor.getValue() + "", 175, 135 + i * 125, 30, 30);
            JLabel goldText = lc.createText(armor.getValue() + "", 215, 135 + i * 125, 200, 35);
            JLabel damage = lc.createLabel(ap.damageIcon, armor.getArmorRating() + "", 175, 170 + i * 125, 30, 30);
            JLabel damageText = lc.createText(armor.getArmorRating() + "", 215, 170 + i * 125, 200, 35);
            i++;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (armory.itemBuyable(hero, armor)) {
                        sound.buySound();
                        hero.setGold(hero.getGold() - armor.getValue());
                        hero.addToBag(armor);
                        armory.removeArmor(armor);
                        f.dispose();
                        visitArmory(hero);
                    }
                }
            });

            JButton back = new JButton("Exit Armory");

            back.setBounds(100, 710, 150, 35);
            back.addActionListener(e -> {
                returner = true;
                f.dispose();
                try {
                    visitArmory(hero);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            container.add(label);
            container.add(armorName);
            container.add(gold);
            container.add(goldText);
            container.add(damage);
            container.add(damageText);
            container.add(back);
        }
        f.setVisible(true);
        return hero;
    }
}
