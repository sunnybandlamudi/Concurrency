import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReadWriteLockImpl {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    static void read(){

        readLock.lock();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Reading " + Thread.currentThread().getName());

        readLock.unlock();
    }

    static void write(){

        writeLock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Writing " + Thread.currentThread().getName());

        writeLock.unlock();
    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(4);

        for(int i : IntStream.range(1,10).toArray()){
            service.submit(()->{
                read();
                write();
            });
        }
    }


}
