package learnThread;

public class ThreadTest {
	public static void main(String[] args) {
		TestSyn test = new TestSyn();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.m2();
			}
		}, "t1").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.m1();
			}
		}, "t2").start();
	}
}

class TestSyn {
	public void m1() {
		Integer i = 5;
		synchronized (i){
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
				}
			}
		}
	}

	public synchronized void m2() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
			}
		}
	}
}