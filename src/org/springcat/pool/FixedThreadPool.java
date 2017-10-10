package org.springcat.pool;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPool
{
    private Queue<Runnable> task = new LinkedBlockingQueue<Runnable>();
    
    public FixedThreadPool()
    {
        for (int i = 0; i < 10; i++)
        {
            FixedWorker woker = new FixedWorker(task);
            woker.start();
        }     
    }
    
    public void submit(Runnable r)
    {
        task.add(r);
    }
}

class FixedWorker extends Thread
{
    
    private Queue<Runnable> queue;
    
    public FixedWorker(Queue<Runnable> queue)
    {
        this.queue = queue;
    }
    
    public void run()
    {
        while (true)
        {
            try
            {
                Runnable r = this.queue.poll();
                if (r != null)
                {
                    r.run();
                }
            }
            catch (Exception e)
            {
                
            }
        }
    }
}
