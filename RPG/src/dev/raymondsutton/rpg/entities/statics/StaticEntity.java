package dev.raymondsutton.rpg.entities.statics;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
    // static meaning it does not MOVE
}
