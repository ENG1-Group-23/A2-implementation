package bytemusketeers.heslingtonhustle.utils;

public class Achievement {
    private int streak;
    String achName;

    public Achievement(String achName) {
        this.achName = achName;
        this.streak = 0;
    }

    public void IncrementStreak() {
        streak++;
    }

    public int ReadStreak() { return streak; }

    public String ReadName() { return achName; }
}