package view;

import java.util.Random;

public class DungeonTile {
    boolean isEdge = false;
    boolean isWall = false;
    boolean hasEnemy = false;
    boolean hasChest = false;
    boolean isExit = false;
    boolean isEntrance = false;
    boolean isVisited = false;
    boolean isCurrent = false;
    boolean isPath = false;
    boolean hasPlayer = false;
    boolean isWalkable = true;
    boolean hasTorch = false;
    boolean hasBoss = false;
    boolean hasBossChest = false;
    boolean isShop = false;
    String shopOwner = "";
    String icon;
    int x;
    int y;

    public void setEntrance() {
        isEntrance = true;
        isWalkable = true;
        isVisited = false;
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

    public void setTownFloor(){
        isWalkable = true;
        icon = "src/Assets/Town/Tiles/emptyGrass.png";
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
        this.x = (x)*100;
        this.y = (y)*100;
    }



    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }
}
