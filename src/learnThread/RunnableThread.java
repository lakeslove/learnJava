package learnThread;

public class RunnableThread implements Runnable{
	private ThreadLocal<Integer> number2 = new ThreadLocal<Integer>();
	
	{
		number2.set(2);
	}
	int number;
	@Override
	public void run(){
		number2.set(2);
		System.out.println(number2.get());
		for (;number2.get()<=5; number2.set(number2.get()+1)){
			System.out.println(Thread.currentThread().getName()+":"+number2.get());
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		RunnableThread st = new RunnableThread();
		for (int i = 0;i<3;i++){
			new Thread(st,"线程"+i).start();
			Thread.sleep(1);
			
//			new Thread(()->System.out.println(Thread.currentThread().getName()),"test").start();
		}
	}
}

class Count{
	static Integer num = 0;
	static Integer sum = 0;
	public static void add(){
		synchronized (sum){
			num ++;
			sum = sum + num;
		}
	}
}
