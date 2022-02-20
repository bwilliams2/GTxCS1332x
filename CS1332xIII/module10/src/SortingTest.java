import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class SortingTest {

	@Test
	void testBubble() {
		Integer[] arr = new Integer[]{3,5,1,78,12};
		SortInt compare = new SortInt();
		
		Sorting.bubbleSort(arr, compare);
		Integer[] arrSorted = new Integer[] {1,3,5,12,78};
		assertArrayEquals(arr, arrSorted);
	}
	
	@Test
	void testSelection() {
		Integer[] arr = new Integer[]{3,5};
		SortInt compare = new SortInt();
		
		Sorting.selectionSort(arr, compare);
		
		SortInt compare2 = new SortInt();
		Integer[] arr2 = new Integer[]{3,5};
		Sorting.selectionSort2(arr2, compare2);

		Integer[] arrSorted = new Integer[] {1,3,5,12,78};
		@SuppressWarnings("unused")
		int compares = compare.getComparisons();
		@SuppressWarnings("unused")
		int compares2 = compare2.getComparisons();
		assertArrayEquals(arr, arrSorted);
	}

	
	@Test
	void testInsertion() {
		Integer[] arr = new Integer[]{3,5,1,78,12};
		SortInt compare = new SortInt();
		
		Sorting.insertionSort(arr, compare);
		Integer[] arrSorted = new Integer[] {1,3,5,12,78};
		assertArrayEquals(arr, arrSorted);
	}
}