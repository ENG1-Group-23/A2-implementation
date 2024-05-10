package bytemusketeers.heslingtonhustle;

import bytemusketeers.heslingtonhustle.entity.Player;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import bytemusketeers.heslingtonhustle.utils.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
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
        HeslingtonHustle game = new HeslingtonHustle();
        game.gameData = new GameData();
        TiledMap tiledMap = new TmxMapLoader().load("map/MainMap.tmx");
        GameMap gameMap = new GameMap(camera, null, tiledMap);
        this.player = new Player(game, gameMap, camera);
        this.collisionHandler = player.getCollisionHandler();
    }

    @Test
    public void testMoveLeft() {
        // Simulating Player initialisation
        HeslingtonHustle game = new HeslingtonHustle();
        float worldX = (float) game.screenWidth / 2 - (float) game.screenHeight / 2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = player.worldX - (player.speed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveRight() {
        // Simulating Player initialisation
        HeslingtonHustle game = new HeslingtonHustle();
        float worldX = (float) game.screenWidth / 2 - (float) game.screenHeight / 2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = player.worldX + (player.speed * Gdx.graphics.getDeltaTime());
        float targY = player.worldY;

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveUp() {
        // Simulating Player initialisation
        HeslingtonHustle game = new HeslingtonHustle();
        float worldX = (float) game.screenWidth / 2 - (float) game.screenHeight / 2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY + (player.speed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }

    @Test
    public void testMoveDown() {
        // Simulating Player initialisation
        HeslingtonHustle game = new HeslingtonHustle();
        float worldX = (float) game.screenWidth / 2 - (float) game.screenHeight / 2;
        float worldY = 500;
        double normalizedSpeed = 200;
        TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
        int tileSize = 16;
        int spriteX = 24;
        int spriteY = 38;
        CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

        // Calculating target coordinates when simulating movement
        float targX = player.worldX;
        float targY = player.worldY - (player.speed * Gdx.graphics.getDeltaTime());

        // Using a player's collision handler to derive the new position of a player after moving left
        Vector2 newPos = collisionHandler.adjustPos(player.worldX, player.worldY, targX, targY);
        assertEquals(targX, newPos.x, 0.0001);
    }
}
