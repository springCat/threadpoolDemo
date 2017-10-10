package org.springcat.pool;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;




class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new  UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("caught " + e.getMessage());
            }
        });
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors
                .newCachedThreadPool(new HandlerThreadFactory());
        
        exec.execute(new Runnable (){
            public void run() {
                throw new RuntimeException();
            }
        });
    }
}