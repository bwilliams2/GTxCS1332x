
import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;

import org.junit.jupiter.api.Test;


class MatchTest {

	@Test
	void FindMatches() {
		CharSequence text = "abcdgaabclslabc";
		CharacterComparator comparator = new CharacterComparator();
		List<Integer> patterns = PatternMatching.boyerMoore("abc", text, comparator);
		List<Integer> expected = List.of(0, 6, 12);
		Assert.assertEquals(patterns, expected);
	}

}
