package learnIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PushbackReader;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

@SuppressWarnings("serial")
public class LearnIO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263878230625969595L;
	public String test;
	public LearnIO(String tmp){
		test = tmp;
	}
	
	public static void main(String[] args) throws IOException
	{
//		testFile3();
//		testFileWriter();
//		testFileOutputStream5();
//		PrintStream();
//		stringNodeTest();
//		KeyinTest();
//		pushbackTest();
//		readFromProcess();
//		PrintStream();
//		PrintStream2();
		randomAccessFileTest2();
//		appendContent();
//		insert("InsertContent.java" , 45 , "插入的内容\r\n");
//		writeObject();
//		readObject();
//		bufferTest();
//		fileChannelTest();
//		randomFileChannelTest();
	}
	
	public static void randomFileChannelTest() throws FileNotFoundException, IOException{
		File f = new File("write.txt");
		try(
			// 创建一个RandomAccessFile对象
			
			FileChannel randomChannel = new RandomAccessFile(f, "rw").getChannel();
			)
		{
			// 将Channel中所有数据映射成ByteBuffer
			randomChannel.position(1000);
			System.out.println(f.length());
			System.out.println(randomChannel.size());
			ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0 ,1);
			System.out.println(buffer.limit());
			System.out.println(buffer.capacity());
			// 使用GBK的字符集来创建解码器
			Charset charset = Charset.forName("UTF-8");
			// 创建解码器(CharsetDecoder)对象
			CharsetDecoder decoder = charset.newDecoder();
			// 使用解码器将ByteBuffer转换成CharBuffer
			CharBuffer charBuffer = decoder.decode(buffer);
			// CharBuffer的toString方法可以获取对应的字符串
			System.out.println(charBuffer);
			// 把Channel的记录指针移动到最后
			randomChannel.position(10);
			System.out.println("randomChannel.position():"+randomChannel.position());
			// 将buffer中所有数据输出
			buffer.flip();
			randomChannel.write(buffer);
			buffer.clear();
			randomChannel.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void fileChannelTest(){
		File f = new File(".project");
		try(
			// 创建FileInputStream，以该文件输入流创建FileChannel
			FileChannel inChannel = new FileInputStream(f).getChannel();
			// 以文件输出流创建FileBuffer，用以控制输出
			FileChannel outChannel = new FileOutputStream("poem.txt",true)
				.getChannel())
		{
			// 将FileChannel里的全部数据映射成ByteBuffer
			MappedByteBuffer buffer = inChannel.map(FileChannel
				.MapMode.READ_ONLY , 0 , f.length());   // ①
			// 直接将buffer里的数据全部输出
			outChannel.write(buffer);     // ②
			// 再次调用buffer的clear()方法，复原limit、position的位置
			buffer.clear();
			
			// 使用GBK的字符集来创建解码器
			Charset charset = Charset.forName("UTF-8");
			// 创建解码器(CharsetDecoder)对象
			CharsetDecoder decoder = charset.newDecoder();
			// 使用解码器将ByteBuffer转换成CharBuffer
			CharBuffer charBuffer =  decoder.decode(buffer);
			// CharBuffer的toString方法可以获取对应的字符串
			System.out.println(charBuffer);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void bufferTest(){
		CharBuffer buff = CharBuffer.allocate(8);    // ①
		System.out.println("capacity: "	+ buff.capacity());
		System.out.println("limit: " + buff.limit());
		System.out.println("position: " + buff.position());
		// 放入元素
		buff.put('a');
		buff.put('b');
		buff.put('c');      // ②
		System.out.println("加入三个元素后，position = "
			+ buff.position());
		// 调用flip()方法
		buff.flip();	  // ③
		System.out.println("执行flip()后，limit = " + buff.limit());
		System.out.println("position = " + buff.position());
		// 取出第一个元素
		System.out.println("第一个元素(position=0)：" + buff.get());  // ④
		System.out.println("取出一个元素后，position = "
			+ buff.position());
		// 调用clear方法
		buff.clear();     // ⑤
		System.out.println("执行clear()后，limit = " + buff.limit());
		System.out.println("执行clear()后，position = "
			+ buff.position());
		System.out.println("执行clear()后，缓冲区内容并没有被清除："
			+ "第三个元素为：" +  buff.get(2));    // ⑥
		System.out.println("执行绝对读取后，position = "
			+ buff.position());
	}
	
	public static void readObject(){
		try(
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));
		){
			LearnIO learnIO = (LearnIO)ois.readObject();
			System.out.println(learnIO.test);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void writeObject() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("obj.txt"));) 
		{
			LearnIO learnIO = new LearnIO("12334444444");
			oos.writeObject(learnIO);
			learnIO.test="abcdetc";
			oos.writeObject(learnIO);

		} catch (IOException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public static void appendContent(){
		try(
				//以读、写方式打开一个RandomAccessFile对象
				RandomAccessFile raf = new RandomAccessFile("out.txt" , "rw"))
			{
				//将记录指针移动到out.txt文件的最后
				raf.seek(raf.length());
				raf.write("追加的内容！\r\n".getBytes());
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
	}
	public static void randomAccessFileTest2(){
		try(
				RandomAccessFile raf =  new RandomAccessFile(
					"newFile.txt" , "rw");
			){
				String line;
				// 使用循环来重复“取水”过程
				while ((line = raf.readLine()) != null )
				{
					System.out.println(new String(line.getBytes("ISO-8859-1"), "utf-8"));
					// 取出“竹筒”中水滴（字节），将字节数组转换成字符串输入！
//					System.out.print(new String(bbuf , 0 , hasRead ));
//					raf.write("lt".getBytes("utf-8"));
				}
				
				
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
	}
	
	public static void randomAccessFileTest(){
		try(
				RandomAccessFile raf =  new RandomAccessFile(
					"newFile.txt" , "r"))
			{
				// 获取RandomAccessFile对象文件指针的位置，初始位置是0
				System.out.println("RandomAccessFile的文件指针的初始位置："
					+ raf.getFilePointer());
				// 移动raf的文件记录指针的位置
				raf.seek(30);
				byte[] bbuf = new byte[1024];
				// 用于保存实际读取的字节数
				int hasRead = 0;
				// 使用循环来重复“取水”过程
				while ((hasRead = raf.read(bbuf)) > 0 )
				{
					// 取出“竹筒”中水滴（字节），将字节数组转换成字符串输入！
					System.out.print(new String(bbuf , 0 , hasRead ));
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
	}
	
	public static void PrintStream2() throws IOException{
		
		try(
				InputStream fod = LearnIO.class.getClassLoader().getResourceAsStream("obj.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fod));
			){
			StringBuffer sb = new StringBuffer(1024);
			String line = "";
			while((line = br.readLine())!=null){
				sb.append(line);
				sb.append("\n");
			}
			System.out.println(sb.toString());
			
		}
	}
	
	public static void insert(String fileName , long pos
			, String insertContent) throws IOException
		{
			File tmp = File.createTempFile("tmp" , null);
			tmp.deleteOnExit();
			try(
				RandomAccessFile raf = new RandomAccessFile(fileName , "rw");
				// 使用临时文件来保存插入点后的数据
				FileOutputStream tmpOut = new FileOutputStream(tmp);
				FileInputStream tmpIn = new FileInputStream(tmp))
			{
				raf.seek(pos);
				// ------下面代码将插入点后的内容读入临时文件中保存------
				byte[] bbuf = new byte[64];
				// 用于保存实际读取的字节数
				int hasRead = 0;
				// 使用循环方式读取插入点后的数据
				while ((hasRead = raf.read(bbuf)) > 0 )
				{
					// 将读取的数据写入临时文件
					tmpOut.write(bbuf , 0 , hasRead);
				}
				// ----------下面代码插入内容----------
				// 把文件记录指针重新定位到pos位置
				raf.seek(pos);
				// 追加需要插入的内容
				raf.write(insertContent.getBytes());
				// 追加临时文件中的内容
				while ((hasRead = tmpIn.read(bbuf)) > 0 )
				{
					raf.write(bbuf , 0 , hasRead);
				}
			}
		}
	
	public static void PrintStream() {
		try(
				FileOutputStream fos = new FileOutputStream("abc.txt");
//				PrintStream ps = new PrintStream(fos))
				OutputStreamWriter out = new OutputStreamWriter(fos,"SHIFT-JIS");
			)
			{
			char myCharactor = 'ー';
			//char StyleBookCharactor = '－';
			//char StyleBookCharactor = '恋';
			char StyleBookCharactor = '−';
			char CSVCharactor = 'ー';
			char myquanjiao='－';
			char myKANAjiao='ー';
			
//			我敲的 ー
//			式样书 －
//			csv的  ー
//			我敲的【全角英数字】  －
//			我敲的かな的全角　　ー
//			System.out.println("我敲的:"+(int)myCharactor);
//			System.out.println("式样书的:"+(int)StyleBookCharactor);
//			System.out.println("csv的:"+(int)CSVCharactor);
//			System.out.println("我敲的【全角英数字】:"+(int)myquanjiao);
//			System.out.println("我敲的かな的全角:"+(int)myKANAjiao);
			
//			out.write("我敲的:"+(int)myCharactor+"\r\n");
//			out.write("式样书的:"+(int)StyleBookCharactor+"\r\n");
//			out.write("csv的:"+(int)CSVCharactor+"\r\n");
//			out.write("我敲的【全角英数字】:"+(int)myquanjiao+"\r\n");
//			out.write("我敲的かな的全角:"+(int)myKANAjiao+"\r\n");
			
//			out.write("我敲的:"+myCharactor+"\r\n");
//			out.write("式样书的:"+StyleBookCharactor+"\r\n");
//			out.write("csv的:"+CSVCharactor+"\r\n");
//			out.write("我敲的【全角英数字】:"+myquanjiao+"\r\n");
//			out.write("我敲的かな的全角:"+myKANAjiao+"\r\n");
			out.write(StyleBookCharactor);
//			char[] test = new char[]{'－','－'};
//				// 使用PrintStream执行输出
//				ps.println("普通字符串");
//				// 直接使用PrintStream输出对象
//				ps.println(new LearnIO());
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
	}
	
	
	
	
	public static void getSql(String inputFileName,String outputFileName){
		try(
			BufferedReader inputReader = new BufferedReader(new FileReader(inputFileName));
			FileWriter fw = new FileWriter(outputFileName);
		) {
			String line;
			StringBuffer sqlBuffer = new StringBuffer(1024);
			while((line=inputReader.readLine())!=null){
				sqlBuffer.append(line);
			}
			fw.write(sqlBuffer.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public static void testFileList6() {
		
	}
	public static void readFromProcess() throws IOException {
		// 运行javac命令，返回运行该命令的子进程
				Process p = Runtime.getRuntime().exec("java -version");
				try(
					// 以p进程的错误流创建BufferedReader对象
					// 这个错误流对本程序是输入流，对p进程则是输出流
					BufferedReader br = new BufferedReader(new
						InputStreamReader(p.getErrorStream())))
				{
					String buff = null;
					// 采取循环方式来读取p进程的错误输出
					while((buff = br.readLine()) != null)
					{
						System.out.println(buff);
					}
				}
	}
	public static void pushbackTest() {
		try(
				// 创建一个PushbackReader对象，指定推回缓冲区的长度为64
				PushbackReader pr = new PushbackReader(new FileReader("a.txt") , 64))
			{
				char[] buf = new char[32];
				// 用以保存上次读取的字符串内容
				String lastContent = "";
				int hasRead = 0;
				// 循环读取文件内容
				while ((hasRead = pr.read(buf)) > 0)
				{
					// 将读取的内容转换成字符串
					String content = new String(buf , 0 , hasRead);
					int targetIndex = 0;
					// 将上次读取的字符串和本次读取的字符串拼起来，
					// 查看是否包含目标字符串, 如果包含目标字符串
					if ((targetIndex = (lastContent + content)
						.indexOf("测试")) > 0)
					{
						// 将本次内容和上次内容一起推回缓冲区
						pr.unread((lastContent + content).toCharArray());
						// 重新定义一个长度为targetIndex的char数组
						if(targetIndex > 32)
						{
							buf = new char[targetIndex];
						}
						// 再次读取指定长度的内容（就是目标字符串之前的内容）
						pr.read(buf , 0 , targetIndex);
						// 打印读取的内容
						System.out.print(new String(buf , 0 ,targetIndex));
						System.exit(0);
					}
					else
					{
						// 打印上次读取的内容
						System.out.print(lastContent);
						// 将本次内容设为上次读取的内容
						lastContent = content;
					}
				}
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
	}
	public static void KeyinTest() {
		try(
				// 将Sytem.in对象转换成Reader对象
				InputStreamReader reader = new InputStreamReader(System.in);
				// 将普通Reader包装成BufferedReader
				BufferedReader br = new BufferedReader(reader))
			{
				String line = null;
				// 采用循环方式来一行一行的读取
				while ((line = br.readLine()) != null)
				{
					// 如果读取的字符串为"exit"，程序退出
					if (line.equals("exit"))
					{
						System.exit(1);
					}
					// 打印读取的内容
					System.out.println("输入内容为:" + line);
				}
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
	}
	
	
	public static void stringNodeTest() {
		String src = "从明天起，做一个幸福的人\n"
				+ "喂马，劈柴，周游世界\n"
				+ "从明天起，关心粮食和蔬菜\n"
				+ "我有一所房子，面朝大海，春暖花开\n"
				+ "从明天起，和每一个亲人通信\n"
				+ "告诉他们我的幸福\n";
			char[] buffer = new char[32];
			int hasRead = 0;
			try(
				StringReader sr = new StringReader(src))
			{
				// 采用循环读取的访问读取字符串
				while((hasRead = sr.read(buffer)) > 0)
				{
					System.out.print(new String(buffer ,0 , hasRead));
				}
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			try(
				StringWriter sw = new StringWriter())
			{
				// 调用StringWriter的方法执行输出
				sw.write("有一个美丽的新世界，\n");
				sw.write("她在远方等我,\n");
				sw.write("哪里有天真的孩子，\n");
				sw.write("还有姑娘的酒窝\n");
				System.out.println("----下面是sw的字符串节点里的内容----");
				// 使用toString()方法返回StringWriter的字符串节点的内容
				System.out.println(sw.toString());
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
	}

	
	
	public static void testFileOutputStream5() {
		try(
				// 创建字节输入流
				FileInputStream fis = new FileInputStream("a.txt");
				// 创建字节输出流
				FileOutputStream fos = new FileOutputStream("newFile.txt"))
			{
				byte[] bbuf = new byte[32];
				int hasRead = 0;
				// 循环从输入流中取出数据
				while ((hasRead = fis.read(bbuf)) > 0 )
				{
					// 每读取一次，即写入文件输出流，读了多少，就写多少。
					fos.write(bbuf , 0 , hasRead);
				}
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}

	}

	
	public static void testFileWriter() {
		try(FileWriter fw = new FileWriter("a.txt"))
				
			{
				fw.write("锦瑟 - 李商隐\r\n");
				fw.write("锦瑟无端五十弦，一弦一柱思华年。\r\n");
				fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n");
				fw.write("沧海月明珠有泪，蓝田日暖玉生烟。\r\n");
				fw.write("此情可待成追忆，只是当时已惘然。\r\n");
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
	}
	
	public static void testFile3() {
		try(
				// 创建字符输入流
				FileReader fr = new FileReader("a.txt")
			){
				// 创建一个长度为32的“竹筒”
				char[] cbuf = new char[32];
				System.out.println(fr.read());
				System.out.println(fr.read(cbuf,0,32));
				System.out.println(new String(cbuf));
				// 用于保存实际读取的字符数
				int hasRead = 0;
				// 使用循环来重复“取水”过程
				while ((hasRead = fr.read(cbuf)) > 0 )
				{
					// 取出“竹筒”中水滴（字符），将字符数组转换成字符串输入！
//					System.out.print(new String(cbuf , 0 , hasRead));
					System.out.print(new String(cbuf));
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}

	}
	
	
	public static void testFile2() throws IOException{
		// 创建字节输入流
					FileInputStream fis = new FileInputStream("a.txt");
					//读取一个字节,返回的是int,所以要用char强转
					System.out.println((char)fis.read());
					// 创建一个长度为1024的“竹筒”
					byte[] bbuf = new byte[1024];
					//读取5个字节,放在bbuf的index为10的位置
					fis.read(bbuf,10,10);
//					从bbuf中index为10的位置读取5个字节构成字符串
					System.out.println(new String(bbuf , 10, 5));
					//转换成String后,byte[]不变
					System.out.println(new String(bbuf , 10, 5));
					// 用于保存实际读取的字节数
					int hasRead = 0;
					// 使用循环来重复“取水”过程
					while ((hasRead = fis.read(bbuf)) > 0 )
					{
						//重新赋值后,byte[]会改变
						System.out.println(new String(bbuf , 10, 5));
						// 取出“竹筒”中水滴（字节），将字节数组转换成字符串输入！
						System.out.println("==============================================");
						String test = new String(bbuf , 0 , hasRead );
						System.out.println(test);
						System.out.println("=================="+new String(bbuf , 0 , hasRead ).length()+"============================");
					}
					// 关闭文件输入流，放在finally块里更安全
					fis.close();
	}
	
	public static void testFile() throws IOException{
		// 创建字节输入流
					FileInputStream fis = new FileInputStream("a.txt");
					// 创建一个长度为1024的“竹筒”
					byte[] bbuf = new byte[1024];
					// 用于保存实际读取的字节数
					int hasRead = 0;
					// 使用循环来重复“取水”过程
					while ((hasRead = fis.read(bbuf)) > 0 )
					{
						// 取出“竹筒”中水滴（字节），将字节数组转换成字符串输入！
						System.out.print(new String(bbuf , 0 , hasRead ));
					}
					// 关闭文件输入流，放在finally块里更安全
					fis.close();
	}
	
	public static void testFileList1() {
//		 使用Lambda表达式（目标类型为FilenameFilter）实现文件过滤器。
//		 如果文件名以.java结尾，或者文件对应一个路径，返回true
		File file = new File(".");

		String[] nameList = file.list((dir, name) -> name.endsWith(".java")
			|| new File(name).isDirectory());
		for(String name : nameList)
		{
			System.out.println(name);
		}

	}

	public static void testFileList2() throws IOException {
		System.out.println(File.listRoots());
		File currentFile = new File(".");
		System.out.println(currentFile.getAbsolutePath());
		for(File file : currentFile.listFiles()){
			System.out.println(file.getName());
		}
		File file = new File(".");
		if(!file.exists()){
			
			System.out.println(file.exists());
			file.createNewFile();
		}
		System.out.println(file.getAbsolutePath());
//		
		System.out.println(new File(file.getAbsolutePath()).getParent());


	}


	

	
	
	
	
	
	
	
	
	
	

}
