package algdata;

import hjelpeklasser.Heltall;
import hjelpeklasser.Tabell;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        int[] a = Tabell.randPerm(10);
        Heltall[] h = new Heltall[a.length];
        
        for(int i = 0; i< h.length; i++) h[i]=new Heltall(a[i]);
        
        List<Heltall> liste = new ArrayList<>();
        for(Heltall x:h) liste.add(x);
        
        System.out.println(Arrays.toString(h));
        
        Heltall fem = new Heltall(5);
        System.out.println(liste.contains(fem));
     }
}
