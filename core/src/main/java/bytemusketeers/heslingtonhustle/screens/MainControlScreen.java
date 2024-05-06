package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The {@link MainControlScreen} provides a visual representation of control instructions for the game, alongside a back
 * button to navigate back to the {@link MainMenuScreen}.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class MainControlScreen extends ScreenAdapter implements Screen, InputProcessor {
    /**
     * The parental {@link com.badlogic.gdx.Game}/{@link HeslingtonHustle} reference
     */
    HeslingtonHustle game;

    /**
     * The LibGDX-managed {@link BitmapFont} used to render on-screen text
     */
    BitmapFont font;

    /**
     * The human-readable objective text of {@link HeslingtonHustle}, outlining the responsibility of the
     * {@link bytemusketeers.heslingtonhustle.entity.Player}
     */
    private static final String OBJECTIVE = "Welcome to Heslington Hustle! You are a second-year Computer Science "
            + "student with exams in only 7 days. Explore the map, \n and interact with buildings to eat, study, sleep "
            + "and have fun. To get a good grade, you need to balance hours of studying with \n self-care and "
            + "recreation. Good luck!";

    /**
     * The LibGDX-managed {@link Texture} of the 'back' button
     */
    private final Texture backButton;

    /**
     * The LibGDX-managed {@link Texture} of the 'control' label
     */
    private final Texture controlLabel;

    /**
     * The LibGDX-managed {@link Texture} of the 'controls' button
     */
    private final Texture controls;
    private final Texture backgroundImage;

    /**
     * The X co-ordinate of the 'back' button
     */
    private float backButtonX;

    /**
     * The Y co-ordinate of the 'back' button
     */
    private float backButtonY;

    /**
     * The X co-ordinate of the 'control' label
     */
    private float controlLabelX;

    /**
     * The Y co-ordinate of the 'control' label
     */
    private float controlLabelY;

    /**
     * The X co-ordinate of the 'controls' button
     */
    private float controlsX;

    /**
     * The Y co-ordinate of the 'controls' button
     */
    private float controlsY;

    /**
     * The Y co-ordinate of the 'objective' text
     */
    private float objectiveY;

    /**
     * The X co-ordinate of the 'playing instructions' label
     */
    private float instructionX;

    /**
     * The Y co-ordinate of the 'playing instructions' label
     */
    private float instructionY;

    /**
     * The width of the 'back' button
     */
    private float backButtonWidth;

    /**
     * The height of the 'back' button
     */
    private float backButtonHeight;

    /**
     * The width of the 'control' label
     */
    private float controlLabelWidth;

    /**
     * The height of the 'control' label
     */
    private float controlLabelHeight;

    /**
     * The height of the 'controls' button
     */
    private float controlsHeight;

    /**
     * The width of the 'controls' button
     */
    private float controlsWidth;

    /**
     * The padding around the 'playing instructions' label
     */
    private float instructionGap;

    /**
     * Initialises the screen with game controls instructions, sets up textures for display elements, and configures
     * input processing.
     *
     * @param game The parental {@link com.badlogic.gdx.Game} reference
     */
    public MainControlScreen(HeslingtonHustle game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        backButton = new Texture("settings_gui/back_button.png");
        controlLabel = new Texture("controls_gui/controls_label.png");
        controls = new Texture("controls_gui/controls_new.png");
        backgroundImage = new Texture("menu_gui/new_background_dark.png");

        calculateDimensions();
        calculatePositions();
    }

    /**
     * Computes and updates the dimensions of the on-screen elements, according to the scale factor required by the
     * parental {@link HeslingtonHustle} instance.
     *
     * @see HeslingtonHustle#scaleFactorX
     * @see HeslingtonHustle#scaleFactorY
     */
    private void calculateDimensions() {
        font.getData().setScale(1.5f * game.scaleFactorX, 1.5f * game.scaleFactorY);
        backButtonWidth = 200 * game.scaleFactorX;
        backButtonHeight = 100 * game.scaleFactorY;
        controlLabelWidth = 500 * game.scaleFactorX;
        controlLabelHeight = 130 * game.scaleFactorY;
        controlsWidth = 500 / 3.5151f * game.scaleFactorX;
        controlsHeight = 500 * game.scaleFactorY;
        instructionGap = 74f * game.scaleFactorY;
    }

    /**
     * Computes and updates the positions of the on-screen elements, according to the scale factor required by the
     * parental {@link HeslingtonHustle} instance.
     *
     * @see HeslingtonHustle#scaleFactorX
     * @see HeslingtonHustle#scaleFactorY
     */
    private void calculatePositions() {
        backButtonX = (game.screenWidth - backButtonWidth) / 2f;
        backButtonY = game.screenHeight / 6f - 120 * game.scaleFactorY;
        controlLabelX = (game.screenWidth - controlLabelWidth) / 2f;
        controlLabelY = game.screenHeight - (controlLabelHeight * 1.2f);
        controlsX = game.screenWidth / 3.2f;
        controlsY = (game.screenHeight / 3.5f) - (controlsHeight / 5f);
        objectiveY = game.screenHeight - 160 * game.scaleFactorY;
        instructionY = game.screenHeight / 1.435f;
        instructionX = game.screenWidth / 2f - 90 * game.scaleFactorX;
    }

    /**
     * The main render method for the screen. Called every frame and responsible for drawing the screen's contents.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, game.screenWidth, game.screenHeight);
        font.draw(game.batch, OBJECTIVE, 0, objectiveY, game.screenWidth, Align.center, false);
        float instructionY = this.instructionY;
        String[] instructions = {
            "Up - Move forward",
            "Left - Turn left",
            "Right - Turn right",
            "Down - Move backward",
            "Shift - Sprint",
            "Esc - Pause",
            "Click - Interact"
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
    public void show() {
        Gdx.input.setInputProcessor(this);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
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
        return false;
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
