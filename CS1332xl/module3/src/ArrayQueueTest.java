import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Test
	void testEnqueDeque() {
		ArrayQueue<Object> aQueue = new ArrayQueue<>();
		aQueue.enqueue(5);
		assertEquals(1, aQueue.size());
		aQueue.enqueue(3);
		assertEquals(2, aQueue.size());
		Integer value = (Integer) aQueue.dequeue();
		assertEquals(5, value);
		assertEquals(1, aQueue.size());
		value = (Integer) aQueue.dequeue();
		assertEquals(3, value);
		assertEquals(0, aQueue.size());
	}
	
	@Test
	void testGrow() {
		ArrayQueue<Object> aQueue = new ArrayQueue<>();
		for (int i = 0; i < 9; i++) {
			aQueue.enqueue(i);
		}
		assertEquals(9, aQueue.size());

		aQueue.enqueue(9);
		assertEquals(18, aQueue.getBackingArray().length);
		Integer dataInt;
		for (int i = 0; i < 10; i++) {
			dataInt = (Integer) aQueue.dequeue();
			assertEquals(i, dataInt);
			assertEquals(10 - 1 - i, aQueue.size());
		}
	}

}
