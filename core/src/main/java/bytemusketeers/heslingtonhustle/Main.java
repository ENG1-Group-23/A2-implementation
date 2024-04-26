package bytemusketeers.heslingtonhustle;

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

import static com.badlogic.gdx.Gdx.graphics;

/**
 * The main class for the game, extending the LibGDX Game class. It initializes and manages the game's resources,
 * screens, and settings.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class Main extends Game {
    public SpriteBatch batch;
    public GameData gameData;
    public ScreenManager screenManager;
    public int screenWidth;
    public int screenHeight;
    public int defWidth;
    public int defHeight;
    public Skin skin;
    public OrthographicCamera defaultCamera;
    public float scaleFactorX;
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

    /**
     * Called when the game is closing.
     * Disposes of resources to avoid memory leaks.
     */
    @Override
    public void dispose() {

    }
}
