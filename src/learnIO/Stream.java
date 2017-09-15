package learnIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stream {
	
	public static void main(String[] args){
		
		try {
			FileReader fr = new FileReader("./tests/simpleResult.txt");
			FileWriter fw = new FileWriter("./tests/fw1.txt");
			char[] cbuf = new char[4];
			int hasRead = 0;
			while((hasRead = fr.read(cbuf))> 0){
				System.out.println(new String(cbuf,0,hasRead));
				fw.write(cbuf);
				fr.skip(100);
			}
			fr.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		
		
//		try (FileInputStream fis = new FileInputStream("./tests/simpleResult.txt");
//				
//				){
//			
//			System.out.println(fis.markSupported());
//			byte[] bbuf = new byte[24];
//			int hasRead = 0;
//			while ((hasRead = fis.read(bbuf)) > 0){
//				System.out.print(new String(bbuf,0,hasRead));
//				System.out.println(fis.skip(124));
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
