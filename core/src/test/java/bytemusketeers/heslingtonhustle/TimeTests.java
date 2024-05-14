package bytemusketeers.heslingtonhustle;

import bytemusketeers.heslingtonhustle.utils.GameTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class TimeTests {
    private GameTime gameTime;

    @Before
    public void setUp() {
        gameTime = new GameTime(0f, 60f / 16, 10);
    }

    @Test
    public void testUpdateGameTimeContinuous() {
        gameTime.isUpdateGameTime(36.12331f);
        assertEquals(gameTime.currentHour, 17, 0.0001);
    }

    @Test
    public void testUpdateGameTimeActivity() {
        gameTime.updateGameTimeActivity(4);
        gameTime.isUpdateGameTime(0);
        assertEquals(gameTime.currentHour, 12, 0.0001);
    }

    @Test
    public void testResetGameTime() {
        gameTime.isUpdateGameTime(36.12331f);
        assertEquals(gameTime.currentHour, 17, 0.0001);
        gameTime.resetGameTime();
        assertEquals(gameTime.currentHour, 8, 0.0001);
        assertEquals(gameTime.timeElapsed, 0, 0.0001);
    }
}
