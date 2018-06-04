package dev.raymondsutton.rpg.entities.creatures;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.entities.Entity;
import dev.raymondsutton.rpg.tiles.Tile;

abstract class Creature extends Entity {
    private static final float DEFAULT_SPEED = 3.0f;
    static final int DEFAULT_CREATURE_WIDTH = 64;
    static final int DEFAULT_CREATURE_HEIGHT = 64;

    float speed;
    float x_move, y_move;

    Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        x_move = 0;
        y_move = 0;
    }

    void move() {
        if (checkEntityCollisions(x_move, 0f)) moveX();
        if (checkEntityCollisions(0f, y_move)) moveY();
    }

    private void moveX() {
        if (x_move > 0) { // moving right
            int tempX = (int) (x + x_move + bounds.x + bounds.width) / Tile.TILE_WIDTH; // where we are moving to
            // checking where we are going and upper right and lower left corner of hit box
            if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tempX,
                    (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += x_move;
            } else {
                x = tempX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (x_move < 0) { //moving left
            int tempX = (int) (x + x_move + bounds.x) / Tile.TILE_WIDTH; // where we are moving to
            // checking where we are going and upper right and lower left corner of hit box
            if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tempX,
                    (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += x_move;
            }
        }
    }

    private void moveY() {
        if (y_move < 0) { // up
            int tempY = (int) (y + y_move + bounds.y) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)) {
                y += y_move;
            } else {
                y = tempY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (y_move > 0) { // down
            int tempY = (int) (y + y_move + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)) {
                y += y_move;
            } else {
                y = tempY * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    private boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).is_solid();
    }

    public float getX_move() {
        return x_move;
    }

    public void setX_move(float x_move) {
        this.x_move = x_move;
    }

    public float getY_move() {
        return y_move;
    }

    public void setY_move(float y_move) {
        this.y_move = y_move;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
