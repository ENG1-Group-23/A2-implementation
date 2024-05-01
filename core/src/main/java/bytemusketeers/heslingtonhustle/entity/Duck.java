package bytemusketeers.heslingtonhustle.entity;

import bytemusketeers.heslingtonhustle.Main;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Duck extends Entity implements Disposable {

    Main game;
    GameMap gameMap;
    OrthographicCamera camera;
    CollisionHandler collisionHandler;
    Animation<TextureRegion> flyAnimation;
    private final int spriteX = 16, spriteY = 16;

    @Override
    public Texture getTexture() {
        return texture;
    }

    Texture texture;

    public Duck(Main game, GameMap gameMap, OrthographicCamera camera) {
        this.game = game;
        this.gameMap = gameMap;
        this.camera = camera;
        this.texture = new Texture("map/duck_spritesheet.png"); // uses CC OpenSource license http://creativecommons.org/publicdomain/zero/1.0/ https://opengameart.org/content/16x16-duck
        TextureRegion[][] duckSpriteSheet = split(texture, spriteX, spriteY);
        flyAnimation = new Animation<>(0.5f, duckSpriteSheet[0]);
        currentAnimation = flyAnimation;
        this.collisionHandler = new CollisionHandler(gameMap.getMap(),  gameMap.getTileSize(), gameMap.getTileSize(), spriteX, spriteY * 0.5f, 0.7f, 0.7f);
    }
    @Override
    public void dispose() {
        texture.dispose();
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }
}
