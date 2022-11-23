import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {
    static int i = 0;
    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();

        for(int j = 0 ;j < 10;j++) {
            new Thread(() -> {
//                i++;
                System.out.println(ai.incrementAndGet());
            }).start();
        }
    }
}
