package com.main.tests;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.main.utils.CollisionHandler;
import com.main.utils.PlayerForTests;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class InteractionTests {
    private PlayerForTests player = new PlayerForTests();
    private CollisionHandler collisionHandler = player.getCollisionHandler();
    private boolean collisionFlag = true;

    @Test
    public void testDoorInteraction() {
        String failedLayer = "";
        String[] layers = {"Comp_sci_door", "Piazza_door", "Gym_door", "Goodricke_door"};
        for (String layerName : layers) {
            TiledMapTileLayer layer = (TiledMapTileLayer) player.gameMap.getLayers().get(layerName);
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
            int spawnX = layerX * player.tileSize - player.spriteX;
            int spawnY = layerY * player.tileSize - player.spriteY;
            player.setWorldX(spawnX);
            player.setWorldY(spawnY);
            int lastX = layerX * player.tileSize + player.tileSize - 1;
            int lastY = layerY * player.tileSize;
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
