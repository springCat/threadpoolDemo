package org.springcat.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest
{
    public static void main(String[] args) throws InterruptedException
    {
        //FixedThreadPool pool = new FixedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        long start =System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
        {
            final int j = i+1;
            pool.submit(new Runnable(){
                
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    if(j==1000){
                        long end =System.currentTimeMillis();
                        System.out.println("my FixedThreadPool cost:"+(end - start));
                    }
                }
                
            });
        }
 
    }
}
