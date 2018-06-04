package dev.raymondsutton.rpg.tiles;

import dev.raymondsutton.rpg.gfx.Assets;

public class RockTile extends Tile {
    RockTile(int ID) {
        super(Assets.stone, ID);
    }

    @Override
    public boolean is_solid() {
        return true; // true means you cannot walk on it
    }
}
