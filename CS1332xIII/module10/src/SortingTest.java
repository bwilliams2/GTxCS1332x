import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class SortingTest {

	@Test
	void testBubble() {
		Integer[] arr = new Integer[]{3,5,1,78,12};
		SortInt compare = new SortInt();
		
		Sorting.bubbleSort(arr, compare);
		
		fail("Not yet implemented");
	}

}