package view;

import Character.Equipment.Artifact;
import Character.Town.Artificiary;
import Game.AssetPath;

import javax.swing.*;
import Character.*;
import Game.Sound;

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
    Artificiary artifactStore= new Artificiary();
    Sound sound = new Sound();
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
        JLabel wizardLabel = lc.createLabelWithoutHover(ap.mage, 40, 20, 75, 75);
        container.add(wizardLabel);
        JLabel wizardText = lc.createText("Welcome to my shop, check out my stock:", 125, 20, 375, 75);
        container.add(wizardText);
        if (artifacts.isEmpty()) artifacts = artifactStore.getItems(hero); //TODO: MAKE ARTIFACT STORE
        int i = 0;
        for (Artifact artifact : artifacts) {
            JLabel label = lc.createLabel(artifact.getIconPath(), "Buy?", 50, 100 + i * 125, 100, 100);
            JLabel artifactName = lc.createText(artifact.getName(), 175, 105 + i * 125, 250, 35);
            JLabel gold = lc.createLabel(ap.Gold, artifact.getValue() + "", 175, 135 + i * 125, 30, 30);
            JLabel goldText = lc.createText(artifact.getValue() + "", 215, 135 + i * 125, 200, 35);
            JLabel artifactEffect = lc.createLabel(ap.damageIcon, artifact.getType() + " " + artifact.getAmplifier(), 175, 170 + i * 125, 30, 30);
            JLabel artifactEffectText = lc.createText(artifact.getType() + " " + artifact.getAmplifier(), 215, 170 + i * 125, 200, 35);
            i++;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (artifactStore.itemBuyable(hero, artifact)) { //TODO: FIX THIS
                        sound.buySound();
                        hero.setGold(hero.getGold() - artifact.getValue());
                        artifactStore.removeArtifact(artifact);
                        f.dispose();
                        visitArtificiary(hero);
                    }
                }
            });

            JButton back = new JButton("Exit Artificiary");

            back.setBounds(100, 710, 150, 35);
            back.addActionListener(e -> {
                returner = true;
                f.dispose();
                try {
                    visitArtificiary(hero);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            container.add(label);
            container.add(artifactName);
            container.add(gold);
            container.add(goldText);
            container.add(artifactEffect);
            container.add(artifactEffectText);
            container.add(back);
        }
        f.setVisible(true);
        return hero;
    }
}
