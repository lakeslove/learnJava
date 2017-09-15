package learnIO;

import java.io.*;

public class Pushback {
	public static void main(String[] args) throws IOException {
		try (PushbackReader pr = new PushbackReader(new FileReader(
				"./tests/fw1.txt"), 64)) {
			char[] buf = new char[32];
			String lastContent = "";
			int hasRead = 0;
			while ((hasRead = pr.read(buf)) > 0) {
				String content = new String(buf, 0, hasRead);
				int targetIndex = 0;
				if ((targetIndex = (lastContent + content)
						.indexOf("cspjn 50n)")) >= 0) {
					pr.unread((lastContent + content).toCharArray());
					if(targetIndex >32){
						buf = new char[targetIndex];
					}
					pr.read(buf, 0, targetIndex);
					System.out.println(new String(buf,0,targetIndex));
					System.exit(0);
				}else{
					System.out.print(lastContent);
					lastContent = content;
				}
			}
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
}
