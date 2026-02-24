package uk.nhsbsa.jobs.config;

public final class PerformanceContext {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    private PerformanceContext() {}

    public static void startTimer() {
        START_TIME.set(System.nanoTime());
    }

    public static long getDurationMillis() {
        long end = System.nanoTime();
        return (end - START_TIME.get()) / 1_000_000;
    }

    public static void clear() {
        START_TIME.remove();
    }
}