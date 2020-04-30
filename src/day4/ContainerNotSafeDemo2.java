package day4;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo2 {
    public static void main(String[] args) {
        /*Set<String> list = new HashSet<>();
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        for(int i = 1; i <= 30; i++)
        {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + list);
            },String.valueOf(i)).start();
        }*/

        //HashMap<String, String> map = new HashMap<>();
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        //ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        for(int i = 1; i <= 30; i++)
        {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    map.put(UUID.randomUUID().toString().substring(0,8),String.valueOf(j));

                }
                System.out.println(map);

            },String.valueOf(i)).start();
        }
    }

}
