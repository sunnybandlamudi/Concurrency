import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceImpl {


//    Fixed threads
    public static void executeService(){

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for(int i = 0; i < 10; i++){
            executorService.execute(new Thread(()->{
                System.out.println("Inside Thread "+Thread.currentThread().getName());
            }));

        }

        System.out.println("Main Function");
    }

    //    cache threads
    public static void cacheThread(){

        ExecutorService executorService = Executors.newCachedThreadPool();


        for(int i = 0; i < 100; i++){
            executorService.execute(new Thread(()->{
                //If we add this 100 Threads will be created
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//
//                }
                System.out.println("Inside Thread "+Thread.currentThread().getName());
            }));

        }

        System.out.println("Main Function");
    }

    public static void scheduleThread(){

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);


        // Call only 1 time
            executorService.schedule(new Thread(()->{
                System.out.println("Inside Thread "+Thread.currentThread().getName());
            }), 1, TimeUnit.SECONDS);

            // Calls repetedly after 10
//            executorService.scheduleAtFixedRate(new Thread(()->{
//                System.out.println("Inside Thread "+Thread.currentThread().getName());
//            }), 0,10, TimeUnit.SECONDS);

            // calls repetedly Initial delay 1 sec after executing task wait for 1 sec.
            executorService.scheduleWithFixedDelay(new Thread(()->{
                System.out.println("Inside Thread "+Thread.currentThread().getName());
            }),1,1,TimeUnit.SECONDS);
    }

    // Only one thead is used to execute all the tasks.
    public static void singleThread(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();


        for(int i = 0; i < 10; i++){
            executorService.execute(new Thread(()->{
                System.out.println("Inside Thread "+Thread.currentThread().getName());
            }));

        }

    }

    public static void futureGet() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> submit = executorService.submit(() -> {

            System.out.println("In Task");
            return 10;
        });

        System.out.println("Before Time");

//        Thread.sleep(3000);

        System.out.println("After Time");
        // If the task is executed it will return value, if not it will act as blocking operation.
        Integer integer = submit.get();
        System.out.println("Integer "+integer);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        executeService();

//        cacheThread();

//        scheduleThread();

//        singleThread();

        futureGet();
    }

}
