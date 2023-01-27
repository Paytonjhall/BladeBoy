package view.Map;

import view.DungeonTile;

public interface MapInterface {
    DungeonTile[][] createMap();
    int width = 13;
    int height = 5;
}
