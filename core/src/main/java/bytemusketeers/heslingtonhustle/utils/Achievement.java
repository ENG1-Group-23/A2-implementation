package bytemusketeers.heslingtonhustle.utils;

/**
 * The {@link Achievement} class provides a framework for tracking in-game activities performed consecutively,
 * henceforth referred to as 'streaks' with an associated (human-readable) name.
 *
 * @author ENG1 Team 23
 */
public class Achievement {
    /**
     * The minimum streak attained to warrant the {@link Achievement} being annotated
     *
     * @see #getAnnotatedName()
     */
    private static final int MENTION_THRESHOLD = 2;
    /**
     * The human-readable name of the streak unifier
     */
    private final String name;
    /**
     * The current streak quantifier
     */
    private int streak = 0;

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
     * Determines whether the streak has met the {@link #MENTION_THRESHOLD}
     *
     * @return Has the streak met the {@link #MENTION_THRESHOLD}?
     */
    public boolean hasMetThreshold() {
        return streak >= MENTION_THRESHOLD;
    }

    /**
     * Retrieves the name of the current {@link Achievement} tracker
     *
     * @return The name of the {@link Achievement}
     */
    public String getAnnotatedName() {
        if (streak >= MENTION_THRESHOLD) {
            if (streak >= 6)
                return "Master " + name;
            else if (streak >= 4)
                return "Intermediate " + name;

            return "Novice " + name;
        }

        return name;
    }
}
