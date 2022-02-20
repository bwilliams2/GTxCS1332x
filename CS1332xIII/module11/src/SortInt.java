import java.util.*;

class SortInt implements Comparator<Integer> {
	protected int comparisons;
	
    public SortInt() {
    	comparisons = 0;
    }
	 
    // Method
    // Sorting in ascending order of roll number
    public int compare(Integer a, Integer b)
    {
    	comparisons += 1;	
        return a - b;
    }
    
    public int getComparisons() {
    	return comparisons;
    }
}
