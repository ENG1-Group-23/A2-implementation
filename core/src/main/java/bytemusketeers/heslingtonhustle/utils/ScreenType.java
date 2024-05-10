package bytemusketeers.heslingtonhustle.utils;

/**
 * Enumerates the types of {@link com.badlogic.gdx.Screen} implementations used in the {@link com.badlogic.gdx.Game}.
 * This allows for easy identification and switching between different {@link com.badlogic.gdx.Screen}s.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public enum ScreenType {
    /**
     * The natural main menu, from which the {@link bytemusketeers.heslingtonhustle.entity.Player} may initiate a new
     * session.
     *
     * @see bytemusketeers.heslingtonhustle.screens.MainMenuScreen
     */
    MAIN_MENU,

    /**
     * The master playing area containing the {@link bytemusketeers.heslingtonhustle.entity.Player} and any applicable
     * {@link bytemusketeers.heslingtonhustle.map.GameMap} and {@link bytemusketeers.heslingtonhustle.entity.Entity}
     * objects.
     *
     * @see bytemusketeers.heslingtonhustle.screens.MainGameScreen
     */
    GAME_SCREEN,

    /**
     * The generic game settings menu
     *
     * @see bytemusketeers.heslingtonhustle.screens.MainSettingsScreen
     */
    SETTINGS,

    /**
     * The controls game settings menu
     *
     * @see bytemusketeers.heslingtonhustle.screens.MainControlScreen
     */
    CONTROLS,

    /**
     * A mini-game, distinct from the master playing area
     *
     * @see bytemusketeers.heslingtonhustle.screens.TypingGame
     * @implNote The set of available mini-games is currently represented by the singleton containing the
     *           {@link bytemusketeers.heslingtonhustle.screens.TypingGame}, but could be easily expanded.
     */
    MINI_GAME,
    FEED_DUCKS,
    EXERCISE_GAME,

    /**
     * The game-ending screen, displaying the final result and a limited set of options
     *
     * @see bytemusketeers.heslingtonhustle.screens.EndScreen
     */
    END_SCREEN,
    PAUSE_SCREEN
}
