package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The MainControlScreen class provides a visual representation of control instructions for the game, alongside a back
 * button to navigate back to the main menu. It implements both the Screen and InputProcessor interfaces from libGDX,
 * handling rendering and input events within the control screen context.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class MainControlScreen extends ScreenAdapter implements InputProcessor {
    HeslingtonHustle game;
    BitmapFont font;
    String objective;
    private final Texture backButton;
    private final Texture controlLabel;
    private final Texture controls;

    // X and Y coordinates
    private float backButtonX;
    private float backButtonY;
    private float controlLabelX;
    private float controlLabelY;
    private float controlsX;
    private float controlsY;
    private float objectiveY;
    private float instructionX;
    private float instructionY;

    // Buttons dimensions
    private float backButtonWidth;
    private float backButtonHeight;
    private float controlLabelWidth;
    private float controlLabelHeight;
    private float controlsHeight;
    private float controlsWidth;
    private float instructionGap;

    /**
     * Constructor for MainControlScreen.
     * Initializes the screen with game controls instructions, sets up textures for display elements,
     * and configures input processing.
     *
     * @param game The main game object that this screen is a part of.
     */
    public MainControlScreen(HeslingtonHustle game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        backButton = new Texture("settings_gui/back_button.png");
        controlLabel = new Texture("controls_gui/controls_label.png");
        controls = new Texture("controls_gui/controls.png");

        calculateDimensions();
        calculatePositions();

        objective = "Welcome to Heslington Hustle! You are a second-year Computer Science student with exams in only"
            + "7 days. Explore the map, \n and interact with buildings to eat, study, sleep and have fun. To get a"
            + "good grade, you need to balance hours of studying with \n self-care and recreation. Good luck!";
    }


    private void calculateDimensions() {
        font.getData().setScale(1.5f * game.scaleFactorX, 1.5f * game.scaleFactorY);
        backButtonWidth = 200 * game.scaleFactorX;
        backButtonHeight = 100 * game.scaleFactorY;
        controlLabelWidth = 500 * game.scaleFactorX;
        controlLabelHeight = 130 * game.scaleFactorY;
        controlsWidth = 500 / 3f * game.scaleFactorX;
        controlsHeight = 500 * game.scaleFactorY;
        instructionGap = 87 * game.scaleFactorY;
    }

    private void calculatePositions() {
        backButtonX = (game.screenWidth - backButtonWidth) / 2f;
        backButtonY = game.screenHeight / 6f - 120 * game.scaleFactorY;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2f;
        controlLabelY = game.screenHeight - (controlLabelHeight * 1.2f);
        controlsX = game.screenWidth / 3.2f;
        controlsY = (game.screenHeight / 3.5f) - (controlsHeight / 5f);
        objectiveY = game.screenHeight - 160 * game.scaleFactorY;
        instructionY = game.screenHeight / 1.45f;
        instructionX = game.screenWidth / 2f - 90 * game.scaleFactorX;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    /**
     * The main render method for the screen. Called every frame and responsible for
     * drawing the screen's contents.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        font.draw(game.batch, objective, 0, objectiveY, game.screenWidth, Align.center, false);
        float instructionY = this.instructionY;
        String[] instructions = {
            "Up - Move forward",
            "Left - Turn left",
            "Right - Turn right",
            "Down - Move backward",
            "Shift - Sprint",
            "Esc - Pause"
        };

        for (String instruction : instructions) {
            font.draw(game.batch, instruction, instructionX, instructionY);
            instructionY -= instructionGap; // Spacing between instructions
        }
        game.batch.draw(controlLabel, controlLabelX, controlLabelY, controlLabelWidth, controlLabelHeight);
        game.batch.draw(controls, controlsX, controlsY, controlsWidth, controlsHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);

        game.batch.end();
    }

    /**
     * Handles touch down input events. Specifically, checks if the back button is pressed
     * and navigates back to the main menu screen.
     *
     * @param touchX The x-coordinate of the touch, in screen coordinates.
     * @param touchY The y-coordinate of the touch, in screen coordinates.
     * @param pointer The pointer for the event.
     * @param button The button pressed.
     * @return true if the event was handled, false otherwise.
     */
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = (game.screenHeight - touchY);

        if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth
                && touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
            game.gameData.buttonClickedSoundActivate();
        }

        return true;
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
    public boolean scrolled(float amountX, float amountY) {
        // Implement scrolling behavior if needed
        return false; // Return false if the event was not handled
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void dispose() {
        backButton.dispose();
        controlLabel.dispose();
        controls.dispose();
        font.dispose();
    }

}
