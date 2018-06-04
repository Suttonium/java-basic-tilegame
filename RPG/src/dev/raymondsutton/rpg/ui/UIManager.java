package dev.raymondsutton.rpg.ui;

import dev.raymondsutton.rpg.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> objs;

    public UIManager(Handler handler) {
        this.handler = handler;
        objs = new ArrayList<>();
    }

    public void tick() {
        for (UIObject o : objs) o.tick();
    }

    public void render(Graphics g) {
        for (UIObject o : objs) o.render(g);
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : objs) o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objs) o.onMouseRelease(e);

    }

    public void addObj(UIObject o) {
        objs.add(o);
    }

    public void removeObj(UIObject o) {
        objs.remove(o);
    }
}
