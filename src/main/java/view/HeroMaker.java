package view;

import Character.Mystics.*;
import Character.Stats.Stats;
import Game.AssetPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Character.Equipment.Armor;
import Character.*;
import Character.Equipment.Artifact;
import Character.Equipment.Weapon;
import Character.Class;
import Character.Town.ArtifactGenerator;
import Game.NewGame;

import java.util.ArrayList;


public class HeroMaker {
    private JLabel Archer;
    private JLabel Barbarian;
    private JLabel Knight;
    private JLabel Mage;
    private JPanel HeroPanel;
    // AssetPath ap = new AssetPath();
    JFrame f;
    LabelCreator lc = new LabelCreator();

    public static void main(String[] args) {
        makeHero();
    }
        public static void makeHero() {
            JFrame f;
            LabelCreator lc = new LabelCreator();
            AssetPath ap = new AssetPath();
//            JFrame frame = new JFrame("Hero Maker");
//            frame.update(frame.getGraphics());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
//            frame.setSize(800, 500);
//            frame.setLayout(null);
            // Container container = frame.getContentPane();
            final boolean[] selectedHero = {false};
            final String[] heroString = {"No hero selected"};
            f = new JFrame("Select Hero");
            Container container = f.getContentPane();
            GameView gameView = new GameView();
            int counter = 0;
            int spacer = 135;
            int base = 40;
            JLabel Archer = lc.createLabel(ap.archer, "Archer", base + (counter * spacer), 40, 125, 125);
            counter++;
            //    public JLabel createLabel(String path, String hover , int x, int y, int width, int height){
            JLabel Barbarian = lc.createLabel(ap.barbarianIcon, "Barbarian", base + (counter * spacer), 40, 125, 125);
            counter++;
            JLabel Knight = lc.createLabel(ap.knight, "Knight", base + (counter * spacer), 40, 125, 125);
            counter++;
            JLabel Mage = lc.createLabel(ap.mage, "Mage", base + (counter * spacer), 40, 125, 125);
            // JTextField selected = new JTextField();
            //selected.setLocation(40, 200);
//            selected.setSize(300, 30);
//            selected.setEditable(false);
//            selected.setText(hero[0]);
              JLabel selected = lc.createText(heroString[0], 40, 200, 300, 30);
//        Archer.

            Archer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("Archer selected");
                    selectedHero[0] = true;
                    heroString[0] = "Archer";
                    selected.setText(heroString[0]);
                    selected.update(f.getGraphics());

                }
            });

            Barbarian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("Barbarian selected");
                    selectedHero[0] = true;
                    heroString[0] = "Barbarian";
                    selected.setText(heroString[0]);
                    selected.update(f.getGraphics());

                }
            });

            Knight.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("Knight selected");
                    selectedHero[0] = true;
                    heroString[0] = "Knight";
                    selected.setText(heroString[0]);
                    selected.update(f.getGraphics());

                }
            });

            Mage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("Mage selected");
                    selectedHero[0] = true;
                    heroString[0] = "Mage";
                    selected.setText(heroString[0]);
                    selected.update(f.getGraphics());
                }
            });



            JButton new_game = new JButton("New Game");
            new_game.setBounds(50, 300, 210, 30);

            new_game.addActionListener(e -> {
            ArtifactGenerator artifactGenerator = new ArtifactGenerator();

            NewGame newGame = new NewGame();
            Hero hero = new Hero(null, new Armor("Plated Leather Armor", 1000, "Plain", .35),
                    new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                    null,
                    180, 500, 0, 8, 500, new ArrayList<MysticInterface>());

            hero.getArmor().setIconPath(ap.basicArmor);
            String dmgType = "Physical";
            if (heroString[0] == "Mage") dmgType = "Magical";

                Stats knightStats= new Stats(8, 6, 3, 4, 12, 3);
                Stats archerStats= new Stats(5, 5, 8, 5, 4, 8);
                Stats mageStats= new Stats(3, 2, 5, 12, 3, 10);
                Stats barbarianStats= new Stats(10, 12, 2, 3, 5, 3);
            hero.setHeroClass(new Class(heroString[0], dmgType));
            // Assign Mystics Here:
                if (heroString[0] == "Mage") {
                    hero.setStats(mageStats);
                    MageTomb tomb = new MageTomb();
                    hero.addMystic(tomb);
                } else if (heroString[0] == "Knight") {
                    hero.setStats(knightStats);
                    Pavise pavise = new Pavise();
                    hero.addMystic(pavise);
                } else if (heroString[0] == "Barbarian") {
                    hero.setStats(barbarianStats);
                    BloodAmulet bloodAmulet = new BloodAmulet();
                    hero.addMystic(bloodAmulet);
                } else if (heroString[0] == "Archer") {
                    hero.setStats(archerStats);
                    HuntingKnife huntingKnife = new HuntingKnife();
                    hero.addMystic(huntingKnife);
                }
            hero = gameView.startNewDungeon(hero, 2);
            });
            JButton load_game = new JButton("Load Game");
            load_game.setBounds(50, 330, 210, 30);
            JButton exit = new JButton("Exit");
            exit.setBounds(50, 360, 210, 30);

            exit.addActionListener(e ->
                    System.exit(0));
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
            container.add(new_game);
            container.add(load_game);
            container.add(exit);
            container.add(Archer);
            container.add(Barbarian);
            container.add(Knight);
            container.add(Mage);
            container.add(selected);
            f.update(f.getGraphics());
            f.setSize(600, 500);
            f.setLayout(null);
//        container.add(Archer);
//        container.add(Barbarian);
//        container.add(Knight);
//        container.add(Mage);

            f.setVisible(true);
        }
    }
