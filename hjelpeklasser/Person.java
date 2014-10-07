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
public class Person implements Comparable<Person> {
    private final String fornavn;
    private final String etternavn;

    public Person(String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }
    
    @Override
    public int compareTo(Person p){
        int cmp = etternavn.compareTo(p.etternavn);
        if(cmp != 0)return cmp;
        return fornavn.compareTo(p.fornavn);
    }
    
    @Override
    public String toString(){
    return fornavn + " " + etternavn;
    }
    
}
