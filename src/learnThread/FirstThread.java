package learnThread;

public class FirstThread extends Thread{
	private int i;
	public FirstThread(){}
	public FirstThread(String threadName){
		this.setName(threadName);
	}
	public FirstThread(Runnable ra,String threadName){
		super(ra,threadName);
		this.setName(threadName);
	}
	
	@Override
	public void run(){
			for(i=0;i<10;i++){
				System.out.println(getName() + " " +i);
			}
	}
	
	public static void main(String[] args){
		for(int i=0;i<100;i++){
			if(i==20){
				new FirstThread("线程1").start();
				new FirstThread("线程2").start();
				
			}
		}
	}

}
