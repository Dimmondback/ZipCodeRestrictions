/*
 * Given a range of restricted zip codes in the format [low, high], see whether
 * or not your package will be allowed to ship to a selected zip code.
 */
package zipcoderestrictions;

import java.util.ArrayList;
import java.util.List;

public class ZipCodeRestrictions {

    //Total list of zip codes.
    public static int[] zipcodeList = new int[100000]; //[0, 99999]

    /*
     * @param zipcodes: The int[] list of restricted zip codes.
     * @param reset: Whether or not to use a new list of restricted zip codes
     * or to build off of the previous one.
     */
    public static boolean sendPackage(int zipcode, List<int[]> restricted, boolean reset) {
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
    
    public static void test(List<int[]> restricted, boolean reset) {
        System.out.println("sent1: " + sendPackage(94030, restricted, reset));

        System.out.println("sent2: " + sendPackage(94051, restricted, reset));

        System.out.println("sent3: " + sendPackage(94042, restricted, reset));

        System.out.println("sent4: " + sendPackage(64117, restricted, reset));

        System.out.println("sent5: " + sendPackage(99999, restricted, reset));
    }

    public static void main(String[] args) {
        List<int[]> restricted = new ArrayList<>();
        restricted.add(new int[]{94030, 94051});
        
        System.out.println("Test1...");
        test(restricted, false); // Initial Test.
        
        restricted.add(new int[]{95722, 99999}); //99999 should now be false.
        restricted.add(new int[]{30210, 80210}); //64117 should now be false.
        restricted.add(new int[]{13579, 24680}); //No changes.

        System.out.println("Test2...");
        test(restricted, false); // Additional zip codes added.

        List<int[]> newRestricted = new ArrayList<>();
        newRestricted.add(new int[]{94030, 94051});
        
        System.out.println("Test3...");
        test(newRestricted, true); // Reset restricted zip codes.
    }
}
