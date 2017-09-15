package learnIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultAverage {
	public static void main(String[] args){
		try{
			FileWriter resultAve = new FileWriter("./result/ResultAverage.txt",false);
			File result = new File("./result");
			File[] fileArray = result.listFiles((dir,name)  -> new File("./result/"+name).isDirectory());
			System.out.println(fileArray.length);
			for(File temFile :fileArray){
				String temFileName = temFile.getName();
				String[] nameArray = temFileName.split("_");
				String title = "";
				if(nameArray.length>2){
					System.out.println(temFileName+"    "+nameArray[2]);
					for(int i = 3;i<nameArray.length;i++){
						title = title+"_"+nameArray[i];
					}
					if(nameArray[2].equals("0")){
						title = "tomcat-默认配置" +title;
					}
					if(nameArray[2].equals("1")){
						title = "tomcat-framework" +title;
					}
				}
				System.out.println(title);
				BufferedReader br = new BufferedReader(new FileReader("./result/"+temFileName+"/simpleResult.txt"));
//				if(title.length()<40){
//					for (int i = title.length();i<40;i++){
//						title=title+"_ ";
//					}
//				}
				resultAve.write("\n"+title+" ");
				String line="";
				List<Double> doubleList = new ArrayList<>();
				Boolean mark = false;
				while((line = br.readLine())!= null){
					if(line.contains("Requests per second")){
						mark = true;
						String temS = line.replace("Requests per second:    ", "").replace(" [#/sec] (mean)", "");
						doubleList.add(Double.parseDouble(temS));
					}else{
						if(mark){
							double sum= 0;
							for(Double temDoub : doubleList){
								sum= sum+temDoub;
							}
//							String average = (new java.text.DecimalFormat("#.00")).format(sum/(doubleList.size()));
							long average = (long)Math.rint(sum/(doubleList.size()));
							resultAve.write(average+" ");
							doubleList.clear();
							mark = false;
						}
					}
				}
				br.close();
			}
			resultAve.close();
			System.out.println("平均数输出成功");
			
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
}
