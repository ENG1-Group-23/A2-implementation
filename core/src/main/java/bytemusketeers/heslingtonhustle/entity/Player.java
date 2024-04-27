package bytemusketeers.heslingtonhustle.entity;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * The {@link Player} represents the in-{@link com.badlogic.gdx.Game} character; handling movement, collision,
 * and animations.
 *
 * @see Entity
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class Player extends Entity implements Disposable {
    /**
     * The parental {@link com.badlogic.gdx.Game} reference, in particular {@link HeslingtonHustle}
     */
    HeslingtonHustle game;

    /**
     * The on-screen {@link GameMap}
     *
     * @implNote This is required to access the size of the map.
     * @see GameMap#getWidth()
     * @see GameMap#getHeight()
     */
    GameMap gameMap;

    /**
     * The {@link com.badlogic.gdx.graphics.Camera} used for tracking the {@link Player} within the bounds of the
     * {@link GameMap}
     */
    OrthographicCamera camera;

    /**
     * The handler and manager for collisions between the user-controlled {@link Player} and various on-screen objects
     */
    CollisionHandler collisionHandler;

    /**
     * The current direction of the {@link Player}, encoded in the following schema:
     * <ul>
     *     <li>'U': Up;</li>
     *     <li>'D': Down;</li>
     *     <li>'L': Left; and</li>
     *     <li>'R': Right.</li>
     * </ul>
     */
    char dir;

    /**
     * The frame duration of each animation step
     *
     * @see Entity#currentAnimation
     * @see Animation
     */
    public static final float ANIMATION_SPEED = 0.5f;

    /**
     * The starting X position of the {@link Player} sprite with respect to the sprite-sheet
     */
    public static final int spriteX = 24;

    /**
     * The starting Y position of the {@link Player} sprite with respect to the sprite-sheet
     */
    public static final int spriteY = 38;

    /**
     * The cached tile-size of the {@link GameMap}
     *
     * @see GameMap#getTileSize()
     */
    int tileSize;

    /**
     * The X component of the default starting position
     */
    public float startX;

    /**
     * The Y component of the default starting position
     */
    public float startY;

    /**
     * The sprite-sheet of the {@link Player} sprites in its idle state
     */
    Texture idleSheet;

    /**
     * The sprite-sheet of the {@link Player} sprites in its walking state
     */
    Texture walkSheet;

    /**
     * The walk-down set of frames
     */
    Animation<TextureRegion> walkDownAnimation;

    /**
     * The walk-right set of frames
     */
    Animation<TextureRegion> walkRightAnimation;

    /**
     * The walk-left set of frames
     */
    Animation<TextureRegion> walkLeftAnimation;

    /**
     * The walk-up set of frames
     */
    Animation<TextureRegion> walkUpAnimation;

    /**
     * The idling-down set of frames
     */
    Animation<TextureRegion> idleDownAnimation;

    /**
     * The idling-right set of frames
     */
    Animation<TextureRegion> idleRightAnimation;

    /**
     * The idling-left set of frames
     */
    Animation<TextureRegion> idleLeftAnimation;

    /**
     * The idling-up set of frames
     */
    Animation<TextureRegion> idleUpAnimation;


    /**
     * Constructs a new Player instance.
     *
     * @param game The main game object.
     * @param gameMap The game map for collision detection and boundaries.
     * @param camera The camera to follow the player.
     */
    public Player(HeslingtonHustle game, GameMap gameMap, OrthographicCamera camera) {
        this.game = game;
        this.gameMap = gameMap;
        this.camera = camera;

        tileSize = gameMap.getTileSize();
        this.collisionHandler = new CollisionHandler(gameMap.getMap(), tileSize, tileSize, spriteX,
                spriteY * 0.5f, 0.7f, 0.7f);
        this.collisionHandler.addCollisionLayers("Trees", "wall_1", "wall_2", "wall_3", "roof_1", "roof_2",
                "roof_3", "other", "lilipads");

        this.speed = 200;
        startX = (float) game.screenWidth / 2 - (float) game.screenHeight / 2;
        startY = 500;
        worldX = startX;
        worldY = startY;

        dir = 'D';
        updateGender();
    }

    /**
     * Updates the player's position, animations, and handles collision.
     *
     * @param delta Time since last frame in seconds.
     */
    public void update(float delta) {
        boolean isMoving = false;

        // Determine if the player is moving diagonally
        boolean isMovingDiagonally = ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
                || (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)))
                && ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
                    || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)));

        // Calculate the normalised speed for diagonal movement
        double normalizedSpeed = speed;
        if (isMovingDiagonally)
            normalizedSpeed = (speed / Math.sqrt(2)) * 1.07; // Adjust speed for diagonal movement

        // shift key doubles player speed
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
            normalizedSpeed *= 2; // Increase speed if shift is pressed

        float targX = worldX;
        float targY = worldY;

        // checks movement and updates animation, adjusts speed with delta time
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            targY = worldY + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkUpAnimation;
            dir = 'U';
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            targY = worldY - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkDownAnimation;
            dir = 'D';
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            targX = worldX - (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkLeftAnimation;
            dir = 'L';
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            targX = worldX + (float) (normalizedSpeed * Gdx.graphics.getDeltaTime());
            currentAnimation = walkRightAnimation;
            dir = 'R';
            isMoving = true;
        }

        // player doesn't walk beyond the map
        if (targX + spriteX > gameMap.getWidth())
            targX = gameMap.getWidth() - spriteX;
        else if (targX < 0)
            targX = 0;

        if (targY + spriteY > gameMap.getHeight())
            targY = gameMap.getHeight() - spriteY;
        else if (targY < 0)
            targY = 0;

        // this will switch sprite sheet to idle sprite sheet when player is not moving
        if (!isMoving) {
            if (currentAnimation == walkDownAnimation) currentAnimation = idleDownAnimation;
            else if (currentAnimation == walkRightAnimation) currentAnimation = idleRightAnimation;
            else if (currentAnimation == walkLeftAnimation) currentAnimation = idleLeftAnimation;
            else if (currentAnimation == walkUpAnimation) currentAnimation = idleUpAnimation;
        }

        Vector2 newPos = collisionHandler.adjustPos(worldX, worldY, targX, targY);
        worldX = newPos.x;
        worldY = newPos.y;

        stateTime += delta;

        float camX = worldX + spriteY / 2f;
        float camY = worldY + spriteY / 2f;

        camera.position.set(camX, camY, 0);
        // this will make sure the camera follows the player
        if (camX + camera.viewportWidth / 2f > gameMap.getWidth())
            camera.position.set(gameMap.getWidth() - camera.viewportWidth / 2f, camera.position.y, 0);
        else if (camX - camera.viewportWidth / 2f < 0)
            camera.position.set(camera.viewportWidth / 2f, camera.position.y, 0);

        if (camY + camera.viewportHeight / 2f > gameMap.getHeight())
            camera.position.set(camera.position.x, gameMap.getHeight() - camera.viewportHeight / 2f, 0);
        else if (camY - camera.viewportHeight / 2f < 0)
            camera.position.set(camera.position.x, camera.viewportHeight / 2f, 0);

        camera.update();
    }

    /**
     * Sets the player's position to the specified coordinates.
     *
     * @param newX The new X coordinate.
     * @param newY The new Y coordinate.
     */
    public void setPos(float newX, float newY) {
        worldX = newX;
        worldY = newY;
    }

    /**
     * Updates the player's gender by changing the TextureRegions' internal path using the player's choice in the
     * settings menu. Then updates corresponding textures and animations.
     *
     * @apiNote This functionality should be segregated into its own class to reduce overheads and
     *          processing delay.
     */
    public void updateGender() {
        if (idleSheet != null)
            idleSheet.dispose();

        if (walkSheet != null)
            walkSheet.dispose();

        if (game.gameData.getGender()) {
            idleSheet = new Texture("character/boy_idle.png");
            walkSheet = new Texture("character/boy_walk.png");
        } else {
            idleSheet = new Texture("character/girl_idle.png");
            walkSheet = new Texture("character/girl_walk.png");
        }

        TextureRegion[][] walkSpriteSheet = split(walkSheet, spriteX, spriteY); // Splits the sprite sheet up by frames

        walkDownAnimation = new Animation<>(ANIMATION_SPEED, walkSpriteSheet[0]); // First row for down
        walkLeftAnimation = new Animation<>(ANIMATION_SPEED, walkSpriteSheet[1]); // Second row for left
        walkRightAnimation = new Animation<>(ANIMATION_SPEED, walkSpriteSheet[2]); // Third row for right
        walkUpAnimation = new Animation<>(ANIMATION_SPEED, walkSpriteSheet[3]); // Fourth row for up

        TextureRegion[][] idleSpriteSheet = split(idleSheet, spriteX, spriteY); // Splits the sprite sheet up by frames

        idleDownAnimation = new Animation<>(ANIMATION_SPEED, idleSpriteSheet[0][0], idleSpriteSheet[0][1]);
        idleLeftAnimation = new Animation<>(ANIMATION_SPEED, idleSpriteSheet[1][0], idleSpriteSheet[1][1]);
        idleRightAnimation = new Animation<>(ANIMATION_SPEED, idleSpriteSheet[2][0], idleSpriteSheet[2][1]);
        idleUpAnimation = new Animation<>(ANIMATION_SPEED, idleSpriteSheet[3][0], idleSpriteSheet[3][1]);

        setDirection(dir);
    }

    /**
     * Updates the direction of the {@link Player} according to the encoding specified in {@link #dir}, updating the
     * animation frame-set as necessary.
     *
     * @param dir The new direction
     * @see #dir
     */
    public void setDirection(char dir) {
        this.dir = dir;

        switch (dir) {
            case 'D':
                currentAnimation = idleDownAnimation;
                break;

            case 'L':
                currentAnimation = idleLeftAnimation;
                break;

            case 'R':
                currentAnimation = idleRightAnimation;
                break;

            case 'U':
                currentAnimation = idleUpAnimation;
                break;

            default:
                break;
        }
    }

    /**
     * Gets the current animation frame for the player based on the state time.
     *
     * @return The current {@link TextureRegion} of the {@link Player}'s {@link Animation}.
     */
    public TextureRegion getCurrentFrame() {
        return currentAnimation.getKeyFrame(stateTime, true);
    }

    /**
     * Retrieves the {@link CollisionHandler}
     *
     * @return The {@link Player}-registered {@link CollisionHandler}
     */
    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    /**
     * Encodes the {@link Player}'s hitbox as a LibGDX {@link Rectangle}
     *
     * @return The {@link Rectangle} with bounds corresponding to the collision area of the {@link Player}
     */
    public Rectangle getHitBox() {
        return new Rectangle(worldX, worldY, spriteX, spriteY);
    }

    /**
     * Releases all non-managed resources used by the {@link Player}
     */
    public void dispose() {
        idleSheet.dispose();
        walkSheet.dispose();
    }
}
