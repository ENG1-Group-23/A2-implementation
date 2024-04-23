package com.main.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.main.utils.CollisionHandler;
import com.main.utils.PlayerForTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class MovementTests {
    private PlayerForTests player = new PlayerForTests();
    private CollisionHandler collisionHandler = player.getCollisionHandler();

    @Test
    public void testMoveLeft() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX - (float) (player.normalizedSpeed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveRight() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX + (float) (player.normalizedSpeed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveUp() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY + (float) (player.normalizedSpeed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveDown() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY - (float) (player.normalizedSpeed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }
}
