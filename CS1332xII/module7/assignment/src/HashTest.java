import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTest {

	@Test
	void test() {
		ExternalChainingHashMap<Integer, Integer> newMap = new ExternalChainingHashMap<>();
		for (int i=0; i<9;i++) {
			newMap.put(i,i);
		}
		System.out.println("[");
		int n = 0;
		for (ExternalChainingMapEntry<Integer, Integer> pair : newMap.getTable()) {
			if (pair != null) {
				System.out.println(String.format("%2d: (%2d, %2d)", n, pair.getKey(), pair.getValue()));		
			} else {
				System.out.println(String.format("%2d: (null, null)", n));
			}
			n += 1;
		}
		System.out.println("]");

		newMap.put(9, 9);
		
		n = 0;
		for (ExternalChainingMapEntry<Integer, Integer> pair : newMap.getTable()) {
			if (pair != null) {
				System.out.println(String.format("%2d: (%2d, %2d)", n, pair.getKey(), pair.getValue()));		
			} else {
				System.out.println(String.format("%2d: (null, null)", n));
			}
			n += 1;
		}
		System.out.println("]");	}

}
