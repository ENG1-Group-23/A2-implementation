package bytemusketeers.heslingtonhustle.utils;

public class Score {
    private float points;
    private float studyMult, recMult;
    /*
    missedStudy:
    0 = No Days Missed
    1 = Missed a Single Day
    2 = Redeemed Missed Day
     */
    private int missedStudy;

    // One day buffer before penalty applied
    private int totalStudiesMissed;

    public Score() {
        this.points = 0f;
        this.studyMult = 1f;
        this.recMult = 1f;
        this.missedStudy = 0;
        this.totalStudiesMissed = 0;
    }

    public void AddScore() {
        points += 15 * studyMult * recMult;
    }

    public void CalculateFinal(int achOne, int achTwo, int achThree) {
        points += (achOne * 5) + (achTwo * 5) + (achThree * 5);
        points /= totalStudiesMissed;
    }

    public void incrementStudy(int studyHours) {
        studyMult += .5f * studyHours;
    }

    public void incrementRec(int recHours) {
        recMult += .1f * recHours;
    }

    public void incrementMissed() {
        missedStudy++;
    }
    public void incrementNoStudy() { totalStudiesMissed++; }
    public void decrementNoStudy() { totalStudiesMissed--; }

    public int ReadMissed() { return missedStudy; }

    public float ReadScore() { return points; }
}