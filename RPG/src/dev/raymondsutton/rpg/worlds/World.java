package dev.raymondsutton.rpg.worlds;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.entities.EntityManager;
import dev.raymondsutton.rpg.entities.creatures.Player;
import dev.raymondsutton.rpg.entities.statics.Rock;
import dev.raymondsutton.rpg.entities.statics.Tree;
import dev.raymondsutton.rpg.tiles.Tile;
import dev.raymondsutton.rpg.utils.Utils;

import java.awt.*;

public class World {
    private int width, height;
    private int spawn_x, spawn_y;
    private int[][] tiles;
    private Handler handler;

    //Entities
    private EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 450));

        load_world(path);

        entityManager.getPlayer().setX(spawn_x);
        entityManager.getPlayer().setY(spawn_y);

    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGame_camera().getX_offset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGame_camera().getX_offset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGame_camera().getY_offset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGame_camera().getY_offset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGame_camera().getX_offset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGame_camera().getY_offset()));
            }
        }

        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.dirtTile;
        }
        return t;
    }

    private void load_world(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); // split on any whitespace
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawn_x = Utils.parseInt(tokens[2]);
        spawn_y = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); // 4 because first 4 variables in file are not world data
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
