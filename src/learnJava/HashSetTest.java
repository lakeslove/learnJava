package learnJava;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedSet<String> s = Collections.synchronizedSortedSet(new TreeSet<>());
		TreeSet nums = new TreeSet<>();
		nums.add(5);
		nums.add(2);
		nums.add(6);
		nums.add(-4);
		nums.add(5.0);
		System.out.println(nums);
	}

}
class A{
	public boolean equals(Object obj){
		return true;
	}
}
class B{
	public int hashCode(){
		return 1;
	}
}
class C{
	public int hashCode(){
		return 2;
	}
	public boolean equals(Object obj){
		return true;
	}
}


















