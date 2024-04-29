package bytemusketeers.heslingtonhustle.utils;

import bytemusketeers.heslingtonhustle.screens.EndScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The {@link Leaderboard} manages and displays a set of high-scoring players. The player scores are based on the
 * current username, and data is persisted with the LibGDX {@link Preferences} API.
 *
 * @author ENG1 Team 23
 */
public class Leaderboard {
    /**
     * The two-column name-score {@link Table} used to hold high-score entries.
     */
    private final Table table = new Table();

    /**
     * The given {@link Label.LabelStyle} style to use for typesetting entries
     */
    private final Label.LabelStyle labelStyle;

    /**
     * The maximum number of entries to be displayed
     */
    private static final int MAX_ENTRY_COUNT = 10;

    /**
     * The LibGDX {@link Preferences} reference to be used for persistent high-score storage.
     */
    private static final Preferences preferences =
        Gdx.app.getPreferences("bytemusketeers.heslingtonhustle.highscores");

    /**
     * Register a new score for the user of the current {@link com.badlogic.gdx.Game}. The {@link #preferences} is
     * updated only if this is a new high score for the user identified by the given name.
     *
     * @param name The name of the user
     * @param score The new score
     */
    public void registerScore(String name, Score score) {
        float points = score.getScore();

        if (points > preferences.getFloat(name, -Float.MAX_VALUE)) {
            preferences.putFloat(name, points);
            preferences.flush();
        }
    }

    /**
     * Register a new score for the user of the current {@link com.badlogic.gdx.Game}. The {@link #preferences} is
     * updated only if this is a new high score for the current Java user.
     *
     * @param score The new score
     * @see #registerScore(Score)
     */
    public void registerScore(Score score) {
        registerScore(System.getProperty("user.name"), score);
    }

    /**
     * Populate the {@link Leaderboard} {@link Table} based on the {@link Preferences} scores, up to a maximum of
     * {@link #MAX_ENTRY_COUNT} entries.
     */
    public void populateBoard() {
        List<Map.Entry<String, Float>> scores = new ArrayList<>();
        preferences.get().forEach((key, value) -> scores.add(new AbstractMap.SimpleEntry<>(key, Float.parseFloat(value
            .toString()))));

        scores.stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(MAX_ENTRY_COUNT)
            .forEach(record -> {
                table.row();
                table.add(new Label(record.getKey(), labelStyle)).padRight(EndScreen.HORIZONTAL_PADDING);
                table.add(new Label(Score.formatLoadedScore(record.getValue()), labelStyle));
            });
    }

    /**
     * Retrieves the populated LibGDX {@link Table}
     *
     * @return The {@link Table}, populated with high-score information
     */
    public Table getTable() {
        return table;
    }

    /**
     * Instantiates a new {@link Leaderboard} to contain high-score entries.
     *
     * @param labelStyle The {@link Label.LabelStyle} required for entry text
     */
    public Leaderboard(Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;

        table.add(new Label("User", labelStyle)).padRight(EndScreen.HORIZONTAL_PADDING)
            .padBottom(EndScreen.VERTICAL_PADDING);
        table.add(new Label("Score", labelStyle)).padBottom(EndScreen.VERTICAL_PADDING);
    }
}
