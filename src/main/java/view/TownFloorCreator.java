package view;

import Game.AssetPath;
import view.Map.MapInterface;

public class TownFloorCreator implements MapInterface {
    AssetPath ap = new AssetPath();
    DungeonTile[][] map;
    DungeonTile blacksmithTile = new DungeonTile();
    DungeonTile oracleTile;
    DungeonTile armoryTile;
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
                //SET PATHS
                if( i < width - 3 && j == 0) {
                    map[i][j].setIcon(ap.horizontalPath);
                    map[i][j].setXandY(i, j);
                } else if ( (i+1 > width-3 && i != width-1  && j == height - 3)) {
                    map[i][j].setIcon(ap.horizontalPath);
                    map[i][j].setXandY(i, j);
                }
                else if ( (i == 4 || i == 9) && j >= 1 && j < 4 ){
                    map[i][j].setIcon(ap.verticalPath);
                    map[i][j].setXandY(i, j);
                }
                else if ( i <= 4 && j == 4) {
                    map[i][j].setIcon(ap.horizontalPath);
                    map[i][j].setXandY(i, j);
                }
                //SET WATER
                else if (( j == 1 || j == 3)&& i < 3){
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

    public DungeonTile setBlackSmith () {
        blacksmithTile = new DungeonTile();
        blacksmithTile.isShop = true;
        blacksmithTile.icon = ap.box;
        return blacksmithTile;
    }

    public DungeonTile setArmorerTile () {
        armoryTile = new DungeonTile();
        armoryTile.isShop = true;
        armoryTile.icon = ap.box;
        return armoryTile;
    }

    public DungeonTile setOracleTile () {
        oracleTile = new DungeonTile();
        oracleTile.isShop = true;
        oracleTile.icon = ap.box;
        return oracleTile;
    }

    public DungeonTile setShopTile () {
        shopTile = new DungeonTile();
        shopTile.isShop = true;
        shopTile.icon = ap.box;
        return shopTile;
    }

    public DungeonTile setArtificiaryTile () {
        artificiaryTile = new DungeonTile();
        artificiaryTile.isShop = true;
        artificiaryTile.icon = ap.box;
        return artificiaryTile;
    }

}
