package bytemusketeers.heslingtonhustle.utils;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.screens.EndScreen;
import bytemusketeers.heslingtonhustle.screens.MainControlScreen;
import bytemusketeers.heslingtonhustle.screens.MainGameScreen;
import bytemusketeers.heslingtonhustle.screens.MainMenuScreen;
import bytemusketeers.heslingtonhustle.screens.MainSettingsScreen;
import bytemusketeers.heslingtonhustle.screens.TypingGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@link ScreenManager} manages the required {@link Screen} implementations, including creation, switching, and
 * memory-management.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class ScreenManager {
    /**
     * The parental {@link com.badlogic.gdx.Game} reference
     */
    private final HeslingtonHustle game;

    /**
     * The {@link Screen} storage map, associating constituent values with their corresponding {@link ScreenType}
     * identifier
     *
     * @see ScreenType
     */
    private final Score score;
    private final Achievement eatAch;
    private final Achievement recAch;
    private final Achievement sleepAch;
    private final Map<ScreenType, Screen> screensInMemory;

    /**
     * The currently visible {@link Screen}
     *
     * @see #curScreenType
     * @see #screensInMemory
     */
    private Screen curScreen;

    /**
     * The type of the currently visible {@link Screen}
     *
     * @see #curScreen
     * @see #screensInMemory
     */
    private ScreenType curScreenType;

    /**
     * Initializes the ScreenManager with a reference to the main game class.
     *
     * @param game The main game class instance.
     */
    public ScreenManager(HeslingtonHustle game) {
        this.game = game;
        this.score = new Score();
        this.eatAch = new Achievement("FAT BOI");
        this.recAch = new Achievement("FIT BOI");
        this.sleepAch = new Achievement("NAP BOI");
        this.screensInMemory = new HashMap<>();
    }

    /**
     * Keeps a screen in memory for quick access without having to recreate it.
     *
     * @param screenType The type of the screen to keep in memory.
     */
    public void keepInMemory(ScreenType screenType) {
        if (screenType.equals(curScreenType) && curScreen != null)
            screensInMemory.put(screenType, curScreen);
        else
            screensInMemory.put(screenType, createScreen(screenType));
    }

    /**
     * Clears the entire memory, disposing of all loaded {@link Screen} objects
     *
     * @see Screen#dispose()
     * @see com.badlogic.gdx.utils.Disposable
     */
    public void clearMemory() {
        for (Screen screen : screensInMemory.values())
            screen.dispose();

        screensInMemory.clear();
    }

    /**
     * Sets the current screen of the game. If the screen is stored in memory, it uses it; otherwise, it creates a new
     * screen.
     *
     * @param screenType The type of the screen to display.
     */
    public void setScreen(ScreenType screenType, Object... args) {
        Gdx.input.setInputProcessor(null);

        if (curScreen != null && !screensInMemory.containsKey(curScreenType))
            curScreen.dispose();

        if (screensInMemory.containsKey(screenType))
            curScreen = screensInMemory.get(screenType);
        else
            curScreen = createScreen(screenType, args);

        curScreenType = screenType;
        game.setScreen(curScreen);
    }

    /**
     * Handles the resizing of the current screen and all screens held in memory.
     * This method is called whenever the window size changes and ensures that
     * the screen elements and layouts are adjusted accordingly across all screens.
     *
     * @param width The new width of the window.
     * @param height The new height of the window.
     */
    public void resize(int width, int height) {
        curScreen.resize(width, height);
        for (Screen screen : screensInMemory.values())
            screen.resize(width, height);
    }

    /**
     * Creates a screen based on the given screen type. This method defines how each screen type is instantiated.
     *
     * @param type The type of the screen to create.
     * @return The created screen, or null if the type is unknown.
     */
    private Screen createScreen(ScreenType type, Object... args) {
        switch (type) {
            case MAIN_MENU:
                return new MainMenuScreen(game);
            case GAME_SCREEN:
                return new MainGameScreen(game, score, eatAch, recAch, sleepAch);
            case SETTINGS:
                return new MainSettingsScreen(game);
            case CONTROLS:
                return new MainControlScreen(game);
            case MINI_GAME:
                return new TypingGame(game, (int) args[0]);
            case END_SCREEN:
                return new EndScreen(game, score, eatAch, recAch, sleepAch);
            default:
                return null;
        }
    }
}
