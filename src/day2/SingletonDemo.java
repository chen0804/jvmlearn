package day2;

public class SingletonDemo {
    //不要指令重拍
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + " 构造函数");
    }

    //DCL 双端检锁机制
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null)
                instance = new SingletonDemo();
            }

        }
        return instance;
    }

    public static void main(String[] args) {
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        System.out.println("====================");

        for(int i = 1; i <= 10; i++)
        {
            new Thread(()->{SingletonDemo.getInstance();},String.valueOf(i)).start();
        }
    }

}
