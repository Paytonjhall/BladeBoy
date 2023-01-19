package view;

import java.util.Random;

public class DungeonTile {
    boolean isEdge = false;
    boolean isWall = false;
    boolean hasEnemy = false;
    boolean hasItem = false;
    boolean isExit = false;
    boolean isEntrance = false;
    boolean isVisited = false;
    boolean isCurrent = false;
    boolean isPath = false;
    boolean hasPlayer = false;
    boolean isWalkable = true;
    String icon;
    int x;
    int y;

    public void setEntrance() {
        isEntrance = true;
        isWalkable = true;
        isVisited = true;
        icon = "src/Assets/Dungeon/Tiles/entrance.png";
    }

    public void setFloor(){
        Random random = new Random();
        int floorType = random.nextInt(7) + 1;
        icon = "src/Assets/Dungeon/Tiles/"+floorType+".png";
        isWalkable = true;
        isPath = true;
    }

    public void setExit(){
        isExit = true;
        isWalkable = true;
        icon = "src/Assets/Dungeon/Tiles/exit.png";
    }

    public void setWall(){
        isWall = true;
        isWalkable = false;
        icon = "src/Assets/Dungeon/Tiles/wall.png";
    }

    public void setVertEdge(){
        isEdge = true;
        isWall = true;
        isWalkable = false;
        icon = "src/Assets/Dungeon/Tiles/verticalEdge.png";
    }

    public void setHorEdge(){
        isEdge = true;
        isWall = true;
        isWalkable = false;
        icon = "src/Assets/Dungeon/Tiles/horizontalEdge.png";
    }

    public void setXandY(int x, int y){
        this.x = (x-1)*100;
        this.y = (y-1)*100;
    }
}
