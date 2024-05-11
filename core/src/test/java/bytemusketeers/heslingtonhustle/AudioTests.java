package bytemusketeers.heslingtonhustle;

import bytemusketeers.heslingtonhustle.sound.GameMusic;
import bytemusketeers.heslingtonhustle.sound.GameSound;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class AudioTests {
    @Test
    public void musicIncrEndsAtMax() {
        GameMusic music = new GameMusic(); //when initialised, the music val is max already
        int initialVolume = music.getMusicLevel();
        music.incrementVolume();
        int finalVolume = music.getMusicLevel();
        Assert.assertEquals(initialVolume, finalVolume);
    }

    @Test
    public void musicDecrEndsAtMin() {
        GameMusic music = new GameMusic();
        int initialVolume = music.getMusicLevel();
        for (int i = 0; i < 10; i++) {
            music.decrementVolume();
        }
        int finalVolume = music.getMusicLevel();
        Assert.assertNotEquals(initialVolume, finalVolume); //make sure that the volume has changed
        Assert.assertEquals(0, finalVolume);
    }

    @Test
    public void soundIncrEndsAtMax() {
        GameSound sound = new GameSound();
        int initialSoundLevel = sound.getSoundLevel();
        for (int i = 0; i < 10; i++) {
            sound.incrementVolume();
        }
        int finalSoundLevel = sound.getSoundLevel();
        Assert.assertEquals(initialSoundLevel, finalSoundLevel);
    }

    @Test
    public void soundDecrEndsAtMin() {
        GameSound sound = new GameSound();
        int initialSoundLevel = sound.getSoundLevel();
        for (int i = 0; i < 10; i++) {
            sound.decrementVolume();
        }
        int finalSoundLevel = sound.getSoundLevel();
        Assert.assertNotEquals(initialSoundLevel, finalSoundLevel);
        Assert.assertEquals(0, finalSoundLevel);
    }

    @Test
    public void upSoundToggle() {
        GameSound sound = new GameSound();
        sound.upSoundActivate();
        sound.upSoundActivate();
        Assert.assertFalse(sound.getUpSound().isPlaying());
    }

    @Test
    public void downSoundToggle() {
        GameSound sound = new GameSound();
        sound.downSoundActivate();
        sound.downSoundActivate();
        Assert.assertFalse(sound.getDownSound().isPlaying());
    }

    @Test
    public void buttonSoundToggle() {
        GameSound sound = new GameSound();
        sound.buttonClickedSoundActivate();
        sound.buttonClickedSoundActivate();
        Assert.assertFalse(sound.getButtonClickedSound().isPlaying());
    }

    @Test
    public void eatingSoundToggle() {
        GameSound sound = new GameSound();
        sound.eatingSoundActivate();
        sound.eatingSoundActivate();
        Assert.assertFalse(sound.getEatingSound().isPlaying());
    }
}