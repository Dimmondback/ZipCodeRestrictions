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
    
    /*
     * @param restricted: The list of int[] restricted zip codes.
     * @param reset: Whether or not to use a new list of restricted zip codes
     * or to build off of the previous one.
     */
    public static List<int[]> restrictedZipCodes(List<int[]> restricted, boolean reset) {
        if (reset) {
            zipcodeList = new int[100000];
        }

        for (int[] current : restricted) {
            for (int i = current[0]; i <= current[1]; i++) { // Treat it like counting-sort and mark places that can't be sent to.
                zipcodeList[i] = 1;
            }
        }
        
        int lowerbound = -1;
        List<int[]> restrictedZipCodes = new ArrayList<>();
        for(int i = 0; i < zipcodeList.length; i++) {
            if (zipcodeList[i] == 0 && lowerbound != -1) {
                restrictedZipCodes.add(new int[]{lowerbound, i-1});
                lowerbound = -1;
            } else if (zipcodeList[i] == 1 && lowerbound == -1) {
                lowerbound = i;
            }
        }
        
        for(int i = 0; i < restrictedZipCodes.size(); i++) {
            System.out.print(restrictedZipCodes.get(i)[0] + ", ");
            System.out.println(restrictedZipCodes.get(i)[1]);
        }

        return restrictedZipCodes;
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
        List<int[]> restricted = new ArrayList<>();
        restricted.add(new int[]{94030, 94051});
        restricted.add(new int[]{94045, 95000});
        restricted.add(new int[]{30210, 30210});
        restricted.add(new int[]{13579, 24680});

        restrictedZipCodes(restricted, false);
    }
}
