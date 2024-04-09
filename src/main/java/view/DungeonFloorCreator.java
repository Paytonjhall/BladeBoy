package view;

import Game.AssetPath;
import view.Map.MapInterface;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class DungeonFloorCreator implements MapInterface {
    //double shopOwnerGenerationChance = .80;
    double shopOwnerGenerationChance = .080;

      String[] shopOwners = new String[]{"Blacksmith", "Armory", "Artifact"};
//    String[] shopOwners = new String[]{"Blacksmith"}; // Delete this one and use the one above
//    boolean blacksmith = false;
//    boolean armory = false;
//    boolean artificiary = false;
    AssetPath ap = new AssetPath();
    LabelCreator labelCreator = new LabelCreator();
    int enemyCount = 0;
    int chestCount = 0;
    int torchCount = 0;
    Random random = new Random();
    DungeonTile[][] map;

    public DungeonTile[][] createMap() {
        ArrayList<String> loadNPCS = new ArrayList<>();
        for (String owners: shopOwners) {
            if (Math.random() >= shopOwnerGenerationChance){
                loadNPCS.add(owners);
//                shopOwners.
//                if(Objects.equals(owners, "Blacksmith")) blacksmith = true;
//                else if(Objects.equals(owners, "Armory")) armory = true;
//                else if(Objects.equals(owners, "Artifact")) artificiary = true;
            }
        }

        Random random = new Random();
        enemyCount = random.nextInt(3) + 2;
        chestCount = random.nextInt(1) + 1;
        torchCount = random.nextInt(3);
        int exitHeight = random.nextInt(height);
        map = new DungeonTile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new DungeonTile();
                if (i == 0 && j == 0) {
                    map[i][j].setEntrance();
                    map[i][j].setXandY(i, j);
                    map[i][j].isWalkable = true;
                } else if (i == width - 1 && j == exitHeight) {
                    map[i][j].setExit();
                    map[i][j].setXandY(i, j);
                } else {
                    int floorType = random.nextInt(20);
                    if (floorType >= 9) {
                        map[i][j].setFloor();
                        map[i][j].setXandY(i, j);
                    } else {
                        map[i][j].setWall();
                        map[i][j].setXandY(i, j);
                    }
                }
            }
        }
        if (completeFloor(0, 0, map, exitHeight)) {

            assignChests();
            assignEnemies();
            assignTorch();
            assignNPCs(loadNPCS);
            System.out.println("Created floor");
            return fixWalls();
        }

        System.out.println("Floor not complete");
        return createMap();
    }

    public DungeonTile[][] createBossFloor() {
        map = new DungeonTile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new DungeonTile();
                if (i == 0 && j == 2) {
                    map[i][j].setEntrance();
                    map[i][j].setXandY(i, j);
                } else if (i == width - 1 && j == 2) {
                    map[i][j].setExit();
                    map[i][j].setXandY(i, j);
                } else if (i == 0 || i == width - 1 || j == 0 || j == height - 1 || (i == 11 && j == 1) || (i == 11 && j == 3) || (i == 12 && j == 1) || (i == 12 && j == 3)) {
                    map[i][j].setWall();
                    map[i][j].setXandY(i, j);
                } else if (i == 10 && j == 2) {
                    map[i][j].setFloor();
                    map[i][j].hasBoss = true;
                    map[i][j].setXandY(i, j);
                } else if (i == 10 && j == 1 || i == 10 && j == 3) {
                    map[i][j].setFloor();
                    map[i][j].hasTorch = true;
                    map[i][j].setXandY(i, j);
                } else if (i == 11 && j == 2) {
                    map[i][j].setFloor();
                    map[i][j].hasBossChest = true;
                    map[i][j].setXandY(i, j);
                } else {
                    map[i][j].setFloor();
                    map[i][j].setXandY(i, j);
                }
            }
        }
        return fixWalls();
    }

    public DungeonTile findEntrance(DungeonTile[][] floor) {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                if (floor[i][j].isEntrance) {
                    return floor[i][j];
                }
            }
        }
        return null;
    }

    public DungeonTile findExit(DungeonTile[][] floor) {
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[i].length; j++) {
                if (floor[i][j].isExit) {
                    return floor[i][j];
                }
            }
        }
        return null;
    }


    public boolean completeFloor(int x, int y, DungeonTile[][] floor, int exitHeight) {
        if (x == width - 1 && y == exitHeight) {
            return true;
        }
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        if (floor[x][y].isWall || floor[x][y].isVisited) {
            return false;
        }
        floor[x][y].isVisited = true;
        if (completeFloor(x + 1, y, floor, exitHeight)) {
            return true;
        }
        if (completeFloor(x, y + 1, floor, exitHeight)) {
            return true;
        }
        if (completeFloor(x - 1, y, floor, exitHeight)) {
            return true;
        }
        if (completeFloor(x, y - 1, floor, exitHeight)) {
            return true;
        }
        return false;
    }


    public boolean accessibleTile(int x, int y, DungeonTile[][] floor, int desiredX, int desiredY) {
        if (x == desiredX && y == desiredY) {
            return true;
        }
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        if (floor[x][y].isWall || floor[x][y].isVisited) {
            return false;
        }
        floor[x][y].isVisited = true;
        if (accessibleTile(x + 1, y, floor, desiredX, desiredY)) {
            return true;
        }
        if (accessibleTile(x, y + 1, floor, desiredX, desiredY)) {
            return true;
        }
        if (accessibleTile(x - 1, y, floor, desiredX, desiredY)) {
            return true;
        }
        if (accessibleTile(x, y - 1, floor, desiredX, desiredY)) {
            return true;
        }
        return false;
    }

    public void assignEnemies() { //TODO: Fixed if enemies are accessible or not
        for (int i = 0; i < enemyCount; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (map[x][y].isWalkable && !map[x][y].isEntrance && !map[x][y].isExit && !map[x][y].hasChest && !map[x][y].hasEnemy) {
                map[x][y].hasEnemy = true;
            } else {
                i--;
            }
        }
    }

    public void assignChests() { //TODO: Fixed if chests are accessible or not
        for (int i = 0; i < chestCount; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (map[x][y].isWalkable && !map[x][y].isEntrance && !map[x][y].isExit && !map[x][y].hasChest && !map[x][y].hasEnemy) {
                map[x][y].hasChest = true;
            } else {
                i--;
            }
        }
    }

    public void assignTorch() {

        for (int i = 0; i < torchCount; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (map[x][y].isWalkable && !map[x][y].isEntrance && !map[x][y].isExit && !map[x][y].hasChest && !map[x][y].hasEnemy && !map[x][y].hasTorch) {
                map[x][y].hasTorch = true;
            } else {
                i--;
            }
        }
    }

    public void assignNPCs(ArrayList<String> shopOwners) {
        System.out.println("Assigning NPCs: " + shopOwners.size());
        for (int i = 0; i < shopOwners.size(); i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (map[x][y].isWalkable && !map[x][y].isEntrance && !map[x][y].isExit && !map[x][y].hasChest && !map[x][y].hasEnemy && !map[x][y].hasTorch) {
                map[x][y].isShop = true;
                map[x][y].shopOwner = shopOwners.get(i);
                if(Objects.equals(shopOwners.get(i), "Blacksmith")) map[x][y].setIcon(ap.blacksmith);
                else if(Objects.equals(shopOwners.get(i), "Armory")) map[x][y].setIcon(ap.armorer);
                else if(Objects.equals(shopOwners.get(i), "Artifact")) map[x][y].setIcon(ap.sage);
            } else {
                i--;
            }
        }
    }

    public DungeonTile[][] fixWalls() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //if (map[i][j].isWall) {
                    if(i == 0 && map[i][j].isWall) map[i][j].setIcon(ap.rightwall);
                    if(i == 0 && map[i][j].isWall && map[i+1][j].isWall) map[i][j].setIcon(ap.leftwall);
                    if(i == 12 && map[i][j].isWall) map[i][j].setIcon(ap.leftwall);
                    if(i == 12 && map[i][j].isWall && map[i-1][j].isWall) map[i][j].setIcon(ap.rightwall);
                    //else if(map[i-1][j].isWall && !map[i][j].isWall) map[i-1][j].setIcon(ap.alonewall);
                    //else if(map[i+1][j].isWall && !map[i][j].isWall) map[i+1][j].setIcon(ap.leftwall);
                    if(i>0 && i < 12 && map[i][j].isWall && map[i-1][j].isWall && map[i+1][j].isWall) map[i][j].setIcon(ap.wall); //This needs to be checked with the other walls to make sure its not the same one.
                if(i>0 && i < 12 && map[i][j].isWall && map[i-1][j].isWall && map[i+1][j].isWall && Objects.equals(map[i - 1][j].getIcon(), ap.wall)) map[i][j].setIcon(ap.middlewall);
                    if(i>0 && i < 12 && map[i][j].isWall && map[i-1][j].isWall && !map[i+1][j].isWall) map[i][j].setIcon(ap.rightwall);
                    if(i>0 && i < 12 && map[i][j].isWall && !map[i-1][j].isWall && map[i+1][j].isWall) map[i][j].setIcon(ap.leftwall);
                    if(i>0 && i < 12 && map[i][j].isWall && !map[i-1][j].isWall && !map[i+1][j].isWall) map[i][j].setIcon(ap.alonewall);
            //    }
            }
        }
        return map;
    }
}


