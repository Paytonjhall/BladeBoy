package Game;

public class AssetPath {
    public String Gold = "src/Assets/Loot/Gold.png";
    public String healthPotion = "src/Assets/Potions/heal.png";
    public String xpPotion = "src/Assets/Potions/xpPotion.png";
    public String luckPotion = "src/Assets/Potions/luckPotion.png";
    public String voidPotion = "src/Assets/Potions/voidPotion.png";
    public String holyPotion = "src/Assets/Potions/holyPotion.png";
    public String bloodPotion = "src/Assets/Potions/bloodPotion.png";
    public String regenPotion = "src/Assets/Potions/regenPotion.png";
    public String critPotion = "src/Assets/Potions/critPotion.png";
    public String devilPotion = "src/Assets/Potions/devilPotion.png";
    public String emptyPotion = "src/Assets/Potions/empty.png";
    public String backpack = "src/Assets/Other/Backpack.png";
    public String blacksmith = "src/Assets/Portraits/smith.png";
    public String viking = "src/Assets/Portraits/viking.png";
    public String damageIcon="src/Assets/Weapons/Swords/damageIcon.png";
    public String wizard = "src/Assets/Portraits/wizard.png";
    public String hero = "src/Assets/Portraits/hero.png";
    public String oracle = "src/Assets/Portraits/Oracle.png";
    public String skillpoint = "src/Assets/Other/skillpoint.png";
    public String forest = "src/Assets/Dungeon/forest.png";
    public String jungle = "src/Assets/Dungeon/jungle.png";
    public String beach = "src/Assets/Dungeon/beach.png";
    public String swamp = "src/Assets/Dungeon/swamp.png";
    public String baseTile = "src/Assets/Dungeon/Tiles/base.png";
    public String enemyTile = "src/Assets/Portraits/enemy.png";
    public String bossTile = "src/Assets/Portraits/boss.png";
    public String chest = "src/Assets/Dungeon/Tiles/chest.png";
    public String openedChest = "src/Assets/Dungeon/Tiles/openedChest.png";
    public String torch = "src/Assets/Dungeon/Tiles/torch.png";
    public String goblinBossIcon = "src/Assets/Portraits/goblinBoss.png";
    public String goblinArcherIcon = "src/Assets/Portraits/goblinArcher.png";
    public String goblinSoldierIcon = "src/Assets/Portraits/goblinSoldier.png";
    public String slimeIcon = "src/Assets/Portraits/slime.png";
    public String slimeBossIcon = "src/Assets/Portraits/slimeBoss.png";
    public String golemIcon = "src/Assets/Portraits/golem.png";
    public String frogIcon = "src/Assets/Portraits/frog.png";
    public String frogBossIcon = "src/Assets/Portraits/frogBoss.png";
    public String wolfIcon = "src/Assets/Portraits/wolf.png";
    public String bearIcon = "src/Assets/Portraits/bear.png";
    public String spiderIcon = "src/Assets/Portraits/spider.png";
    public String monsterIcon = "src/Assets/Portraits/monster.png";
    public String boarIcon = "src/Assets/Portraits/boar.png";
    public String boarBossIcon = "src/Assets/Portraits/boarBoss.png";
    public String barbarianIcon = "src/Assets/Portraits/barbarian.png";
    public String batIcon = "src/Assets/Portraits/bat.png";

    public String enemyIcon(){
        return "src/Assets/Portraits/enemy"+ (int)(Math.random() * 3)+".png";
    }
}
