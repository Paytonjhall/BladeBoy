package view;

import Game.AssetPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClearedDungeon {

    AssetPath ap = new AssetPath();
    LabelCreator labelCreator = new LabelCreator();
//   private JPanel ClearedDungeonPanel;
//    private JButton ContinueButton;
//    private JLabel ClearedText;
//    private JLabel HeroDamageText;
//    private JLabel HeroDamageValue;
//    private JLabel DamageTakenValue;
//    private JLabel LevelUpValue;
//    private JLabel GoldEarnedValue;
//    private JLabel EnemiesKilledValue;
//    private JLabel TilesExploredValue;
//    private JLabel TilesExploredText;
//    private JLabel GoldEarnedText;
//    private JLabel EnemiesKilledText;
//    private JLabel LevelUpText;
//    private JLabel DamageTakenText;

    public ClearedDungeon(DungeonClearedData data) {
        JFrame frame = new JFrame("ClearedDungeon");
        Container cont = frame.getContentPane();
//
//        HeroDamageValue.setText(String.valueOf(data.getHeroDamage()));
//        DamageTakenValue.setText(String.valueOf(data.getDamageTaken()));
//        GoldEarnedValue.setText(String.valueOf(data.getGoldEarned()));
//        EnemiesKilledValue.setText(String.valueOf(data.getEnemiesKilled()));
//        LevelUpValue.setText(String.valueOf(data.getLevelsGained()));
//        TilesExploredValue.setText(String.valueOf(data.getTilesExplored()));
//
        JButton ContinueButton  = new JButton("Continue");
        ContinueButton.setBounds(15, 605, 260, 50);
        ContinueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
            }
        });

        JLabel damage = labelCreator.createLabel(ap.damageIcon, data.getHeroDamage() + "", 25, 150, 50, 50);
        JLabel damageText = labelCreator.createText("Damage Dealt: " + data.getHeroDamage() , 100, 150, 150, 50);
        JLabel damageTaken = labelCreator.createLabel(ap.frogIcon, data.getDamageTaken() + "", 25, 215, 50, 50);
        JLabel damageTakenText = labelCreator.createText("Damage Taken: " + data.getDamageTaken(), 100, 215, 150, 50);
        JLabel xp = labelCreator.createLabel(ap.xpPotion, data.getDamageTaken() + "", 25, 280, 50, 50);
        JLabel xpText = labelCreator.createText("XP Earned: " + data.getXpEarned(), 100, 280, 150, 50);
        JLabel gold = labelCreator.createLabel(ap.Gold, data.getGoldEarned() + "", 25, 345, 50, 50);
        JLabel goldText = labelCreator.createText("Gold Earned: " + data.getGoldEarned(), 100, 345, 150, 50);
        JLabel enemies = labelCreator.createLabel(ap.enemyTile, data.getEnemiesKilled() + "", 25, 410, 50, 50);
        JLabel enemiesText = labelCreator.createText("Enemies Killed: " + data.getEnemiesKilled(), 100, 410, 150, 50);
        JLabel levels = labelCreator.createLabel(ap.skillpoint, data.getLevelsGained() + "", 25, 475, 50, 50);
        JLabel levelsText = labelCreator.createText("Levels Gained: " + data.getLevelsGained(), 100, 475, 150, 50);
        JLabel tiles = labelCreator.createLabel(ap.baseTile, data.getTilesExplored() + "", 25, 540, 50, 50);
        JLabel tilesText = labelCreator.createText("Tiles Explored: " + data.getTilesExplored(), 100, 540, 150, 50);
        JLabel cleared = labelCreator.createText("", 25, 25, 150, 50);



        cont.add(damage);
        cont.add(damageText);
        cont.add(damageTaken);
        cont.add(damageTakenText);
        cont.add(xp);
        cont.add(xpText);
        cont.add(gold);
        cont.add(goldText);
        cont.add(enemies);
        cont.add(enemiesText);
        cont.add(levels);
        cont.add(levelsText);
        cont.add(tiles);
        cont.add(tilesText);
        cont.add(ContinueButton);
        cont.add(cleared);


       // cont.add(ContinueButton);


        frame.setTitle("Dungeon Cleared");
        frame.setSize(300, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
