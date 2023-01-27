package view;
import javax.swing.*;
import Character.*;
import Character.Equipment.Weapon;
import Character.Town.Blacksmith;
import Game.AssetPath;
import Game.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class blacksmith {
    boolean returner =false;
    Hero hero;
    AssetPath ap = new AssetPath();
    JFrame f;
    LabelCreator lc = new LabelCreator();
    Blacksmith blacksmith = new Blacksmith();
    Sound sound = new Sound();
    public blacksmith() {
    }

    public Hero visitBlackSmith(Hero hero){
        List<Weapon> weapons = new ArrayList<>();
        this.hero = hero;
        if(returner) return hero;
        f = new JFrame("BlackSmith");
        f.update(f.getGraphics());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(500,800);
        f.setLayout(null);
        Container container = f.getContentPane(); //Gets the content layer
        JLabel heroGold = lc.createLabel(ap.Gold,hero.getGold()+"", 400, 25, 50, 50);
        container.add(heroGold);
        JLabel heroGoldText = lc.createText(hero.getGold()+"", 450, 10, 200, 100);
        container.add(heroGoldText);
        JLabel blacksmithLabel = lc.createLabelWithoutHover(ap.blacksmith, 40, 20, 75, 75);
        container.add(blacksmithLabel);
        JLabel blacksmithText = lc.createText("Welcome to my shop, check out my stock:", 125, 20, 375, 75);
        container.add(blacksmithText);
        if(weapons.isEmpty()) weapons = blacksmith.getItems(hero);
        int i = 0;
        for(Weapon weapon: weapons){
            JLabel label =lc.createLabel(weapon.getIconPath(), "Buy?", 50, 100 + i *125, 100, 100);
            JLabel weaponName = lc.createText(weapon.getName(),175, 105 +i *125, 250, 35);
            JLabel gold = lc.createLabel(ap.Gold,weapon.getValue()+"",175, 135 + i *125, 30, 30);
            JLabel goldText = lc.createText(weapon.getValue()+"", 215, 135 +i * 125, 200, 35  );
            JLabel damage = lc.createLabel(ap.damageIcon, weapon.getWeaponDamage()+"", 175,170+i*125, 30, 30);
            JLabel damageText = lc.createText(weapon.getWeaponDamage()+"", 215, 170 +i * 125, 200, 35  );
            i++;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(blacksmith.itemBuyable(hero, weapon)){
                        sound.buySound();
                        hero.setGold(hero.getGold() - weapon.getValue());
                        blacksmith.removeWeapon(weapon);
                        f.dispose();
                        visitBlackSmith(hero);
                    }
                }
            });

            JButton back = new JButton("Exit Blacksmith");

            back.setBounds(100, 710, 150, 35);
            back.addActionListener(e -> {
                returner = true;
                f.dispose();
                try {
                    visitBlackSmith(hero);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            container.add(label);
            container.add(weaponName);
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
