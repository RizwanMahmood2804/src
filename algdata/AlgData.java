package algdata;

import hjelpeklasser.Tabell;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.*;
import oblig.Oblig1;

/**
 *
 * @author kristofferjohansen
 */
public class AlgData {

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
//        int[] a = {4,1,3,5,4,3,32,5,2,134,35,25,1,1,3,5,21};
        int TREKK = 15;
/*
        int[] a = new int[TREKK];
        Random randomGenerator = new Random();
        
        for(int i = 0; i<TREKK; i++){
        int randomInt = randomGenerator.nextInt(100);
            a[i] = randomInt;
        }
*/
//        int[] a = {5,2,6,32,12,52,5,21,2,5,6,3,1,32,5,2,5,6};
        int[] a = {3,2,1,1,3,1,2};
        
        char[] c = {'a', 'b','c','d','e','f','g','h','i','j'};
        System.out.println(Arrays.toString(c));
        Oblig1.rotasjon(c, 4);
        System.out.println(Arrays.toString(c));
//        System.out.println(Oblig1.antallUlikeSortert(Oblig1.sortering2(a)));
//        System.out.println("Antall ulike sorter: "+Oblig1.antallUlikeUsortert(a));
//        Oblig1.sortering(a);
        
     }
}
