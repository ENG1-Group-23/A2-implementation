package com.main.tests;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.main.HeslingtonHustle;
import com.main.entity.Player;
import com.main.map.GameMap;
import com.main.screens.MainGameScreen;
import com.main.utils.CollisionHandler;
import com.main.utils.GameData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class InteractionTests {
    private Player player;
    private CollisionHandler collisionHandler;
    private GameMap gameMap;
    private int tileSize;
    private boolean collisionFlag = true;
    private final String[] layers = {"Comp_sci_door", "Piazza_door", "Gym_door", "Goodricke_door"};
    private MainGameScreen screen;

    @Before
    public void setUp() {
        OrthographicCamera camera = new OrthographicCamera();
        HeslingtonHustle game = new HeslingtonHustle();
        game.scaleFactorX = 1;
        game.scaleFactorY = 1;
        game.gameData = new GameData();
        game.tiledMap = new TmxMapLoader().load("map/MainMap.tmx");
        this.gameMap = new GameMap(camera, null, game.tiledMap);
        this.tileSize = this.gameMap.getTileSize();
        this.player = new Player(game, gameMap, camera);
        this.collisionHandler = player.getCollisionHandler();
        screen = new MainGameScreen(game);
    }

    @Test
    public void testDoorCollision() {
        String failedLayer = "";
        for (String layerName : layers) {
            TiledMapTileLayer layer = (TiledMapTileLayer) this.gameMap.getMap().getLayers().get(layerName);
            int layerX = 0;
            int layerY = 0;
            for (int y = 0; y < layer.getHeight(); y++) {
                for (int x = 0; x < layer.getWidth(); x++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                    if (cell != null){
                        layerX = x;
                        layerY = y;
                    }
                }
            }
            int spawnX = layerX * tileSize - Player.spriteX;
            int spawnY = layerY * tileSize - Player.spriteY;
            player.setPos(spawnX, spawnY);
            int lastX = layerX * tileSize + tileSize - 1;
            int lastY = layerY * tileSize;
            for (; spawnX < lastX; spawnX++) {
                for (; spawnY < lastY; spawnY++) {
                    collisionFlag = collisionHandler.isTouching(layerName, player.getHitBox());
                }
                if (!collisionFlag) {
                    failedLayer = layerName;
                    break;
                }
            }
            if (!collisionFlag) {
                break;
            }
        }
        assertTrue("Failed interaction with layer: " + failedLayer, collisionFlag);
    }

    @Test
    public void testEatButton() {
        int energy = screen.getEnergyCounter();
        int meal = screen.getMealCount();
        int energyToCompare;
        if (energy == 10) {
            energyToCompare = 10;
        } else {
            energyToCompare = energy + 3;
        }
        screen.eatClickHandler();
        assertEquals(energyToCompare, screen.getEnergyCounter(), 0.0001);
        assertEquals(meal + 1, screen.getMealCount(), 0.0001);
    }

    @Test
    public void testStudyButton() {
        screen.studyClickHandler();
        assertTrue(screen.isShowMenu());
        assertTrue(screen.isLockMovement());
        assertEquals("study", screen.getActivity());
        assertEquals(1, screen.getDuration(), 0.0001);
    }

    @Test
    public void testExerciseButton() {
        screen.exerciseClickHandler();
        assertTrue(screen.isShowMenu());
        assertTrue(screen.isLockMovement());
        assertEquals("exercise", screen.getActivity());
        assertEquals(1, screen.getDuration(), 0.0001);
    }

    @Test
    public void testSleepButton() {
        screen.sleepClickHandler();
        assertTrue(screen.isShowMenu());
        assertTrue(screen.isLockMovement());
        assertEquals("sleep", screen.getActivity());
        assertEquals(1, screen.getDuration(), 0.0001);
    }
}
