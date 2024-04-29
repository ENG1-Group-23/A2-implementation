package com.main.tests;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.main.Main;
import com.main.entity.Player;
import com.main.map.GameMap;
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
    private TiledMap tiledMap;
    private int tileSize;
    private boolean collisionFlag = true;

    @Before
    public void setUp() {
        OrthographicCamera camera = new OrthographicCamera();
        Main game = new Main();
        game.gameData = new GameData();
        this.tiledMap = new TmxMapLoader().load("map/MainMap.tmx");
        this.gameMap = new GameMap(camera, null, tiledMap);
        this.tileSize = this.gameMap.getTileSize();
        this.player = new Player(game, gameMap, camera);
        this.collisionHandler = player.getCollisionHandler();
    }

    @Test
    public void testDoorInteraction() {
        String failedLayer = "";
        String[] layers = {"Comp_sci_door", "Piazza_door", "Gym_door", "Goodricke_door"};
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
}
