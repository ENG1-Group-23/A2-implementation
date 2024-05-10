package bytemusketeers.heslingtonhustle.utils;

import bytemusketeers.heslingtonhustle.sound.GameMusic;
import bytemusketeers.heslingtonhustle.sound.GameSound;

/**
 * The {@link GameData} class stores game settings and data and handles the manipulation and querying thereof.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class GameData {
    /**
     * The {@link GameMusic} data reference
     *
     * @see GameMusic
     */
    private final GameMusic music;
    /**
     * The {@link GameSound} data reference
     *
     * @see GameSound
     */
    private final GameSound sound;
    /**
     * The current gender of the {@link bytemusketeers.heslingtonhustle.entity.Player}
     *
     * @apiNote The male state is encoded as {@code true}, and the female state as {@code false}.
     */
    private boolean gender = true;

    /**
     * Constructor initializes the game music settings.
     */
    public GameData() {
        this.music = new GameMusic();
        this.sound = new GameSound();
    }

    /**
     * Returns the player's gender selection.
     *
     * @return A boolean representing the gender, true for male, false for female.
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * Sets the player's gender preference.
     *
     * @param gender A boolean, true for male and false for female.
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * Gets the current sound level setting.
     *
     * @return The sound level as an integer.
     */
    public int getSoundLevel() {
        return sound.getSoundLevel();
    }

    /**
     * Gets the current music level setting.
     *
     * @return The music volume level
     */
    public int getMusicLevel() {
        return music.getMusicLevel();
    }

    /**
     * Increments the music volume level if not at maximum.
     */
    public void incrementMusicLevel() {
        music.incrementVolume();
    }

    /**
     * Decrements the music volume level if not at minimum.
     */
    public void decrementMusicLevel() {
        music.decrementVolume();
    }

    /**
     * Increments the sound-effects volume level if not at maximum.
     */
    public void incrementSoundLevel() {
        sound.incrementVolume();
    }

    /**
     * Decrements the sound-effects volume level if not at minimum.
     */
    public void decrementSoundLevel() {
        sound.decrementVolume();
    }

    /**
     * Activates the sound of the volume being raised
     *
     * @see GameSound#incrementVolume()
     */
    public void upSoundActivate() {
        sound.upSoundActivate();
    }

    /**
     * Activates the sound of the volume being lowered
     *
     * @see GameSound#decrementVolume()
     */
    public void downSoundActivate() {
        sound.downSoundActivate();
    }

    /**
     * Activates the sound of the {@link bytemusketeers.heslingtonhustle.entity.Player} consuming food
     *
     * @see GameSound#eatingSoundActivate()
     */
    public void eatingSoundActivate() {
        sound.eatingSoundActivate();
    }

    /**
     * Activates the sound of a UI button being pressed
     *
     * @see GameSound#buttonClickedSoundActivate()
     */
    public void buttonClickedSoundActivate() {
        sound.buttonClickedSoundActivate();
    }

    public void duckSoundActivate() {
        sound.duckSoundActivate();
    }
}
