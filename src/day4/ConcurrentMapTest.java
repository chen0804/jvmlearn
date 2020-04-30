package day4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {



    public static void main(String[] args) {
        Map<Integer, Integer> map1 = new ConcurrentHashMap<>();
        Map<Integer, Integer> map2 = Collections.synchronizedMap(new HashMap<>());

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (Integer j = 0; j < 1000; j++) {
                    map2.put(j, j);
                }
            }, String.valueOf(i)).start();
        }
        long endtime = 0;
        while (map2.size() == 10000) {
            endtime = System.currentTimeMillis();
        }
        System.out.println(startTime-endtime);//map1 1588057372354   map2 1588057436745
    }

}
