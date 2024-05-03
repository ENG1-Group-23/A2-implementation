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

public class FeedDucks implements Screen, InputProcessor {
    private final int duckScale = 7;
    private final Main game;
    private final OrthographicCamera camera;
    private final GameMap gameMap;
    private final int initialDuckCount = 3;
    BitmapFont displayText;
    private float displayTextY, displayTextHeight;
    private float gameObjectiveY;
    private float titleX, titleY, titleWidth, titleHeight;
    private float backButtonX, backButtonY, backButtonWidth, backButtonHeight;
    private Texture backButton;
    private int ducksFed = 0;
    private float timeSeconds = 0.0f;
    private float period = 15.0f;
    private List<Entity> entities = new ArrayList<>(); // ducks and lilypads
    String gameObjective;
    private boolean gameOver = false;

    public FeedDucks(Main game, OrthographicCamera camera, GameMap gameMap) {
        this.game = game;
        this.camera = camera;
        this.gameMap = gameMap;
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        backButton = new Texture("menu_gui/exit_button.png");

        calculateDimensions();
        calculatePositions();

        Gdx.input.setInputProcessor(this);

        gameObjective = "Feed the ducks";

        playGame();
    }

    private void calculateDimensions(){
        displayText.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);
        displayTextHeight = 100 * game.scaleFactorY;
        gameObjectiveY = game.screenHeight - 80 * game.scaleFactorY;
        backButtonHeight = 100 * game.scaleFactorY;
        backButtonWidth = 250 * game.scaleFactorX;
    }

    private void calculatePositions(){
        displayTextY = game.screenHeight/2f - displayTextHeight;
        backButtonY = game.screenHeight / 5f;
        backButtonX = game.screenWidth / 2f - (backButtonWidth / 2);
        titleX = (game.screenWidth - titleWidth)/2f;
        titleY = (game.screenHeight - titleHeight)/2f + 400 * game.scaleFactorY;
    }

    public void playGame(){
        initialiseDucks();
        addLilyPads(6);
    }

    public void endGame() {
        gameOver = true;
    }

    private void initialiseDucks() {
        for(int i = 0; i < initialDuckCount; i++) {
            Duck tmp = new Duck(game, gameMap, camera);
            setNonOverlappingPosition(tmp);
            entities.add(tmp);
        }
    }

    private void setNonOverlappingPosition(Duck duck) {
        Random random = new Random();
        duck.setPosition(random.nextInt(game.screenWidth), random.nextInt(game.screenHeight / 2));
        for(Entity entity : entities) {
            while (duck.getBoundingRectangle().overlaps(entity.getBoundingRectangle())) {
                duck.setPosition(random.nextInt(game.screenWidth - 50), random.nextInt(game.screenHeight / 2));
            }
            return;
        }
    }

    private void setNonOverlappingPosition(Entity lilyPad) {
        Random random = new Random();
        lilyPad.worldX = random.nextInt(game.screenWidth);
        lilyPad.worldY = random.nextInt(game.screenHeight / 2);
        for(Entity entity : entities) {
            while (lilyPad.getBoundingRectangle().overlaps(entity.getBoundingRectangle())) {
                lilyPad.worldX = random.nextInt(game.screenWidth - 50);
                lilyPad.worldY = random.nextInt(game.screenHeight / 2);
            }
            return;
        }
    }

    private void addDuck() {
        Duck tmp = new Duck(game, gameMap, camera);
        setNonOverlappingPosition(tmp);
        entities.add(tmp);
    }

    private void addLilyPads() {
        addLilyPads(1);
    }

    private void addLilyPads(int quantity) {
        for(int i = 0; i < quantity; i++) {
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

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        if (!gameOver) {
            timeSeconds += Gdx.graphics.getDeltaTime();
            if(timeSeconds > period){
                timeSeconds -= period;
                endGame();
            }
            for (Entity entity : entities) {
                if (entity instanceof Duck) {
                    ((Duck) entity).update(delta);
                    game.batch.draw(entity.currentAnimation.getKeyFrame(entity.stateTime, true), entity.getX(), entity.getY(), ((Duck) entity).getSpriteX() * duckScale, ((Duck) entity).getSpriteY() * duckScale);
                } else {
                    game.batch.draw(entity.getTr(), entity.worldX, entity.worldY, 16 * 7, 16 * 7);
                }
            }
            displayText.draw(game.batch, gameObjective, 0, gameObjectiveY, game.screenWidth, Align.center, false);
            displayText.draw(game.batch, ducksFed + "", 0, gameObjectiveY - 50, game.screenWidth, Align.center, false);
            displayText.draw(game.batch, "Time left " + (int)(period - timeSeconds) + 1, 0, 150, game.screenWidth, Align.center, false);
            game.batch.end();
            return;
        }
        displayText.draw(game.batch, "You fed " + ducksFed + " ducks!", 0, game.screenHeight, game.screenWidth, Align.center, false);
        displayText.draw(game.batch, "Back to studying", 0, backButtonY + 200 + backButtonHeight, game.screenWidth, Align.center, false);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
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
        for (Entity entity : entities) {
            entity.dispose();
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
        if(gameOver) {
            if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth && touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
                game.gameData.buttonClickedSoundActivate();
                MainGameScreen MGS = (MainGameScreen) game.screenManager.getScreen(ScreenType.GAME_SCREEN);
                MGS.lowerEnergyCounter();
                MGS.incrementRecActivity();
                game.screenManager.setScreen(ScreenType.GAME_SCREEN);
            }
            return false;
        }

        for(Entity entity : entities) {
            if(entity instanceof Duck) {
                Duck duck = (Duck) entity;
                float duckX = duck.getX();
                float duckY = duck.getY();
                int width = 16 * 7, height = 16 * 7;
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
