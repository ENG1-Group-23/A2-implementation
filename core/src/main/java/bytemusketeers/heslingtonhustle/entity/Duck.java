package bytemusketeers.heslingtonhustle.entity;

import bytemusketeers.heslingtonhustle.Main;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Duck extends Entity implements Disposable {

    Main game;
    GameMap gameMap;
    OrthographicCamera camera;
    CollisionHandler collisionHandler;

    @Override
    public Texture getTexture() {
        return texture;
    }

    Texture texture;

    public Duck(Main game, GameMap gameMap, OrthographicCamera camera) {
        int spriteX = 24;
        int spriteY = 36;
        this.game = game;
        this.gameMap = gameMap;
        this.camera = camera;
        this.texture = new Texture("character/player.png"); //TODO: NOT FINAL IMAGE
        this.collisionHandler = new CollisionHandler(gameMap.getMap(),  gameMap.getTileSize(), gameMap.getTileSize(), spriteX, spriteY * 0.5f, 0.7f, 0.7f);
    }
    @Override
    public void dispose() {
        texture.dispose();
    }
}
