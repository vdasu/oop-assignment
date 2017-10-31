package ops.multithreaded;

public class ThreadOps {
    public static void waitForThreads(Thread threads[]) {

        for (Thread thread : threads)
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public static void startAllThreads(Thread threads[]) {
        for (Thread thread : threads)
            thread.start();
    }
}
