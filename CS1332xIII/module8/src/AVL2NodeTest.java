import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVL2NodeTest {

	@Test
	void test() {
		AVL2<Integer> avl = new AVL2<Integer>();
		avl.add(2);
		avl.add(1);
		avl.add(3);
		avl.add(0);
		avl.remove(3);
		fail("Not yet implemented");
	}

}
