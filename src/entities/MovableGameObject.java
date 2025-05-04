package entities;

import core.GameMap;
import java.awt.*;
import tiles.*;

public abstract class MovableGameObject extends GameObject {
    protected GameMap gameMap;

    public MovableGameObject(int x, int y, GameMap gameMap) {
        super(x, y);
        this.gameMap = gameMap;
    }
    public boolean canMoveTo(int newX, int newY) {
        int tileX1 = newX / Tile.TILE_SIZE;
        int tileY1 = newY / Tile.TILE_SIZE;
        int tileX2 = (newX + Tile.TILE_SIZE - 1) / Tile.TILE_SIZE;
        int tileY2 = (newY + Tile.TILE_SIZE - 1) / Tile.TILE_SIZE;

        return (gameMap.isGrassTile(tileX1, tileY1) || gameMap.isBomberOnPortal(tileX1, tileY1)) &&
                (gameMap.isGrassTile(tileX2, tileY1) || gameMap.isBomberOnPortal(tileX2, tileY1)) &&
                (gameMap.isGrassTile(tileX1, tileY2) || gameMap.isBomberOnPortal(tileX1, tileY2)) &&
                (gameMap.isGrassTile(tileX2, tileY2) || gameMap.isBomberOnPortal(tileX2, tileY2));
    }


    public abstract void update();
}
