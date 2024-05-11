package bytemusketeers.heslingtonhustle;

import bytemusketeers.heslingtonhustle.utils.Achievement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class AchievementTests {
    private Achievement eatAch;
    private Achievement recAch;
    private Achievement sleepAch;

    @Before
    public void setUp() {
        eatAch = new Achievement("Eater");
        recAch = new Achievement("Recreator");
        sleepAch = new Achievement("Sleeper");
    }

    @Test
    public void testAchievementInitialisation() {
        assertEquals("Eater", eatAch.getAnnotatedName());
        assertEquals("Recreator", recAch.getAnnotatedName());
        assertEquals("Sleeper", sleepAch.getAnnotatedName());
    }

    @Test
    public void testModifiedName() {
        String initialEatAchName = eatAch.getAnnotatedName();
        String initialRecAchName = recAch.getAnnotatedName();
        String initialSleepAchName = sleepAch.getAnnotatedName();
        for (int i = 0; i < 3; i++) {
            eatAch.incrementStreak();
            recAch.incrementStreak();
            sleepAch.incrementStreak();
        }

        assertEquals("Novice " + initialEatAchName, eatAch.getAnnotatedName());
        assertEquals("Novice " + initialRecAchName, recAch.getAnnotatedName());
        assertEquals("Novice " + initialSleepAchName, sleepAch.getAnnotatedName());

        eatAch.incrementStreak();
        recAch.incrementStreak();
        sleepAch.incrementStreak();

        assertEquals("Intermediate " + initialEatAchName, eatAch.getAnnotatedName());
        assertEquals("Intermediate " + initialRecAchName, recAch.getAnnotatedName());
        assertEquals("Intermediate " + initialSleepAchName, sleepAch.getAnnotatedName());

        for (int i = 0; i < 2; i++) {
            eatAch.incrementStreak();
            recAch.incrementStreak();
            sleepAch.incrementStreak();
        }

        assertEquals("Master " + initialEatAchName, eatAch.getAnnotatedName());
        assertEquals("Master " + initialRecAchName, recAch.getAnnotatedName());
        assertEquals("Master " + initialSleepAchName, sleepAch.getAnnotatedName());
    }
}
