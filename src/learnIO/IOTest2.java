package learnIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.FileStore;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOTest2 {
	public static void main(String[] args) throws IOException
	{
		filesTest();
	}
	public static void filesTest() throws FileNotFoundException, IOException{
        //复制文件
        Files.copy(Paths.get("abc.txt"), new FileOutputStream("testFile/abc2.txt"));
        //判断是否隐藏文件
        System.out.println(Files.isHidden(Paths.get("abc.txt")));
        //读取文件的所有行到list
        List<String> lines = Files.readAllLines(Paths.get("abc.txt"));
        lines.forEach (line -> System.out.println(line));
        //文件大小
        System.out.println(Files.size(Paths.get("abc.txt")));
        //向文件里写入东西
        List<String> poem = new ArrayList<String>();
        poem.add("quzhinannnnnnnnnnn曲志楠南安安那那那那那");
        poem.addAll(lines);
        Files.write(Paths.get("testFile/path.txt"), poem, Charset.forName("utf-8"));
        //列出文件目录下的所有文件(不递归)
        Files.list(Paths.get("/")).forEach(Name -> System.out.println(Name));
        //打印所有文件到控制台
        Files.lines(Paths.get("abc.txt"),Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        //查看存储空间
        FileStore fs = Files.getFileStore(Paths.get("/"));
        System.out.println(fs.getTotalSpace());
    }
	
	public static void fileChannelTest2(){
        try(FileChannel inputChannel = new FileInputStream("newFile.txt").getChannel();
        		FileChannel outputChannel = new FileOutputStream("b.txt").getChannel()
        		){
        	MappedByteBuffer buffer = inputChannel.map(MapMode.READ_ONLY, 0, inputChannel.size());
        	ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity()*2);
        	newBuffer.put(buffer);
        	newBuffer.put("你好".getBytes("UTF-8"));
        	newBuffer.flip();
           outputChannel.write(newBuffer);
           newBuffer.flip();
           CharBuffer charBuffer = Charset.forName("UTF-8").decode(newBuffer);
           System.out.println(charBuffer.toString());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
	
	public static void threadPoolTest(){
        		// 创建足够的线程来支持4个CPU并行的线程池
                // 创建一个具有固定线程数（6）的线程池
                ExecutorService pool = Executors.newFixedThreadPool(4);
                // 使用Lambda表达式创建Runnable对象
                Runnable target = () -> {
                    for (int i = 0; i < 3 ; i++ )
                    {
                        System.out.println(Thread.currentThread().getName()
                            + "的i值为:" + i);
                    }
                };
                // 向线程池中提交两个线程
                pool.submit(target);
                pool.submit(target);
                pool.submit(target);
                pool.submit(target);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pool.submit(target);
                pool.submit(target);
                // 关闭线程池
                pool.shutdown();
    }
	
	public static void fileChannelTest(){
        File f = new File(".project");
        try(// 创建FileInputStream，以该文件输入流创建FileChannel
            FileChannel inChannel = new FileInputStream(f).getChannel();
            // 以文件输出流创建FileBuffer，用以控制输出
            FileChannel outChannel = new FileOutputStream("aw.txt")
                .getChannel()){
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
	
	public static void walkFileTree() throws IOException{
        Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {  
              
            @Override  
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {  
                // TODO Auto-generated method stub  
                // return super.preVisitDirectory(dir, attrs);  
                System.out.println("正在访问：" + dir + "目录");  
                return FileVisitResult.CONTINUE;  
            }  
  
            @Override  
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {  
                // TODO Auto-generated method stub  
                // return super.visitFile(file, attrs);  
                System.out.println("\t正在访问" + file + "文件");  
                if (file.endsWith("LearnIO.java")) {  
                    System.out.println("******找到目标文件LearnIO.java******");  
                    return FileVisitResult.TERMINATE; // 找到了就终止  
                }
                return FileVisitResult.CONTINUE; // 没找到继续找  
            }  
        });  ;
    }
	
	
	public static void test() throws IOException{
        try(FileWriter fw = new FileWriter("b.txt");){
        	fw.write(new String("你好".getBytes("UTF-8")));
        }
    }
	
	
	

}
