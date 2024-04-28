package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.Main;
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

/**
 * The {@link EndScreen} represents a {@link Screen} displayed at the end of the {@link Main} game.
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
     * The {@link BitmapFont} used for rendering the {@link EndScreen} {@link String} objects.
     * TODO: The {@link EndScreen} currently uses the default LibGDX {@link BitmapFont} due to excessive vertical
     * TODO: padding on the 'White Peaberry'. This should be an easy fix and won't delay integration.
     */
    private final BitmapFont font = new BitmapFont();

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
    public static final int VERTICAL_PADDING = 20;

    private final Achievement eatAch; // TODO
    private final Achievement recAch; // TODO
    private final Achievement sleepAch; // TODO

    // TODO
    private String AchievementText(int num, String name) {
        if (num >= 6) {
            return "Master " + name;
        }
        else if (num >= 4) {
            return "Intermediate " + name;
        }
        else if (num >= 2) {
            return "Novice " + name;
        }
        else {
            return "Hidden";
        }
    }

    /**
     * Construct a single {@link ImageButton} with the given {@link Texture} and {@link EventListener}-defined action.
     *
     * @param texture The LibGDX {@link Texture} for the {@link ImageButton}
     * @param listener The {@link EventListener} to be coupled to the button
     * @return The constructed {@link ImageButton}
     */
    private static ImageButton createButton(Texture texture, EventListener listener) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(style);

        button.addListener(listener);
        return button;
    }

    /**
     * Construct the 'Exit' and 'Play Again' buttons.
     *
     * @param game The parental {@link Main} object, containing scaling information.
     * @return A {@link Table} of the constructed buttons
     */
    private Table constructButtons(Main game) {
        final int BUTTON_WIDTH = playAgainButton.getWidth() * 10 * (int) game.scaleFactorX;
        final int BUTTON_HEIGHT = playAgainButton.getHeight() * 10 * (int) game.scaleFactorY;

        Table buttonsTable = new Table();

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
                game.setup();
            }
        })).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        return buttonsTable;
    }

    /**
     * Construct an {@link EndScreen} with the assets.
     *
     * @param game The parental {@link com.badlogic.gdx.Game} reference
     */
    public EndScreen(Main game) {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true); // TODO: remove

        font.getData().setScale(game.scaleFactorX * 3, game.scaleFactorY * 3);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        table.add(new Label("The End!", labelStyle)).padBottom(VERTICAL_PADDING);
        table.row();

        new Leaderboard(table, labelStyle);
        table.row();
        table.add(constructButtons(game)).padTop(VERTICAL_PADDING);

        stage.addActor(table);
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
        font.dispose();
        stage.dispose();
    }
}
