package view;

import java.util.Random;

public class DungeonFloorCreator {
    LabelCreator labelCreator = new LabelCreator();
    Random random = new Random();
    public DungeonTile[][] createFloor(int width, int height){
        int entranceHeight = random.nextInt(height-2)+1;
        int exitHeight = random.nextInt(height-2)+1;
        DungeonTile[][] floor = new DungeonTile[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                floor[i][j] = new DungeonTile();
                if(i == 0 || j == 0 || i == width - 1 || j == height - 1){
                    if(i==0||i==width-1)
                        floor[i][j].setVertEdge();
                    else floor[i][j].setHorEdge();
                    floor[i][j].setXandY(i, j);
                } else if(i == 1 && j == entranceHeight){
                    floor[i][j].setEntrance();
                    floor[i][j].setXandY(i, j);
                    floor[i][j].isWalkable = true;
                } else if (i == width - 2 && j == exitHeight){
                    floor[i][j].setExit();
                    floor[i][j].setXandY(i, j);
                } else {
                    floor[i][j].setFloor();
                    floor[i][j].setXandY(i, j);
                }
            }
        }
        return floor;
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
}

