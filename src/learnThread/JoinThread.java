package learnThread;

public class JoinThread extends Thread{
	public JoinThread(String name){
		super(name);
	}
	
	@Override
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println(getName() + " "+i);
		}
	}
	
	public static void main(String[] args)throws Exception{
		for (int i=0;i<100;i++){
			if(i==20){
				JoinThread jt = new JoinThread("join");
				jt.start();
				jt.join();
			}
			System.out.println(Thread.currentThread().getName()+"--- "+i);
		}
	}
	
}

