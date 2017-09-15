package learnJava;

import java.util.TreeSet;

public class LearnList {
	public static void main(String[] args)
	{
		TreeSet<Integer> nums = new TreeSet<>((a,b) -> (b-a));
        nums.add(5);
        nums.add(2);
        nums.add(6);
        nums.add(-4);
        System.out.println(nums);
	}

}
