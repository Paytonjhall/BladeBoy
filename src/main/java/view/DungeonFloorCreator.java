package view;

import java.util.Random;

public class DungeonFloorCreator {
    public int width =13;
    public int height=5;
    LabelCreator labelCreator = new LabelCreator();
    int enemyCount = 0;
    int chestCount = 0;
    Random random = new Random();
    DungeonTile[][] floor;
    public DungeonTile[][] createFloor(){
        Random random = new Random();
        enemyCount = random.nextInt(3) + 2;
        chestCount = random.nextInt(1) + 1;
        int exitHeight = random.nextInt(height);
        floor = new DungeonTile[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                floor[i][j] = new DungeonTile();
                    if(i == 0 && j == 0){
                    floor[i][j].setEntrance();
                    floor[i][j].setXandY(i, j);
                    floor[i][j].isWalkable = true;
                } else if (i == width - 1 && j == exitHeight){
                    floor[i][j].setExit();
                    floor[i][j].setXandY(i, j);
                } else {
                        int floorType = random.nextInt(20);
                        if(floorType >= 9){
                            floor[i][j].setFloor();
                            floor[i][j].setXandY(i, j);
                        } else {
                            floor[i][j].setWall();
                            floor[i][j].setXandY(i, j);
                        }
                }
            }
        }
        if(completeFloor(0,0,floor,exitHeight)){
            assignChests();
            assignEnemies();
            System.out.println("Created floor");
            return floor;
        }

        System.out.println("Floor not complete");
        return createFloor();
    }

    public DungeonTile findEntrance(DungeonTile[][] floor){
        for(int i = 0; i < floor.length; i++){
            for(int j = 0; j < floor[i].length; j++){
                if(floor[i][j].isEntrance){
                    return floor[i][j];
                }
            }
        }
        return null;
    }

    public DungeonTile findExit(DungeonTile[][] floor){
        for(int i = 0; i < floor.length; i++){
            for(int j = 0; j < floor[i].length; j++){
                if(floor[i][j].isExit){
                    return floor[i][j];
                }
            }
        }
        return null;
    }


    public boolean completeFloor(int x, int y, DungeonTile[][] floor, int exitHeight){
        if(x == width-1 && y == exitHeight){
            return true;
        }
        if(x < 0 || y < 0 || x >= width || y >= height){
            return false;
        }
        if(floor[x][y].isWall || floor[x][y].isVisited){
            return false;
        }
        floor[x][y].isVisited = true;
        if(completeFloor(x + 1, y, floor, exitHeight)){
            return true;
        }
        if(completeFloor(x, y + 1, floor,exitHeight)){
            return true;
        }
        if(completeFloor(x - 1, y, floor, exitHeight)){
            return true;
        }
        if(completeFloor(x, y - 1, floor,exitHeight)){
            return true;
        }
        return false;
    }


    public boolean accessibleTile(int x, int y, DungeonTile[][] floor, int desiredX, int desiredY){
            if(x == desiredX && y == desiredY){
                return true;
            }
            if(x < 0 || y < 0 || x >= width || y >= height){
                return false;
            }
            if(floor[x][y].isWall || floor[x][y].isVisited){
                return false;
            }
            floor[x][y].isVisited = true;
            if(accessibleTile(x + 1, y, floor, desiredX, desiredY)){
                return true;
            }
            if(accessibleTile(x, y + 1, floor, desiredX, desiredY)){
                return true;
            }
            if(accessibleTile(x - 1, y, floor, desiredX, desiredY)){
                return true;
            }
            if(accessibleTile(x, y - 1, floor, desiredX, desiredY)){
                return true;
            }
            return false;
    }

    public void assignEnemies(){ //TODO: Fixed if enemies are accessible or not
        for(int i = 0; i < enemyCount; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if(floor[x][y].isWalkable && !floor[x][y].isEntrance && !floor[x][y].isExit && !floor[x][y].hasChest && !floor[x][y].hasEnemy){
                floor[x][y].hasEnemy = true;
            } else {
                i--;
            }
        }
    }

    public void assignChests(){ //TODO: Fixed if chests are accessible or not
        for(int i = 0; i < chestCount; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if(floor[x][y].isWalkable && !floor[x][y].isEntrance && !floor[x][y].isExit && !floor[x][y].hasChest && !floor[x][y].hasEnemy){
                floor[x][y].hasChest= true;
            } else {
                i--;
            }
        }
    }

}


