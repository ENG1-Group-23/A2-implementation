package bytemusketeers.heslingtonhustle.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * The {@link GameMusic} manager controls game music, including playback and volume level adjustments. It also
 * initialises the background music and allows for dynamic volume control.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class GameMusic {
    /**
     * The generic audio track played during game-time
     */
    Music audio;

    /**
     * The transient volume of the {@link #audio}
     *
     * @see #incrementVolume()
     * @see #decrementVolume()
     */
    private int musicLevel = 4;

    /**
     * The maximal volume of the {@link #audio}
     */
    private static final float MUSIC_CAP = 0.1f;

    /**
     * Constructor for GameMusic. Initializes and starts playing the game's background music at the maximum volume
     * level.
     */
    public GameMusic() {
        audio = Gdx.audio.newMusic(Gdx.files.internal("music_loop/Ludum Dare 30 - 01.ogg"));
        audio.play();
        audio.setVolume(MUSIC_CAP); // Set volume to 100%
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
            audio.setVolume(floatingMusicLevel * 25 / 100 * MUSIC_CAP);
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
            audio.setVolume(floatingMusicLevel * 25 / 100 * MUSIC_CAP);
        }
    }
}
