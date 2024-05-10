package com.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.main.Main;
import com.main.utils.ScreenType;

public class PauseScreen implements Screen, InputProcessor {
    private final Main game;
    private final BitmapFont font;
    private Texture backButton, settingsButton, exitButton;
    private int backButtonHeight, settingsButtonHeight, exitButtonHeight;
    private int backButtonWidth, settingsButtonWidth, exitButtonWidth;
    private float backButtonX, backButtonY;
    private float settingsButtonX, settingsButtonY;
    private float exitButtonX, exitButtonY;
    private boolean exitFlag;

    public PauseScreen(Main game) {
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("font/WhitePeaberry.fnt"));

        loadTextures();

        calculatePositions();
        calculateDimensions();
    }

    private void loadTextures() {
        backButton = new Texture("settings_gui/back_button.png");
        settingsButton = new Texture("menu_gui/settings_button.png");
        exitButton = new Texture("menu_gui/exit_button.png");
    }


    /**
     *
     */
    @Override
    public void show() {
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Calculates the dimensions of buttons based on their textures.
     */
    private void calculateDimensions() {
        backButtonHeight = (int) (backButton.getHeight() * 10 * game.scaleFactorY);
        backButtonWidth = (int) (backButton.getWidth() * 10 * game.scaleFactorX);
        settingsButtonHeight = (int) (settingsButton.getHeight() * 10 * game.scaleFactorY);
        settingsButtonWidth = (int) (settingsButton.getWidth() * 10 * game.scaleFactorX);
        exitButtonHeight = (int) (exitButton.getHeight() * 10 * game.scaleFactorY);
        exitButtonWidth = (int) (exitButton.getWidth() * 10 * game.scaleFactorX);
    }

    /**
     * Calculates the positions of buttons to be drawn on the screen.
     */
    private void calculatePositions() {
        backButtonX = (game.screenWidth - backButtonWidth) / 2f;
        backButtonY = (game.screenHeight - backButtonHeight) * 1.25f;
        settingsButtonX = (game.screenWidth - settingsButtonWidth) / 2f;
        settingsButtonY = game.screenHeight - settingsButtonHeight * 5f;
        exitButtonX = (game.screenWidth - exitButtonWidth) / 2f;
        exitButtonY = game.screenHeight - exitButtonHeight * 6.25f;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public boolean keyUp(int i) {
        return false;
    }

    /**
     * @param c
     * @return
     */
    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    /**Functionality for mouse button presses on the screen
     *
     * @param touchX
     * @param touchY
     * @param pointer
     * @param button
     * @return
     */
    public boolean touchDown(int touchX, int touchY, int pointer, int button) {
        touchY = game.screenHeight - touchY;

        if (touchX >= backButtonX && touchX <= backButtonX + backButtonWidth &&
                touchY >= backButtonY && touchY <= backButtonY + backButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.pause();
        }
        else if (touchX >= settingsButtonX && touchX <= settingsButtonX + settingsButtonWidth &&
                touchY >= settingsButtonY && touchY <= settingsButtonY + settingsButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.setScreen(ScreenType.SETTINGS);
        }
        else if (touchX >= exitButtonX && touchX <= exitButtonX + exitButtonWidth &&
                touchY >= exitButtonY && touchY <= exitButtonY + exitButtonHeight) {
            game.gameData.buttonClickedSoundActivate();
            game.screenManager.clearMemory();
            exitFlag = true;
            dispose();
            Gdx.app.exit();
        }
        return true;
    }

    /**
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    /**
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    /**
     * @param i
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    /**
     * @param i
     * @param i1
     * @return
     */
    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    /**
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    /**
     * @param v
     */
    @Override
    public void render(float v) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { game.pause(); }

        if (exitFlag) return;
        ScreenUtils.clear(0.3f, 0.55f, 0.7f, 1);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.setProjectionMatrix(game.defaultCamera.combined);
        game.batch.begin();
        game.batch.draw(settingsButton, settingsButtonX, settingsButtonY, settingsButtonWidth, settingsButtonHeight);
        game.batch.draw(exitButton, exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
        game.batch.draw(backButton, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        game.batch.end();
    }

    /**
     * @param i
     * @param i1
     */
    @Override
    public void resize(int i, int i1) {
        calculatePositions();
        calculateDimensions();
    }

    /**
     *
     */
    @Override
    public void pause() {

    }

    /**
     *
     */
    @Override
    public void resume() {

    }

    /**
     *
     */
    @Override
    public void hide() {

    }

    /**
     *
     */
    @Override
    public void dispose() {
        settingsButton.dispose();
        backButton.dispose();
        exitButton.dispose();
    }
}
