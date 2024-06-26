package bytemusketeers.heslingtonhustle;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {
    //can this be made neater?
    private boolean energyAssets() {
        for (int i = 0; i < 10; i++) {
            if (!Gdx.files.internal("energy/energy_" + i + ".png").exists()) {
                return false;
            }
        }
        return true;
    }

    private boolean volumeBarAssets() {
        for (int i = 0; i < 5; i++) {
            if (!Gdx.files.internal("settings_gui/bar_" + 25 * i + ".png").exists()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void tilemapAssetsExist() {
        //Maps
        assertTrue("The main map tilemap asset doesn't exist", Gdx.files.internal("map/MainMap.tmx").exists());
    }

    @Test
    public void fontAssetsExist() {
        //Fonts
        assertTrue("The main font asset doesn't exist", Gdx.files.internal("font/WhitePeaberry.fnt").exists());
    }

    @Test
    public void soundAssetsExist() {
        //Sounds
        assertTrue("The BGM asset doesn't exist", Gdx.files.internal("music_loop/Ludum Dare 30 - 01.ogg").exists());
        assertTrue("Volume up sound asset doesn't exist", Gdx.files.internal("sfx/high_note.mp3").exists());
        assertTrue("Volume down sound asset doesn't exist", Gdx.files.internal("sfx/low_note.mp3").exists());
        assertTrue("Button press sound asset doesn't exist", Gdx.files.internal("sfx/button_press.mp3").exists());
        assertTrue("Eating sound asset doesn't exist", Gdx.files.internal("sfx/eating_sound.wav").exists()); //why is this a wav
    }

    @Test
    public void controlsScreenTexturesExist() {
        //Controls screen
        assertTrue("Back button(Controls) asset doesn't exist", Gdx.files.internal("settings_gui/back_button.png").exists());
        assertTrue("Labels for controls asset doesn't exist", Gdx.files.internal("controls_gui/controls_label.png").exists());
        assertTrue("Controls asset doesn't exist", Gdx.files.internal("controls_gui/controls.png").exists());
    }

    @Test
    public void menuScreenTexturesExist() {
        //Menu screen
        assertTrue("HeslingtonHustle label asset doesn't exist", Gdx.files.internal("menu_gui/heslington_hustle_label.png").exists());
        assertTrue("Play button asset doesn't exist", Gdx.files.internal("menu_gui/play_button.png").exists());
        assertTrue("Controls button asset doesn't exist", Gdx.files.internal("menu_gui/controls_button.png").exists());
        assertTrue("Settings button asset doesn't exist", Gdx.files.internal("menu_gui/settings_button.png").exists());
        assertTrue("Exit button asset doesn't exist", Gdx.files.internal("menu_gui/exit_button.png").exists());
    }

    @Test
    public void gameScreenTexturesExist() {
        //Game screen
        assertTrue("Menu button asset doesn't exist", Gdx.files.internal("menu_buttons/menu_icon.png").exists());
        assertTrue("Stats background asset doesn't exist", Gdx.files.internal("counter_background.png").exists());
        assertTrue("Popup when near activity asset doesn't exist", Gdx.files.internal("popup_menu.png").exists());
        assertTrue("Popup after clicking on activity asset doesn't exist", Gdx.files.internal("duration_menu_background.png").exists());
        assertTrue("Increase duration asset doesn't exist", Gdx.files.internal("settings_gui/arrow_right_button.png").exists());
        assertTrue("Decrease duration asset doesn't exist", Gdx.files.internal("settings_gui/arrow_left_button.png").exists());
        assertTrue("Back out of activity asset doesn't exist", Gdx.files.internal("settings_gui/back_button.png").exists());
        assertTrue("Study button asset doesn't exist", Gdx.files.internal("study_button.png").exists());
        assertTrue("Study button asset doesn't exist", Gdx.files.internal("study_button.png").exists());
        assertTrue("Sleep button asset doesn't exist", Gdx.files.internal("sleep_button.png").exists());
        assertTrue("Exercise go button asset doesn't exist", Gdx.files.internal("go_button.png").exists());
        assertTrue("Energy assets doesn't exist", energyAssets());
    }

    @Test
    public void minigameTexturesExist() {
        //Minigame
        assertTrue("Guess button asset doesn't exist", Gdx.files.internal("mini_games/guess_button.png").exists());
        assertTrue("Memoriser label asset doesn't exist", Gdx.files.internal("mini_games/number_memoriser_label.png").exists());
        // NEED TO ADD DUCK HUN- FEEDING MINIGAME HERE
    }

    @Test
    public void endScreenTexturesExist() {
        //EndScreen
        assertTrue("Play again button asset doesn't exist", Gdx.files.internal("end_gui/play_button.png").exists());
        assertTrue("Exit button asset doesn't exist", Gdx.files.internal("end_gui/exit_button.png").exists());
    }
    @Test
    public void settingsScreenTexturesExist() {
        //Settings
        //back button already tested
        assertTrue("Back button(Settings) asset doesn't exist", Gdx.files.internal("settings_gui/back_button.png").exists());
        assertTrue("Settings label asset doesn't exist", Gdx.files.internal("settings_gui/settings_label.png").exists());
        //music up already tested
        assertTrue("Music/sound up asset doesn't exist", Gdx.files.internal("settings_gui/arrow_right_button.png").exists());
        //music down already tested
        assertTrue("Music/sound down asset doesn't exist", Gdx.files.internal("settings_gui/arrow_left_button.png").exists());
        assertTrue("Music label asset doesn't exist", Gdx.files.internal("settings_gui/music_label.png").exists());
        assertTrue("Sound label asset doesn't exist", Gdx.files.internal("settings_gui/sound_label.png").exists());
        assertTrue("Music/sound bar assets doesn't exist", volumeBarAssets());
        assertTrue("Boy selection assets doesn't exist", Gdx.files.internal("settings_gui/boy_button.png").exists() & Gdx.files.internal("settings_gui/boy_button_indented.png").exists());
        assertTrue("Girl selection assets doesn't exist", Gdx.files.internal("settings_gui/girl_button.png").exists() & Gdx.files.internal("settings_gui/girl_button_indented.png").exists());
    }
    @Test
    public void playerTexturesExist() {
        //Player
        assertTrue("Boy idle asset doesn't exist", Gdx.files.internal("character/boy_idle.png").exists());
        assertTrue("Boy walking asset doesn't exist", Gdx.files.internal("character/boy_walk.png").exists());
        assertTrue("Girl idle asset doesn't exist", Gdx.files.internal("character/girl_idle.png").exists());
        assertTrue("Girl walking asset doesn't exist", Gdx.files.internal("character/girl_walk.png").exists());
    }
}
