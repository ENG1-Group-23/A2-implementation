package bytemusketeers.heslingtonhustle.screens;

import bytemusketeers.heslingtonhustle.HeslingtonHustle;
import bytemusketeers.heslingtonhustle.utils.Achievement;
import bytemusketeers.heslingtonhustle.utils.Score;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen, InputProcessor {
    HeslingtonHustle game;
    Texture playAgainButton, exitButton;
    BitmapFont font;
    String titleText;
    float playAgainButtonY, exitButtonY;
    float buttonX, buttonWidth, buttonHeight;
    float titleY;
    boolean exitFlag;
    private final Score score;
    private final Achievement eatAch;
    private final Achievement recAch;
    private final Achievement sleepAch;

    public EndScreen(HeslingtonHustle game, Score score, Achievement eatAch, Achievement recAch, Achievement sleepAch){
        this.game = game;
        this.score = score;
        this.eatAch = eatAch;
        this.recAch = recAch;
        this.sleepAch = sleepAch;
        titleText = "The End";
        loadAssets();
        calculateDimensions();
        calculatePositions();
    }

    private void loadAssets(){
        playAgainButton = new Texture("end_gui/play_button.png");
        exitButton = new Texture("end_gui/exit_button.png");
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));
    }

    private void calculateDimensions(){
        buttonWidth = playAgainButton.getWidth() * 10 * game.scaleFactorX;
        buttonHeight = playAgainButton.getHeight() * 10 * game.scaleFactorY;
        font.getData().setScale(5.5f * game.scaleFactorX, 5.5f * game.scaleFactorY);
    }

    private void calculatePositions(){
        buttonX = (game.screenWidth - buttonWidth)/2f;
        playAgainButtonY = game.screenHeight - buttonHeight * 4.5f;
        exitButtonY = game.screenHeight - buttonHeight * 6f;
        titleY = game.screenHeight - 120f * game.scaleFactorY;
    }

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

    @Override
    public void render(float v) {
        if (exitFlag) return;
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        font.draw(game.batch, titleText, 0, titleY, game.screenWidth, Align.center, false);
        font.draw(game.batch, "Score: " + score.ReadScore(), 0, titleY, game.screenWidth, Align.right, false);

        font.draw(game.batch, AchievementText(eatAch.ReadStreak(), eatAch.ReadName()), 0,
                titleY, game.screenWidth, Align.left, false);
        font.draw(game.batch, AchievementText(recAch.ReadStreak(), recAch.ReadName()), 0,
                titleY - 70f * game.scaleFactorY, game.screenWidth, Align.left, false);
        font.draw(game.batch, AchievementText(sleepAch.ReadStreak(), sleepAch.ReadName()), 0,
                titleY - 140f * game.scaleFactorY, game.screenWidth, Align.left, false);

        game.batch.draw(playAgainButton, buttonX, playAgainButtonY, buttonWidth, buttonHeight);
        game.batch.draw(exitButton, buttonX, exitButtonY, buttonWidth, buttonHeight);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
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
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;
        if (touchX >= buttonX && touchX <= buttonX + buttonWidth &&
                touchY >= playAgainButtonY && touchY <= playAgainButtonY + buttonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.setup();
        }
        else if (touchX >= buttonX && touchX <= buttonX + buttonWidth &&
                touchY >= exitButtonY && touchY <= exitButtonY + buttonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.clearMemory();
            exitFlag = true;
            dispose();
            Gdx.app.exit();
        }
        return true;
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

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
    }

    @Override
    public void resize(int i, int i1) {
        calculateDimensions();
        calculatePositions();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playAgainButton.dispose();
        exitButton.dispose();
        font.dispose();
    }
}
