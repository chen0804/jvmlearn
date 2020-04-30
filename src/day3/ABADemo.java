package day3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {

        System.out.println("=======以下是ABA问题的产生=======");
        /*new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = atomicReference.compareAndSet(100, 2019);
            System.out.println(atomicReference.get() + " " + b);
            },"t2").start();*/



        System.out.println("=======以下是ABA问题的解决=======");
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号" + stamp);

            //保证t3暂停1秒
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,stamp,stamp + 1);
            System.out.println(Thread.currentThread().getName() + "第二次版本号" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,102,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号" + atomicStampedReference.getStamp());

        },"t3").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号" + stamp);

            //保证t3暂停1秒
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(b);
            System.out.println("当前实际版本号" + atomicStampedReference.getStamp());


        },"t4").start();


    }

}
