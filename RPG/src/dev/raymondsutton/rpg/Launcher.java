package dev.raymondsutton.rpg;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("RPG", 800, 700);
        game.start();
    }
}
