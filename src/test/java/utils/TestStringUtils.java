package utils;

import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by olga on 5/5/16.
 */
public class TestStringUtils {

	@Test
	public void testCountCharacterMethod() throws Exception {
		final String ANY_STRING = "01AF04F";
		final TreeMap<Character, Integer> mapWithCountedCharacters = new TreeMap<Character, Integer>() {
			{
				put('0', 2);
				put('1', 1);
				put('4', 1);
				put('A', 1);
				put('F', 2);
			}
		};
		assertEquals(mapWithCountedCharacters, StringUtils.countCharacter(ANY_STRING));
	}
}
