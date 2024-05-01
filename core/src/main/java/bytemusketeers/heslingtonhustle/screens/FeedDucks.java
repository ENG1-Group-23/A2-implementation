package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.Main;
import bytemusketeers.heslingtonhustle.entity.Duck;
import bytemusketeers.heslingtonhustle.entity.Entity;
import bytemusketeers.heslingtonhustle.entity.Player;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.badlogic.gdx.graphics.g2d.TextureRegion.split;

/**
 * The TypingGame class implements a mini-game for the player to increase their study hours.
 * Players are shown a number that they need to memorize and then type it correctly to succeed.
 */
public class FeedDucks implements Screen, InputProcessor {
    private final int duckScale = 7;
    private final Main game;
    private final OrthographicCamera camera;
    private final GameMap gameMap;
    private final int initialDuckCount = 3;
    private List<Duck> ducks = new ArrayList<>();
    private List<Entity> lilyPads = new ArrayList<>();
    BitmapFont displayText;
    private float displayTextY, displayTextHeight;
    private float gameObjectiveY;
    private float titleX, titleY, titleWidth, titleHeight;
    private int ducksFed = 0;
    String gameObjective;

    /**
     * Constructs a TypingGame screen with the game instance and study duration.
     *
     * @param game The main game instance.
     */
    public FeedDucks(Main game, OrthographicCamera camera, GameMap gameMap) {
        this.game = game;
        this.camera = camera;
        this.gameMap = gameMap;
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        calculateDimensions();
        calculatePositions();

        Gdx.input.setInputProcessor(this);

        gameObjective = "Feed the ducks";

        playGame();
    }

    /**
     * Begins a new challenge by generating a number for the player to memorize.
     * Handles the logic for correct and incorrect guesses and progresses the game.
     */
    private void calculateDimensions(){
        displayText.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);
        displayTextHeight = 100 * game.scaleFactorY;
        gameObjectiveY = game.screenHeight - 80 * game.scaleFactorY;
    }

    private void calculatePositions(){
        displayTextY = game.screenHeight/2f - displayTextHeight;
        titleX = (game.screenWidth - titleWidth)/2f;
        titleY = (game.screenHeight - titleHeight)/2f + 400 * game.scaleFactorY;
    }

    /**
     * Begins a new challenge by generating a number for the player to memorize.
     * Handles the logic for correct and incorrect guesses and progresses the game.
     */
    public void playGame(){
        initialiseDucks();
        addLilyPads(3);
    }

    private void initialiseDucks() {
        for(int i = 0; i < initialDuckCount; i++) {
            Random random = new Random();
            Duck tmp = new Duck(game, gameMap, camera);
            tmp.setPosition(random.nextInt(gameMap.getWidth() / 2), random.nextInt(gameMap.getHeight() / 2));
            ducks.add(tmp);
        }
    }

    private void addDuck() {
        Random random = new Random();
        Duck tmp = new Duck(game, gameMap, camera);
        tmp.setPosition(random.nextInt(gameMap.getWidth() / 2), random.nextInt(gameMap.getHeight() / 2));
        ducks.add(tmp);
    }

    private void addLilyPads(int quantity) {
        for(int i = 0; i < quantity; i++) {
            Random random = new Random();
            Texture texture = new Texture("map/Basic_Grass_Biom_things.png");
            TextureRegion[][] textureRegion = split(texture, 16, 16);
            Entity lilyPad = new Entity();
            lilyPad.worldX = random.nextInt(gameMap.getWidth() / 2);
            lilyPad.worldY = random.nextInt(gameMap.getHeight() / 2);
            lilyPad.setTr(textureRegion[4][7]);
            lilyPads.add(lilyPad);
        }
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        for(Duck duck : ducks) {
            duck.update(delta);
            game.batch.draw(duck.currentAnimation.getKeyFrame(duck.stateTime, true), duck.getX(), duck.getY(), duck.getSpriteX() * duckScale, duck.getSpriteY() * duckScale);
        }
        for(Entity lilyPad : lilyPads) {
            game.batch.draw(lilyPad.getTr(), lilyPad.worldX, lilyPad.worldY, 16 * 7, 16 * 7);
        }
        displayText.draw(game.batch, gameObjective, 0, gameObjectiveY, game.screenWidth, Align.center, false);
        displayText.draw(game.batch, ducksFed + "", 0, gameObjectiveY - 50, game.screenWidth, Align.center, false);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(Duck duck : ducks) {
            duck.dispose();
        }
        displayText.dispose();
    }

    @Override
    public boolean keyDown(int keycode){
        return false;
    }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return true; }

    @Override
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;

        for(Duck duck : ducks) {
            float duckX = duck.getX();
            float duckY = duck.getY();
            int width = 16 * 7, height = 16 * 7;
            if(touchX >= duckX && touchX <= duckX + width && touchY >= duckY && touchY <= duckY + height) {
                // TODO: Add duck feeding sound
                ducksFed++;
                duck.dispose();
                ducks.remove(duck);
                addDuck();
                break;
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
