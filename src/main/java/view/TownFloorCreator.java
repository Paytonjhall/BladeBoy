package view;

import Game.AssetPath;
import view.Map.MapInterface;

public class TownFloorCreator implements MapInterface {
    AssetPath ap = new AssetPath();
    DungeonTile[][] map;
    DungeonTile blacksmithTile;
    DungeonTile oracleTile;
    DungeonTile armoryTile;
    DungeonTile storeTile;
    DungeonTile artificiaryTile;
    DungeonTile shopTile;

    public DungeonTile[][] createMap() {

        shopTile = new DungeonTile();
        shopTile.setIcon(ap.storeHouse);
        map = new DungeonTile[width][height];

        map[0][2] = shopTile;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(map[i][j]==null) {
                    map[i][j] = new DungeonTile();
                    map[i][j].setTownFloor();
                }
                if( i < width - 4 && j == 0) {
                    map[i][j].setIcon(ap.horizontalPath);
                    map[i][j].setXandY(i, j);
                } else if ( i > width - 4 && j == height - 1) {
                    //map[i][j].setIcon(ap.fence);
                    map[i][j].setXandY(i, j);
                } else if (( j == 1 || j == 3)&& i < 3){
                    //map[i][j].setWall();
                    map[i][j].setIcon(ap.waterHorizontal);
                }
                else if((j ==1 || j ==3) && i == 3){
                    map[i][j].setIcon(ap.waterIntersection);

                }
                else if (j == 2 && i == 3){
                    map[i][j].setIcon(ap.waterVertical);
                } else if (map[i][j] == shopTile) {

                }
            }
        }


        return map;
    }

    public DungeonTile setBlackSmith() {
        blacksmithTile = new DungeonTile();
        blacksmithTile.isShop = true;
        blacksmithTile.icon = ap.box;
        return blacksmithTile;
    }
}
