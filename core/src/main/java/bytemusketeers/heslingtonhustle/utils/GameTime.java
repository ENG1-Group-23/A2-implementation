package bytemusketeers.heslingtonhustle.utils;

public class GameTime {
    public float timeElapsed;
    public final float secondsPerGameHour;
    public int currentHour;

    public GameTime(float timeElapsed, float secondsPerGameHour, int currentHour) {
        this.timeElapsed = timeElapsed;
        this.secondsPerGameHour = secondsPerGameHour;
        this.currentHour = currentHour;
    }

    public boolean isUpdateGameTime(float delta) {
        this.timeElapsed += delta;

        // Calculate the current hour in game time
        int hoursPassed = (int) (this.timeElapsed / this.secondsPerGameHour);
        this.currentHour = 8 + hoursPassed; // Starts at 08:00 AM

        return this.currentHour >= 24;
    }

    public void updateGameTimeActivity(int duration) {
        this.timeElapsed += duration * this.secondsPerGameHour;
    }

    public void resetGameTime() {
        this.currentHour = 8;
        this.timeElapsed = 0;
    }
}
