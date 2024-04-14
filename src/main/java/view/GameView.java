package view;

// Things to add:
// Traps

import Character.*;
import Character.Abilities.AbilityInterface;
import Character.Combat.AttackObject;
import Character.Combat.Combat;
import Character.Mystics.MysticInterface;
import Character.Stats.Stats;
import Dungeon.ChestLoot;
import Dungeon.Enemy;
import Dungeon.EnemyGenerator;
import Dungeon.Loot;
import Game.AssetPath;
import Game.Sound;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
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
    JLabel str;
    JLabel strValue;

    JLabel con;
    JLabel conValue;
    JLabel agi;
    JLabel agiValue;

    JLabel intel;
    JLabel intelValue;

    JLabel def;
    JLabel defValue;

    JLabel luck;
    JLabel luckValue;


    int statSize = 20;
    int statStartY = 645;
    int statStartX = 44;
    JProgressBar healthBar;
    JProgressBar manaBar;
    JProgressBar XPBar;
    JLabel abilities;
    JLabel attack;
    JLabel run;
    JLabel items;
    JLabel enemyIcon;
    JLabel enemyBorder;
    JLabel enemyHealth;
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
    JLabel levelUpArrow;
    int mysticX = 130;
    int mysticY = 560;
    int mysticSize = 25;

    int combatButtonW = 125;
    int combatButtonH = 35;

    JComboBox abilitySelector = new JComboBox();

    DungeonTile exit;
    DungeonFloorCreator dungeonFloorCreator;
    TownFloorCreator townFloorCreator;
    JTextPane displayPane;
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

    int heroInventoryIconX = 45;
    int heroInventoryIconY = 552;

    int enemyInventoryIconX = 1171;
    int enemyInventoryIconY = 562;

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

    public void levelUp() {
        LevelUpView levelUpView = new LevelUpView();
        update(levelUpView.levelUp(hero));
    }

    public void update(Hero hero){
        this.hero = hero;
        if (hero.getAnnouncement() != "") {
            print(hero.getAnnouncement());
            hero.setAnnouncement("");
        }
        // System.out.println(hero.getStats().getConstitution().value()); // for testing
        hero.calcHeroStatus();
        frame.invalidate();
        frame.validate();
        frame.repaint();
        //System.out.println(hero.getStats().getConstitution().value() + " " + conValue.getText());
        if( (conValue != null && hero.getStats().getConstitution().value() +"" != conValue.getText()) ||
                strValue!= null && hero.getStats().getStrength().value() +"" != strValue.getText() ||
                agiValue!= null && hero.getStats().getAgility().value() +"" != agiValue.getText() ||
                defValue!= null && hero.getStats().getDefense().value() +"" != defValue.getText() ||
                intelValue!= null && hero.getStats().getIntelligence().value() +"" != intelValue.getText() ||
                luckValue!= null && hero.getStats().getLuck().value() +"" != luckValue.getText()) {
            conValue.setText(hero.getStats().getConstitution().value() + "");
            strValue.setText(hero.getStats().getStrength().value() + "");
            agiValue.setText(hero.getStats().getAgility().value() + "");
            defValue.setText(hero.getStats().getDefense().value() + "");
            intelValue.setText(hero.getStats().getIntelligence().value() + "");
            luckValue.setText(hero.getStats().getLuck().value() + "");
        }
        if (hero.getMystics().size() > mysticIcons.size()) {
            updateMystics();
        }
        // Consider adding Mystics update here.

        if(hero.statPoints>0 && !levelUpArrow.isVisible()) {
            levelUpArrow.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // hero.statPoints = 0; // For testing
                    super.mouseClicked(e);
                    if (hero.statPoints>0) levelUp();


                }
            });
            levelUpArrow.setVisible(true);

        } else if (hero.statPoints == 0){
            levelUpArrow.setVisible(false);
        }

        if(weapon != null){
            labelCreator.update(hero.getWeapon().getIconPath(), weapon, hero.getWeapon().hoverString());
            //weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().toString(), 35, 850, 75, 75);
        }
        if(armor != null){
            labelCreator.update(hero.getArmor().getIconPath(), armor, hero.getArmor().hoverString());
            //armor = labelCreator.createLabel("src/Assets/Armor/armor4.png",hero.getArmor().toString(), 145, 850, 75, 75);
        }
        if(artifact != null){
            labelCreator.update(hero.getArtifact().getIconPath(), artifact, hero.getArtifact().hoverString());
            //artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().toString(), 255, 850, 75, 75);
        }
        if(healthBar != null){
            healthBar.setValue(hero.getHealth());
            healthBar.setString(hero.getHealth() + "/" + hero.getMaxHealth());
            healthBar.setMaximum(hero.getMaxHealth());
        }
        if(manaBar != null){
            manaBar.setValue(hero.getMana());
            manaBar.setString(hero.getMana() + "/" + hero.getMaxMana());
            manaBar.setMaximum(hero.getMaxMana());
        }
        if(XPBar != null){
            XPBar.setValue(hero.getXp());
            XPBar.setString(hero.getXp() + "/" + hero.getNextLevelXp());
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
            cont.remove(enemyHealth);
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

    public void openAwardScreen() {
        // AwardModal awardModal = new AwardModal();


    }

    public void keyInput(char e) throws InterruptedException {
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
                    for(MysticInterface mystics: hero.getMystics()) {
                        if(mystics.onProgressFloor(hero) != null) hero = mystics.onProgressFloor(hero);
                    }
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
        print("You defeated the " + enemy.getName() + "!");
        print("You gained " + enemy.getXp() + " experience.");
        awardModal am = new awardModal(hero, enemy.generateLoot());
        hero = am.openLoot(hero, enemy.generateLoot(), enemy.getName() + " Loot");
        dungeonClearedData.addXpEarned(enemy.getXp());
        dungeonClearedData.addGoldEarned(enemy.getGold());
        cont.remove(enemyHealthBar);
        cont.remove(enemyHealth);
        cont.remove(enemyIcon);
        if(attack!=null){
            attack.setVisible(false);
            attack.removeMouseListener(attack.getMouseListeners()[0]);
        }
        if(run!=null)run.setVisible(false);
        if(items!=null)items.setVisible(false);
        if(abilities !=null) abilities.setVisible(false);
        if(abilitySelector != null) abilitySelector.setVisible(false);
        if(enemyHealthBar!=null)enemyHealthBar.setVisible(false);
        if(enemyIcon!=null)enemyIcon.setVisible(false);
        dungeonClearedData.addEnemiesKilled(1);
        removeEnemyTile(hero.x, hero.y);
    }

    public void loadEnemy(){
        //TODO: Need to rethink all of this.
        cont.remove(enemyBorder);
        if(enemy != null && hero.inCombat) {
            attack = labelCreator.createLabel(ap.attackButton, "Attack", 500, 665, combatButtonW, combatButtonH);
            // attack.setBounds(500, 600, 100, 30);
            attack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(hero.inCombat){
                        hero = combat.attack(new AttackObject(hero.getBaseDamage()));
                        dungeonClearedData.addHeroDamage(combat.getRecentHeroDamage());
                        dungeonClearedData.addDamageTaken(combat.getRecentEnemyDamage());
                    }
                    update(hero);
                }
            });

            if(hero.getAbilities().size()>0 && abilitySelector.getItemCount() != hero.getAbilities().size()) abilitySelector = labelCreator.createCustomComboBox(hero.getAbilities(), 500, 740, combatButtonW, combatButtonH, 25, 25);
            abilitySelector.setVisible(true);


            abilities = labelCreator.createLabel(ap.abilitiesButton, "Abilities", 500, 710, combatButtonW, combatButtonH);
            abilities.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(hero.inCombat && hero.getAbilities().size() > 0){
                        // Get selected ability.
                        //Object obj = abilitySelector.getSelectedItem();
                        AbilityInterface ability = hero.getAbilities().get(abilitySelector.getSelectedIndex());
                        if (ability.canCast(hero)) {
                            hero = ability.payAbility(hero);
                            AttackObject attack = ability.useAbility(hero, enemy);
                            hero = combat.attack(attack);
                            dungeonClearedData.addHeroDamage(combat.getRecentHeroDamage());
                            dungeonClearedData.addDamageTaken(combat.getRecentEnemyDamage());
                            update(hero);
                        } else {
                            print("You cannot cast " + ability.getName() + " at this time!");
                        }
                        //if(abilitySelector.isVisible()) abilitySelector.setVisible(false);
                        // else
                            abilitySelector.setVisible(true);
                    }
                }
            });

            //abilities.setBounds(500, 660, 100, 30);
            items = labelCreator.createLabel(ap.itemButton, "Items", 650, 710, combatButtonW, combatButtonH);
            //items.setBounds(650, 660, 100, 30);
            run = labelCreator.createLabel(ap.runButton, "Run", 650, 665, combatButtonW, combatButtonH);
            // run.setBounds(650, 600, 100, 30);
            enemyBorder = labelCreator.createLabelWithoutHover(ap.enemyInventory, 800, 525, 475, 250);
            enemyHealth = labelCreator.createLabelWithoutHover(ap.enemyHealthBar, 800, 525, 475, 250);

            enemyIcon = labelCreator.createLabel(enemy.getIconPath(),enemy.getName() + "", enemyInventoryIconX, enemyInventoryIconY, 60, 60);
            enemyHealthBar = labelCreator.createEnemyProgressBar(868, 597, 280, 25, enemy.getHealth(), enemy.getMaxHealth(), Color.RED.getRGB());

            print("You encountered a " + enemy.getName() + "!");
            cont.add(attack);
            cont.add(abilities);
            cont.add(abilitySelector);
            cont.add(items);
            cont.add(run);
            enemyHealthBar.setForeground(Color.RED);
            attack.setVisible(true);
            cont.add(enemyIcon);
            //cont.add(panel);
            cont.add(enemyHealthBar);
            cont.add(enemyHealth);
            cont.add(enemyBorder);

        }
    }

    public void updateKeyBindings(){
        heroIcon.requestFocus();
    }

    public void loadKeyBindings(){
        try {
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
                    try {
                        keyInput('w');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("a", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('a');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("s", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('s');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("d", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('d');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("g", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('g');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("t", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('t');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("i", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('i');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            heroIcon.getActionMap().put("e", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        keyInput('e');
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInventory(){
        heroIcon = null;
        cont = frame.getContentPane();
        cont.removeAll();
        displayPane = new JTextPane();
        scrollPane = new JScrollPane(displayPane);
        scrollPane.getHorizontalScrollBar().setEnabled(false);
        scrollPane.getVerticalScrollBar().setEnabled(true);
        scrollPane.getViewport().getView().setEnabled(false);
        scrollPane.setBounds(15, 775, 1250, 175);
        border = labelCreator.createLabelWithoutHover(ap.heroInventory, 5, 525, 475, 250);
        int statY = statStartY;
        int statX = statStartX + 35;
        // Load Stats: const, str, dex, int, def, luck
        Stats stats = hero.getStats();
        con = labelCreator.createLabel(ap.getStat("Constitution"), stats.getConstitution().description(),statStartX, statY, statSize, statSize);
        conValue = labelCreator.createText(stats.getConstitution().value() + "", statStartX + 20, statY, statSize, statSize); //TODO: Link up constitution
        statY += 28;
        str = labelCreator.createLabel(ap.getStat("Strength"), stats.getStrength().description(),statStartX, statY, statSize, statSize);
        strValue = labelCreator.createText(stats.getStrength().value()+"", statStartX + 20, statY, statSize, statSize); //TODO: Link up strength
        statY += 28;
        agi = labelCreator.createLabel(ap.getStat("Agility"), stats.getAgility().description(),statStartX, statY, statSize, statSize);
        agiValue = labelCreator.createText( stats.getAgility().value() + "", statStartX + 20, statY, statSize, statSize); //TODO: Link up dexterity
        statY += 28;
        statY = statStartY;
        intel = labelCreator.createLabel(ap.getStat("Intelligence"), stats.getIntelligence().description(),statX, statY, statSize, statSize);
        intelValue = labelCreator.createText(stats.getIntelligence().value()+ "", statX + 20, statY, statSize, statSize); //TODO: Link up intelligence
        statY += 28;
        def = labelCreator.createLabel(ap.getStat("Defense"), stats.getDefense().description(),statX, statY, statSize, statSize);
        defValue = labelCreator.createText(stats.getDefense().value() + "", statX + 20, statY, statSize, statSize); //TODO: Link up defense
        statY += 28;
        luck = labelCreator.createLabel(ap.getStat("Luck"), stats.getIntelligence().description(),statX, statY, statSize, statSize);
        luckValue = labelCreator.createText(stats.getLuck().value() + "", statX + 20, statY, statSize, statSize); //TODO: Link up luck

        cont.add(str);
        cont.add(strValue);
        cont.add(con);
        cont.add(conValue);
        cont.add(agi);
        cont.add(agiValue);
        cont.add(intel);
        cont.add(intelValue);
        cont.add(def);
        cont.add(defValue);
        cont.add(luck);
        cont.add(luckValue);

        levelUpArrow = labelCreator.createLabel(ap.levelUpArrow, "Level Up!", 35, 595, 40, 40);
        levelUpArrow.setVisible(false);
        cont.add(levelUpArrow);

        // Add mouse listener actions for weapon armor and artifact to switch to another item when clicked.
        if (hero.getWeapon() != null) weapon = labelCreator.createLabel(hero.getWeapon().getIconPath(),hero.getWeapon().hoverString(), 131, 687, 40, 40);
        if (hero.getArmor() != null) armor = labelCreator.createLabel(hero.getArmor().getIconPath(),hero.getArmor().hoverString(), 203, 687, 40, 40);
        if (hero.getArtifact() != null) artifact = labelCreator.createLabel(hero.getArtifact().getIconPath(),hero.getArtifact().hoverString(), 276, 687, 40, 40);
        heroIcon = labelCreator.createLabel(ap.getIcon(hero.getIconString()), "Level: " + hero.getLevel(), heroInventoryIconX, heroInventoryIconY, 70, 70);
        // Load Hero Mystics
        mysticX = 130;
        mysticIcons = new ArrayList<>();
        for(MysticInterface mystics : hero.getMystics()) {
            mysticIcons.clear();
            JLabel mystic = labelCreator.createLabel(ap.getMystic(mystics.IconName()), mystics.hoverTextString(), mysticX, mysticY, mysticSize, mysticSize);
            mysticX += 30;
            mysticIcons.add(mystic);
            cont.add(mystic);
        }

        //Load floor panel
        JLabel dungeonFloor = labelCreator.createLabel(ap.dungeon, "Completed Dungeon Count" ,520, 565, 50, 50);
        cont.add(dungeonFloor);
        JLabel dungeonCount = labelCreator.createHugeText(hero.getDungeonCount() + "", 555, 565, 50, 50);
        cont.add(dungeonCount);

        JLabel floors = labelCreator.createLabel(ap.floor, "Current floor",650, 565, 50, 50);
        cont.add(floors);
        JLabel floorCountText = labelCreator.createHugeText(this.floorCount + "", 685, 565, 50, 50);
        cont.add(floorCountText);



        JLabel floorPanel = labelCreator.createLabelWithoutHover(ap.floorPanel , 480, 530, 320, 120);
        cont.add(floorPanel);

        loadKeyBindings();
        enemyBorder = labelCreator.createLabelWithoutHover(ap.enemyInventory, 800, 525, 475, 250);
        enemyHealth = labelCreator.createLabelWithoutHover(ap.enemyHealthBar, 800, 525, 475, 250);
        //gold = labelCreator.createLabel(ap.Gold,hero.getGold()+"", 350, 570, 65, 65);
        inventory = labelCreator.createLabel(ap.backpack, "Inventory", 390, 687, 45, 45);
        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openInventory();
            }
        });
        UIManager.put("ProgressBar.selectionBackground", Color.RED);
        healthBar = labelCreator.createProgressBar(157, 597, 280, 25, hero.getHealth(), hero.getMaxHealth(), Color.RED.getRGB());

        // Mana bar x = 157 and y = 625
        manaBar = labelCreator.createProgressBar(157, 625, 280, 25, hero.getMana(), hero.getMaxMana(), Color.BLUE.getRGB());


        UIManager.put("ProgressBar.selectionBackground", Color.GREEN);
        XPBar = labelCreator.createProgressBar(157, 653, 280, 25, hero.getXp(), hero.getNextLevelXp(), Color.BLUE.getRGB());
//        XPBar = new JProgressBar(0, hero.getNextLevelXp());
//        XPBar.setValue(hero.getXp());
//        XPBar.setBounds(157, 653, 280, 25);
//        XPBar.setStringPainted(true);
//        XPBar.setFont(new Font("MV Boli", Font.BOLD, 20));
//        XPBar.setForeground(Color.blue);
        XPBar.setString(hero.getXp() + "/" + hero.getNextLevelXp());


        attack = labelCreator.createLabel(ap.attackButton, "Attack", 500, 660, combatButtonW, combatButtonH);
        //attack.setBounds(500, 600, 100, 30);
        attack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            if(hero.inCombat) {
                hero = combat.attack(new AttackObject(hero.getBaseDamage()));
                update(hero);
            }
        }});

        displayPane.setEditable(false);
        //displayPane.setLineWrap(true);
        ///displayPane.setWrapStyleWord(true);
        displayPane.setFont(labelCreator.getPixel());

        cont.add(scrollPane);
        cont.add(weapon);
        cont.add(armor);
        if(hero.getArtifact() != null) cont.add(artifact);
        cont.add(healthBar);
        cont.add(manaBar);
        cont.add(XPBar);
        //cont.add(gold);
        cont.add(enemyBorder);
        cont.add(enemyHealth);
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

    public void unlockChest(int x, int y) throws InterruptedException {
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
                    awardModal am = new awardModal(hero, loot);
                    // hero.awardingLoot = true;
                    hero = am.openLoot(hero, loot, "Chest Loot");

                    updateMystics();
//                    for(MysticInterface mystic: hero.getMystics()) {
//                        hero = mystic.onChest(hero);
//                    }
//                    hero.addLoot(loot);
//                    if(loot.getMystic() != null) {
//                        print("You got a mystic!: " + loot.getMystic().nameString());
//                        addMysticToInventory(loot.getMystic());
//                    }
                    dungeon[x][y].hasChest = false;
                    chestTiles.remove(chest);
                    break;
                }
                if(dungeon[x][y].hasBossChest){ //Make boss loot.
                    sound.openChestSound();
                    Loot loot = chestLoot.generateBossLoot(hero);
                    // Dialogue box to say what was in the chest!
                    print("You opened the boss chest. " + loot.getLoot());
                    awardModal am = new awardModal(hero, loot);
                    // hero.awardingLoot = true;
                    hero = am.openLoot(hero, loot, "Boss Chest Loot");

                    updateMystics();
//                    for(MysticInterface mystic: hero.getMystics()) {
//                        hero = mystic.onChest(hero);
//                    }
                    // hero.addLoot(loot);
//                    if(loot.getMystic() != null) {
//                        print("You got a mystic!: " + loot.getMystic().nameString());
//                        addMysticToInventory(loot.getMystic());
//                    }
                    dungeon[x][y].hasBossChest = false;
                    chestTiles.remove(chest);
                    break;
                }
            }
        }
    }

    public void shopKeeper (int x, int y) {
        if (dungeon[x][y].isShop) {
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
            hero.calcHeroStatus();
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

    public void print(String string, Color color) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date);
        if(displayPane!= null) {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setForeground(set, color);
            try {
                displayPane.getDocument().insertString(displayPane.getDocument().getLength(), '\n' + time + ": " + string, set);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMystics() {
        cont.remove(border);
        for(JLabel mystic : mysticIcons) {
            cont.remove(mystic);
        }
        mysticIcons.clear();
        mysticX = 130;
        for(MysticInterface mystics : hero.getMystics()) {
            JLabel mystic = labelCreator.createLabel(ap.getMystic(mystics.IconName()), mystics.hoverTextString(), mysticX, mysticY, mysticSize, mysticSize);
            mysticX += 30;
            mysticIcons.add(mystic);
            cont.add(mystic);
        }
        border = labelCreator.createLabelWithoutHover(ap.heroInventory, 5, 525, 475, 250);
        cont.add(border);
    }
}
