package org.springcat.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest
{
    public static void main(String[] args)
    {
        CachedThreadPool pool = new CachedThreadPool();
       // ExecutorService pool = Executors.newCachedThreadPool();
        long start =System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
        {
            final int j = i+1;
            pool.submit(new Runnable(){
                
                @Override
                public void run()
                {
                    // TODO Auto-generated method stub
                    //System.out.println(j);
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
       pool.printNum();
    
    }
}
