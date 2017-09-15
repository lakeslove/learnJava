package learnIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromProcess {
	
	public static void main(String[] agrs) throws IOException{
		Process p = Runtime.getRuntime().exec("ping 172.20.15.73");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())))
		{
			String buff = null;
			while ((buff = br.readLine()) !=null){
				System.out.println(buff);
			}
		}
		
	}

	
}
