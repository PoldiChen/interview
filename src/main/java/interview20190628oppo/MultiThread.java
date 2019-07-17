package interview20190628oppo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author poldi.chen
 * @className MultiThread
 * @description TODO
 * @date 2019/6/28 16:14
 **/
public class MultiThread {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    private static Object lock3 = new Object();
    private static Object lock4 = new Object();
    private static Object lock5 = new Object();


    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);

        service.submit(new SubThread(lock1, "thead" + 1));
        service.submit(new SubThread(lock2, "thead" + 2));
        service.submit(new SubThread(lock3, "thead" + 3));
        service.submit(new SubThread(lock4, "thead" + 4));
        service.submit(new SubThread(lock5, "thead" + 5));

        service.shutdown();

        synchronized (lock1) {
            synchronized (lock2) {
                synchronized (lock3) {
                    synchronized (lock4) {
                        synchronized (lock5) {
                            try {
                                lock1.wait();
                                lock2.wait();
                                lock3.wait();
                                lock4.wait();
                                lock5.wait();
                                System.out.println("main thread running.");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        }


    }

}

class SubThread implements Runnable {

    private Object lock;
    private String threadName;

    public SubThread(Object lock, String threadName) {
        this.lock = lock;
        this.threadName = threadName;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            System.out.println(this.threadName + " running.");
            lock.notify();
        }
    }
}