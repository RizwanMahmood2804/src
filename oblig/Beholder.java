/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oblig;

/**
 *
 * @author kristofferjohansen
 */
  import java.util.*;
  import java.util.function.Predicate;

  public interface Beholder<T> extends Iterable<T>  // ny versjon
  {
    public boolean leggInn(T t);       // legger inn t i beholderen
    public boolean inneholder(T t);    // sjekker om den inneholder t
    public boolean fjern(T t);         // fjerner t fra beholderen
    public int antall();               // returnerer antallet i beholderen
    public boolean tom();              // sjekker om beholderen er tom
    public void nullstill();           // tømmer beholderen
    public Iterator<T> iterator();     // returnerer en iterator

    default boolean fjernHvis(Predicate<? super T> p)  // betingelsesfjerning
    {
      Objects.requireNonNull(p);                       // kaster unntak

      boolean fjernet = false;
      for (Iterator<T> i = iterator(); i.hasNext(); )  // løkke
      {
        if (p.test(i.next()))                          // betingelsen
        {
          i.remove(); fjernet = true;                  // fjerner
        }
      }
      return fjernet;
    }

  } // interface Beholder