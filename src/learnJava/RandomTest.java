package learnJava;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			System.out.println(createPassword());
		}
	}
	
	public static String createPassword(){
		char capital = (char) ('A'+getRandomNumber(26));
		char[] number = new char[5];
		for(int i = 0;i<5;i++){
			number[i] = (char) ('0'+getRandomNumber(10));
		}
		char lowercase = (char) ('a'+getRandomNumber(26));
		String password = capital+String.valueOf(number)+lowercase;
		return password;
	}
	
	public static int getRandomNumber(int range){
		int number = new Random().nextInt(range);
		return number;
	}

}
