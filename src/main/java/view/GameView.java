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
import java.util.Random;

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
    Enemy enemy;
    DungeonTile[][] dungeon;
    JLabel dungeonHero;
    Sound sound = new Sound();
    Container dungeonContainer;
    Container combatContainer;
    DungeonTile exit;
    DungeonFloorCreator dungeonFloorCreator;
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
        loadInventory();
        frame.setLayout(null);
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

        if(hero.finishedCombat){
            endCombat();
            hero.finishedCombat = false;
            hero.inCombat = false;
        }

        if(hero.inCombat){
            updateEnemy();
        }

        if(hero.inDungeon){
            dungeonHero.setBounds(hero.x*100, hero.y*100, 100, 100);
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

    public void keyInput(char e) {
        switch (e) {
            case 'i' -> openInventory();
            case 'w' -> {
                if(hero.inDungeon && !hero.inCombat)checkMove(hero.x, hero.y -1);
            }
            case 'a' -> {
                if(hero.inDungeon && !hero.inCombat)checkMove(hero.x -1, hero.y);
            }
            case 's' -> {
                if(hero.inDungeon && !hero.inCombat)checkMove(hero.x, hero.y +1);
            }
            case 'd' -> {
                if(hero.inDungeon && !hero.inCombat)checkMove(hero.x +1, hero.y);
            }
            case 'g' -> {
                if(exit != null && hero.x == exit.x/100 && hero.y == exit.y/100){
                    loadInventory();
                    loadDungeon();
                }
            }
        }
    }


    public void startCombat(){
        //panel = pc.getCombatPanel();
        combat = new Combat();
        EnemyGenerator enemyGenerator = new EnemyGenerator(hero.getLevel());
        enemy = enemyGenerator.generateEnemy();
        combat.startFight(hero, enemy);
        loadEnemy();
        //Populate buttons.
        update(hero);
    }

    public void updateEnemy(){
        if(enemyHealthBar!=null) {
            enemyHealthBar.setValue(enemy.getHealth());
            enemyHealthBar.setString("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
            enemyHealthBar.setMaximum(enemy.getMaxHealth());
        }
    }

    public void endCombat(){
        cont.remove(enemyHealthBar);
        cont.remove(enemyIcon);
        if(attack!=null)attack.setVisible(false);
        if(run!=null)run.setVisible(false);
        if(items!=null)items.setVisible(false);
        if(skills!=null)skills.setVisible(false);
        if(enemyHealthBar!=null)enemyHealthBar.setVisible(false);
        if(enemyIcon!=null)enemyIcon.setVisible(false);
    }

    public void loadEnemy(){
        //combatContainer = frame.getContentPane();
        cont = frame.getContentPane();
        enemyBorder = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 800, 525, 475, 250);
        if(enemy != null && hero.inCombat) {
            attack = new JButton("Attack");
            attack.setBounds(500, 600, 100, 30);
            attack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(hero.inCombat)hero = combat.attack();
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
            enemyHealthBar.setValue(enemy.getMaxHealth());
            enemyHealthBar.setBounds(825, 575, 300, 25);

            enemyHealthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
            enemyHealthBar.setForeground(Color.RED);
            attack.setVisible(true);
            cont.add(enemyIcon);
            //cont.add(panel);
            cont.add(enemyHealthBar);
            cont.add(enemyBorder);

        }
    }

    public void loadInventory(){
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


        attack = new JButton("Attack");
        attack.setBounds(500, 600, 100, 30);
        attack.addActionListener( e -> {
            if(hero.inCombat) {
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

        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openInventory();

            }
        });

//        cont.add(attack);
//        cont.add(skills);
//        cont.add(items);
//        cont.add(run);
        cont.add(weapon);
        cont.add(armor);
        cont.add(artifact);
        cont.add(healthBar);
        cont.add(XPBar);
        //cont.add(gold);
        cont.add(heroIcon);
        cont.add(inventory);
        cont.add(border);
    }

    public void loadDungeon(){
        dungeonContainer = frame.getContentPane();
        dungeonFloorCreator = new DungeonFloorCreator();
        dungeon = dungeonFloorCreator.createFloor();
        DungeonTile entrance = dungeonFloorCreator.findEntrance(dungeon);
        exit = dungeonFloorCreator.findExit(dungeon);
        for(int i = 0; i < dungeonFloorCreator.width; i++){
            for(int j = 0; j < dungeonFloorCreator.height; j++){
                if(dungeon[i][j] == entrance){
                    dungeonHero = labelCreator.createLabel(ap.hero, "Hero",  dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                    hero.x = i;
                    hero.y = j;
                    dungeonContainer.add(dungeonHero);
                    dungeonContainer.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                }
                else if(dungeon[i][j].hasChest) dungeonContainer.add(labelCreator.createLabelWithoutHover("src/Assets/Dungeon/Tiles/chest.png", dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                else if(dungeon[i][j].hasEnemy) dungeonContainer.add(labelCreator.createLabelWithoutHover(ap.enemyTile, dungeon[i][j].x+15, dungeon[i][j].y+15, 70, 70));
                else dungeonContainer.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                if(dungeon[i][j] == entrance || dungeon[i][j]== exit || dungeon[i][j].hasChest || dungeon[i][j].hasEnemy) dungeonContainer.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                if(j==dungeonFloorCreator.height-1) dungeonContainer.add(labelCreator.createLabelWithoutHover("src/Assets/Dungeon/Tiles/horizontalEdge.png", dungeon[i][j].x, dungeon[i][j].y + 100, 100, 100));
            }
        }
        hero.inDungeon = true;
    }

    public void checkMove(int x, int y){
        if(hero.inDungeon){
            if(x<dungeonFloorCreator.width && y<dungeonFloorCreator.height && x > -1 && y > -1 && dungeon[x][y]!= null && dungeon[x][y].isWalkable){
                hero.x = x;
                hero.y = y;
                if(dungeon[x][y].hasEnemy){
                    startCombat();
                    hero.inCombat = true;
                    dungeon[x][y].hasEnemy = false;
                    //hero.inCombat = false;
                }
                //makeRandomTileBlackTest();
                update(hero);
            }
            else System.out.println("Can't move there, x:d" + x + " y:" + y);
        }
    }

//    public void makeRandomTileBlackTest(){
//        Random rand = new Random();
//        int x = rand.nextInt(dungeonFloorCreator.width);
//        int y = rand.nextInt(dungeonFloorCreator.height);
//        JLabel label = labelCreator.createLabelWithoutHover("src/Assets/Dungeon/Tiles/blackTile.png", dungeon[x][y].x, dungeon[x][y].y, 100, 100);
//        label.setComponentZOrder(frame, 0);
//        dungeonContainer.add(label);
//
//    }
}
