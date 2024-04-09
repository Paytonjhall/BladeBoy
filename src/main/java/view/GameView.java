package view;

// Things to add:
// Traps

import Character.*;
import Character.Mystics.MysticInterface;
import Character.Town.Armory;
import Character.Town.Artificiary;
import Character.Town.Blacksmith;
import Dungeon.ChestLoot;
import Dungeon.Enemy;
import Dungeon.EnemyGenerator;
import Dungeon.Loot;
import Game.AssetPath;
import Game.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameView {
    Hero hero;
    LabelCreator labelCreator = new LabelCreator();
    AssetPath ap = new AssetPath();
    PanelCreator pc = new PanelCreator();
    ChestLoot chestLoot = new ChestLoot();
    Container cont;
    //Container cont;
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
    JLabel[][] dungeonLabels;
    JLabel dungeonHero;
    Sound sound = new Sound();
    List<JLabel> enemyTiles;
    List<JLabel> chestTiles;
    List<JLabel> shopTiles;
    List<JLabel> torchTiles;
    List<JLabel> mysticIcons;
    DungeonTile exit;
    DungeonFloorCreator dungeonFloorCreator;
    TownFloorCreator townFloorCreator;
    JTextArea displayPane;
    JScrollPane scrollPane;
    DungeonClearedData dungeonClearedData = new DungeonClearedData();
    ClearedDungeon clearedDungeon;

//    Blacksmith blacksmith;
    blacksmith blacksmith;
//    Armory armory;
    armory armory;
//    Artificiary artificiary;
    artificiary artificiary;

    int heroLevel = 0;
    int floorCount = 1;
    int bossFloorCount = 0;
    public GameView() {
    }

    //Consider adding a string to the parameters here for the type of dungeon we want to do.
    public Hero startNewDungeon(Hero hero,int length) {
        bossFloorCount = length;
        startGameView(hero);
        if(length == floorCount) loadBossFloor();
        else loadDungeon();
        while(this.hero.getHealth()>0){
            if (floorCount < bossFloorCount) {
                return hero;
            }
        }
        return null;
    }

    public Hero enterTown(Hero hero){
        startGameView(hero);
        hero.inCombat = false;
        hero.inDungeon = false;
        hero.inTown = true;
        loadTown();
        return hero;
    }

    public Hero startGameView(Hero hero){
        this.hero = hero;
        heroLevel = hero.getLevel();
        frame = new JFrame("BladeBoy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(1300, 1000);
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
            labelCreator.update(hero.getWeapon().getIconPath(), weapon, hero.getWeapon().hoverString());
            //weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
        }
        if(armor != null){
            labelCreator.update("src/Assets/Armor/armor4.png", armor, hero.getArmor().hoverString());
            //armor = labelCreator.createLabel("src/Assets/Armor/armor4.png",hero.getArmor().toString(), 145, 850, 75, 75);
        }
        if(artifact != null){
            labelCreator.update(hero.getArtifact().getIconPath(), artifact, hero.getArtifact().hoverString());
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

        if(enemyHealthBar != null && enemyHealthBar.getValue() == 0){
            cont.remove(enemyHealthBar);
        }

        if(hero.finishedCombat){
            endCombat();
            hero.finishedCombat = false;
            hero.inCombat = false;
        }

        checkHeroLevel();

        if(hero.inCombat){
            updateEnemy();
        }

        if(hero.inDungeon) dungeonHero.setBounds(hero.x*100, hero.y*100, 100, 100);


        if(hero.inTown) dungeonHero.setBounds(hero.x*100, hero.y*100, 100, 100);

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
            case 'i' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) openInventory();
                else print("You can't open your inventory while in combat!");
            }
            case 'w' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) checkMove(hero.x, hero.y -1);
                else print("You can't move in combat!");
            }
            case 'a' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) checkMove(hero.x -1, hero.y);
                else print("You can't move in combat!");
            }
            case 's' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) checkMove(hero.x, hero.y +1);
                else print("You can't move in combat!");
            }
            case 'd' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) checkMove(hero.x +1, hero.y);
                else print("You can't move in combat!");
            }
            case 'e' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) unlockChest(hero.x, hero.y);
            }
            case 'g' -> {
                if(exit != null && hero.x == exit.x/100 && hero.y == exit.y/100) {
                    //heroIcon.getInputMap().clear();
                    dungeonClearedData.addFloorsCleared(1);
                    dungeonLabels = null;
                    floorCount++;
                    sound.enterDoorSound();
                    loadInventory();
                    if(bossFloorCount>1 && floorCount == bossFloorCount)loadBossFloor();
                    else if(bossFloorCount < floorCount) {
                        hero.inDungeon = false;
                        clearedDungeon = new ClearedDungeon(dungeonClearedData);
                        loadTown();
                    }
                    else loadDungeon();
                    updateKeyBindings();
                }
            }
            case 't' -> {
                if((hero.inDungeon || hero.inTown) && !hero.inCombat) shopKeeper(hero.x, hero.y);
            }
        }
    }

    public void startCombat(){
        //panel = pc.getCombatPanel();
        hero.inCombat = true;
        combat = new Combat();
        EnemyGenerator enemyGenerator = new EnemyGenerator(hero);
        enemy = enemyGenerator.generateEnemy();
        combat.startFight(hero, enemy);
        loadEnemy();
        //Populate buttons.
        update(hero);
    }

    public void startBossCombat(){
        //panel = pc.getCombatPanel();
        hero.inCombat = true;
        combat = new Combat();
        EnemyGenerator enemyGenerator = new EnemyGenerator(hero);
        enemy = enemyGenerator.generateBoss();
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
        if(enemy.getDrops() != null && enemy.getDrops().size() > 0){
            StringBuilder drops = new StringBuilder();
            for(int i = 0; i < enemy.getDrops().size(); i++){
                drops.append(enemy.getDrops().get(i).hoverString()).append(", ");
            }
            print("You defeated the " + enemy.getName() + "! Gained " + enemy.getXp() + " XP and " + enemy.getGold() + " gold.");
            print(enemy.getName() + " dropped: " + drops);
        }
        else {
            print("You defeated the " + enemy.getName() + "! Gained " + enemy.getXp() + " XP and " + enemy.getGold() + " gold.");
        }
        dungeonClearedData.addXpEarned(enemy.getXp());
        dungeonClearedData.addGoldEarned(enemy.getGold());
        cont.remove(enemyHealthBar);
        cont.remove(enemyIcon);
        if(attack!=null){
            attack.setVisible(false);
            attack.removeMouseListener(attack.getMouseListeners()[0]);
        }
        if(run!=null)run.setVisible(false);
        if(items!=null)items.setVisible(false);
        if(skills!=null)skills.setVisible(false);
        if(enemyHealthBar!=null)enemyHealthBar.setVisible(false);
        if(enemyIcon!=null)enemyIcon.setVisible(false);
        dungeonClearedData.addEnemiesKilled(1);
        removeEnemyTile(hero.x, hero.y);
    }

    public void loadEnemy(){
        //TODO: Need to rethink all of this.
        cont.remove(enemyBorder);
        if(enemy != null && hero.inCombat) {
            attack = new JButton("Attack");
            attack.setBounds(500, 600, 100, 30);
            attack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(hero.inCombat){
                        hero = combat.attack();
                        dungeonClearedData.addHeroDamage(combat.getRecentHeroDamage());
                        dungeonClearedData.addDamageTaken(combat.getRecentEnemyDamage());
                    }
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
            enemyIcon = labelCreator.createLabel(enemy.getIconPath(),enemy.getName() + "", 1135, 565, 75, 75);
            enemyHealthBar = new JProgressBar(0, enemy.getHealth());
            enemyHealthBar.setValue(enemy.getMaxHealth());
            enemyHealthBar.setBounds(825, 575, 300, 25);

            print("You encountered a " + enemy.getName() + "!");
            cont.add(attack);
            cont.add(skills);
            cont.add(items);
            cont.add(run);

            enemyHealthBar.setFont(new Font("MV Boli", Font.BOLD, 20));
            enemyHealthBar.setForeground(Color.RED);
            attack.setVisible(true);
            cont.add(enemyIcon);
            //cont.add(panel);
            cont.add(enemyHealthBar);
            cont.add(enemyBorder);

        }
    }

    public void updateKeyBindings(){
        heroIcon.requestFocus();
    }

    public void loadKeyBindings(){
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("W"), "w");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("A"), "a");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("S"), "s");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("D"), "d");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("G"), "g");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("I"), "i");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("E"), "e");
        heroIcon.getInputMap().put(KeyStroke.getKeyStroke("T"), "t");
        heroIcon.getActionMap().put("w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('w');
            }
        });
        heroIcon.getActionMap().put("a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('a');
            }
        });
        heroIcon.getActionMap().put("s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('s');
            }
        });
        heroIcon.getActionMap().put("d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('d');
            }
        });
        heroIcon.getActionMap().put("g", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('g');
            }
        });
        heroIcon.getActionMap().put("t", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('t');
            }
        });
        heroIcon.getActionMap().put("i", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('i');
            }
        });
        heroIcon.getActionMap().put("e", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyInput('e');
            }
        });
    }

    public void loadInventory(){
        heroIcon = null;
        cont = frame.getContentPane();
        cont.removeAll();
        displayPane = new JTextArea();
        scrollPane = new JScrollPane(displayPane);
        scrollPane.setBounds(15, 775, 1250, 175);
        border = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 5, 525, 475, 250);
        if (hero.getWeapon() != null) weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().hoverString(), 35, 650, 75, 75);
        if (hero.getArmor() != null) armor = labelCreator.createLabel("src/Assets/Armor/armor4.png",hero.getArmor().hoverString(), 145, 650, 75, 75);
        if (hero.getArtifact() != null) artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().hoverString(), 255, 650, 75, 75);
        heroIcon = labelCreator.createLabel(ap.getIcon(hero.getIconString()), "Level: " + hero.getLevel(), 35, 570, 75, 75);
        // Load Hero Mystics
        int mysticCounter = 30;
        mysticIcons = new ArrayList<>();
        for(MysticInterface mystics : hero.getMystics()) {
            JLabel mystic = labelCreator.createLabel(ap.getMystic(mystics.IconName()), mystics.hoverTextString(), mysticCounter, 540, 30, 30);
            mysticCounter += 30;
            mysticIcons.add(mystic);
            cont.add(mystic);
        }

        loadKeyBindings();
        enemyBorder = labelCreator.createLabelWithoutHover("src/Assets/UI/itemBorder.png", 800, 525, 475, 250);
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
        displayPane.setEditable(false);
        displayPane.setLineWrap(true);
        displayPane.setWrapStyleWord(true);
        displayPane.setFont(new Font("HelveticaNeue", Font.BOLD, 20));

        cont.add(scrollPane);
        cont.add(weapon);
        cont.add(armor);
        if(hero.getArtifact() != null) cont.add(artifact);
        cont.add(healthBar);
        cont.add(XPBar);
        //cont.add(gold);
        cont.add(enemyBorder);
        cont.add(heroIcon);
        cont.add(inventory);
        cont.add(border);
    }

    public void loadTown(){
        hero.inCombat = false;
        hero.inTown = true;
        cont = frame.getContentPane();
        townFloorCreator = new TownFloorCreator();
        dungeon = townFloorCreator.createMap();
        for(int i = 0; i < dungeonFloorCreator.width; i++) {
            for (int j = 0; j < dungeonFloorCreator.height; j++) {
                if(i == 0 && j ==0){
                    dungeonHero = labelCreator.createLabel(ap.getIcon(hero.getIconString()), "Hero", dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                    cont.add(dungeonHero);
                }
                cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon,  i * 100, j * 100, 100, 100));
            }
        }
        hero.x = 1;
        hero.y = 1;

        print("You have arrived at the town");
    }

    public void loadDungeon(){
        hero.inTown = false;
        dungeonLabels = new JLabel[13][5];
        enemyTiles = new ArrayList<>();
        chestTiles = new ArrayList<>();
        torchTiles = new ArrayList<>();
        cont = frame.getContentPane();
        dungeonFloorCreator = new DungeonFloorCreator();
        dungeon = dungeonFloorCreator.createMap();
        DungeonTile entrance = dungeonFloorCreator.findEntrance(dungeon);
        exit = dungeonFloorCreator.findExit(dungeon);
        for(int i = 0; i < dungeonFloorCreator.width; i++){
            for(int j = 0; j < dungeonFloorCreator.height; j++) {
                dungeonLabels[i][j] = labelCreator.createLabelWithoutHover(ap.blackTile, (i * 100), (j * 100), 100, 100);
                if (dungeon[i][j] == entrance) {
                    dungeonHero = labelCreator.createLabel(ap.getIcon(hero.getIconString()), "Hero", dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                    hero.x = i;
                    hero.y = j;
                    cont.add(dungeonHero);
                    cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    cont.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                } else {
                    cont.add(dungeonLabels[i][j]);
                    if (dungeon[i][j].hasChest) {
                        JLabel chest = labelCreator.createLabelWithoutHover(ap.chest, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        chestTiles.add(chest);
                        cont.add(chest);
                    } else if (dungeon[i][j].hasEnemy) {
                    JLabel enemyTile = labelCreator.createLabelWithoutHover(ap.enemyTile, dungeon[i][j].x + 15, dungeon[i][j].y + 15, 70, 70);
                    enemyTiles.add(enemyTile);
                    cont.add(enemyTile);
                    } else if (dungeon[i][j].hasBoss) {
                        JLabel enemyTile = labelCreator.createLabelWithoutHover(ap.bossTile, dungeon[i][j].x + 15, dungeon[i][j].y + 15, 70, 70);
                        enemyTiles.add(enemyTile);
                        cont.add(enemyTile);
                    } else if (dungeon[i][j].hasTorch) {
                        JLabel torch = labelCreator.createLabelWithoutHover(ap.torch, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        torchTiles.add(torch);
                        cont.add(torch);
                    } else if (dungeon[i][j].isShop) {
                        JLabel shop = labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        // shopTiles.add(shop);
                        cont.add(shop);
                    } else if (dungeon[i][j].isExit) {
                        if(bossFloorCount-1 == floorCount){
                            //make boss door
                            JLabel bossDoor = labelCreator.createLabelWithoutHover(ap.bossDoor, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                            cont.add(bossDoor);
                        } else {
                            //normal door
                            JLabel door = labelCreator.createLabelWithoutHover(ap.exit, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                            cont.add(door);
                        }
                    } else {
                        cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    }
                    if (dungeon[i][j] == entrance || dungeon[i][j] == exit || dungeon[i][j].hasChest || dungeon[i][j].hasEnemy || dungeon[i][j].hasTorch || dungeon[i][j].hasBoss || dungeon[i][j].isShop) {
                        cont.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    }
                    if (j == dungeonFloorCreator.height - 1)
                        cont.add(labelCreator.createLabelWithoutHover(ap.horizontalEdge, dungeon[i][j].x, dungeon[i][j].y + 100, 100, 100));
                }
            }
        }
        for(JLabel t : torchTiles){
            removeDarkness(t.getX()/100, t.getY()/100);
        }
        removeDarkness();
        hero.inDungeon = true;
        if(floorCount == bossFloorCount) print("You have reached the boss floor!");
        else print("You are on floor: " + floorCount);

    }

    public void loadBossFloor(){
        hero.inTown = false;
        enemyTiles = new ArrayList<>();
        chestTiles = new ArrayList<>();
        torchTiles = new ArrayList<>();
        cont = frame.getContentPane();
        dungeonFloorCreator = new DungeonFloorCreator();
        dungeon = dungeonFloorCreator.createBossFloor();
        DungeonTile entrance = dungeonFloorCreator.findEntrance(dungeon);
        exit = dungeonFloorCreator.findExit(dungeon);
        for(int i = 0; i < dungeonFloorCreator.width; i++){
            for(int j = 0; j < dungeonFloorCreator.height; j++) {
                if (dungeon[i][j] == entrance) {
                    dungeonHero = labelCreator.createLabel(ap.getIcon(hero.getIconString()), "Hero", dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                    hero.x = i;
                    hero.y = j;
                    cont.add(dungeonHero);
                    cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    cont.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                } else {
                    //cont.add(dungeonLabels[i][j]);
                    if (dungeon[i][j].hasChest || dungeon[i][j].hasBossChest) {
                        JLabel chest = labelCreator.createLabelWithoutHover(ap.chest, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        chestTiles.add(chest);
                        cont.add(chest);
                    } else if (dungeon[i][j].hasEnemy) {
                        JLabel enemyTile = labelCreator.createLabelWithoutHover(ap.enemyTile, dungeon[i][j].x + 15, dungeon[i][j].y + 15, 70, 70);
                        enemyTiles.add(enemyTile);
                        cont.add(enemyTile);
                    } else if (dungeon[i][j].hasBoss) {
                        JLabel enemyTile = labelCreator.createLabelWithoutHover(ap.bossTile, dungeon[i][j].x + 15, dungeon[i][j].y + 15, 70, 70);
                        enemyTiles.add(enemyTile);
                        cont.add(enemyTile);
                    } else if (dungeon[i][j].hasTorch) {
                        JLabel torch = labelCreator.createLabelWithoutHover(ap.torch, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        torchTiles.add(torch);
                        cont.add(torch);
                    } else if (dungeon[i][j].isShop) {
                        JLabel shop = labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100);
                        // shopTiles.add(shop);
                        cont.add(shop);
                    }
                    else {
                        cont.add(labelCreator.createLabelWithoutHover(dungeon[i][j].icon, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    }
                    if (dungeon[i][j] == entrance || dungeon[i][j] == exit || dungeon[i][j].hasChest || dungeon[i][j].hasEnemy || dungeon[i][j].hasTorch || dungeon[i][j].hasBoss || dungeon[i][j].hasBossChest) {
                        cont.add(labelCreator.createLabelWithoutHover(ap.baseTile, dungeon[i][j].x, dungeon[i][j].y, 100, 100));
                    }
                    if (j == dungeonFloorCreator.height - 1)
                        cont.add(labelCreator.createLabelWithoutHover("src/Assets/Dungeon/Tiles/horizontalEdge.png", dungeon[i][j].x, dungeon[i][j].y + 100, 100, 100));
                }
            }
        }
        // Don't have darkness in boss rooms.
        hero.inDungeon = true;
        print("You are on the boss floor!");
    }

    public void checkMove(int x, int y){
        if(hero.inDungeon){
            if(x<dungeonFloorCreator.width && y<dungeonFloorCreator.height && x > -1 && y > -1 && dungeon[x][y]!= null && dungeon[x][y].isWalkable){
                hero.x = x;
                hero.y = y;
                sound.stepSound();
                if(!dungeon[x][y].isVisited) {
                    dungeon[x][y].isVisited = true;
                    dungeonClearedData.addTilesExplored(1);
                }
                if(dungeon[x][y].hasEnemy){
                    startCombat();
                    dungeon[x][y].hasEnemy = false;
                    //hero.inCombat = false;
                } else if (dungeon[x][y].hasBoss) {
                    startBossCombat();
                    dungeon[x][y].hasBoss = false;
                }
                removeDarkness();
                frame.repaint();
                update(hero);
            }
            // else print("Can't move there, x:" + x + " y:" + y);
        }
    }

    public void removeDarkness(){
        if(dungeonLabels!=null) {
            int x = hero.x;
            int y = hero.y;
            cont.remove(dungeonLabels[x][y]);
            if (x - 1 > -1) cont.remove(dungeonLabels[x - 1][y]);
            if (x + 1 < dungeonFloorCreator.width) cont.remove(dungeonLabels[x + 1][y]);
            if (y - 1 > -1) cont.remove(dungeonLabels[x][y - 1]);
            if (y + 1 < dungeonFloorCreator.height) cont.remove(dungeonLabels[x][y + 1]);
        }
    }

    public void removeDarkness(int x, int y){
        cont.remove(dungeonLabels[x][y]);
        if(x-1 > -1) cont.remove(dungeonLabels[x-1][y]);
        if(x+1 < dungeonFloorCreator.width) cont.remove(dungeonLabels[x+1][y]);
        if(y-1 > -1) cont.remove(dungeonLabels[x][y-1]);
        if(y+1 < dungeonFloorCreator.height) cont.remove(dungeonLabels[x][y+1]);
    }

    public void removeEnemyTile(int x, int y){
        for(JLabel enemyTile : enemyTiles){
            if(enemyTile.getX() == (x * 100) + 15 && enemyTile.getY() == (y * 100) + 15){
                cont.remove(enemyTile);
                enemyTiles.remove(enemyTile);
                dungeon[x][y].hasEnemy = false;
                break;
            }
        }
    }

    public void unlockChest(int x, int y){
        for(JLabel chest : chestTiles){
            if(chest.getX() == (x * 100) && chest.getY() == (y * 100)){
                dungeonClearedData.addChestsOpened(1);
                //I would like to replace the chest with a new tile, but right now the new tile looks funny.
                JLabel openedChest = labelCreator.createLabelWithoutHover(ap.openedChest, chest.getX(), chest.getY(), 100, 125);
                chest.setIcon(openedChest.getIcon());
                chest.repaint();
                //cont.remove(chest);
                if(dungeon[x][y].hasChest) {
                    sound.openChestSound();
                    Loot loot = chestLoot.generateLoot(hero);
                    // Dialogue box to say what was in the chest!
                    print("You opened the chest. " + loot.getLoot());
                    hero.addLoot(loot);
                    for(MysticInterface mystic: hero.getMystics()) {
                        hero = mystic.onChest(hero);
                    }
                    dungeon[x][y].hasChest = false;
                    chestTiles.remove(chest);
                    break;
                }
                if(dungeon[x][y].hasBossChest){ //Make boss loot.
                    sound.openChestSound();
                    Loot loot = chestLoot.generateBossLoot(hero);
                    // Dialogue box to say what was in the chest!
                    print("You opened the boss chest. " + loot.getLoot());
                    hero.addLoot(loot);
                    for(MysticInterface mystic: hero.getMystics()) {
                        hero = mystic.onChest(hero);
                    }
                    dungeon[x][y].hasBossChest = false;
                    chestTiles.remove(chest);
                    break;
                }
            }
        }
    }

    public void shopKeeper (int x, int y) {
        if (dungeon[x][y].isShop) {
            // System.out.println("SHOP");
            if (dungeon[x][y].shopOwner == "Blacksmith") {
                blacksmith = new blacksmith();
                hero = blacksmith.visitBlackSmith(hero);
                hero.visitedBlacksmith = true;
                print("You have now visited the blacksmith, he will now be available in town!");
            }
            else if (dungeon[x][y].shopOwner == "Armory") {
                armory = new armory();
                armory.visitArmory(hero);
                hero.visitedArmory = true;
                print("You have now visited the Armory, he will now be available in town!");

            }
            else if (dungeon[x][y].shopOwner == "Artifact") {
                artificiary = new artificiary();
                artificiary.visitArtificiary(hero);
                hero.visitedArtificiary = true;
                print("You have now visited the artificiary, he will now be available in town!");

            }
        }
        else System.out.println("NOT AT SHOP");

    }

    public void checkHeroLevel(){
        if(hero.getLevel() > heroLevel){
            print("You have leveled up! You are now level " + hero.getLevel());
            heroLevel++;
            dungeonClearedData.addLevelsGained(1);
            for(MysticInterface mystic: hero.getMystics()) {
                hero = mystic.onLevelUp(hero);
            }
            checkHeroLevel();
        }
    }

    public void print(String string){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date);
        if(displayPane!= null) displayPane.setText(displayPane.getText() + '\n' + time + ": " + string);
        //displayPane.update(cont.getGraphics());
    }
}
