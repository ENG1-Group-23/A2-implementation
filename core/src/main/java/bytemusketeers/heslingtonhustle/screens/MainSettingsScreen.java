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
 * Represents the settings screen in the game, allowing players to adjust game settings like music and sound levels,
 * and the character's gender.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class MainSettingsScreen extends ScreenAdapter implements Screen, InputProcessor {
    HeslingtonHustle game;
    boolean gender;
    private final Texture backButton;
    private final Texture settingsLabel;
    private final Texture musicUpButton;
    private final Texture musicDownButton;
    private final Texture musicLabel;
    private final Texture soundUpButton;
    private final Texture soundLabel;
    private final Texture soundDownButton;
    private Texture musicBar;
    private Texture soundBar;
    private Texture boyButton;
    private Texture girlButton;
    private final Texture backgroundImage;

    // X and Y coordinates for each button and label
    private float backButtonX;
    private float settingsLabelX;
    private float musicUpButtonX;
    private float musicDownButtonX;
    private float musicLabelX;
    private float musicBarX;
    private float soundUpButtonX;
    private float soundLabelX;
    private float soundDownButtonX;
    private float soundBarX;
    private float boyButtonX;
    private float girlButtonX;
    // Y coordinates for each button and label
    private float backButtonY;
    private float settingsLabelY;
    private float musicUpButtonY;
    private float musicDownButtonY;
    private float musicLabelY;
    private float musicBarY;
    private float soundUpButtonY;
    private float soundLabelY;
    private float soundDownButtonY;
    private float soundBarY;
    private float boyButtonY;
    private float girlButtonY;

    // Button and label dimensions
    private float backButtonWidth = 200;
    private float settingsLabelWidth = 500;
    private float musicUpButtonWidth = 75;
    private float musicDownButtonWidth = 75;
    private float musicLabelWidth = 200;
    private float musicBarWidth = 250;
    private float soundUpButtonWidth = 75;
    private float soundLabelWidth = 200;
    private float soundDownButtonWidth = 75;
    private float soundBarWidth = 250;
    private float boyButtonWidth = 150;
    private float girlButtonWidth = 150;
    private float backButtonHeight = 100;
    private float settingsLabelHeight = 130;
    private float musicUpButtonHeight = 75;
    private float musicDownButtonHeight = 75;
    private float musicLabelHeight = 50;
    private float musicBarHeight = 50;
    private float soundUpButtonHeight = 75;
    private float soundLabelHeight = 50;
    private float soundDownButtonHeight = 75;
    private float soundBarHeight = 50;
    private float boyButtonHeight = 150;
    private float girlButtonHeight = 150;

    /**
     * Constructs the settings screen with references to main game object and initializes UI components.
     *
     * @param game The main game object for accessing global properties and methods.
     */
    public MainSettingsScreen(HeslingtonHustle game) {
        this.game = game;
        gender = game.gameData.getGender();

        backButton = new Texture("settings_gui/back_button.png");
        settingsLabel = new Texture("settings_gui/settings_label.png");
        musicUpButton = new Texture("settings_gui/arrow_right_button.png");
        musicDownButton = new Texture("settings_gui/arrow_left_button.png");
        musicLabel = new Texture("settings_gui/music_label.png");
        musicBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getMusicLevel() + ".png");
        soundUpButton = new Texture("settings_gui/arrow_right_button.png");
        soundLabel = new Texture("settings_gui/sound_label.png");
        soundDownButton = new Texture("settings_gui/arrow_left_button.png");
        soundBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getSoundLevel() + ".png");
        backgroundImage = new Texture("menu_gui/new_background_dark.png");
        if (gender) {
            boyButton = new Texture("settings_gui/boy_button_indented.png");
            girlButton = new Texture("settings_gui/girl_button.png");
        } else {
            girlButton = new Texture("settings_gui/girl_button_indented.png");
            boyButton = new Texture("settings_gui/boy_button.png");
        }

        calculateDimensions();

        calculatePosition();
    }

    private void calculateDimensions() {
        backButtonWidth = 200 * game.scaleFactorX;
        settingsLabelWidth = 500 * game.scaleFactorX;
        musicUpButtonWidth = 75 * game.scaleFactorX;
        musicDownButtonWidth = 75 * game.scaleFactorX;
        musicLabelWidth = 200 * game.scaleFactorX;
        musicBarWidth = 250 * game.scaleFactorX;
        soundUpButtonWidth = 75 * game.scaleFactorX;
        soundLabelWidth = 200 * game.scaleFactorX;
        soundDownButtonWidth = 75 * game.scaleFactorX;
        soundBarWidth = 250 * game.scaleFactorX;
        boyButtonWidth = 150 * game.scaleFactorX;
        girlButtonWidth = 150 * game.scaleFactorX;

        backButtonHeight = 100 * game.scaleFactorY;
        settingsLabelHeight = 130 * game.scaleFactorY;
        musicUpButtonHeight = 75 * game.scaleFactorY;
        musicDownButtonHeight = 75 * game.scaleFactorY;
        musicLabelHeight = 50 * game.scaleFactorY;
        musicBarHeight = 50 * game.scaleFactorY;
        soundUpButtonHeight = 75 * game.scaleFactorY;
        soundLabelHeight = 50 * game.scaleFactorY;
        soundDownButtonHeight = 75 * game.scaleFactorY;
        soundBarHeight = 50 * game.scaleFactorY;
        boyButtonHeight = 150 * game.scaleFactorY;
        girlButtonHeight = 150 * game.scaleFactorY;
    }

    private void calculatePosition() {
        backButtonX = (game.screenWidth - backButtonWidth) / 2;
        backButtonY = (float) game.screenHeight / 6 - (100 * game.scaleFactorY);
        settingsLabelX = (game.screenWidth - settingsLabelWidth) / 2;
        settingsLabelY =  game.screenHeight - (settingsLabelHeight * 2);
        musicUpButtonX = (game.screenWidth - musicUpButtonWidth) / 2 + (200 * game.scaleFactorX);
        musicUpButtonY = game.screenHeight - musicUpButtonHeight - (350 * game.scaleFactorY);
        musicDownButtonX = (game.screenWidth - musicUpButtonWidth) / 2 - (200 * game.scaleFactorX);
        musicDownButtonY = game.screenHeight - musicUpButtonHeight - (350 * game.scaleFactorY);
        musicLabelX = (game.screenWidth - musicLabelWidth) / 2;
        musicLabelY = game.screenHeight - musicLabelHeight - (290 * game.scaleFactorY);
        musicBarX = (game.screenWidth - musicBarWidth) / 2;
        musicBarY = game.screenHeight - musicBarHeight - (375 * game.scaleFactorY);
        soundUpButtonX = (game.screenWidth - soundUpButtonWidth) / 2 + (200 * game.scaleFactorX);
        soundUpButtonY = game.screenHeight - soundUpButtonHeight - (530 * game.scaleFactorY);
        soundLabelX = (game.screenWidth - soundLabelWidth) / 2;
        soundLabelY = game.screenHeight - soundLabelHeight - (470 * game.scaleFactorY);
        soundDownButtonX = (game.screenWidth - soundDownButtonWidth) / 2 - (200 * game.scaleFactorX);
        soundDownButtonY = game.screenHeight - soundDownButtonHeight - (530 * game.scaleFactorY);
        soundBarX = (game.screenWidth - soundBarWidth) / 2;
        soundBarY = game.screenHeight - soundBarHeight - (555 * game.scaleFactorY);
        boyButtonX = (game.screenWidth - boyButtonWidth) / 2 - (100 * game.scaleFactorX);
        boyButtonY = game.screenHeight - boyButtonHeight - (650 * game.scaleFactorY);
        girlButtonX = (game.screenWidth - boyButtonWidth) / 2 + (100 * game.scaleFactorX);
        girlButtonY = game.screenHeight - boyButtonHeight - (650 * game.scaleFactorY);
    }

    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, game.screenWidth, game.screenHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.draw(settingsLabel, settingsLabelX, settingsLabelY, settingsLabelWidth, settingsLabelHeight);
        game.batch.draw(musicUpButton, musicUpButtonX, musicUpButtonY, musicUpButtonWidth, musicUpButtonHeight);
        game.batch.draw(musicDownButton, musicDownButtonX, musicDownButtonY, musicDownButtonWidth,
                musicDownButtonHeight);
        game.batch.draw(musicLabel, musicLabelX, musicLabelY, musicLabelWidth, musicLabelHeight);
        game.batch.draw(musicBar, musicBarX, musicBarY, musicBarWidth, musicBarHeight);
        game.batch.draw(soundUpButton, soundUpButtonX, soundUpButtonY, soundUpButtonWidth, soundUpButtonHeight);
        game.batch.draw(soundLabel, soundLabelX, soundLabelY, soundLabelWidth, soundLabelHeight);
        game.batch.draw(soundDownButton, soundDownButtonX, soundDownButtonY, soundDownButtonWidth,
                soundDownButtonHeight);
        game.batch.draw(soundBar, soundBarX, soundBarY, soundBarWidth, soundBarHeight);
        game.batch.draw(boyButton, boyButtonX, boyButtonY, boyButtonWidth, boyButtonHeight);
        game.batch.draw(girlButton, girlButtonX, girlButtonY, girlButtonWidth, girlButtonHeight);
        game.batch.end();
    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Handles touch input for interactive elements on the screen.
     *
     * @param worldX The x-coordinate of the touch.
     * @param worldY The y-coordinate of the touch.
     * @param pointer The pointer for the event.
     * @param button The button that was touched.
     * @return true if the input was processed.
     */
    @Override
    public boolean touchDown(int worldX, int worldY, int pointer, int button) {
        worldY = game.screenHeight - worldY;

        if (worldX >= backButtonX && worldX <= backButtonX + backButtonWidth
                && worldY >= backButtonY && worldY <= backButtonY + backButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
        } else if (worldX >= musicUpButtonX && worldX <= musicUpButtonX + musicUpButtonWidth
                && worldY >= musicUpButtonY && worldY <= musicUpButtonY + musicUpButtonHeight) {
            if (game.gameData.getMusicLevel() <= 3) {
                game.gameData.incrementMusicLevel();
                game.gameData.upSoundActivate();
                if (musicBar != null)
                    musicBar.dispose();
                musicBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getMusicLevel() + ".png");
            }
        } else if (worldX >= musicDownButtonX && worldX <= musicDownButtonX + musicDownButtonWidth
                && worldY >= musicDownButtonY && worldY <= musicDownButtonY + musicDownButtonHeight) {
            if (game.gameData.getMusicLevel() >= 1) {
                game.gameData.decrementMusicLevel();
                game.gameData.downSoundActivate();
                if (musicBar != null)
                    musicBar.dispose();
                musicBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getMusicLevel() + ".png");
            }
        } else if (worldX >= soundUpButtonX && worldX <= soundUpButtonX + soundUpButtonWidth
                && worldY >= soundUpButtonY && worldY <= soundUpButtonY + soundUpButtonHeight) {
            if (game.gameData.getSoundLevel() <= 3) {
                game.gameData.incrementSoundLevel();
                game.gameData.upSoundActivate();
                if (soundBar != null)
                    soundBar.dispose();
                soundBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getSoundLevel() + ".png");
            }
        } else if (worldX >= soundDownButtonX && worldX <= soundDownButtonX + soundDownButtonWidth
                && worldY >= soundDownButtonY && worldY <= soundDownButtonY + soundDownButtonHeight) {
            if (game.gameData.getSoundLevel() >= 1) {
                game.gameData.decrementSoundLevel();
                game.gameData.downSoundActivate();
                if (soundBar != null)
                    soundBar.dispose();
                soundBar = new Texture("settings_gui/bar_" + 25 * game.gameData.getSoundLevel() + ".png");
            }
        } else if (worldX >= boyButtonX && worldX <= boyButtonX + boyButtonWidth
                && worldY >= boyButtonY && worldY <= boyButtonY + boyButtonHeight) {
            gender = true;
            game.gameData.buttonClickedSoundActivate();
            if (boyButton != null)
                boyButton.dispose();
            if (girlButton != null)
                girlButton.dispose();
            boyButton = new Texture("settings_gui/boy_button_indented.png");
            girlButton = new Texture("settings_gui/girl_button.png");
        } else if (worldX >= girlButtonX && worldX <= girlButtonX + girlButtonWidth
                && worldY >= girlButtonY && worldY <= girlButtonY + girlButtonHeight) {
            gender = false;
            game.gameData.buttonClickedSoundActivate();
            if (boyButton != null)
                boyButton.dispose();
            if (girlButton != null)
                girlButton.dispose();
            girlButton = new Texture("settings_gui/girl_button_indented.png");
            boyButton = new Texture("settings_gui/boy_button.png");
        }

        game.gameData.setGender(gender);
        return true;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be
     * {@link com.badlogic.gdx.Input.Buttons#LEFT} on iOS.
     *
     * @param screenX The {@link Screen} X position
     * @param screenY The {@link Screen} Y position
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when the touch gesture is cancelled. Reason may be from OS interruption to touch becoming a large surface
     * (such as the user cheek). Relevant on Android and iOS only. The button parameter will be
     * {@link com.badlogic.gdx.Input.Buttons#LEFT} on iOS.
     *
     * @param screenX The {@link Screen} X position
     * @param screenY The {@link Screen} Y position
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX The {@link Screen} X position
     * @param screenY The {@link Screen} Y position
     * @param pointer the pointer for the event.
     * @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX The {@link Screen} X position
     * @param screenY The {@link Screen} Y position
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amountX the horizontal scroll amount, negative or positive depending on the direction the wheel was
     *                scrolled.
     * @param amountY the vertical scroll amount, negative or positive depending on the direction the wheel was
     *                scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePosition();
    }

    @Override
    public void dispose() {
        backButton.dispose();
        settingsLabel.dispose();
        musicUpButton.dispose();
        musicDownButton.dispose();
        musicLabel.dispose();
        musicBar.dispose();
        soundUpButton.dispose();
        soundLabel.dispose();
        soundDownButton.dispose();
        soundBar.dispose();
        boyButton.dispose();
        girlButton.dispose();
        backgroundImage.dispose();
    }
}
