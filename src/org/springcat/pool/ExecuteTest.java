package org.springcat.pool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecuteTest
{
    
    private static Map<Thread, Integer> m = new ConcurrentHashMap<Thread, Integer>();
    
    public static void main(String[] args)
    {
        
        ThreadFactory threadFactory = new ThreadFactory()
        {
            public Thread newThread(Runnable r)
            {
                Thread t = new Thread(r);
                m.put(t, 0);
                return t;
            };
        };
        ExecutorService service = Executors.newFixedThreadPool(10, threadFactory);
        for (int i = 0; i < 10000; i++)
        {
            service.execute(new Runnable()
            {            
                @Override
                public void run()
                {
                    
                    Thread t = Thread.currentThread();
                    int n = m.get(t);
                    n++;
                    m.put(t, n);
                    // System.out.println(t.getName());
                }    
            });
        }
        
        System.out.println(m.entrySet().stream().mapToInt(k -> k.getValue()).sum());     
        
        List<String> list = Arrays.asList("a", "b,3,4A,6,", "7,8,9");
       // List<String> lists = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> lists =list.stream().flatMap( e ->Stream.of(e.split(","))).collect(Collectors.toList());
        System.out.println(lists);   
    }
    
}
