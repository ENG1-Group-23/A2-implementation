package bytemusketeers.heslingtonhustle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import bytemusketeers.heslingtonhustle.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class MovementTests {
    @Test
    public void testMoveLeft() {
        // Simulating Player initialisation
        Main game = new Main();
        float worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = worldX - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
        float targY = worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        Assert.assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveRight() {
        // Simulating Player initialisation
        Main game = new Main();
        float worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = worldX + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
        float targY = worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        Assert.assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveUp() {
        // Simulating Player initialisation
        Main game = new Main();
        float worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = worldX;
        float targY = worldY + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        Assert.assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveDown() {
        // Simulating Player initialisation
        Main game = new Main();
        float worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = worldX;
        float targY = worldY - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        Assert.assertEquals(targX, newPos.x, 0.0001);
    }
}
