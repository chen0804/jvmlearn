package day4;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo {

    public static void main(String[] args) {
         //List<String> list = new ArrayList<>();
         //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for(int i = 1; i <= 30; i++)
        {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + list);
            },String.valueOf(i)).start();
        }
        //Exception in thread "10" java.util.ConcurrentModificationException

    }

}
