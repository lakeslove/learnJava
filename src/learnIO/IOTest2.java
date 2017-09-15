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
        //�����ļ�
        Files.copy(Paths.get("abc.txt"), new FileOutputStream("testFile/abc2.txt"));
        //�ж��Ƿ������ļ�
        System.out.println(Files.isHidden(Paths.get("abc.txt")));
        //��ȡ�ļ��������е�list
        List<String> lines = Files.readAllLines(Paths.get("abc.txt"));
        lines.forEach (line -> System.out.println(line));
        //�ļ���С
        System.out.println(Files.size(Paths.get("abc.txt")));
        //���ļ���д�붫��
        List<String> poem = new ArrayList<String>();
        poem.add("quzhinannnnnnnnnnn��־��ϰ�������������");
        poem.addAll(lines);
        Files.write(Paths.get("testFile/path.txt"), poem, Charset.forName("utf-8"));
        //�г��ļ�Ŀ¼�µ������ļ�(���ݹ�)
        Files.list(Paths.get("/")).forEach(Name -> System.out.println(Name));
        //��ӡ�����ļ�������̨
        Files.lines(Paths.get("abc.txt"),Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        //�鿴�洢�ռ�
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
        	newBuffer.put("���".getBytes("UTF-8"));
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
        		// �����㹻���߳���֧��4��CPU���е��̳߳�
                // ����һ�����й̶��߳�����6�����̳߳�
                ExecutorService pool = Executors.newFixedThreadPool(4);
                // ʹ��Lambda���ʽ����Runnable����
                Runnable target = () -> {
                    for (int i = 0; i < 3 ; i++ )
                    {
                        System.out.println(Thread.currentThread().getName()
                            + "��iֵΪ:" + i);
                    }
                };
                // ���̳߳����ύ�����߳�
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
                // �ر��̳߳�
                pool.shutdown();
    }
	
	public static void fileChannelTest(){
        File f = new File(".project");
        try(// ����FileInputStream���Ը��ļ�����������FileChannel
            FileChannel inChannel = new FileInputStream(f).getChannel();
            // ���ļ����������FileBuffer�����Կ������
            FileChannel outChannel = new FileOutputStream("aw.txt")
                .getChannel()){
            // ��FileChannel���ȫ������ӳ���ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel
                .MapMode.READ_ONLY , 0 , f.length());   // ��
            // ֱ�ӽ�buffer�������ȫ�����
            outChannel.write(buffer);     // ��
            // �ٴε���buffer��clear()��������ԭlimit��position��λ��
            buffer.clear();
            
            // ʹ��GBK���ַ���������������
            Charset charset = Charset.forName("UTF-8");
            // ����������(CharsetDecoder)����
            CharsetDecoder decoder = charset.newDecoder();
            // ʹ�ý�������ByteBufferת����CharBuffer
            CharBuffer charBuffer =  decoder.decode(buffer);
            // CharBuffer��toString�������Ի�ȡ��Ӧ���ַ���
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
                System.out.println("���ڷ��ʣ�" + dir + "Ŀ¼");  
                return FileVisitResult.CONTINUE;  
            }  
  
            @Override  
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {  
                // TODO Auto-generated method stub  
                // return super.visitFile(file, attrs);  
                System.out.println("\t���ڷ���" + file + "�ļ�");  
                if (file.endsWith("LearnIO.java")) {  
                    System.out.println("******�ҵ�Ŀ���ļ�LearnIO.java******");  
                    return FileVisitResult.TERMINATE; // �ҵ��˾���ֹ  
                }
                return FileVisitResult.CONTINUE; // û�ҵ�������  
            }  
        });  ;
    }
	
	
	public static void test() throws IOException{
        try(FileWriter fw = new FileWriter("b.txt");){
        	fw.write(new String("���".getBytes("UTF-8")));
        }
    }
	
	
	

}
