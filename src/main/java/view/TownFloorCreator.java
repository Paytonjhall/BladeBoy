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

    public DungeonTile[][] createMap() {

        return map;
    }

    public DungeonTile setBlackSmith() {
        blacksmithTile = new DungeonTile();
        blacksmithTile.isShop = true;
        blacksmithTile.icon = ap.box;
        return blacksmithTile;
    }
}
