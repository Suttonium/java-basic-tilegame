package dev.raymondsutton.rpg;

import dev.raymondsutton.rpg.display.Display;
import dev.raymondsutton.rpg.gfx.Assets;
import dev.raymondsutton.rpg.gfx.GameCamera;
import dev.raymondsutton.rpg.input.KeyManager;
import dev.raymondsutton.rpg.input.MouseManager;
import dev.raymondsutton.rpg.states.GameState;
import dev.raymondsutton.rpg.states.MenuState;
import dev.raymondsutton.rpg.states.SettingsState;
import dev.raymondsutton.rpg.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;
    private Thread thread;
    private static final int BILLION = 1000000000;

    private int width, height;
    private String title;
    private boolean running = false;

    //States
    public State gameState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera game_camera;


    Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);

        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);

        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        //Handler
        Handler handler = new Handler(this);

        game_camera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        State menuState = new MenuState(handler);
        State settingsState = new SettingsState(handler);
        State.setState(menuState);
    }


    private void tick() {
        keyManager.tick();
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        //Draw Here
        if (State.getState() != null) {
            State.getState().render(g);
        }
        //End Drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        int FPS = 60; // tick and render methods will run this many times per second
        double timePerTick = BILLION / FPS; // one second / FPS, max amount of time we are allowed to run tick and render
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime(); // current time in nanoseconds
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    KeyManager getKeyManager() {
        return keyManager;
    }

    MouseManager getMouseManager() {
        return mouseManager;
    }


    GameCamera getGame_camera() {
        return game_camera;
    }

    synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start(); // calls run method
    }

    private synchronized void stop() {
        if (!running) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
