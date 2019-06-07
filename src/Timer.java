import java.util.concurrent.TimeUnit;

public class Timer {
    private double startTime;
    private double endTime;

    public Timer() {
        startTime = 0;
        endTime = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    /**
     * Set end time to difference between current time and start time
     */
    public void setEndTime() {
        endTime = System.nanoTime() - startTime;
    }

    /**
     * Get time elapsed between start time and end time
     * @return time elapsed in milliseconds
     */
    public long getTimeElapsed() {
        setEndTime();
        return TimeUnit.NANOSECONDS.toMillis((long) endTime);
    }

    /**
     * Reset timer to take new measurements
     * Must be called any time new measurements are needed
     */
    public void resetTimer() {
        this.startTime = 0;
        this.endTime = 0;
    }

    /**
     * Block for a specified amount of time
     * @param waitTime amount of time to wait in milliseconds
     */
    public void waitGivenTime(int waitTime) {
        while (getTimeElapsed() < waitTime) {
            getTimeElapsed();
        }
    }

    public static void main(String[] args) {
        Timer t1 = new Timer();
        t1.startTimer();
        t1.waitGivenTime(3500);
        System.out.println("5 seconds passed");
    }
}
