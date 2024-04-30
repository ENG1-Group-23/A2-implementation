package bytemusketeers.heslingtonhustle.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Manages game sound effects including sound level adjustments and playing specific sounds. It handles sound effects
 * for increasing volume, decreasing volume, and button clicks.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class GameSound {
    /**
     * The {@link Music} sound associated with increasing the volume
     */
    private final Music upSound;

    /**
     * The {@link Music} sound associated with decreasing the volume
     */
    private final Music downSound;

    /**
     * The {@link Music} sound associated with clicking of a button
     */
    private final Music buttonClickedSound;

    /**
     * The {@link Music} sound associated with the {@link bytemusketeers.heslingtonhustle.entity.Player} consuming food
     */
    private final Music eatingSound;

    /**
     * The transient volume, in quarters
     */
    private int soundLevel = 4;

    /**
     * Initializes sound effects by loading the audio files.
     */
    public GameSound() {
        upSound = Gdx.audio.newMusic(Gdx.files.internal("sfx/high_note.mp3"));
        downSound = Gdx.audio.newMusic(Gdx.files.internal("sfx/low_note.mp3"));
        buttonClickedSound = Gdx.audio.newMusic(Gdx.files.internal("sfx/button_press.mp3"));
        eatingSound = Gdx.audio.newMusic(Gdx.files.internal("sfx/eating_sound.wav"));
    }

    /**
     * Gets the current sound level.
     *
     * @return The current sound level.
     */
    public int getSoundLevel() {
        return this.soundLevel;
    }

    /**
     * Plays the sound effect for increasing the volume. Stops the sound if it is already playing before restarting it.
     */
    public void upSoundActivate() {
        if (upSound.isPlaying())
            upSound.stop();

        upSound.play();
    }

    /**
     * Plays the sound effect for decreasing the volume. Stops the sound if it is already playing before restarting it.
     */
    public void downSoundActivate() {
        if (downSound.isPlaying()) {
            downSound.stop();
            downSound.play();
        }

        downSound.play();
    }

    /**
     * Plays the sound effect for a button click. Stops the sound if it is already playing before restarting it.
     */
    public void buttonClickedSoundActivate() {
        if (buttonClickedSound.isPlaying())
            buttonClickedSound.stop();

        buttonClickedSound.play();
    }

    /**
     * Plays the sound effect for increasing the volume. Stops the sound if it is already playing before restarting it.
     */
    public void eatingSoundActivate() {
        if (eatingSound.isPlaying())
            eatingSound.stop();

        eatingSound.play();
    }

    /**
     * Increments the sound volume level by one step if not already at the maximum. Also adjusts the volume of all
     * sound effects accordingly.
     */
    public void incrementVolume() {
        // Check if volume is not already at maximum
        if (soundLevel <= 3) {
            soundLevel = soundLevel + 1;
            float floatingMusicLevel = (float) soundLevel;
            upSound.setVolume(floatingMusicLevel * 25 / 100);
            downSound.setVolume(floatingMusicLevel * 25 / 100);
            buttonClickedSound.setVolume(floatingMusicLevel * 25 / 100);
            eatingSound.setVolume(floatingMusicLevel * 25 / 100);
        }
    }

    /**
     * Decrements the music volume level by one, if it is not already at the minimum level. Adjusts the music playback
     * volume accordingly.
     */
    public void decrementVolume() {
        // Check if volume is not already at minimum
        if (this.soundLevel >= 1) {
            soundLevel = soundLevel - 1;
            float floatingMusicLevel = (float) soundLevel;
            upSound.setVolume(floatingMusicLevel * 25 / 100);
            downSound.setVolume(floatingMusicLevel * 25 / 100);
            buttonClickedSound.setVolume(floatingMusicLevel * 25 / 100);
        }
    }

    /**
     * Retrieves the volume-increasing indicator sound
     *
     * @return The {@link #upSound}
     */
    public Music getUpSound() {
        return upSound;
    }

    /**
     * Retrieves the volume-decreasing indicator sound
     *
     * @return The {@link #downSound}
     */
    public Music getDownSound() {
        return downSound;
    }

    /**
     * Retrieves the button-actuate indicator sound
     *
     * @return The {@link #buttonClickedSound}
     */
    public Music getButtonClickedSound() {
        return buttonClickedSound;
    }

    /**
     * Retrieves the {@link bytemusketeers.heslingtonhustle.entity.Player}-eating indicator sound
     *
     * @return The {@link #eatingSound}
     */
    public Music getEatingSound() {
        return eatingSound;
    }
}


