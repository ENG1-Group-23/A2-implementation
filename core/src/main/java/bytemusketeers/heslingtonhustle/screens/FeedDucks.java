package bytemusketeers.heslingtonhustle.screens;

import static com.badlogic.gdx.graphics.g2d.TextureRegion.split;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.entity.Duck;
import bytemusketeers.heslingtonhustle.entity.Entity;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.Score;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a duck feeding mini-game in which the player clicks
 * on the ducks and new ducks appear.
 */

public class FeedDucks extends ScreenAdapter implements Screen, InputProcessor {
    private final int duckScale = 7;
    private final HeslingtonHustle game;
    private final OrthographicCamera camera;
    private final GameMap gameMap;
    private final int initialDuckCount = 3;
    private final Texture backButton;
    private final float period = 15.0f;
    private final List<Entity> entities = new ArrayList<>(); // ducks and lilypads
    private final Score score;
    BitmapFont displayText;
    String gameObjective;
    private float displayTextY, displayTextHeight;
    private float gameObjectiveY;
    private float titleX, titleY, titleWidth, titleHeight;
    private float backButtonX, backButtonY, backButtonWidth, backButtonHeight;
    private int ducksFed = 0;
    private float timeSeconds = 0.0f;
    private boolean gameOver = false;

    /**
     * Initialises the mini-game and starts it by running playGame()
     *
     * @param game The parental {@link com.badlogic.gdx.Game} reference
     * @param camera The {@link OrthographicCamera} responsible for rendering
     * @param gameMap The {@link GameMap} of the current level
     * @param score The {@link Score} object regarding the current playing session
     */
    public FeedDucks(HeslingtonHustle game, OrthographicCamera camera, GameMap gameMap, Score score) {
        this.game = game;
        this.camera = camera;
        this.gameMap = gameMap;
        this.score = score;
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        backButton = new Texture("menu_gui/exit_button.png");

        calculateDimensions();
        calculatePositions();

        Gdx.input.setInputProcessor(this);

        gameObjective = "Feed the ducks";

        playGame();
    }

    /**
     * Adjusts dimensions after rescaling of window
     */
    private void calculateDimensions() {
        displayText.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);
        displayTextHeight = 100 * game.scaleFactorY;
        gameObjectiveY = game.screenHeight - 80 * game.scaleFactorY;
        backButtonHeight = 100 * game.scaleFactorY;
        backButtonWidth = 250 * game.scaleFactorX;
    }

    /**
     * Adjusts positions after rescaling of window
     */
    private void calculatePositions() {
        displayTextY = game.screenHeight / 2f - displayTextHeight;
        backButtonY = game.screenHeight / 5f;
        backButtonX = game.screenWidth / 2f - (backButtonWidth / 2);
        titleX = (game.screenWidth - titleWidth) / 2f;
        titleY = (game.screenHeight - titleHeight) / 2f + 400 * game.scaleFactorY;
    }

    /**
     * Spawns the first ducks and lily pads
     */
    private void playGame() {
        initialiseDucks();
        addLilyPads(6);
    }

    private void endGame() {
        gameOver = true;
    }

    private void initialiseDucks() {
        for (int i = 0; i < initialDuckCount; i++) {
            Duck tmp = new Duck(game, gameMap, camera);
            setNonOverlappingPosition(tmp);
            entities.add(tmp);
        }
    }

    /**
     * Finds a position for the duck that does not overlap
     * with another entity on the screen
     *
     * @param duck The duck
     */
    private void setNonOverlappingPosition(Duck duck) {
        Random random = new Random();
        duck.setPosition(random.nextInt(game.screenWidth - 100), random.nextInt(game.screenHeight / 2));
        Rectangle[] boundingRectangles = new Rectangle[entities.size()];
        int counter = 0;
        for (Entity entity : entities) {
            boundingRectangles[counter] = entity.getBoundingRectangle();
            counter++;
        }
        while (overlapsAny(boundingRectangles, duck.getBoundingRectangle())) {
            duck.setPosition(random.nextInt(game.screenWidth - 100), random.nextInt(game.screenHeight / 2));
        }
    }

    /**
     * Checks whether one bounding rectangle overlaps with any other in
     * the list
     *
     * @param list The list of bounding {@link Rectangle}s whose union should be checked
     * @param item The candidate {@link Rectangle}
     * @return does any {@link Rectangle} in the list overlaps with the given item?
     */
    private boolean overlapsAny(Rectangle[] list, Rectangle item) {
        for (Rectangle listItem : list)
            if (listItem.overlaps(item))
                return true;

        return false;
    }

    /**
     * Finds and sets the position of the lily pad such that it does
     * not overlap with any entity on the screen
     *
     * @param lilyPad The lilypad to consider
     */
    private void setNonOverlappingPosition(Entity lilyPad) {
        Random random = new Random();
        lilyPad.worldX = random.nextInt(game.screenWidth - 64);
        lilyPad.worldY = random.nextInt(game.screenHeight / 2);
        Rectangle[] boundingRectangles = new Rectangle[entities.size()];
        int counter = 0;
        for (Entity entity : entities) {
            if (entity.equals(lilyPad)) continue;
            boundingRectangles[counter] = entity.getBoundingRectangle();
            counter++;
        }
        while (overlapsAny(boundingRectangles, lilyPad.getBoundingRectangle())) {
            lilyPad.worldX = random.nextInt(game.screenWidth - 64);
            lilyPad.worldY = random.nextInt(game.screenHeight / 2);
        }
    }

    /**
     * Adds a single duck to the screen
     */
    private void addDuck() {
        Duck tmp = new Duck(game, gameMap, camera);
        setNonOverlappingPosition(tmp);
        entities.add(tmp);
    }

    private void addLilyPads() {
        addLilyPads(1);
    }

    /**
     * Adds amount of lily pads to the screen
     *
     * @param amount The number of lilypads to add
     */
    private void addLilyPads(int amount) {
        for (int i = 0; i < amount; i++) {
            Texture texture = new Texture("map/Basic_Grass_Biom_things.png");
            TextureRegion[][] textureRegion = split(texture, 16, 16);
            Entity lilyPad = new Entity();
            setNonOverlappingPosition(lilyPad);
            lilyPad.setTr(textureRegion[4][7]); // texture position in sprite sheet
            entities.add(lilyPad);
        }
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    /**
     * Handles all the visual elements of the mini-game including the
     * rendering of the ducks/lily pads etc
     *
     * @param delta The time passed, in milliseconds, since the last render. Currently unused in the implementation.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        if (!gameOver) {
            timeSeconds += Gdx.graphics.getDeltaTime();
            if (timeSeconds > period) {
                timeSeconds -= period;
                endGame();
            }
            for (Entity entity : entities) {
                if (entity instanceof Duck) {
                    ((Duck) entity).update(delta);
                    game.batch.draw(entity.currentAnimation.getKeyFrame(entity.stateTime, true), entity.getX(),
                            entity.getY(), ((Duck) entity).getSpriteX() * duckScale,
                            ((Duck) entity).getSpriteY() * duckScale);
                } else {
                    game.batch.draw(entity.getTr(), entity.worldX, entity.worldY, 16 * 7, 16 * 7);
                }
            }
            displayText.draw(game.batch, gameObjective, 0, gameObjectiveY, game.screenWidth, Align.center,
                    false);
            displayText.draw(game.batch, ducksFed + "", 0, gameObjectiveY - 50, game.screenWidth, Align.center,
                    false);
            displayText.draw(game.batch, "Time left " + (int) (period - timeSeconds + 1), 0, 150,
                    game.screenWidth, Align.center, false);
            game.batch.end();
            return;
        }
        displayText.draw(game.batch, "You fed " + ducksFed + " ducks!", 0, game.screenHeight, game.screenWidth,
                Align.center, false);
        displayText.draw(game.batch, "Back to studying", 0, backButtonY + 200 + backButtonHeight,
                game.screenWidth, Align.center, false);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void dispose() {
        for (Entity entity : entities) {
            entity.dispose();
        }
        displayText.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    /**
     * Handles mouse button presses including the interaction with clicking
     * on a duck or the return button at the end of the game
     *
     * @param touchX The X position of the touch
     * @param touchY The Y position of the touch
     * @param pointer Is the pointer active?
     * @param button Is the button active?
     */
    @Override
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;
        if (gameOver) {
            if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth && touchY >= backButtonY
                    && touchY <= backButtonY + backButtonHeight) {
                game.gameData.buttonClickedSoundActivate();
                MainGameScreen mgs = (MainGameScreen) game.screenManager.getScreen(ScreenType.GAME_SCREEN);
                mgs.lowerEnergyCounter();
                mgs.lowerEnergyCounter();
                mgs.lowerEnergyCounter();
                mgs.lowerEnergyCounter();
                mgs.incrementRecActivity();
                score.incrementRec(ducksFed / 10);
                game.screenManager.setScreen(ScreenType.GAME_SCREEN);
            }
            return false;
        }

        for (Entity entity : entities) {
            if (entity instanceof Duck) {
                Duck duck = (Duck) entity;
                float duckX = duck.getX();
                float duckY = duck.getY();
                int width = 16 * 7;
                int height = 16 * 7;
                if (touchX >= duckX && touchX <= duckX + width && touchY >= duckY && touchY <= duckY + height) {
                    game.gameData.duckSoundActivate();
                    ducksFed++;
                    duck.dispose();
                    entities.remove(entity);
                    addDuck();
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
