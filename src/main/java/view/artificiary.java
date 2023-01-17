package view;

import Character.Town.Armory;
import Game.AssetPath;

import javax.swing.*;
import Character.*;
import Character.Town.Armory;
import Character.Town.Blacksmith;
import Game.AssetPath;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class artificiary {
    boolean returner =false;
    Hero hero;
    AssetPath ap = new AssetPath();
    JFrame f;
    LabelCreator lc = new LabelCreator();
    Armory armory = new Armory();
    public Hero visitArtificiary(Hero hero) {
        List<Artifact> artifacts = new ArrayList<>();
        this.hero = hero;
        if (returner) return hero;
        f = new JFrame("Artificiary");
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
        JLabel blacksmithLabel = lc.createLabelWithoutHover(ap.viking, 40, 20, 75, 75);
        container.add(blacksmithLabel);
        JLabel blacksmithText = lc.createText("Welcome to my shop, check out my stock:", 125, 20, 375, 75);
        container.add(blacksmithText);
        if (artifacts.isEmpty()) artifacts = ; //TODO: MAKE ARTIFACT STORE
        int i = 0;
        for (Artifact artifact : artifacts) {
            JLabel label = lc.createLabel(artifact.getIconPath(), "Buy?", 50, 100 + i * 125, 100, 100);
            JLabel armorName = lc.createText(artifact.getName(), 175, 105 + i * 125, 250, 35);
            JLabel gold = lc.createLabel(ap.Gold, artifact.getValue() + "", 175, 135 + i * 125, 30, 30);
            JLabel goldText = lc.createText(artifact.getValue() + "", 215, 135 + i * 125, 200, 35);
            JLabel damage = lc.createLabel(ap.damageIcon, artifact.getType() + " " + artifact.getAmplifier(), 175, 170 + i * 125, 30, 30);
            JLabel damageText = lc.createText(artifact.getType() + " " + artifact.getAmplifier(), 215, 170 + i * 125, 200, 35);
            i++;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (armory.itemBuyable(hero, artifact)) { //TODO: FIX THIS
                        hero.setGold(hero.getGold() - artifact.getValue());
                        //armory.removeArmor(artifact);
                        f.dispose();
                        visitArtificiary(hero);
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
