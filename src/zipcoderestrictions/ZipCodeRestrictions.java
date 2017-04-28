/*
 * Given a range of restricted zip codes in the format [low, high], see whether
 * or not your package will be allowed to ship to a selected zip code.
 */
package zipcoderestrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ZipCodeRestrictions {

    //Total list of zip codes.
    public static int[] zipcodeList = new int[100000]; //[0, 99999]

    /*
     * @param zipcodes: The int[] list of restricted zip codes.
     * @param reset: Whether or not to use a new list of restricted zip codes
     * or to build off of the previous one.
     */
    public static boolean countingSortStyle(int zipcode, List<int[]> restricted, boolean reset) {
        if (reset) {
            zipcodeList = new int[100000];
        }

        for (int[] current : restricted) {
            for (int i = current[0]; i <= current[1]; i++) { // Treat it like counting-sort and mark places that can't be sent to.
                zipcodeList[i] = 1;
            }
        }

        return zipcodeList[zipcode] == 0; //True if sendable, false if restricted.
    }
    
    public static boolean linearSearchStyle(int zipcode, List<int[]> restricted) {
        ListIterator<int[]> iterator = restricted.listIterator();
        
        while (iterator.hasNext()) {
            int[] range = iterator.next();
            if (zipcode >= range[0] && zipcode <= range[1]) {
                return false; // Within a restricted range.
            }
        }
        return true; // No applicable ranges.
    }
    
    public static void countingSortTest(List<int[]> restricted, boolean reset) {
        System.out.println("countingSort1: " + countingSortStyle(94030, restricted, reset));

        System.out.println("countingSort2: " + countingSortStyle(94051, restricted, reset));

        System.out.println("countingSort3: " + countingSortStyle(94042, restricted, reset));

        System.out.println("countingSort4: " + countingSortStyle(64117, restricted, reset));

        System.out.println("countingSort5: " + countingSortStyle(99999, restricted, reset));
    }
    
    public static void linearSearchTest(List<int[]> restricted) {
        System.out.println("linearSearch1: " + linearSearchStyle(94030, restricted));

        System.out.println("linearSearch2: " + linearSearchStyle(94051, restricted));

        System.out.println("linearSearch3: " + linearSearchStyle(94042, restricted));

        System.out.println("linearSearch4: " + linearSearchStyle(64117, restricted));

        System.out.println("linearSearch5: " + linearSearchStyle(99999, restricted));
    }

    public static void main(String[] args) {
        // Begin Counting Sort Version tests.
        List<int[]> countingRestricted = new ArrayList<>();
        countingRestricted.add(new int[]{94030, 94051});
        
        System.out.println("Counting Test1...");
        countingSortTest(countingRestricted, false); // Initial Test.
        
        countingRestricted.add(new int[]{95722, 99999}); //99999 should now be false.
        countingRestricted.add(new int[]{30210, 80210}); //64117 should now be false.
        countingRestricted.add(new int[]{13579, 24680}); //No changes.

        System.out.println("Counting Test2...");
        countingSortTest(countingRestricted, false); // Additional zip codes added.

        List<int[]> resetRestricted = new ArrayList<>();
        resetRestricted.add(new int[]{94030, 94051});
        
        System.out.println("Counting Test3...");
        countingSortTest(resetRestricted, true); // Reset restricted zip codes.
        // End of Counting Sort Version tests.
        
        
        // Begin Linear Search Version tests.
        List<int[]> linearRestricted = new ArrayList<>();
        linearRestricted.add(new int[]{94030, 94051});
        
        System.out.println("Linear Test1...");
        linearSearchTest(linearRestricted); // Initial Test.
        
        linearRestricted.add(new int[]{95722, 99999}); //99999 should now be false.
        linearRestricted.add(new int[]{30210, 80210}); //64117 should now be false.
        linearRestricted.add(new int[]{13579, 24680}); //No changes.

        System.out.println("Linear Test2...");
        linearSearchTest(linearRestricted); // Additional zip codes added.
        // End of Linear Search Version tests.
    }
}
