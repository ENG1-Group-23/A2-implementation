package bytemusketeers.heslingtonhustle.entity;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Represents a duck which is shown in the feed ducks mini-game
 */
public class Duck extends Entity implements Disposable {

    private final int spriteX = 16, spriteY = 16;
    private HeslingtonHustle game;
    private GameMap gameMap;
    private OrthographicCamera camera;
    private CollisionHandler collisionHandler;
    private Animation<TextureRegion> flyAnimation;
    private Texture texture;

    /**
     * Initialises the mini-game and starts it by running playGame()
     *
     * @param game The parental {@link com.badlogic.gdx.Game} reference
     * @param gameMap The {@link GameMap} of the current level
     * @param camera The {@link OrthographicCamera} responsible for rendering
     */
    public Duck(HeslingtonHustle game, GameMap gameMap, OrthographicCamera camera) {
        this.game = game;
        this.gameMap = gameMap;
        this.camera = camera;
        this.texture = new Texture("map/duck_spritesheet.png");
        TextureRegion[][] duckSpriteSheet = split(texture, spriteX, spriteY);
        flyAnimation = new Animation<>(0.5f, duckSpriteSheet[0]);
        currentAnimation = flyAnimation;
        this.collisionHandler = new CollisionHandler(gameMap.getMap(), gameMap.getTileSize(), gameMap.getTileSize(),
                spriteX, spriteY * 0.5f, 0.7f, 0.7f);
    }

    @Override
    public Texture getTexture() {
        return texture;
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

    public float getX() {
        return super.getX();
    }

    public float getY() {
        return super.getY();
    }
}
