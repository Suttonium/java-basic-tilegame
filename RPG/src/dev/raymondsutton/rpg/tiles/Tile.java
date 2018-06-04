package dev.raymondsutton.rpg.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //STATIC
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);

    //CLASS
    protected BufferedImage texture;
    protected final int ID;
    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

    Tile(BufferedImage texture, int ID) {
        this.texture = texture;
        this.ID = ID;

        tiles[getID()] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean is_solid() {
        return false; // false means you can walk on it
    }

    public int getID() {
        return ID;
    }
}
