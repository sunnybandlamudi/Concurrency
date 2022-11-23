import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockImpl {

    static ReentrantLock lock = new ReentrantLock();


    public static void task() throws InterruptedException {

        lock.lock();
        System.out.println("Is lock available "+lock.tryLock());
        System.out.println("Started Thread name "+Thread.currentThread().getName());

        Thread.sleep(5000);

        System.out.println("Ended Thread name "+Thread.currentThread().getName());
        lock.unlock();
    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i : IntStream.range(1,10).toArray()){

            service.submit(()->{
                try {
                    task();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
