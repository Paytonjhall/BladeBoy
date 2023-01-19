package view;

import Character.*;
import Dungeon.EnemyGenerator;
import Game.AssetPath;
import Game.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class GameView {
    Hero hero;
    LabelCreator labelCreator = new LabelCreator();
    AssetPath ap = new AssetPath();
    PanelCreator pc = new PanelCreator();
    Container cont;
    JPanel panel = new JPanel();
    JFrame frame;
    Combat combat;
    JLabel border;
    JLabel weapon;
    JLabel armor;
    JLabel artifact;
    JLabel gold;
    JLabel inventory;
    JLabel heroIcon;
    JProgressBar healthBar;
    JProgressBar XPBar;
    JButton skills;
    JButton attack;
    JButton run;
    JButton items;
    JLabel enemyIcon;
    JLabel enemyBorder;
    JProgressBar enemyHealthBar;
    Boolean inventoryOpen = false;
    Boolean inCombat = false;
    Enemy enemy;
    DungeonTile[][] dungeon;
    JLabel dungeonHero;
    Sound sound = new Sound();

    public GameView() {
    }

    public Hero startGameView(Hero hero){

        this.hero = hero;
        frame = new JFrame("BladeBoy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(1300, 800);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyInput(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        frame.setLayout(null);
        cont = frame.getContentPane();



         border = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 5, 525, 475, 250);
         weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 650, 75, 75);
         armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 650, 75, 75);
         artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 650, 75, 75);
         heroIcon = labelCreator.createLabel(ap.hero, "Level: " + hero.getLevel(), 35, 570, 75, 75);
         //gold = labelCreator.createLabel(ap.Gold,hero.getGold()+"", 350, 570, 65, 65);
         inventory = labelCreator.createLabel(ap.backpack, "Inventory", 350, 650, 75, 75);
        healthBar = new JProgressBar(0, hero.getMaxHealth());
        healthBar.setValue(hero.getHealth());
        healthBar.setBounds(135, 575, 300, 25);
        healthBar.setStringPainted(true);
        healthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        healthBar.setForeground(Color.RED);
        healthBar.setString("Health: " + hero.getHealth() + "/" + hero.getMaxHealth());

        XPBar = new JProgressBar(0, hero.getNextLevelXp());
        XPBar.setValue(hero.getXp());
        XPBar.setBounds(135, 615, 300, 25);
        XPBar.setStringPainted(true);
        XPBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        XPBar.setForeground(Color.blue);
        XPBar.setString("XP: " + hero.getXp() + "/" + hero.getNextLevelXp());


        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openInventory();

            }
        });

        cont.add(weapon);
        cont.add(armor);
        cont.add(artifact);
        cont.add(healthBar);
        cont.add(XPBar);
        //cont.add(gold);
        cont.add(heroIcon);
        cont.add(inventory);
        cont.add(border);

        frame.setVisible(true);
        return hero;
    }


    public void update(Hero hero){
        this.hero = hero;
        frame.invalidate();
        frame.validate();
        frame.repaint();

        if(weapon != null){
            labelCreator.update(hero.getWeapon().getIconPath(), weapon, hero.getWeapon().toString());
            //weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
        }
        if(armor != null){
            labelCreator.update("src/Assets/Armor/platemail.png", armor, hero.getArmor().toString());
            //armor = labelCreator.createLabel("src/Assets/Armor/platemail.png",hero.getArmor().toString(), 145, 850, 75, 75);
        }
        if(artifact != null){
            labelCreator.update(hero.getArtifact().getIconPath(), artifact, hero.getArtifact().toString());
            //artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 850, 75, 75);
        }
        if(healthBar != null){
            healthBar.setValue(hero.getHealth());
            healthBar.setString("Health: " + hero.getHealth() + "/" + hero.getMaxHealth());
            healthBar.setMaximum(hero.getMaxHealth());
        }
        if(XPBar != null){
            XPBar.setValue(hero.getXp());
            XPBar.setString("XP: " + hero.getXp() + "/" + hero.getNextLevelXp());
            XPBar.setMaximum(hero.getNextLevelXp());
        }
        if(gold != null){
            gold.setText(hero.getGold()+"");
            gold.setToolTipText(hero.getGold()+"");
        }
        if(heroIcon != null){
            heroIcon.setToolTipText("Level: " + hero.getLevel());
        }

        inCombat = hero.inCombat;

        if(inCombat){
            if(enemy != null){
                enemyHealthBar.setValue(enemy.getHealth());
                if(attack!=null)attack.setVisible(true);
                if(run!=null)run.setVisible(true);
                if(items!=null)items.setVisible(true);
                if(skills!=null)skills.setVisible(true);
                if(enemyHealthBar!=null)enemyHealthBar.setVisible(true);
                if(enemyIcon!=null)enemyIcon.setVisible(true);
            }
        } else {
            if(attack!=null)attack.setVisible(false);
            if(run!=null)run.setVisible(false);
            if(items!=null)items.setVisible(false);
            if(skills!=null)skills.setVisible(false);
            if(enemyHealthBar!=null)enemyHealthBar.setVisible(false);
            if(enemyIcon!=null)enemyIcon.setVisible(false);
        }

        //System.out.println("Hero in combat: " + hero.inCombat + ", Hero in dungeon: " + hero.inDungeon);
        //System.out.print("Hero x: " + hero.x + ", Hero y: " + hero.y);
        if(hero.inDungeon){
            //cont.remove(dungeonHero);
            //dungeonHero = labelCreator.createLabel(ap.hero, "Hero", hero.x*100, hero.y*100, 100, 100);
            dungeonHero.setBounds(hero.x*100, hero.y*100, 100, 100);
            //cont.add(dungeonHero);
        }


        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    public void openInventory()  {
        HeroInventory heroInventory = new HeroInventory();
        try {
            sound.openBagSound();
            update(heroInventory.openInventory(hero));
            inventoryOpen = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void progressChat(){

    }

    public void keyInput(char e) {
        switch (e) {
            case 'i' -> openInventory();
            case 'w' -> {
                if(hero.inDungeon && !hero.inCombat)checkMove(hero.x, hero.y -1);
            }
            case 'a' -> {
                if(hero.inDungeon&& !hero.inCombat)checkMove(hero.x -1, hero.y);
            }
            case 's' -> {
                if(hero.inDungeon&& !hero.inCombat)checkMove(hero.x, hero.y +1);
            }
            case 'd' -> {
                if(hero.inDungeon)checkMove(hero.x +1, hero.y);
            }

        }
    }


    public void startCombat(){
        inCombat = true;
        hero.inCombat = true;
        panel = pc.getCombatPanel();
        combat = new Combat();
        EnemyGenerator enemyGenerator = new EnemyGenerator(hero.getLevel());
        enemy = enemyGenerator.generateEnemy();
        combat.startFight(hero, enemy);
        //Populate buttons.
        attack = new JButton("Attack");
        attack.setBounds(500, 600, 100, 30);
        attack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                hero = combat.attack();
                update(hero);
            }
        });
        skills = new JButton("Skills");
        skills.setBounds(500, 660, 100, 30);
        items = new JButton("Item");
        items.setBounds(650, 660, 100, 30);
        run = new JButton("Run");
        run.setBounds(650, 600, 100, 30);
        enemyBorder = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 800, 525, 475, 250);
        enemyIcon = labelCreator.createLabel("src/Assets/Portraits/Icons_24.png",enemy.getName() + "", 1135, 565, 75, 75);
        enemyHealthBar = new JProgressBar(0, enemy.getHealth());
        enemyHealthBar.setValue(enemy.getHealth());
        enemyHealthBar.setBounds(825, 575, 300, 25);

        enemyHealthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
        enemyHealthBar.setForeground(Color.RED);

        cont.add(enemyIcon);
        cont.add(attack);
        cont.add(skills);
        cont.add(items);
        cont.add(run);
        cont.add(panel);
        cont.add(enemyHealthBar);
        cont.add(enemyBorder);

        update(hero);
    }

    public void loadDungeon(){
        DungeonFloorCreator dungeonFloorCreator = new DungeonFloorCreator();
        dungeon = dungeonFloorCreator.createFloor(15,7);
        DungeonTile entrance = dungeonFloorCreator.findEntrance(dungeon);
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 7; j++){
                if(dungeon[i][j] == entrance){
                    dungeonHero = labelCreator.createLabel(ap.hero, "Hero",  dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                    hero.x = i;
                    hero.y = j;
                    cont.add(dungeonHero);
                }
                cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                if(i!=0 && i != 14 && j!=0 && j!=6)cont.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
            }
        }
        hero.inDungeon = true;
    }

//    public void loadDungeon(DungeonTile[][] floor){
//
//    }

    public void checkMove(int x, int y){
        if(hero.inDungeon){
            if(dungeon[x][y].isWalkable){
                hero.x = x;
                hero.y = y;
                update(hero);
            }
        }
    }





}
