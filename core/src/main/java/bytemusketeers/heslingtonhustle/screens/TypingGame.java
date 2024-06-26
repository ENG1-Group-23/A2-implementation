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
import com.badlogic.gdx.utils.Timer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The TypingGame class implements a mini-game for the player to increase their study hours.
 * Players are shown a number that they need to memorize and then type it correctly to succeed.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class TypingGame extends ScreenAdapter implements Screen, InputProcessor {
    private final HeslingtonHustle game;
    private final int studyDuration;
    private final Texture guessButton;
    private final Texture title;
    Boolean acceptInput = false, displayCorrect = false, displayWrong = false;
    BitmapFont displayText;
    String gameObjective;
    private int attempts = 0;
    private int currentNumber = 0;
    private int correct = 0;
    private String userGuess = "";
    private float displayTextY, displayTextHeight;
    private float gameObjectiveY;
    private float guessButtonX, guessButtonY, guessButtonWidth, guessButtonHeight;
    private float titleX, titleY, titleWidth, titleHeight;

    /**
     * Constructs a TypingGame screen with the game instance and study duration.
     *
     * @param game          The main game instance.
     * @param studyDuration The duration of the study session in attempts.
     */
    public TypingGame(HeslingtonHustle game, int studyDuration) {
        this.game = game;
        displayText = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
        guessButton = new Texture("mini_games/guess_button.png");
        title = new Texture("mini_games/number_memoriser_label.png");

        calculateDimensions();
        calculatePositions();

        Gdx.input.setInputProcessor(this);
        this.studyDuration = studyDuration;

        gameObjective = "Remember the number given and try to input the number from memory";

        playGame();
    }

    /**
     * Begins a new challenge by generating a number for the player to memorize.
     * Handles the logic for correct and incorrect guesses and progresses the game.
     */
    private void calculateDimensions() {
        displayText.getData().setScale(3f * game.scaleFactorX, 3f * game.scaleFactorY);
        displayTextHeight = 100 * game.scaleFactorY;
        gameObjectiveY = game.screenHeight - 280 * game.scaleFactorY;
        guessButtonWidth = 156 * game.scaleFactorX;
        guessButtonHeight = 84 * game.scaleFactorY;
        titleWidth = title.getWidth() * game.scaleFactorX * 11;
        titleHeight = title.getHeight() * game.scaleFactorY * 11;
    }

    private void calculatePositions() {
        displayTextY = game.screenHeight / 2f - displayTextHeight;
        guessButtonX = (game.screenWidth - guessButtonWidth) / 2f;
        guessButtonY = (game.screenHeight - guessButtonHeight) / 2f - 300 * game.scaleFactorY;
        titleX = (game.screenWidth - titleWidth) / 2f;
        titleY = (game.screenHeight - titleHeight) / 2f + 400 * game.scaleFactorY;
    }

    /**
     * Begins a new challenge by generating a number for the player to memorize.
     * Handles the logic for correct and incorrect guesses and progresses the game.
     */
    public void playGame() {
        userGuess = "";
        displayWrong = false;
        displayCorrect = false;
        if (attempts < studyDuration) {
            currentNumber = generateNumber();
            delay(5, this::makeUserGuess);
        } else
            game.screenManager.setScreen(ScreenType.GAME_SCREEN);
    }

    /**
     * Allows the player to input their guess after a short delay.
     */
    public void makeUserGuess() {
        acceptInput = true;
    }

    /**
     * Implements a delay before executing a given runnable task.
     *
     * @param seconds  The delay in seconds before running the task.
     * @param runnable The task to execute after the delay.
     */
    public void delay(int seconds, Runnable runnable) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runnable.run();
            }
        }, seconds);
    }


    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);

        game.batch.begin();
        game.batch.draw(title, titleX, titleY, titleWidth, titleHeight);

        displayText.draw(game.batch, gameObjective, 0, gameObjectiveY, game.screenWidth, Align.center, false);

        if (acceptInput) {
            displayText.draw(game.batch, userGuess, 0, displayTextY, game.screenWidth, Align.center, false);
            game.batch.draw(guessButton, guessButtonX, guessButtonY, guessButtonWidth, guessButtonHeight);
        } else if (displayCorrect)
            displayText.draw(game.batch, "Correct well done.", 0, displayTextY, game.screenWidth, Align.center,
                    false);
        else if (displayWrong)
            displayText.draw(game.batch, "Incorrect. Answer: " + currentNumber, 0, displayTextY,
                    game.screenWidth, Align.center, false);
        else
            displayText.draw(game.batch, String.valueOf(currentNumber), 0, displayTextY, game.screenWidth,
                    Align.center, false);

        game.batch.end();
    }

    /**
     * Generates a random number for the player to memorize.
     *
     * @return The generated number.
     */
    public int generateNumber() {
        int startingNumLength = 5;
        int startingNum = (int) (10 * Math.pow(10, startingNumLength - 1));
        int lowerLimit = (int) (startingNum * Math.pow(10, attempts - 1));
        int num = ThreadLocalRandom.current().nextInt(lowerLimit, lowerLimit * 10 - 1);
        attempts++;

        return num;
    }

    @Override
    public void resize(int width, int height) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void dispose() {
        guessButton.dispose();
        title.dispose();
        displayText.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (acceptInput) {
            if (character == '\b' && !userGuess.isEmpty()) { // Handles backspace
                userGuess = userGuess.substring(0, userGuess.length() - 1);
            } else if (Character.isDigit(character) && userGuess.length() < String.valueOf(currentNumber).length()) {
                userGuess += character;
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(int worldX, int worldY, int pointer, int button) {
        worldY = game.screenHeight - worldY;

        if (worldX >= guessButtonX && worldX <= guessButtonX + guessButtonWidth &&
                worldY >= guessButtonY && worldY <= guessButtonY + guessButtonHeight) {

            if (!userGuess.isEmpty()) {
                acceptInput = false;
                if (Integer.parseInt(userGuess) == currentNumber) {
                    correct = correct + 1;
                    displayCorrect = true;
                } else
                    displayWrong = true;

                delay(2, this::playGame);
            }

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
