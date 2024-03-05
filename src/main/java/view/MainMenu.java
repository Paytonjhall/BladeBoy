package view;

import javax.swing.*;

import Character.Abilities.Mystic;
import Character.Equipment.Armor;
import Character.Equipment.Artifact;
import Character.Equipment.Weapon;
import Character.*;
import Character.Town.ArtifactGenerator;
import Game.NewGame;

import java.util.ArrayList;

public class MainMenu {
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton exitButton;
    private JPanel panel;

    public MainMenu() {
    }

    public static void main(String[] args) {
        JFrame f=new JFrame("BladeBoy");
        GameView gameView = new GameView();

        JButton new_game=new JButton("New Game");
        new_game.setBounds(50,100,210,30);
        JButton load_game=new JButton("Load Game");
        load_game.setBounds(50,130,210,30);
        JButton exit=new JButton("Exit");
        exit.setBounds(50,160,210,30);

        new_game.addActionListener(e -> {
            ArtifactGenerator artifactGenerator = new ArtifactGenerator();

            NewGame newGame = new NewGame();
            Hero hero = new Hero(new Armor("Plated Leather Armor", 1000, "Plain", .35),
                    new Weapon("Incredible Steel Sword", 1000, "An impressively sharp steel blade", 15),
                    null,
                    180, 500, 0, 8, 500, new ArrayList<Mystic>());
            hero = gameView.startNewDungeon(hero, 2);
            // newGame.start("Payton");
        });

        load_game.addActionListener(e -> {

        });

        exit.addActionListener(e ->
                System.exit(0));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.add(new_game);
        f.add(load_game);
        f.add(exit);
        f.update(f.getGraphics());
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
