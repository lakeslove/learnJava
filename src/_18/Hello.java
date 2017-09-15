package _18;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("tes22t2");
        for (String arg : args)
        {
            System.out.println("hello proms£º" + arg);
        }
        for(int i = 10;i>0;i--){
        	System.out.println(fn(i));
        }
	}
	
	public static int fn(Integer n){
		if(n.equals(1)){
			return 3;
		}
		if(n.equals(2)){
			return 5;
		}
		return 2*fn(n-2)+fn(n-1);
	}
}
