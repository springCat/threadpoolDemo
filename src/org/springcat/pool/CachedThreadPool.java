package org.springcat.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CachedThreadPool
{
    private SynchronousQueue<Runnable> queue = new SynchronousQueue<Runnable>();
    
    
    private AtomicInteger  num= new AtomicInteger(0);
    
    public CachedThreadPool(){
        for (int i = 0; i < 10; i++)
        {
           new Worker(queue,num).start();
        }
       
    }
    
    public void submit(Runnable r){
        try{
                queue.add(r);
        }
        catch(Exception e){
                new Worker(queue,num).start();
                submit(r);
        }
    }
    
    public void printNum(){
        System.out.println("worknum:"+num.get());
    }
    
}


class Worker  extends Thread{
    
    private SynchronousQueue<Runnable>  queue;
    public Worker(SynchronousQueue<Runnable>  queue,AtomicInteger i ){
        this.queue = queue;
        i.incrementAndGet();
    }
    
    public void run(){
        while(true){
        try
        {
            Runnable r = queue.take();
            if (r != null){
                r.run();
            }
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
    }
}
