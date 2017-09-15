package learnJava;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

import javax.swing.JFrame;

@Target(ElementType.TYPE_USE)
@interface NotNull{}

@NotNull
public class TypeAnnotationTest implements @NotNull Serializable{
	public static void main(@NotNull String[] args) throws @NotNull FileNotFoundException{
		Object obj = "fkjava.org";
		String str = (@NotNull String)obj;
		Object win = new @NotNull JFrame("疯狂软件");
	}
	public void foo(List<@NotNull String> info){
		
	}
}
