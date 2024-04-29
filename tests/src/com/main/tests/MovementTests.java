package com.main.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.main.Main;
import com.main.entity.Player;
import com.main.map.GameMap;
import com.main.utils.CollisionHandler;
import com.main.utils.GameData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class MovementTests {
    private Player player;
    private CollisionHandler collisionHandler;

    @Before
    public void setUp() {
        OrthographicCamera camera = new OrthographicCamera();
        Main game = new Main();
        game.gameData = new GameData();
        TiledMap tiledMap = new TmxMapLoader().load("map/MainMap.tmx");
        GameMap gameMap = new GameMap(camera, null, tiledMap);
        this.player = new Player(game, gameMap, camera);
        this.collisionHandler = player.getCollisionHandler();
    }

    @Test
    public void testMoveLeft() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX - (float) (player.speed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveRight() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX + (float) (player.speed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveUp() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY + (float) (player.speed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveDown() {
        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY - (float) (player.speed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }
}
