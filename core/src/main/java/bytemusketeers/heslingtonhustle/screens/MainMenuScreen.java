package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The MainMenuScreen class represents the main menu screen for the game.
 * It handles the display and interaction with the main menu, including navigating to different parts of the game
 * such as starting the gameplay, viewing controls, adjusting settings, or exiting the game.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class MainMenuScreen extends ScreenAdapter implements Screen, InputProcessor {
    HeslingtonHustle game;
    Texture heslingtonHustleLabel;
    Texture playButton;
    Texture controlsButton;
    Texture settingsButton;
    Texture exitButton;

    int heslingtonHustleLabelHeight;
    int playButtonHeight;
    int controlsButtonHeight;
    int settingsButtonHeight;
    int exitButtonHeight;
    int heslingtonHustleLabelWidth;
    int playButtonWidth;
    int controlsButtonWidth;
    int settingsButtonWidth;
    int exitButtonWidth;

    int xPosition;
    float heslingtonHustleLabelX;
    float heslingtonHustleLabelY;
    float playButtonY;
    float controlsButtonY;
    float settingsButtonY;
    float exitButtonY;

    boolean exitFlag;

    /**
     * Constructs a MainMenuScreen with the specified game instance.
     * Initializes UI elements and calculates their positions.
     *
     * @param game the game instance this screen belongs to.
     */
    public MainMenuScreen(HeslingtonHustle game) {
        this.game = game;

        loadTextures();
        calculateDimensions();
        calculatePositions();
    }

    /**
     * Loads textures for UI elements from the assets directory.
     */
    private void loadTextures() {
        heslingtonHustleLabel = new Texture("menu_gui/heslington_hustle_label.png");
        playButton = new Texture("menu_gui/play_button.png");
        controlsButton = new Texture("menu_gui/controls_button.png");
        settingsButton = new Texture("menu_gui/settings_button.png");
        exitButton = new Texture("menu_gui/exit_button.png");
    }

    /**
     * Calculates the dimensions of buttons based on their textures.
     */
    private void calculateDimensions() {
        heslingtonHustleLabelHeight = (int) (heslingtonHustleLabel.getHeight() * 10 * game.scaleFactorY);
        heslingtonHustleLabelWidth = (int) (heslingtonHustleLabel.getWidth() * 10 * game.scaleFactorX);
        playButtonHeight = (int) (playButton.getHeight() * 10 * game.scaleFactorY);
        playButtonWidth = (int) (playButton.getWidth() * 10 * game.scaleFactorX);
        controlsButtonHeight = (int) (controlsButton.getHeight() * 10 * game.scaleFactorY);
        controlsButtonWidth = (int) (controlsButton.getWidth() * 10 * game.scaleFactorX);
        settingsButtonHeight = (int) (settingsButton.getHeight() * 10 * game.scaleFactorY);
        settingsButtonWidth = (int) (settingsButton.getWidth() * 10 * game.scaleFactorX);
        exitButtonHeight = (int) (exitButton.getHeight() * 10 * game.scaleFactorY);
        exitButtonWidth = (int) (exitButton.getWidth() * 10 * game.scaleFactorX);
    }

    /**
     * Calculates the positions of buttons to be drawn on the screen.
     */
    private void calculatePositions() {
        heslingtonHustleLabelX = (game.screenWidth - heslingtonHustleLabelWidth) / 2f;
        xPosition = (int) ((game.screenWidth - playButtonWidth) / 2f); // this is to make sure the buttons are centered
        heslingtonHustleLabelY = game.screenHeight - heslingtonHustleLabelHeight * 1.25f;
        playButtonY = game.screenHeight - playButtonHeight * 2.5f;
        controlsButtonY = game.screenHeight - controlsButtonHeight * 3.75f;
        settingsButtonY = game.screenHeight - settingsButtonHeight * 5f;
        exitButtonY = game.screenHeight - exitButtonHeight * 6.25f;
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        if (exitFlag) return;
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        game.batch.draw(heslingtonHustleLabel, heslingtonHustleLabelX, heslingtonHustleLabelY,
                heslingtonHustleLabelWidth, heslingtonHustleLabelHeight);
        game.batch.draw(playButton, xPosition, playButtonY, playButtonWidth, playButtonHeight);
        game.batch.draw(controlsButton, xPosition, controlsButtonY, controlsButtonWidth, controlsButtonHeight);
        game.batch.draw(settingsButton, xPosition, settingsButtonY, settingsButtonWidth, settingsButtonHeight);
        game.batch.draw(exitButton, xPosition, exitButtonY, exitButtonWidth, exitButtonHeight);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;

        if (touchX >= xPosition && touchX <= xPosition + playButtonWidth
                && touchY >= playButtonY && touchY <= playButtonY + playButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.GAME_SCREEN);
        } else if (touchX >= xPosition && touchX <= xPosition + controlsButtonWidth
                && touchY >= controlsButtonY && touchY <= controlsButtonY + controlsButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.CONTROLS);
        } else if (touchX >= xPosition && touchX <= xPosition + settingsButtonWidth
                && touchY >= settingsButtonY && touchY <= settingsButtonY + settingsButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.SETTINGS);
        } else if (touchX >= xPosition && touchX <= xPosition + exitButtonWidth
                && touchY >= exitButtonY && touchY <= exitButtonY + exitButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.clearMemory();
            exitFlag = true;
            dispose();
            Gdx.app.exit();
        }

        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void dispose() {
        playButton.dispose();
        controlsButton.dispose();
        settingsButton.dispose();
        exitButton.dispose();
        heslingtonHustleLabel.dispose();
    }
}
