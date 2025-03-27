package com.future.csamsserver.utils;

/**
 * @description
 * @dateTime 2025:03:11 18:57
 * @user Jenming
 */
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressBarUtil {
//    private static final String DEFAULT_CHAR = "█";
    private static final String DEFAULT_CHAR = "=";
    private static final int DEFAULT_LENGTH = 30;
    private static final int DEFAULT_INTERVAL = 200;

    public static ProgressController start() {
        return start(100, DEFAULT_LENGTH, DEFAULT_INTERVAL);
    }

    public static ProgressController start(int total) {
        return start(total, DEFAULT_LENGTH, DEFAULT_INTERVAL);
    }

    public static ProgressController start(int total, int length) {
        return start(total, length, DEFAULT_INTERVAL);
    }

    public static ProgressController start(int total, int length, int interval) {
        ProgressController controller = new ProgressController(total, length, interval);
        controller.start();
        return controller;
    }

    public static class ProgressController {
        private final AtomicInteger current = new AtomicInteger(0);
        private final AtomicBoolean isPaused = new AtomicBoolean(false);
        private final AtomicBoolean isRunning = new AtomicBoolean(true);
        private final int total;
        private final int length;
        private final int interval;
        private Thread updateThread;

        public ProgressController(int total, int length, int interval) {
            this.total = total;
            this.length = length;
            this.interval = interval;
        }

        public void start() {
            updateThread = new Thread(() -> {
                try {
                    while (isRunning.get() && current.get() <= total) {
                        if (!isPaused.get()) {
                            printProgress(current.get());
                            current.incrementAndGet();
                        }
                        Thread.sleep(interval);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            updateThread.start();
        }

        public void pause() {
            isPaused.set(true);
        }

        public void resume() {
            isPaused.set(false);
        }

        public void terminate() {
            isRunning.set(false);
            updateThread.interrupt();
        }

        public void restart() {
            current.set(0);
            isPaused.set(false);
            isRunning.set(true);
            start();
        }

        private void printProgress(int current) {
            double percent = (current * 100.0) / total;
            int filledLength = (int) (length * current / total);

            String bar = String.format("[%s%s] %.2f%%",
                    DEFAULT_CHAR.repeat(filledLength),
                    " ".repeat(length - filledLength),
                    percent);

            System.out.print("\r" + bar);
            if (current >= total) System.out.println();
        }
    }
}
