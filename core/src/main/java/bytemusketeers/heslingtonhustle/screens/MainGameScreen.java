package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.entity.Player;
import bytemusketeers.heslingtonhustle.map.GameMap;
import bytemusketeers.heslingtonhustle.utils.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The MainGameScreen class is responsible for rendering and updating all the game elements
 * including the player, game world, UI, and handling user input during the main gameplay phase.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class MainGameScreen extends ScreenAdapter implements Screen, InputProcessor {
    private final Color shader;
    private final float zoom = 3f;
    private final Player player;
    private final BitmapFont font;
    private final BitmapFont popupFont;
    private final BitmapFont durationFont;
    private final Score score;
    private final Achievement eatAch;
    private final Achievement recAch;
    private final Achievement sleepAch;
    private final GameMap gameMap;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private final HeslingtonHustle game;
    private final Texture menuButton;
    private final Texture popupMenu;
    private final Texture durationUpButton;
    private final Texture durationDownButton;
    private final Texture menuBackButton;
    private final Texture menuStudyButton;
    private final Texture menuSleepButton;
    private final Texture menuGoButton;
    private final Texture durationMenuBackground;
    private final Texture feedDuckButton;
    private final Texture counterBackground;
    private final float gameDayLengthInSeconds;
    private final GameTime gameTime;

    // Non-final attributes
    private Texture energyBar;
    private float menuButtonY;
    private float menuButtonX;
    private float menuButtonWidth;
    private float menuButtonHeight;
    private float counterBackgroundY;
    private float counterBackgroundX;
    private float counterBackgroundWidth;
    private float counterBackgroundHeight;
    private float popupMenuWidth;
    private float popupMenuHeight;
    private float durationUpButtonX;
    private float durationDownButtonX;
    private float durationMenuBackgroundX;
    private float durationButtonY;
    private float durationMenuBackgroundY;
    private float durationUpButtonWidth;
    private float durationDownButtonWidth;
    private float durationMenuBackgroundWidth;
    private float durationUpButtonHeight;
    private float durationDownButtonHeight;
    private float durationMenuBackgroundHeight;
    private float menuBackButtonWidth;
    private float menuBackButtonHeight;
    private float activityButtonWidth;
    private float activityButtonHeight;
    private float menuBackButtonX;
    private float activityButtonX;
    private float durationMenuButtonY;
    private float durationTextY;
    private float menuTitleY;
    private float hoursLabelY;
    private float energyBarY;
    private float energyBarX;
    private float energyBarWidth;
    private float energyBarHeight;
    private String activity;
    private String popupMenuType;
    private int energyCounter;
    private int duration;
    private int dayNum;
    private int recActivity;
    private int studyHours;
    private int mealCount;
    private int dailyStudyHours;
    private float fadeTime;
    private float minShade;
    private boolean fadeOut;
    private boolean lockTime;
    private boolean lockMovement;
    private boolean lockPopup;
    private boolean resetPos;
    private boolean popupVisible;
    private boolean showMenu;
    private boolean dayStudied;
    private boolean hasEaten;
    private boolean hasExercised;
    private boolean doneDarts;

    /**
     * Constructs the main game screen with necessary game components.
     * Initializes game map, player, camera, UI elements, and sets the initial game state.
     *
     * @param game The main game application instance.
     */
    public MainGameScreen(HeslingtonHustle game, Score score, Achievement eatAch, Achievement recAch, Achievement sleepAch) {
        this.game = game;
        this.shader = new Color(0.5f, 0.5f, 0.5f, 1);
        this.gameDayLengthInSeconds = 60f;

        // Initialize final Texture objects
        this.menuButton = new Texture("menu_buttons/menu_icon.png");
        this.counterBackground = new Texture("counter_background.png");
        this.popupMenu = new Texture("popup_menu.png");
        this.durationMenuBackground = new Texture("duration_menu_background.png");
        this.durationUpButton = new Texture("settings_gui/arrow_right_button.png");
        this.durationDownButton = new Texture("settings_gui/arrow_left_button.png");
        this.menuBackButton = new Texture("settings_gui/back_button.png");
        this.menuStudyButton = new Texture("study_button.png");
        this.menuSleepButton = new Texture("sleep_button.png");
        this.menuGoButton = new Texture("go_button.png");
        this.feedDuckButton = new Texture("feed_button.png");

        // Initialize non-final attributes
        this.activity = "";
        this.popupMenuType = "";
        this.energyCounter = 10;
        this.duration = 1;
        this.dayNum = 1;
        this.gameTime = new GameTime(0f, this.gameDayLengthInSeconds / 16, 10);
        this.fadeTime = 0;
        this.minShade = 0;
        this.dailyStudyHours = 0;
        this.dayStudied = false;
        this.hasEaten = false;
        this.hasExercised = false;
        this.doneDarts = false;
        this.fadeOut = this.lockTime = this.lockMovement = this.lockPopup = this.resetPos = this.popupVisible =
                this.showMenu = false;

        // Setting up the game
        this.camera = new OrthographicCamera();
        this.gameMap = new GameMap(this.camera, game.renderer, game.tiledMap);
        this.player = new Player(this.game, this.gameMap, this.camera);
        this.score = score;
        this.eatAch = eatAch;
        this.recAch = recAch;
        this.sleepAch = sleepAch;
        this.font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        this.popupFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        this.durationFont = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        this.shapeRenderer = game.shapeRenderer;
        this.energyBar = setEnergyBar();

        this.calculateDimensions();
        this.calculatePositions();
        this.popupFont.getData().setScale(0.4f, 0.4f);
        this.player.setPos(1389, 635);
        this.camera.setToOrtho(false, this.game.screenWidth / this.zoom, this.game.screenHeight / this.zoom);
        this.camera.update();
    }


    private void calculateDimensions() {
        menuButtonWidth = 64 * game.scaleFactorX;
        menuButtonHeight = 64 * game.scaleFactorY;
        energyBarWidth = 200 * game.scaleFactorX;
        energyBarHeight = 64 * game.scaleFactorY;
        counterBackgroundWidth = 370 * game.scaleFactorX;
        counterBackgroundHeight = 150 * game.scaleFactorY;
        durationUpButtonWidth = 50 * game.scaleFactorX;
        durationUpButtonHeight = 50 * game.scaleFactorY;
        durationDownButtonWidth = 50 * game.scaleFactorX;
        durationDownButtonHeight = 50 * game.scaleFactorY;
        durationMenuBackgroundWidth = 500 * game.scaleFactorX;
        durationMenuBackgroundHeight = 500 * game.scaleFactorY;
        menuBackButtonWidth = 150 * game.scaleFactorX;
        menuBackButtonHeight = 75 * game.scaleFactorY;
        activityButtonWidth = 150 * game.scaleFactorX;
        activityButtonHeight = 75 * game.scaleFactorY;
        popupMenuWidth = 35;
        popupMenuHeight = 10;
        font.getData().setScale(game.scaleFactorX, game.scaleFactorY);
        durationFont.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);

    }

    private void calculatePositions() {
        menuButtonX = 10 * game.scaleFactorX;
        menuButtonY = game.screenHeight - menuButtonHeight - 10 * game.scaleFactorY;
        energyBarX = 30 * game.scaleFactorX + menuButtonWidth;
        energyBarY = game.screenHeight - energyBarHeight - 10 * game.scaleFactorY;
        counterBackgroundX = game.screenWidth - counterBackgroundWidth;
        counterBackgroundY = game.screenHeight - counterBackgroundHeight;
        durationMenuBackgroundX = game.screenWidth / 2f - durationMenuBackgroundWidth / 2f;
        durationMenuBackgroundY = game.screenHeight / 2f - durationMenuBackgroundHeight / 2f;
        durationDownButtonX = game.screenWidth / 2f - durationDownButtonWidth / 2f - 150 * game.scaleFactorX;
        durationUpButtonX = game.screenWidth / 2f - durationUpButtonWidth / 2f + 150 * game.scaleFactorX;
        durationButtonY = game.screenHeight / 2f - durationUpButtonHeight / 2f - 60 * game.scaleFactorY;
        menuBackButtonX = durationDownButtonX;
        activityButtonX = durationUpButtonX + durationUpButtonWidth - activityButtonWidth;
        durationMenuButtonY = durationButtonY - 125 * game.scaleFactorY;
        menuTitleY = 730 * game.scaleFactorY;
        durationTextY = 503 * game.scaleFactorY;
        hoursLabelY = 580 * game.scaleFactorY;
    }

    @Override
    public void render(float delta) {
        if (!lockMovement) player.update(delta);
        if (!lockTime) updateGameTime(delta); // Update the game clock

        handleInput();

        ScreenUtils.clear(0, 0, 1, 1);
        drawWorldElements(delta);
        drawUIElements();
        drawGameTime(); // Draw current time
    }

    /**
     * Handles user input,
     * Ran every render loop of the main game
     */
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.pause();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
            gameTime.timeElapsed = 55;
        }
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        lockTime = false;
        player.updateGender();
    }

    /**
     * Identifies which door, if any, the player is currently touching.
     *
     * @return The name of the door the player is touching or an empty string if none.
     */
    private String getDoorTouching() {
        CollisionHandler collisionHandler = player.getCollisionHandler();
        if (collisionHandler.isTouching("Comp_sci_door", player.getHitBox())) return "Comp_sci_door";
        if (collisionHandler.isTouching("Piazza_door", player.getHitBox())) return "Piazza_door";
        if (collisionHandler.isTouching("Gym_door", player.getHitBox())) return "Gym_door";
        if (collisionHandler.isTouching("Goodricke_door", player.getHitBox())) return "Goodricke_door";
        if (collisionHandler.isTouching("pier", player.getHitBox())) return "pier";
        if (collisionHandler.isTouching("Ron_cooke_door", player.getHitBox())) return "rch_door";
        return "";
    }

    /**
     * Determines the menu title based on the current activity selected by the player.
     *
     * @return The title for the menu based on the current activity.
     */
    private String getMenuTitle() {
        switch (activity) {
            case "feed-ducks":
                return "Feed the ducks";
            case "study":
                return "Study Schedule";
            case "sleep":
                return "Sleep Early";
            case "darts":
                return "Play Darts";
            case "exercise":
                return "Exercise";
            default:
                return "";
        }
    }

    /**
     * Retrieves the appropriate button texture based on the current activity.
     *
     * @return The texture for the activity button.
     */
    private Texture getActivityButton() {
        switch (activity) {
            case "study":
                return menuStudyButton;
            case "sleep":
                return menuSleepButton;
            case "darts":
                return menuGoButton;
            case "exercise":
                return menuGoButton;
            case "feed-ducks":
                return feedDuckButton;
            default:
                return null;
        }
    }

    /**
     * Checks if the cursor is hovering over a menu option and changes its color accordingly.
     *
     * @param posX The X position of the menu option.
     * @param posY The Y position of the menu option.
     */
    private void isHovering(float posX, float posY) {
        int mouseX = Gdx.input.getX();
        int mouseY = game.screenHeight - Gdx.input.getY();
        Vector3 menuOpt = camera.project(new Vector3(posX, posY, 0));
        if (mouseX >= menuOpt.x && mouseX <= menuOpt.x + popupMenuWidth * zoom && mouseY >= menuOpt.y && mouseY
                <= menuOpt.y + popupMenuHeight * zoom)
            game.batch.setColor(shader);
        else
            game.batch.setColor(Color.WHITE);
    }

    /**
     * Draws a menu option at the specified position with a specified text and shade option.
     *
     * @param posX        The X position of the menu option.
     * @param posY        The Y position of the menu option.
     * @param text        The text to display on the menu option.
     * @param shadeOption Determines the shade of the menu option.
     */
    private void drawMenuOption(float posX, float posY, String text, int shadeOption) {
        if (shadeOption == 0) isHovering(posX, posY);
        else if (shadeOption == 1) game.batch.setColor(Color.WHITE);
        else if (shadeOption == 2) game.batch.setColor(shader);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(popupFont, text);
        game.batch.draw(popupMenu, posX, posY, popupMenuWidth, popupMenuHeight);
        popupFont.draw(game.batch, text, posX + (popupMenuWidth - layout.width) / 2, posY + (popupMenuHeight
                + layout.height) / 2f - popupFont.getDescent() - layout.height / 4f);
        game.batch.setColor(Color.WHITE);
    }

    /**
     * Renders the menu for setting the duration of an activity.
     * This functionality of the popup menu (along with all the other methods that relate to this functionality)
     * should be segregated into its own class to reduce overheads and processing delay.
     */
    private void drawDurationMenu() {
        game.batch.begin();
        Texture activityButton;
        String title;
        activityButton = getActivityButton();
        title = getMenuTitle();

        game.batch.draw(durationMenuBackground, durationMenuBackgroundX, durationMenuBackgroundY,
                durationMenuBackgroundWidth, durationMenuBackgroundHeight);
        game.batch.draw(menuBackButton, menuBackButtonX, durationMenuButtonY, menuBackButtonWidth,
                menuBackButtonHeight);
        game.batch.draw(activityButton, activityButtonX, durationMenuButtonY, activityButtonWidth,
                activityButtonHeight);
        durationFont.draw(game.batch, title, 0, menuTitleY, game.screenWidth, Align.center, false);

        if (!activity.equals("sleep") && !activity.equals("darts")) {
            game.batch.draw(durationDownButton, durationDownButtonX, durationButtonY, durationDownButtonWidth,
                    durationDownButtonHeight);
            game.batch.draw(durationUpButton, durationUpButtonX, durationButtonY, durationUpButtonWidth,
                    durationUpButtonHeight);
            durationFont.draw(game.batch, Integer.toString(duration), 0, durationTextY, game.screenWidth,
                    Align.center, false);
            durationFont.draw(game.batch, "Hours", 0, hoursLabelY, game.screenWidth, Align.center, false);
        }

        game.batch.end();
    }

    /**
     * Draws the popup menu for interaction with various doors.
     */
    private void drawPopUpMenu() {
        popupMenuType = getDoorTouching();
        switch (popupMenuType) {
            case "pier":
                drawMenuOption(player.worldX + 30, player.worldY + 20, "Feed", 0);
                popupVisible = true;
                break;
            case "Comp_sci_door":
                drawMenuOption(player.worldX + 30, player.worldY + 20, "Study", 0);
                popupVisible = true;
                break;
            case "Piazza_door":
                drawMenuOption(player.worldX + 30, player.worldY + 20, "Study", 0);
                drawMenuOption(player.worldX + 30, player.worldY + 35, "Eat", 0);
                popupVisible = true;
                break;
            case "Gym_door":
                drawMenuOption(player.worldX + 30, player.worldY + 20, "Exercise", 0);
                popupVisible = true;
                break;
            case "Goodricke_door":
                int shadeOption;
                if (gameTime.currentHour >= 20) {
                    popupVisible = true;
                    shadeOption = 0;
                } else {
                    popupVisible = false;
                    shadeOption = 2;
                }
                drawMenuOption(player.worldX + 30, player.worldY + 20, "Sleep", shadeOption);
                break;
            case "rch_door":
                if (!doneDarts) {
                    popupVisible = true;
                    drawMenuOption(player.worldX + 30, player.worldY + 20, "Darts", 0);
                } else {
                    popupVisible = false;
                    drawMenuOption(player.worldX + 30, player.worldY + 20, "Darts", 2);
                }

                break;
            default:
                popupVisible = false;
                break;
        }
    }

    /**
     * Draws a shade overlay on the screen with a specified alpha transparency.
     *
     * @param alpha The transparency level of the overlay.
     */
    private void drawShadeOverlay(float alpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha); // Adjust alpha for darkness
        shapeRenderer.rect(0, 0, gameMap.getWidth(), gameMap.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Initiates the fade out process and optionally resets the player's position.
     *
     * @param resetPos A boolean indicating whether to reset the player's position.
     */
    private void executeFadeOut(boolean resetPos) {
        if (fadeOut) return;
        fadeOut = true;
        lockMovement = true;
        lockTime = true;
        lockPopup = true;
        showMenu = false;
        this.resetPos = resetPos;
        minShade = gameTime.timeElapsed / gameTime.secondsPerGameHour > 11 ? (gameTime.timeElapsed - 11 * gameTime.secondsPerGameHour) / (
                gameDayLengthInSeconds - 11 * gameTime.secondsPerGameHour) : 0;
    }

    /**
     * Manages the stepwise execution of the fade-out effect.
     *
     * @param delta The time elapsed since the last frame.
     */
    private void fadeOutStep(float delta) {
        if (fadeOut) {
            if (fadeTime == 0)
                fadeTime = minShade;

            if (fadeTime <= 1) {
                fadeTime += delta;
                drawShadeOverlay(fadeTime);
            } else {
                if (resetPos) {
                    player.setPos(1389, 635);
                    player.setDirection('D');
                }

                fadeTime = 0;
                fadeOut = false;
                lockTime = false;
                lockMovement = false;
                lockPopup = false;
                resetPos = false;
            }
        }
    }

    /**
     * Renders the game world elements including the map and player.
     *
     * @param delta The time elapsed since the last frame.
     */
    private void drawWorldElements(float delta) {
        gameMap.update(delta);
        gameMap.render();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(player.getCurrentFrame(), player.worldX, player.worldY, Player.spriteX, Player.spriteY);
        if (!lockPopup) drawPopUpMenu();
        game.batch.end();
        if (!fadeOut && gameTime.timeElapsed / gameTime.secondsPerGameHour > 11) drawShadeOverlay((gameTime.timeElapsed - 11
                * gameTime.secondsPerGameHour) / (gameDayLengthInSeconds - 11 * gameTime.secondsPerGameHour));
        fadeOutStep(delta);
    }

    /**
     * Renders the UI elements of the game.
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private void drawUIElements() {
        final String counterString = String.format("Recreation Activities done: " + recActivity + "\nStudy hours: "
                + studyHours + "\nMeals Eaten: " + mealCount, dayNum, gameTime.timeElapsed);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        if (showMenu && !activity.equals("feed-ducks")) drawDurationMenu();
        game.batch.begin();
        game.batch.draw(menuButton, menuButtonX, menuButtonY, menuButtonWidth, menuButtonHeight);
        game.batch.draw(energyBar, energyBarX, energyBarY, energyBarWidth, energyBarHeight);
        game.batch.draw(counterBackground, counterBackgroundX, counterBackgroundY, counterBackgroundWidth,
                counterBackgroundHeight);
        font.draw(game.batch, counterString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 40
                * game.scaleFactorY);
        game.batch.end();
    }

    /**
     * Updates the game time and handles the transition from day to night.
     *
     * @param delta The time elapsed since the last frame.
     */
    public void updateGameTime(float delta) {
        if (gameTime.isUpdateGameTime(delta)) { // If it reaches 12 AM, reset to 8 AM the next day
            if (dayNum == 7) {
                score.computeFinalScore(eatAch.getStreak(), sleepAch.getStreak(), recAch.getStreak());
                game.screenManager.setScreen(ScreenType.END_SCREEN);
            }
            resetDay();
        }
    }

    /**
     * Resets the game day, including resetting time and increasing the day counter.
     */
    private void resetDay() {
        executeFadeOut(true);

        if (dailyStudyHours > 4)
            score.markAsOverstudied();

        score.updateScore();

        if (!dayStudied) {
            if (score.getMissedStudySessions() == 0)
                score.incrementMissed();
            score.incrementNoStudy();
        }

        gameTime.resetGameTime();
        dayNum++;
        dailyStudyHours = 0;
        energyCounter += 4;
        hasExercised = false;
        hasEaten = false;
        doneDarts = false;
        dayStudied = false;
        score.resetMultipliers();
        if (energyCounter > 10) energyCounter = 10;
        energyBar.dispose();
        energyBar = setEnergyBar();
    }

    /**
     * Draws the game time display.
     */
    private void drawGameTime() {
        // Adjust the format if you want to display minutes or seconds
        String timeString = String.format("Day: %d       Time: %02d:00", dayNum, gameTime.currentHour % 24);
        game.batch.begin();
        font.draw(game.batch, timeString, game.screenWidth - 320 * game.scaleFactorX, game.screenHeight - 15
                * game.scaleFactorY);
        game.batch.end();
    }

    /**
     * Sets the texture for the energy bar based on the current energy level.
     *
     * @return The texture of the current energy bar.
     */
    public Texture setEnergyBar() {
        if (energyCounter > 0)
            return new Texture("energy/energy_" + energyCounter + ".png");
        else
            return new Texture("energy/energy_0.png");
    }

    public int getEnergyCounter() {
        return energyCounter;
    }

    public int getMealCount() {
        return mealCount;
    }

    /**
     * Handles touch input from the user, managing interactions with UI elements and game objects.
     *
     * @param touchX  The x-coordinate of the touch.
     * @param touchY  The y-coordinate of the touch.
     * @param pointer The pointer for the event.
     * @param button  The button that was pressed.
     * @return true if the input was processed, false otherwise.
     */
    @Override
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;

        if (touchX >= menuButtonX && touchX <= menuButtonX + menuButtonWidth && touchY >= menuButtonY
                && touchY <= menuButtonY + menuButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.MAIN_MENU);
        } else if (showMenu) {
            switch (activity) {
                case "study":
                    if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration < 4)
                            duration++;
                    } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration > 1)
                            duration--;
                    } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;
                        duration = 1;
                    } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;
                        studyHours += duration;
                        dailyStudyHours += duration;
                        score.incrementStudy(studyHours);

                        if (score.getMissedStudySessions() == 1) {
                            score.incrementMissed();
                            score.incrementStudy(studyHours);
                            score.decrementNoStudy();
                        }

                        dayStudied = true;
                        if (energyCounter > (duration + 1) / 2)
                            energyCounter -= (duration + 1) / 2;
                        energyBar.dispose();
                        energyBar = setEnergyBar();
                        gameTime.updateGameTimeActivity(duration);
                        game.screenManager.setScreen(ScreenType.MINI_GAME, duration);
                    }

                    break;

                case "exercise":
                    if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration < 4)
                            duration++;
                    } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration > 1)
                            duration--;
                    } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;
                        duration = 1;
                    } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (energyCounter >= duration) {
                            executeFadeOut(false);
                            showMenu = false;
                            lockMovement = fadeOut;
                            recActivity++;
                            score.incrementRec(duration);

                            if (!hasExercised) {
                                hasExercised = true;
                                recAch.incrementStreak();
                            }

                            energyCounter -= duration;
                            energyBar.dispose();
                            energyBar = setEnergyBar();
                            gameTime.updateGameTimeActivity(duration);
                            duration = 1;
                        }
                    }
                    break;

                case "darts":
                    if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration < 4)
                            duration++;
                    } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration > 1)
                            duration--;
                    } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;
                        duration = 1;
                    } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight && !doneDarts) {
                        game.gameData.buttonClickedSoundActivate();
                        if (energyCounter >= duration) {
                            executeFadeOut(false);
                            showMenu = false;
                            lockMovement = fadeOut;
                            recActivity++;
                            score.incrementRec(5);

                            if (!doneDarts) {
                                doneDarts = true;
                            }

                            energyCounter -= 2;
                            energyBar.dispose();
                            energyBar = setEnergyBar();
                            gameTime.updateGameTimeActivity(2);
                            duration = 1;
                        }
                    }
                    break;


                case "sleep":
                    if (touchX >= durationUpButtonX && touchX <= durationUpButtonX + durationUpButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationUpButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration < 10) duration++;
                    } else if (touchX >= durationDownButtonX && touchX <= durationDownButtonX + durationDownButtonWidth
                            && touchY >= durationButtonY && touchY <= durationButtonY + durationDownButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        if (duration > 1) duration--;
                    } else if (touchX >= menuBackButtonX && touchX <= menuBackButtonX + menuBackButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + menuBackButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;
                        duration = 1;
                    } else if (touchX >= activityButtonX && touchX <= activityButtonX + activityButtonWidth
                            && touchY >= durationMenuButtonY && touchY <= durationMenuButtonY + activityButtonHeight) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = false;
                        lockMovement = fadeOut;

                        if (dayNum == 7) {
                            score.computeFinalScore(eatAch.getStreak(), sleepAch.getStreak(), recAch.getStreak());
                            game.create();
                            game.screenManager.setScreen(ScreenType.END_SCREEN);
                        }

                        sleepAch.incrementStreak();
                        resetDay();
                        duration = 1;
                    }
                    break;

                default:
                    return false;
            }
        } else if (popupVisible) {
            Vector3 studyOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 20, 0));
            Vector3 eatOpt = camera.project(new Vector3(player.worldX + 30, player.worldY + 35, 0));
            switch (popupMenuType) {
                case "pier":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        activity = "feed-ducks";
                        showMenu = false;
                        game.gameData.buttonClickedSoundActivate();
                        game.screenManager.setScreen(ScreenType.FEED_DUCKS, camera, gameMap, score);
                        updateGameTime(10);
                    }
                    break;
                case "Comp_sci_door":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y
                            && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = true;
                        lockMovement = true;
                        activity = "study";
                        duration = 1;
                    }
                    break;

                case "Piazza_door":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y
                            && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = true;
                        lockMovement = true;
                        activity = "study";
                        duration = 1;
                    } else if (touchX >= eatOpt.x && touchX <= eatOpt.x + popupMenuWidth * zoom && touchY >= eatOpt.y
                            && touchY <= eatOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        game.gameData.eatingSoundActivate();
                        energyCounter += 3;
                        mealCount++;

                        if (!hasEaten) {
                            eatAch.incrementStreak();
                            hasEaten = true;
                        }

                        if (energyCounter > 10) energyCounter = 10;
                        energyBar.dispose();
                        energyBar = setEnergyBar();
                    }
                    break;

                case "Gym_door":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y
                            && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = true;
                        lockMovement = true;
                        activity = "exercise";
                        duration = 1;
                    }
                    break;

                case "rch_door":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y
                            && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = true;
                        lockMovement = true;
                        activity = "darts";
                        duration = 1;
                    }
                    break;

                case "Goodricke_door":
                    if (touchX >= studyOpt.x && touchX <= studyOpt.x + popupMenuWidth * zoom && touchY >= studyOpt.y
                            && touchY <= studyOpt.y + popupMenuHeight * zoom) {
                        game.gameData.buttonClickedSoundActivate();
                        showMenu = true;
                        lockMovement = true;
                        activity = "sleep";
                        duration = 1;
                    }
                    break;

                default:
                    return false;
            }
        }

        return false;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public boolean isLockMovement() {
        return lockMovement;
    }

    public String getActivity() {
        return activity;
    }

    public int getDuration() {
        return duration;
    }

    public void studyClickHandler() {
        game.gameData.buttonClickedSoundActivate();
        showMenu = true;
        lockMovement = true;
        activity = "study";
        duration = 1;
    }

    public void exerciseClickHandler() {
        game.gameData.buttonClickedSoundActivate();
        showMenu = true;
        lockMovement = true;
        activity = "exercise";
        duration = 1;
    }

    public void sleepClickHandler() {
        game.gameData.buttonClickedSoundActivate();
        showMenu = true;
        lockMovement = true;
        activity = "sleep";
        duration = 1;
    }

    public void eatClickHandler() {
        game.gameData.buttonClickedSoundActivate();
        game.gameData.eatingSoundActivate();
        energyCounter += 3;
        mealCount++;
        if (energyCounter > 10) energyCounter = 10;
        energyBar.dispose();
        energyBar = setEnergyBar();
    }

    @Override
    public void resize(int i, int i1) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        menuButton.dispose();
        counterBackground.dispose();
        popupMenu.dispose();
        durationMenuBackground.dispose();
        durationUpButton.dispose();
        durationDownButton.dispose();
        menuBackButton.dispose();
        menuStudyButton.dispose();
        menuSleepButton.dispose();
        menuGoButton.dispose();
        energyBar.dispose();
        player.dispose();
        font.dispose();
        popupFont.dispose();
        durationFont.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.screenManager.setScreen(ScreenType.MAIN_MENU);

        return true;
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
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public void lowerEnergyCounter() {
        if (this.energyCounter == 0)
            return;
        this.energyCounter--;
        energyBar = setEnergyBar();
    }

    public void incrementRecActivity() {
        this.recActivity++;
    }
}
