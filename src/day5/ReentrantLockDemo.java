package day5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;



class Phone{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        //
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getId() + "\t ****invoked sendEmail()");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendSMS();
    }


}

public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();

        },"t1").start();

        new Thread(()->{
            phone.sendEmail();
        },"t2").start();
    }

}
