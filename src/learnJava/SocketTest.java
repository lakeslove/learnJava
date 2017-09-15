package learnJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        // TODO Auto-generated method stub
        Socket socket = new Socket("127.0.0.1", 8080);
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(),autoflush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("GET /openamHelper/private/openAMProperties HTTP/1.1");
        out.println("Host: 172.20.15.181:8080");
//        out.println("Connection: close");
        out.println();
        System.out.println(socket.getLocalPort());
        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while(loop){
            if(in.ready()){
                int i=0;
                int num = 0;
                while(i!=-1){
                    num++;
                    i = in.read();
                    sb.append((char)i);
                }
                System.out.println(num++);
                loop = false;
            }
            Thread.currentThread();
            Thread.sleep(2000);
        }
        System.out.println(sb.toString());
//        socket.close();

    }

}
