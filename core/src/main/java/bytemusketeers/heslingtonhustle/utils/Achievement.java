package bytemusketeers.heslingtonhustle.utils;

/**
 * The {@link Achievement} class provides a framework for tracking in-game activities performed consecutively,
 * henceforth referred to as 'streaks' with an associated (human-readable) name.
 *
 * @author ENG1 Team 23
 */
public class Achievement {
    /**
     * The current streak quantifier
     */
    private int streak = 0;

    /**
     * The human-readable name of the streak unifier
     */
    private final String name;

    /**
     * Creates a new {@link Achievement} with the given name and an initial streak of zero
     *
     * @param name The human-readable name of the {@link Achievement}
     */
    public Achievement(String name) {
        this.name = name;
    }

    /**
     * Increment the streak by a single point
     */
    public void incrementStreak() {
        streak++;
    }

    /**
     * Retrieves the streak of the current {@link Achievement}
     *
     * @return The current streak count
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Retrieves the name of the current {@link Achievement} tracker
     *
     * @return The name of the {@link Achievement}
     */
    public String getAchievementName() {
        return name;
    }
}
