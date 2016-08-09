package arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Amazon Phone Interview Question.
 * 
 * Find the mode of an array. Mode is the number which repeats maximum number of time.
 * 
 * @author sumitkumar
 * 
 */
public class ModeOfArray {
	public static void main(String[] args) {
		int[] a = { 5, 3, 8, 4, 8, 8, 4, 1, 4 };

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) { // n
			if (map.get(a[i]) == null) {
				map.put(a[i], 1); // 1
			} else {
				int count = map.get(a[i]);
				map.put(a[i], ++count); // 1
			}
		}

		int mode = 0;
		int key = 0;

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > mode) {
				mode = entry.getValue();
				key = entry.getKey();
			}
		}

		System.out.println(key);

	}

}
