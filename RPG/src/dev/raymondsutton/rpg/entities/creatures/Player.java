package dev.raymondsutton.rpg.entities.creatures;

import dev.raymondsutton.rpg.Handler;
import dev.raymondsutton.rpg.entities.Entity;
import dev.raymondsutton.rpg.gfx.Animation;
import dev.raymondsutton.rpg.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
    //Animations
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;

    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;


    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32; // customize these values depending on the sprite

        //Initialize Animation
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

    }

    @Override
    public void die() {
        System.out.println("You Lose.");
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        move();
        handler.getGame_camera().centerOnEntity(this);
        //Attack
        checkAttacks();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) return;

        Rectangle collision_bounds = getCollisionBounds(0, 0);
        Rectangle attack_rec = new Rectangle();
        int attack_rec_size = 20;
        attack_rec.width = attack_rec_size;
        attack_rec.height = attack_rec_size;

        if (handler.getKeyManager().aUp) {
            attack_rec.x = collision_bounds.x + collision_bounds.width / 2 - attack_rec_size / 2;
            attack_rec.y = collision_bounds.y - attack_rec_size;
        } else if (handler.getKeyManager().aDown) {
            attack_rec.x = collision_bounds.x + collision_bounds.width / 2 - attack_rec_size / 2;
            attack_rec.y = collision_bounds.y + collision_bounds.height;
        } else if (handler.getKeyManager().aLeft) {
            attack_rec.x = collision_bounds.x - attack_rec_size;
            attack_rec.y = collision_bounds.y + collision_bounds.height / 2 - attack_rec_size / 2;
        } else if (handler.getKeyManager().aRight) {
            attack_rec.x = collision_bounds.x + collision_bounds.width;
            attack_rec.y = collision_bounds.y + collision_bounds.height / 2 - attack_rec_size / 2;
        } else {
            return; // not attacking
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0, 0).intersects(attack_rec)) {
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationDirectionImage(), (int) (x - handler.getGame_camera().getX_offset()),
                (int) (y - handler.getGame_camera().getY_offset()), width, height, null);
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGame_camera().getX_offset()),
//                (int) (y + bounds.y - handler.getGame_camera().getY_offset()), bounds.width, bounds.height);
        // draws temp bounding box
    }

    private void getInput() {
        x_move = 0;
        y_move = 0;

        if (handler.getKeyManager().up) {
            y_move = -speed;
        }
        if (handler.getKeyManager().down) {
            y_move = speed;
        }
        if (handler.getKeyManager().left) {
            x_move = -speed;
        }
        if (handler.getKeyManager().right) {
            x_move = speed;
        }
    }

    private BufferedImage getCurrentAnimationDirectionImage() {
        if (x_move < 0) return animLeft.getCurrentFrame();
        else if (x_move > 0) return animRight.getCurrentFrame();
        else if (y_move < 0) return animUp.getCurrentFrame();
        else return animDown.getCurrentFrame();
    }
}
