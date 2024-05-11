package bytemusketeers.heslingtonhustle;

import bytemusketeers.heslingtonhustle.utils.Score;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class ScoreTests {
    private Score score = new Score();

    @Test
    public void testScoreUpdate() {
        float currentScore = score.getScore();
        score.incrementStudy(3);
        score.incrementRec(2);
        score.markAsOverstudied();
        score.updateScore();
        float desiredScore = currentScore + (float) (15 * 2.5 * 1.2 * 0.75);
        assertEquals(desiredScore, score.getScore(), 0.0001);
    }

    @Test
    public void testFinalScore() {
        float currentScore = score.getScore();
        for (int i = 0; i < 3; i++) {
            score.incrementNoStudy();
        }
        score.decrementNoStudy();
        score.computeFinalScore(3, 5, 2);
        float desiredScore = (currentScore + (float) 5 * (3 + 5 + 2)) / 2;
        assertEquals(desiredScore, score.getScore(), 0.0001);
    }

    @Test
    public void testScoreReset() {
        float initialScore = score.getScore();
        score.incrementStudy(3);
        score.incrementRec(2);
        score.markAsOverstudied();
        score.updateScore();
        float firstUpdateScore = initialScore + (float) (15 * 2.5 * 1.2 * 0.75);
        score.resetMultipliers();
        score.updateScore();
        float desiredScore = firstUpdateScore + 15f;
        assertEquals(desiredScore, score.getScore(), 0.0001);
    }
}
