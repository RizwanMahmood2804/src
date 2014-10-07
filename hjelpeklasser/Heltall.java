/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hjelpeklasser;

/**
 *
 * @author kristofferjohansen
 */
public final class Heltall implements Comparable<Heltall> {

    private final int verdi;
    
    public Heltall(int verdi) {
        this.verdi = verdi;
    }
    
    public int intVerdi(){
        return verdi;
    }

    @Override
    public int compareTo(Heltall h) {
        return verdi < h.verdi ? -1 : (verdi == h.verdi ? 0 : 1);
    }
    

    @Override
    public String toString() {
        return String.valueOf(verdi);
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Heltall)) return false;
        else return verdi == ((Heltall)o).verdi;
    }
    
    public boolean equals(Heltall h){
        return verdi == h.verdi;
    }
}
