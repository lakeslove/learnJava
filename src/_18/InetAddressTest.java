package _18;

import java.net.InetAddress;

public class InetAddressTest {
	public static void main(String[] args) throws Exception{
		InetAddress ip = InetAddress.getByName("baidu.com");
		System.out.println("crazyit是否可达�?" + ip.isReachable(2000));
		System.out.println(ip.getHostAddress());
		InetAddress local = InetAddress.getByAddress(new byte[]{(byte) 172,20,15,73});
		System.out.println("本机是否可达�?"+ local.isReachable(50000));
		System.out.println(local.getCanonicalHostName()+local.getHostAddress()+local.getHostName());
	}

}
