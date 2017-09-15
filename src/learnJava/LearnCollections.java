package learnJava;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.lang.model.element.Element;

import learnIO.systemIn;

public class LearnCollections {
	public static void main(String[] args) {
		Collection<String> books = new HashSet<>();
		books.add("123241232");
		books.add("wdw12ddw");
		books.add("es23d");
		System.out.println(books.stream()
				.filter(ele -> ((String) ele).contains("123")).count());
		
	}
	
	public static int callAll(Collection<String> books, Predicate<Object> p){
	    int total = 0;
	    for(Object obj :books){
	      if(p.test(obj)){
	    	  total ++;
	     }
	   }
	   return total;
	}

}
