import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortingTest {

	@Test
	void mergeSortTest() {
		Integer[] arr = new Integer[]{3,5,1,78,12};
		SortInt compare = new SortInt();

		Sorting.mergeSort(arr, compare);
		Integer[] arrSorted = new Integer[] {1,3,5,12,78};
		assertArrayEquals(arr, arrSorted);
	}
	
	@Test
	void LSDSortTest() {
		int[] arr = {3,5,510,1,78,12};
		SortInt compare = new SortInt();

		Sorting.lsdRadixSort(arr);
		int[] arrSorted = {1,3,5,12,78,510};
		assertArrayEquals(arr, arrSorted);
	}


}
