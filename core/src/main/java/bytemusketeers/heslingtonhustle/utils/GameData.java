package bytemusketeers.heslingtonhustle.utils;

import bytemusketeers.heslingtonhustle.sound.GameMusic;
import bytemusketeers.heslingtonhustle.sound.GameSound;

/**
 * Stores game settings and data.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class GameData {
    private boolean gender = true;
    GameMusic music;
    GameSound sound;

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
     * @return The music level as an integer.
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

    public void incrementSoundLevel() {
        sound.incrementVolume();
    }

    public void decrementSoundLevel() {
        sound.decrementVolume();
    }

    public void upSoundActivate() {
        sound.upSoundActivate();
    }

    public void downSoundActivate() {
        sound.downSoundActivate();
    }

    public void eatingSoundActivate() {
        sound.eatingSoundActivate();
    }

    public void buttonClickedSoundActivate() {
        sound.buttonClickedSoundActivate();
    }
}
