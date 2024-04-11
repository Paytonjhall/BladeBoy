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
    public String blacksmith = "src/Assets/Portraits/blacksmith.png";
    public String armorer = "src/Assets/Portraits/armorer.png";
    public String sage = "src/Assets/Portraits/sage.png";
    public String archer = "src/Assets/Portraits/archer.png";
    public String barbarianWarLordIcon = "src/Assets/Portraits/barbarianWarLord.png";
    public String damageIcon="src/Assets/Weapons/Swords/damageIcon.png";
    public String mage = "src/Assets/Portraits/Mage.png";
    public String knight = "src/Assets/Portraits/knight.png";
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
    public String barbarianIcon = "src/Assets/Portraits/Barbarian.png";
    public String batIcon = "src/Assets/Portraits/bat.png";
    public String blackTile = "src/Assets/Dungeon/Tiles/blackTile.png";
    public String horizontalEdge = "src/Assets/Dungeon/Tiles/horizontalEdge.png";
    public String exit = "src/Assets/Dungeon/Tiles/exit.png";
    public String bossDoor = "src/Assets/Dungeon/Tiles/bossDoor.png";
    public String entrance = "src/Assets/Dungeon/Tiles/entrance.png";
    public String wall = "src/Assets/Dungeon/Tiles/wall.png";
    public String rightwall = "src/Assets/Dungeon/Tiles/rightwall.png";
    public String leftwall = "src/Assets/Dungeon/Tiles/leftwall.png";
    public String middlewall = "src/Assets/Dungeon/Tiles/middlewall.png";
    public String alonewall = "src/Assets/Dungeon/Tiles/alonewall.png";
    public String barrel = "src/Assets/Dungeon/Tiles/barrel.png";
    public String box = "src/Assets/Dungeon/Tiles/box.png";
    public String UIScroll = "src/Assets/UI/scrollCleared.png";
    public String stump1 = "src/Assets/Town/Tiles/stump.png";
    public String stump2 = "src/Assets/Town/Tiles/stump2.png";
    public String grass = "src/Assets/Town/Tiles/grass.png";
    public String yellowGrass = "src/Assets/Town/Tiles/yellowGrass.png";
    public String blueShroom = "src/Assets/Town/Tiles/blueShroom.png";
    public String redShrooms = "src/Assets/Town/Tiles/redShrooms.png";
    public String rock1 = "src/Assets/Town/Tiles/rock1.png";
    public String rock2 = "src/Assets/Town/Tiles/rock2.png";
    public String fence = "src/Assets/Town/Tiles/fence.png";
    public String pinkFlowers = "src/Assets/Town/Tiles/pinkFlowers.png";
    public String whiteFlowers = "src/Assets/Town/Tiles/whiteFlowers.png";
    public String sign = "src/Assets/Town/Tiles/sign.png";
    public String stoneSign = "src/Assets/Town/Tiles/stone_sign.png";
    public String statue = "src/Assets/Town/Tiles/statue.png";
    public String bush1 = "src/Assets/Town/Tiles/bush1.png";
    public String bush2 = "src/Assets/Town/Tiles/bush2.png";
    public String fern = "src/Assets/Town/Tiles/fern.png";
    public String sprout = "src/Assets/Town/Tiles/sprout.png";
    public String waterHorizontal = "src/Assets/Town/Tiles/waterHorizontal.png";
    public String waterVertical = "src/Assets/Town/Tiles/waterVertical.png";
    public String waterIntersection = "src/Assets/Town/Tiles/waterIntersection.png";
    public String storeHouse = "src/Assets/Town/Tiles/storeHouse.png";
    public String horizontalPath = "src/Assets/Town/Tiles/horizontalPath.png";
    public String verticalPath = "src/Assets/Town/Tiles/verticalPath.png";

    public String inventoryBackground = "src/Assets/UI/inventoryBackground.png";


    // Mystics:



    public String enemyIcon(){
        return "src/Assets/Portraits/enemy"+ (int)(Math.random() * 3)+".png";
    }

    public String getIcon (String name) {
        return "src/Assets/Portraits/" + name + ".png";
    }

    public String getMystic (String name) {return "src/Assets/Mystics/" + name + ".png";}
}
