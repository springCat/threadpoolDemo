package org.springcat.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronousQueueTest
{
    public static void main(String[] args)
    {
        SynchronousQueue<String> q = new SynchronousQueue<String>();
        AtomicInteger a = new AtomicInteger(0);
        for (int i = 0; i < 20; i++)
        {
            System.out.println("custumer start");
            Runnable r = new Runnable()
            {
                
                @Override
                public void run()
                {
                    try
                    {
                        String  i = q.take();
                        if (i != null)
                        {
                            System.out.println("end custumer get " + i);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                  
                }
                
            };
            Thread t = new Thread(r);
            t.start();
        }
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println("product start");
            Runnable r = new Runnable()
            {
                
                @Override
                public void run()
                {
                    int i = a.incrementAndGet();
                   q.add(String.valueOf(i));
                    System.out.println("end product");
                   
                }
                
            };
            Thread t = new Thread(r);
            t.start();
        }
        
    
        
    
        
    }
}
