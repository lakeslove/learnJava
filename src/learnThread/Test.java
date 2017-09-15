package learnThread;
class NumberTest implements Runnable{

    Exercise exercise;
    public NumberTest(Exercise exercise){
        this.exercise = exercise;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i = 1;i<27;i++){
            try {
                exercise.printSomething(i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}

class Letter implements Runnable{
    
    Exercise exercise;
    public Letter(Exercise exercise){
        this.exercise = exercise;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i = 'A';i<='Z';i++){
            try {
                exercise.printSomething(i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}



class Exercise{
	
    public synchronized void printSomething(int i) throws InterruptedException{
        if(i>64){
            System.out.println((char)i);
            notify();
            wait();
        }else{
            System.out.print(i*2-1);
            System.out.print(i*2);
            wait();
            notify();
        }
    }
}

public class Test {
	public synchronized void printSomething(int i){
		if (i > 64) {
			System.out.println((char) i);
			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print(i * 2 - 1);
			System.out.print(i * 2);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notify();
		}
	}

	public static void main(String[] args){
		Test test = new Test();
		Runnable num = () -> {
			for (int i = 1; i < 27; i++) {
				test.printSomething(i);
			}
		};
		Runnable letter = () -> {
			for (int i = 'A'; i <= 'Z'; i++) {
				test.printSomething(i);
			}
		};
		new Thread(num).start();
		new Thread(letter).start();
	}
}