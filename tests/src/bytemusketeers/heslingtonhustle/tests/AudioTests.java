package bytemusketeers.heslingtonhustle.tests;

import bytemusketeers.heslingtonhustle.sound.GameMusic;
import bytemusketeers.heslingtonhustle.sound.GameSound;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class AudioTests {
    @Test
    public void testIncrementGameMusicVolumeStopsAtMaximum() {
        GameMusic music = new GameMusic();
        int initialVolume = music.getMusicLevel();
        music.incrementVolume();
        int finalVolume = music.getMusicLevel();
        assertEquals(initialVolume, finalVolume);
    }

    @Test
    public void testDecrementGameMusicVolumeStopsAtMinimum() {
        GameMusic music = new GameMusic();
        int initialVolume = music.getMusicLevel();
        for(int i = 0; i < 10; i++) {
            music.decrementVolume();
        }
        int finalVolume = music.getMusicLevel();
        assertNotEquals(initialVolume, finalVolume); //make sure that the volume has changed
        assertEquals(0, finalVolume);
    }

    @Test
    public void testIncrementGameSoundVolumeStopsAtMaximum() {
        GameSound sound = new GameSound();
        int initialSoundLevel = sound.getSoundLevel();
        for(int i = 0; i < 10; i++) {
            sound.incrementVolume();
        }
        int finalSoundLevel = sound.getSoundLevel();
        assertEquals(initialSoundLevel, finalSoundLevel);
    }

    @Test
    public void testDecrementGameSoundVolumeStopsAtMinimum() {
        GameSound sound = new GameSound();
        int initialSoundLevel = sound.getSoundLevel();
        for(int i = 0; i < 10; i++) {
            sound.decrementVolume();
        }
        int finalSoundLevel = sound.getSoundLevel();
        assertNotEquals(initialSoundLevel, finalSoundLevel);
        assertEquals(0, finalSoundLevel);
    }

    @Test
    public void testUpSoundStopsWhenActivatedIfAlreadyPlaying() {
        GameSound sound = new GameSound();
        sound.upSoundActivate();
        sound.upSoundActivate();
        assertFalse(sound.getUpSound().isPlaying());
    }

    @Test
    public void testDownSoundStopsWhenActivatedIfAlreadyPlaying() {
        GameSound sound = new GameSound();
        sound.downSoundActivate();
        sound.downSoundActivate();
        assertFalse(sound.getDownSound().isPlaying());
    }

    @Test
    public void testButtonClickedSoundStopsWhenActivatedIfAlreadyPlaying() {
        GameSound sound = new GameSound();
        sound.buttonClickedSoundActivate();
        sound.buttonClickedSoundActivate();
        assertFalse(sound.getButtonClickedSound().isPlaying());
    }

    @Test
    public void testEatingSoundStopsWhenActivatedIfAlreadyPlaying() {
        GameSound sound = new GameSound();
        sound.eatingSoundActivate();
        sound.eatingSoundActivate();
        assertFalse(sound.getEatingSound().isPlaying());
    }
}