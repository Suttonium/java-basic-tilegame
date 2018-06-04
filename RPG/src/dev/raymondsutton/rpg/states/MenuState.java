package dev.raymondsutton.rpg.states;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.gfx.Assets;
import dev.raymondsutton.rpg.ui.UIImageButton;
import dev.raymondsutton.rpg.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObj(new UIImageButton(200, 200, 128, 64, Assets.btn_start, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
