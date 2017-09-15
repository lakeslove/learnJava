package learnThread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
	public static void main(String[] args){
		Runnable run = () -> {
			 System.out.println(Thread.currentThread().getName());
		 };
		 
		
	}

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public void ThreadLocalExecutorsTest(Runnable run){
		 ExecutorService pool =  new ThreadPoolExecutor(0, Integer.MAX_VALUE,  
                 60L, TimeUnit.SECONDS,  
                 new LinkedBlockingQueue<Runnable>(10));  
		 
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
	}
	
	public void ExecutorsTest(Runnable run){
		 ExecutorService pool = Executors.newFixedThreadPool(4);
		 
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
		 pool.submit(run);
	}

}
