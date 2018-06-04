package dev.raymondsutton.rpg.gfx;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.entities.Entity;
import dev.raymondsutton.rpg.tiles.Tile;

public class GameCamera {
    private float x_offset, y_offset;
    private Handler handler;

    public GameCamera(Handler handler, float x_offset, float y_offset) {
        this.handler = handler;
        this.x_offset = x_offset;
        this.y_offset = y_offset;
    }

    private void checkBlankSpace() {
        if (getX_offset() < 0) {
            x_offset = 0;
        } else if (getX_offset() > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            x_offset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }
        if (getY_offset() < 0) {
            y_offset = 0;
        } else if (getY_offset() > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
            y_offset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }
    }

    public void move(float x_amt, float y_amt) {
        x_offset += x_amt;
        y_offset += y_amt;
        checkBlankSpace();
    }

    public void centerOnEntity(Entity e) {
        x_offset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        y_offset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public float getX_offset() {
        return x_offset;
    }

    public void setX_offset(float x_offset) {
        this.x_offset = x_offset;
        checkBlankSpace();
    }

    public float getY_offset() {
        return y_offset;
    }

    public void setY_offset(float y_offset) {
        this.y_offset = y_offset;
        checkBlankSpace();
    }
}
