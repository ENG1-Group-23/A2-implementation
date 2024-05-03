package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.utils.Achievement;
import bytemusketeers.heslingtonhustle.utils.Leaderboard;
import bytemusketeers.heslingtonhustle.utils.Score;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@link EndScreen} represents a {@link Screen} displayed at the end of the {@link HeslingtonHustle} game.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class EndScreen extends ScreenAdapter implements Screen {
    /**
     * The LibGDX {@link Texture} of the "Play Again" button.
     */
    private final Texture playAgainButton = new Texture("end_gui/play_button.png");

    /**
     * The LibGDX {@link Texture} of the "Exit" button.
     */
    private final Texture exitButton = new Texture("end_gui/exit_button.png");

    /**
     * The {@link BitmapFont} used for rendering the large {@link EndScreen} {@link String} objects.
     */
    private final BitmapFont largeFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

    /**
     * The {@link BitmapFont} used for rendering the small {@link EndScreen} {@link String} objects.
     *
     * @see Leaderboard
     */
    private final BitmapFont smallFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

    /**
     * The {@link Label.LabelStyle} used to inform all constructed LibGDX {@link Label}
     * {@link com.badlogic.gdx.scenes.scene2d.Actor} objects.
     */
    private final Label.LabelStyle labelStyle;

    /**
     * The {@link Stage} object for organising on-{@link Screen} {@link com.badlogic.gdx.scenes.scene2d.Actor} objects.
     */
    private final Stage stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

    /**
     * The standard horizontal padding between columns, in pixels
     */
    public static final int HORIZONTAL_PADDING = 50;

    /**
     * The standard vertical padding around leader rows, in pixels
     */
    public static final int VERTICAL_PADDING = 30;

    /**
     * Construct a single {@link ImageButton} with the given {@link Texture} and {@link EventListener}-defined action.
     *
     * @param texture The LibGDX {@link Texture} for the {@link ImageButton}
     * @param listener The {@link EventListener} to be coupled to the button
     * @return The constructed {@link ImageButton}
     */
    private static ImageButton createButton(Texture texture, EventListener listener) {
        Gdx.app.log("Step 2", "Create Button");
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(style);

        button.addListener(listener);
        return button;
    }

    /**
     * Construct the 'Exit' and 'Play Again' buttons.
     *
     * @param game The parental {@link HeslingtonHustle} object, containing scaling information.
     * @return A {@link Table} of the constructed buttons
     */
    private Table constructButtons(HeslingtonHustle game) {
        Gdx.app.log("Step 1", "Start Button Table");
        Gdx.app.log("game.scaleFactorX", String.valueOf((int) game.scaleFactorX));
        Gdx.app.log("game.scaleFactorY", String.valueOf((int) game.scaleFactorY));
        final int BUTTON_WIDTH = playAgainButton.getWidth() * 10 * (int) game.scaleFactorX;
        final int BUTTON_HEIGHT = playAgainButton.getHeight() * 10 * (int) game.scaleFactorY;
        Gdx.app.log("BUTTON_WIDTH", String.valueOf(BUTTON_WIDTH));
        Gdx.app.log("BUTTON_HEIGHT", String.valueOf(BUTTON_HEIGHT));

        final Table buttonsTable = new Table();

        buttonsTable.add(createButton(exitButton, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gameData.buttonClickedSoundActivate();
                game.screenManager.clearMemory();
                dispose();
                Gdx.app.exit();
            }
        })).size(BUTTON_WIDTH, BUTTON_HEIGHT).padRight(HORIZONTAL_PADDING);

        buttonsTable.add(createButton(playAgainButton, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gameData.buttonClickedSoundActivate();
                game.create();
                game.setup();
            }
        })).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        Gdx.app.log("Step 3", "Return Button Table");
        return buttonsTable;
    }

    /**
     * Construct the main body {@link Table} consisting of the {@link Leaderboard}, and, if applicable, the index of
     * attained {@link Achievement} awards.
     *
     * @param score The overall game {@link Score}
     * @param achievements The {@link Achievement} trackers
     * @return The constructed {@link Table}
     */
    private Table constructBodyTable(Score score, Achievement[] achievements) {
        AtomicInteger mentioned = new AtomicInteger();
        StringBuilder string = new StringBuilder("Achievements: ");
        Leaderboard leaderboard = new Leaderboard(new Label.LabelStyle(smallFont, Color.WHITE));

        leaderboard.registerScore(score);
        leaderboard.populateBoard();

        Arrays.stream(achievements).forEach(
            achievement -> {
                if (achievement.hasMetThreshold()) {
                    string.append(achievement.getAnnotatedName());
                    string.append("; ");
                    mentioned.getAndIncrement();
                }
            }
        );

        Table bodyTable = new Table();
        bodyTable.row().height(16);

        if (mentioned.get() > 0)
            bodyTable.add(new Label(string.toString(), labelStyle));
        else
            bodyTable.add(new Label("No achievements earned in this session", labelStyle));

        bodyTable.row();
        bodyTable.add(leaderboard.getTable()).padTop(60).padBottom(VERTICAL_PADDING);

        return bodyTable;
    }

    /**
     * Construct an {@link EndScreen} with the assets. The {@link Score} of the
     * {@link bytemusketeers.heslingtonhustle.entity.Player} is also registered and recorded as a 'high-score' if
     * appropriate.
     *
     * @param game The parental {@link com.badlogic.gdx.Game} reference
     * @param score The final {@link com.badlogic.gdx.Game} score
     * @param achievements Array of {@link Achievement} objects to display next to the {@link Leaderboard}
     * @see Score
     * @see Leaderboard
     */
    public EndScreen(HeslingtonHustle game, Score score, Achievement[] achievements) {
        Gdx.app.log("Start","Creating Endscreen");
        Table table = new Table();
        table.setFillParent(true);

        largeFont.getData().setScale(3);
        smallFont.getData().setScale(2);
        labelStyle = new Label.LabelStyle(largeFont, Color.WHITE);

        if (score.getScore() >= 400) {
            table.add(new Label("The End! That's a PASS: " + score + ".", labelStyle));
        }
        else {
            table.add(new Label("The End! That's a FAIL: " + score + ".", labelStyle));
        }

        table.row();

        table.add(constructBodyTable(score, achievements));
        table.row();
        table.add(constructButtons(game)).padTop(VERTICAL_PADDING);

        stage.addActor(table);
        Gdx.app.log("End","Finished Endscreen");
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        stage.draw();
    }

    /**
     * Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        playAgainButton.dispose();
        exitButton.dispose();
        largeFont.dispose();
        smallFont.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
