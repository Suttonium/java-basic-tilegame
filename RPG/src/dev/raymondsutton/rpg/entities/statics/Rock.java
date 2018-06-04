package dev.raymondsutton.rpg.entities.statics;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.gfx.Assets;
import dev.raymondsutton.rpg.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void die() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x - handler.getGame_camera().getX_offset()),
                (int) (y - handler.getGame_camera().getY_offset()), width, height, null);
    }

}
