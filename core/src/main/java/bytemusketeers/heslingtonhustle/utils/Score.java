package bytemusketeers.heslingtonhustle.utils;

/**
 * The {@link Score} persists and provides an interface to manage the score of a user-controlled
 * {@link bytemusketeers.heslingtonhustle.entity.Player} throughout the duration of a {@link com.badlogic.gdx.Game}
 * instance.
 *
 * @author ENG1 Team 23
 */
public class Score {
    /**
     * The current score
     */
    private float score = 0f;

    /**
     * The multiplier by which future study sessions should be awarded
     */
    private float studyMultiplier = 1f;

    /**
     * The multiplier by which future recreational sessions should be awarded
     */
    private float recreationalMultiplier = 1f;

    /**
     * The tracker of missed study sessions, encoded in the following schema:
     * <ul>
     *     <li>0: No days missed;</li>
     *     <li>1: Missed a single day; or</li>
     *     <li>2: Redeemed a missed day.</li>
     * </ul>
     *
     * @see #totalStudiesMissed
     */
    private int missedStudySessions = 0;

    /**
     * The number of days elapsed without a constituent study session
     *
     * @see #missedStudySessions
     */
    private int totalStudiesMissed = 0;

    /**
     * Updates the {@link #score} according to the current multipliers
     *
     * @see #studyMultiplier
     * @see #recreationalMultiplier
     */
    public void updateScore() {
        score += 15 * studyMultiplier * recreationalMultiplier;
    }

    /**
     * Computes the final score, according to the given state of achievements earned throughout the game.
     *
     * @param achOne 'Category 1' achievement count
     * @param achTwo 'Category 2' achievement count
     * @param achThree 'Category 3' achievement count
     */
    public void computeFinalScore(int achOne, int achTwo, int achThree) {
        score += 5 * (achOne + achTwo + achThree);
        score /= totalStudiesMissed;
    }

    /**
     * Update the multiplier by which future study sessions should be rewarded
     *
     * @param studyHours The number of hours spent studying
     */
    public void incrementStudy(int studyHours) {
        studyMultiplier += .5f * studyHours;
    }

    /**
     * Update the multiplier by which future recreational sessions should be rewarded
     *
     * @param recHours The number of hours spend recreating
     */
    public void incrementRec(int recHours) {
        recreationalMultiplier += .1f * recHours;
    }

    /**
     * Increment the tracker of missed study sessions, according to its defined encoding schema
     *
     * @see #missedStudySessions
     */
    public void incrementMissed() {
        missedStudySessions++;
    }

    /**
     * Increment the number of days spent without studying
     */
    public void incrementNoStudy() {
        totalStudiesMissed++;
    }

    /**
     * Decrement the number of days without studying, in the case of a 'redeemed' day
     *
     * @see #missedStudySessions
     */
    public void decrementNoStudy() {
        totalStudiesMissed--;
    }

    /**
     * Retrieve the number of days spent without studying
     *
     * @return The number of days spent without studying
     */
    public int getMissedStudySessions() {
        return missedStudySessions;
    }

    /**
     * Retrieves the final score
     *
     * @return The final computed score
     */
    public float getScore() {
        return score;
    }

    /**
     * Reset the study and recreational multipliers to their initial state
     */
    public void resetMultipliers() {
        studyMultiplier = 1f;
        recreationalMultiplier = 1f;
    }
}
