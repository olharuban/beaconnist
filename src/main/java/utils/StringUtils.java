package utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by olga on 4/15/16.
 */
public class StringUtils {

	public static TreeMap<Character, Integer> countCharacter(String inputString) {
		TreeMap<Character, Integer> charCountMap = new TreeMap<Character, Integer>();
		char[] strArray = inputString.toCharArray();
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {
				charCountMap.put(c, 1);
			}
		}
		return charCountMap;
	}

	public static <K, V> void customPrintMap(Map<? extends K, ? extends V> map) {
		for (Map.Entry e : map.entrySet()) {
			System.out.println(e.getKey().toString() + ", " + e.getValue().toString());
		}
	}

}
