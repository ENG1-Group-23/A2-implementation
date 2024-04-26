package bytemusketeers.heslingtonhustle.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@link Leaderboard} manages and displays a set of high-scoring players. The player scores are based on the
 * current username, and data is persisted with the LibGDX {@link Preferences} API.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class Leaderboard {
    /**
     * The two-column name-score {@link Table} used to hold high-score entries.
     */
    private final Table table = new Table();

    /**
     * The standard padding between columns, in pixels
     */
    private static final int HORIZONTAL_PADDING = 50;

    /**
     * The given {@link Label.LabelStyle} style to use for typesetting entries
     */
    private final Label.LabelStyle labelStyle;

    /**
     * The maximum number of entries to be displayed
     */
    private static final int MAX_ENTRY_COUNT = 5;

    /**
     * The LibGDX {@link Preferences} reference to be used for persistent high-score storage.
     */
    private final Preferences preferences = Gdx.app.getPreferences("bytemusketeers.heslingtonhustle.highscores");

    /**
     * Register a new score for the user of the current {@link com.badlogic.gdx.Game}. The {@link #preferences} is
     * updated only if this is a new high score for the user identified by the given name.
     *
     * @param name The name of the user
     * @param score The new score
     */
    public void registerScore(String name, int score) {
        if (score > preferences.getInteger(name, 0)) {
            preferences.putInteger(name, score);
            preferences.flush();
        }
    }

    /**
     * Register a new score for the user of the current {@link com.badlogic.gdx.Game}. The {@link #preferences} is
     * updated only if this is a new high score for the current Java user.
     *
     * @param score The new score
     * @see #registerScore(int)
     */
    public void registerScore(int score) {
        registerScore(System.getProperty("user.name"), score);
    }

    /**
     * Populate the {@link Leaderboard} {@link Table} based on the {@link Preferences} scores, up to a maximum of
     * {@link #MAX_ENTRY_COUNT} entries.
     */
    private void populateBoard() {
        List<Map.Entry<String, Integer>> scores = new ArrayList<>();
        preferences.get().forEach((key, value) -> scores.add(new AbstractMap.SimpleEntry<>(key,
            Integer.parseUnsignedInt(value.toString()))));

        scores.stream().sorted().limit(MAX_ENTRY_COUNT).forEach(record -> {
            table.row();
            table.add(new Label(record.getKey(), labelStyle)).padRight(HORIZONTAL_PADDING);
            table.add(new Label(record.getValue().toString(), labelStyle));
        });
    }

    /**
     * Instantiates a new {@link Leaderboard} to contain high-score entries.
     *
     * @param parent The parental {@link Table} to which the {@link Leaderboard} should be added
     * @param labelStyle The {@link Label.LabelStyle} required for entry text
     */
    public Leaderboard(Table parent, Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;

        table.setDebug(true);
        registerScore(10);

        table.add(new Label("User", labelStyle)).padRight(HORIZONTAL_PADDING);
        table.add(new Label("Score", labelStyle));

        populateBoard();

        parent.add(table);
    }
}
