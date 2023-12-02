package tenaya.suomi.tampere.logistiikka.logistiikka;

import java.util.HashMap;
import java.util.Map;

/**
 * A static map to store the mapping between shoe sizes in string format and their corresponding double values.
 * The keys are shoe sizes in string format, and the values are their corresponding double values.
 */
public class SizeInStringAndDouble {

    /**
     * A static map to store the mapping between shoe sizes in string format and their corresponding double values.
     * The keys are shoe sizes in string format, and the values are their corresponding double values.
     */
    private static final HashMap<String, String> hashMap = new HashMap<>();
    // Static initialization block to populate the map with size mappings
    static {
        hashMap.put("one", "1");
        hashMap.put("oneH", "1.5");
        hashMap.put("two", "2");
        hashMap.put("twoH", "2.5");
        hashMap.put("three", "3");
        hashMap.put("threeH", "3.5");
        hashMap.put("four", "4");
        hashMap.put("fourH", "4.5");
        hashMap.put("five", "5");
        hashMap.put("fiveH", "5.5");
        hashMap.put("six", "6");
        hashMap.put("sixH", "6.5");
        hashMap.put("seven", "7");
        hashMap.put("sevenH", "7.5");
        hashMap.put("eight", "8");
        hashMap.put("eightH", "8.5");
        hashMap.put("nine", "9");
        hashMap.put("nineH", "9.5");
        hashMap.put("ten", "10");
        hashMap.put("tenH", "10.5");
        hashMap.put("eleven", "11");
        hashMap.put("elevenH", "11.5");
        hashMap.put("twelve", "12");
        hashMap.put("twelveH", "12.5");
        hashMap.put("thirteen", "13");
    }

    /**
     * Finds and returns the mapping entry for a given shoe size in string format.
     *
     * @param toFind The shoe size in string format to find.
     * @return The mapping entry for the given shoe size, or null if not found.
     */
    public static Map.Entry<String, String> findPair(String toFind) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (toFind.equals(entry.getValue())) {
                return entry;
            }
        }
        return null;
    }
}
