package bytemusketeers.heslingtonhustle;

import static com.badlogic.gdx.Gdx.graphics;

import bytemusketeers.heslingtonhustle.utils.GameData;
import bytemusketeers.heslingtonhustle.utils.ScreenManager;
import bytemusketeers.heslingtonhustle.utils.ScreenType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The main class for the game, extending the LibGDX Game class. It initializes and manages the game's resources,
 * screens, and settings.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class HeslingtonHustle extends Game {
    /**
     * The LibGDX-controlled {@link SpriteBatch}, used for batching numerous
     * {@link com.badlogic.gdx.graphics.g2d.Sprite} objects to be drawn to a {@link com.badlogic.gdx.Screen} on a single
     * transaction.
     */
    public SpriteBatch batch;

    /**
     * Stores the settings and transient data for the current {@link Game} instantiation
     */
    public GameData gameData;

    /**
     * Manages the allocation and despatching of {@link com.badlogic.gdx.Screen} implementations
     *
     * @see bytemusketeers.heslingtonhustle.screens.EndScreen
     * @see bytemusketeers.heslingtonhustle.screens.MainControlScreen
     * @see bytemusketeers.heslingtonhustle.screens.MainGameScreen
     * @see bytemusketeers.heslingtonhustle.screens.MainMenuScreen
     * @see bytemusketeers.heslingtonhustle.screens.MainSettingsScreen
     * @see bytemusketeers.heslingtonhustle.screens.TypingGame
     */
    public ScreenManager screenManager;

    /**
     * The current width of the window, in pixels.
     *
     * @see Gdx#graphics
     * @see #defWidth
     */
    public int screenWidth;

    /**
     * The current height of the window, in pixels
     *
     * @see Gdx#graphics
     * @see #defHeight
     */
    public int screenHeight;

    /**
     * The default width of the window, in pixels
     *
     * @see #screenWidth
     */
    public int defWidth;

    /**
     * The default height of the window, in pixels
     */
    public int defHeight;

    /**
     * The LibGDX {@link Skin} to supply the {@link BitmapFont} bitmaps with the corresponding {@link Label.LabelStyle}
     */
    public Skin skin;

    /**
     * The basal LibGDX {@link com.badlogic.gdx.graphics.Camera} shared across {@link com.badlogic.gdx.Screen}
     * implementations
     */
    public OrthographicCamera defaultCamera;

    /**
     * The horizontal scale factor by which pixel units should be multiplied
     */
    public float scaleFactorX;

    /**
     * The vertical scale factor by which pixel units should be multiplied
     */
    public float scaleFactorY;

    /**
     * Called when the game is first created.
     * Initializes the game's main components and sets the main menu as the initial screen.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        gameData = new GameData();
        screenWidth = graphics.getWidth();
        screenHeight = graphics.getHeight();

        defWidth = 1922;
        defHeight = 995;

        defaultCamera = new OrthographicCamera();

        defaultCamera.setToOrtho(false, defWidth, defHeight);
        scaleFactorX = 1;
        scaleFactorY = 1;

        // Fonts for writing in game
        skin = new Skin();
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        skin.add("default-font", font, BitmapFont.class);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        skin.add("Peaberry", labelStyle, Label.LabelStyle.class);

        // Initialize and set up the screen manager
        screenManager = new ScreenManager(this);
        screenManager.keepInMemory(ScreenType.GAME_SCREEN);
        screenManager.setScreen(ScreenType.MAIN_MENU);
    }

    /**
     * Load core {@link com.badlogic.gdx.Screen} implementations and set sensible defaults
     *
     * @see ScreenManager
     */
    public void setup() {
        screenManager.clearMemory();
        screenManager.keepInMemory(ScreenType.GAME_SCREEN);
        screenManager.setScreen(ScreenType.MAIN_MENU);
    }

    /**
     * Called each frame, responsible for rendering the game.
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Called when the application is resized.
     *
     * @param width The new width of the application.
     * @param height The new height of the application.
     */
    @Override
    public void resize(int width, int height) {
        if (width != 0 || height != 0) {
            super.resize(width, height);
            defaultCamera.setToOrtho(false, width, height);
            defaultCamera.update();
            screenWidth = width;
            screenHeight = height;
            scaleFactorX = screenWidth / (float) defWidth;
            scaleFactorY = screenHeight / (float) defHeight;
            screenManager.resize(width, height);
        }
    }
}
