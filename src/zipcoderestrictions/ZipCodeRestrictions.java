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
        
        return restrictedZipCodes;
    }
    
    public static void main(String[] args) {
        // Begin Counting Sort Version tests.
        List<int[]> restricted = new ArrayList<>();
        restricted.add(new int[]{94030, 94051});
        
        List<int[]> restrictedZipCodes1 = restrictedZipCodes(restricted, false);
        for(int i = 0; i < restrictedZipCodes1.size(); i++) {
            System.out.print(restrictedZipCodes1.get(i)[0] + ", ");
            System.out.println(restrictedZipCodes1.get(i)[1]);
        }
        
        restricted.add(new int[]{94045, 95000});
        restricted.add(new int[]{30210, 30210});
        restricted.add(new int[]{13579, 24680});
        
        List<int[]> restrictedZipCodes2 = restrictedZipCodes(restricted, false);
        for(int i = 0; i < restrictedZipCodes2.size(); i++) {
            System.out.print(restrictedZipCodes2.get(i)[0] + ", ");
            System.out.println(restrictedZipCodes2.get(i)[1]);
        }
    }
}

