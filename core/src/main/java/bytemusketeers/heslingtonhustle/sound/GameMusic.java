package bytemusketeers.heslingtonhustle.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * The GameMusic class manages game music, including playback and volume level adjustments.
 * Initializes the game's background music and allows for dynamic volume control.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class GameMusic {
    Music audio;

    private int musicLevel = 4;

    private final float musicCap = 0.1f;

    /**
     * Constructor for GameMusic. Initializes and starts playing the game's background music at the maximum volume
     * level.
     */
    public GameMusic() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("music_loop/Ludum Dare 30 - 01.ogg"));
        audio.play();
        audio.setVolume(musicCap); // Set volume to 100%
        audio.setLooping(true);
    }

    /**
     * Returns the current music volume level.
     *
     * @return The current music volume level
     */
    public int getMusicLevel() {
        return this.musicLevel;
    }


    /**
     * Increments the music volume level by one, if it is not already at the maximum level. Adjusts the music playback
     * volume accordingly.
     */
    public void incrementVolume() {
        // Check if volume is not already at maximum
        if (musicLevel <= 3) {
            musicLevel = musicLevel + 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel * 25 / 100 * musicCap);
        }
    }

    /**
     * Decrements the music volume level by one, if it is not already at the minimum level. Adjusts the music playback
     * volume accordingly.
     */
    public void decrementVolume() {
        // Check if volume is not already at minimum
        if (this.musicLevel >= 1) {
            musicLevel = musicLevel - 1;
            float floatingMusicLevel = (float) musicLevel;
            audio.setVolume(floatingMusicLevel * 25 / 100 * musicCap);
        }
    }
}
