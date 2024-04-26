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

    public Score() {
        this.points = 0f;
        this.studyMult = 1f;
        this.recMult = 1f;
        this.missedStudy = 0;
    }

    public void AddScore() {
        points += 20 * studyMult * recMult;
    }

    public void CalculateFinal(int achOne, int achTwo, int achThree) {
        points += (achOne * 20) + (achTwo * 20) + (achThree * 20);
    }

    public void incrementStudy(int studyHours) {
        studyMult += .5f * studyHours;
    }

    public void incrementRec() {
        recMult += .5f;
    }

    public void incrementMissed() {
        missedStudy++;
    }

    public int ReadMissed() { return missedStudy; }

    public float ReadScore() { return points; }
}