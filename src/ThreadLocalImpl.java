class S{
    String s = "Default";
    String get(){
        return s;
    }
    void set(String s){
        this.s = s;
    }
}

public class ThreadLocalImpl {

    static ThreadLocal<String> s = new ThreadLocal<>(){
        @Override
        protected String initialValue() {
            return "Default";
        }
    };

//    static S s = new S();
}



class S1 extends Thread {

    @Override
    public void run() {
        print();
        System.out.println(ThreadLocalImpl.s);
        ThreadLocalImpl.s.set("S1");
        print();
    }

    void print(){
        System.out.println("S1 ->" + ThreadLocalImpl.s.get());
    }
}

class S2 extends Thread{
    @Override
    public void run() {
        print();
        System.out.println(ThreadLocalImpl.s);

        ThreadLocalImpl.s.set("S2");
        print();
    }

    void print(){
        System.out.println("S2 ->"+ThreadLocalImpl.s.get());
    }
}

class M{
    public static void main(String[] args) throws InterruptedException {

        S1 s1 = new S1();
        S2 s2 = new S2();

        s1.start();
        s2.start();


        System.out.println("Main "+ThreadLocalImpl.s.get());

    }
}



class Run implements Runnable{

    ThreadLocal <String> s = new ThreadLocal<>(){
        @Override
        protected String initialValue() {
            return "Default";
        }
    };
//    S s = new S();

    Run(String s){
    }
    @Override
    public void run() {
        System.out.println(s.get());
        this.s.set(Thread.currentThread().getName());
        System.out.println(s.get());
    }
}

class M2{
    public static void main(String[] args) throws InterruptedException {
        Run r = new Run("Run");
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}