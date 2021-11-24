import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArrayListTest {

	@Test
	void initialBackingArray() {
		ArrayList<Object> aList = new ArrayList<>();
		Object[] backingArray = aList.getBackingArray();
		assertEquals(backingArray.length, 9);
	}
	
	@Test
	void testAddToFront() {
		ArrayList<Object> aList = new ArrayList<>();
		for (Integer i = 0; i < 12; i++) {
			aList.addToFront(i);
		}
		Object[] backingArray = aList.getBackingArray();
		assertEquals(backingArray.length, 18);
		assertEquals(backingArray[0], 11);
		for (Integer i = 0; i < 12; i++) {
			aList.addToFront(i);
		}
		backingArray = aList.getBackingArray();
		assertEquals(backingArray.length, 36);
		assertEquals(backingArray[0], 11);
	}
	
	@Test
	void testAddToBack() {
		ArrayList<Object> aList = new ArrayList<>();
		for (Integer i = 0; i < 12; i++) {
			aList.addToBack(i);
		}
		Object[] backingArray = aList.getBackingArray();
		assertEquals(backingArray.length, 18);
		assertEquals(backingArray[11], 11);
		for (Integer i = 0; i < 12; i++) {
			aList.addToBack(i);
		}
		backingArray = aList.getBackingArray();
		assertEquals(backingArray.length, 36);
		assertEquals(backingArray[11], 11);
	}
	
	@Test
	void testRemoveFromFront() {
		ArrayList<Object> aList = new ArrayList<>();
		for (Integer i = 0; i < 12; i++) {
			aList.addToFront(i);
		}
		Integer front = (Integer) aList.removeFromFront();
		assertEquals(front, 11);
		assertEquals(aList.size(), 11);
	}
	
	@Test
	void testRemoveFromBack() {
		ArrayList<Object> aList = new ArrayList<>();
		for (Integer i = 0; i < 12; i++) {
			aList.addToBack(i);
		}
		Integer back = (Integer) aList.removeFromBack();
		assertEquals(back, 11);
		assertEquals(aList.size(), 11);
	}

}
