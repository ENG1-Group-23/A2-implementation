package com.main.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.main.Main;

public class PlayerForTests {
    private Main game = new Main();
    public float worldX = (float) game.screenWidth /2 - (float) game.screenHeight /2;
    public float worldY = 500;
    public double normalizedSpeed = 200;
    public TiledMap gameMap = new TmxMapLoader().load("map/MainMap.tmx");
    public int tileSize = 16;
    public int spriteX = 24;
    public int spriteY = 38;
    private CollisionHandler collisionHandler = new CollisionHandler(gameMap, tileSize, tileSize, spriteX, spriteY * 0.5f, 0.7f, 0.7f);

    public PlayerForTests() {}

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }

    public Rectangle getHitBox(){
        return new Rectangle(worldX, worldY, spriteX, spriteY);
    }
}
