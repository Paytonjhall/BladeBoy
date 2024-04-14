package view;
import Character.Hero;
import Character.Stats.*;
import Game.AssetPath;

import javax.swing.*;
import java.awt.*;

public class LevelUpView {
    boolean returner = false;
    Hero hero;

    public LevelUpView() {
    }

    public Hero levelUp(Hero h) {
        this.hero = h;
        if (returner) return hero;
        AssetPath assetPath = new AssetPath();
        LabelCreator labelCreator = new LabelCreator();
        // Variables:

        final int[] count = {0};
        int startConst = hero.getStats().getConstitution().value();
        int startStr = hero.getStats().getStrength().value();
        int startAgi = hero.getStats().getAgility().value();
        int startDef = hero.getStats().getDefense().value();
        int startInt = hero.getStats().getIntelligence().value();
        int startLuck = hero.getStats().getLuck().value();
        int startStatPoints = hero.statPoints;

        final int[] constUp = {0};
        int strUp = 0;
        int agiUp = 0;
        int defUp = 0;
        int intUp = 0;
        int luckUp = 0;

        JLabel background = labelCreator.createLabelWithoutHover(assetPath.levelUpView, 0, 0, 480, 480);
        JFrame frame = new JFrame("Level Up");
        Container container = frame.getContentPane();

        JLabel header = labelCreator.createHugeText("Level Up", 200, 30, 100, 50);
        container.add(header);

        JLabel statPoints = labelCreator.createLabel(assetPath.skillpoint, "Stat Points", 300, 35, 50, 50);
        JLabel statPointsValue = labelCreator.createBigText(String.valueOf(hero.statPoints), 330, 35, 50, 50);
        container.add(statPoints);
        container.add(statPointsValue);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 515);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        int statSize = 50;
        int statX = 50;
        int statY = 85;
        int statYInterval = 50;
        int upArrowX = 125;
        int arrowSize= 30;

        // Constitution
        JLabel constitution = labelCreator.createLabel(assetPath.getStat(hero.getStats().getConstitution().icon()), "Constitution", statX, statY, statSize, statSize);
        JLabel constitutionValue = labelCreator.createBigText(String.valueOf(hero.getStats().getConstitution().value()), 150, statY, 100, 50);
        JLabel constitutionUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel constitutionDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);

        statY+= statYInterval;
        // Strength
        JLabel strength = labelCreator.createLabel(assetPath.getStat(hero.getStats().getStrength().icon()), "Strength", statX, statY, statSize, statSize);
        JLabel strengthValue = labelCreator.createBigText(String.valueOf(hero.getStats().getStrength().value()), 150, statY, 100, 50);
        JLabel strengthUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel strengthDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);


        statY+= statYInterval;
        // Agility
        JLabel agility = labelCreator.createLabel(assetPath.getStat(hero.getStats().getAgility().icon()), "Agility", statX, statY, statSize, statSize);
        JLabel agilityValue = labelCreator.createBigText(String.valueOf(hero.getStats().getAgility().value()), 150, statY, 100, 50);
        JLabel agilityUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel agilityDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);


        statY+= statYInterval;

        // Defense
        JLabel defense = labelCreator.createLabel(assetPath.getStat(hero.getStats().getDefense().icon()), "Defense", statX, statY, statSize, statSize);
        JLabel defenseValue = labelCreator.createBigText(String.valueOf(hero.getStats().getDefense().value()), 150, statY, 100, 50);
        JLabel defenseUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel defenseDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);

        statY+= statYInterval;
        // Intelligence
        JLabel intelligence = labelCreator.createLabel(assetPath.getStat(hero.getStats().getIntelligence().icon()), "Intelligence", statX, statY, statSize, statSize);
        JLabel intelligenceValue = labelCreator.createBigText(String.valueOf(hero.getStats().getIntelligence().value()), 150, statY, 100, 50);
        JLabel intelligenceUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel intelligenceDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);

        statY+= statYInterval;
        // Luck
        JLabel luck = labelCreator.createLabel(assetPath.getStat(hero.getStats().getLuck().icon()), "Luck", statX, statY, statSize, statSize);
        JLabel luckValue = labelCreator.createBigText(String.valueOf(hero.getStats().getLuck().value()), 150, statY, 100, 50);
        JLabel luckUpArrow = labelCreator.createLabel(assetPath.levelUpArrow, "+1", upArrowX, statY+10, arrowSize, arrowSize);
        JLabel luckDownArrow = labelCreator.createLabel(assetPath.downArrow, "-1", upArrowX + 125, statY+10, arrowSize, arrowSize);





        // add to container
        container.add(constitution);
        container.add(constitutionValue);

        constitutionUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    //hero.getStats().getConstitution().addValue(1);
                    hero.statPoints--;
                    hero.getStats().setConstitution(new Constitution(hero.getStats().getConstitution().value() + 1));
                    constitutionValue.setText(String.valueOf(hero.getStats().getConstitution().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        constitutionDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getConstitution().value() > startConst){
                    //hero.getStats().getConstitution().subtractValue(1);
                    hero.statPoints++;
                    hero.getStats().setConstitution(new Constitution(hero.getStats().getConstitution().value() - 1));
                    constitutionValue.setText(String.valueOf(hero.getStats().getConstitution().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });
        container.add(constitutionUpArrow);
        container.add(constitutionDownArrow);



        container.add(strength);
        container.add(strengthValue);



        container.add(strengthUpArrow);
        container.add(strengthDownArrow);


        strengthUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    hero.statPoints--;
                    hero.getStats().setStrength(new Strength(hero.getStats().getStrength().value() + 1));
                    strengthValue.setText(String.valueOf(hero.getStats().getStrength().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        strengthDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getStrength().value() > startStr){
                    hero.statPoints++;
                    hero.getStats().setStrength(new Strength(hero.getStats().getStrength().value() - 1));
                    strengthValue.setText(String.valueOf(hero.getStats().getStrength().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        //TODO: DO REST OF THE STATS

        container.add(agility);
        container.add(agilityValue);

        agilityUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    hero.statPoints--;
                    hero.getStats().setAgility(new Agility(hero.getStats().getAgility().value() + 1));
                    agilityValue.setText(String.valueOf(hero.getStats().getAgility().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        agilityDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getAgility().value() > startAgi){
                    hero.statPoints++;
                    hero.getStats().setAgility(new Agility(hero.getStats().getAgility().value() - 1));
                    agilityValue.setText(String.valueOf(hero.getStats().getAgility().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        container.add(agilityUpArrow);
        container.add(agilityDownArrow);

        container.add(intelligence);
        container.add(intelligenceValue);

        intelligenceUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    hero.statPoints--;
                    hero.getStats().setIntelligence(new Intelligence(hero.getStats().getIntelligence().value() + 1));
                    intelligenceValue.setText(String.valueOf(hero.getStats().getIntelligence().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        intelligenceDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getIntelligence().value() > startInt){
                    hero.statPoints++;
                    hero.getStats().setIntelligence(new Intelligence(hero.getStats().getIntelligence().value() - 1));
                    intelligenceValue.setText(String.valueOf(hero.getStats().getIntelligence().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });
        container.add(intelligenceUpArrow);
        container.add(intelligenceDownArrow);

        container.add(luck);
        container.add(luckValue);

        luckUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    hero.statPoints--;
                    hero.getStats().setLuck(new Luck(hero.getStats().getLuck().value() + 1));
                    luckValue.setText(String.valueOf(hero.getStats().getLuck().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        luckDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getLuck().value() > startLuck){
                    hero.statPoints++;
                    hero.getStats().setLuck(new Luck(hero.getStats().getLuck().value() - 1));
                    luckValue.setText(String.valueOf(hero.getStats().getLuck().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });
        container.add(luckUpArrow);
        container.add(luckDownArrow);

        container.add(defense);
        container.add(defenseValue);

        defenseUpArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.statPoints > 0){
                    hero.statPoints--;
                    hero.getStats().setDefense(new Defense(hero.getStats().getDefense().value() + 1));
                    defenseValue.setText(String.valueOf(hero.getStats().getDefense().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });

        defenseDownArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hero.getStats().getDefense().value() > startDef){
                    hero.statPoints++;
                    hero.getStats().setDefense(new Defense(hero.getStats().getDefense().value() - 1));
                    defenseValue.setText(String.valueOf(hero.getStats().getDefense().value()));
                    statPointsValue.setText(String.valueOf(hero.statPoints));
                }
            }
        });
        container.add(defenseUpArrow);
        container.add(defenseDownArrow);






        JLabel continueButton = labelCreator.createLabel(assetPath.continueButton, "Continue", 200, 390, 100, 35);
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //hero.getStats().getConstitution().setValue(startConst + constUp[0]);
                returner = true;
                frame.dispose();
                //levelUp(hero);

            }
        });
        container.add(continueButton);
        container.add(background);
        return hero;
    }
}
