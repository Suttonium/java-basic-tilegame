package dev.raymondsutton.rpg.entities.statics;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.gfx.Assets;
import dev.raymondsutton.rpg.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void die() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGame_camera().getX_offset()),
                (int) (y - handler.getGame_camera().getY_offset()), width, height, null);
    }
}
