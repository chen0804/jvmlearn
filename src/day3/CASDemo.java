package day3;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(5, 10);
        System.out.println(b);
        System.out.println(atomicInteger.compareAndSet(5,100) +" " +  atomicInteger.get());

        atomicInteger.getAndIncrement();
    }

}
