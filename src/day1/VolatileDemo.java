package day1;

import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;
    public void addT060(){
        this.number = 60;
    }

    public  void addPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1验证volatile可见性
 * 2验证volatile不保证原子性
 */

public class VolatileDemo {

    public static void main(String[] args) {
        atomic();
        //seeOkVolatile();
        atomicAdd();
        Socket socket = new Socket();
        


    }

    private static void atomicAdd() {
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i++)
        {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        //用main线程取得最终结果值
        //try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + myData.atomicInteger);
    }

    private static void atomic() {
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i++)
        {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                }
            },String.valueOf(i)).start();
        }

        //用main线程取得最终结果值
        //try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + myData.number);
    }

    private static void seeOkVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(myData.number);

        },"AAA").start();

        while (myData.number == 0){
            //number未加volatile main线程不知道，一直在循环
        }

        System.out.println(Thread.currentThread().getName() + " " + myData.number);
    }


}
