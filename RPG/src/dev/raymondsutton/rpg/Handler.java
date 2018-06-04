package dev.raymondsutton.rpg;

import dev.raymondsutton.rpg.gfx.GameCamera;
import dev.raymondsutton.rpg.input.KeyManager;
import dev.raymondsutton.rpg.input.MouseManager;
import dev.raymondsutton.rpg.worlds.World;

public class Handler {
    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public GameCamera getGame_camera() {
        return game.getGame_camera();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
