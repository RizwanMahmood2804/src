package oblig;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{


    ObligSBinTre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }
    private Node(T verdi)  // konstruktør
    {
      this(verdi, null, null, null);
    }
    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  
  public static <T extends Comparable<? super T>> ObligSBinTre<T> naturligOrdenTre()
  {
    return new ObligSBinTre<>(Comparator.naturalOrder());
  }

  public static <T> ObligSBinTre<T> komparatorTre(Comparator<? super T> c)
  {
    return new ObligSBinTre<>(c);
  }  
  
  @Override
  public boolean leggInn(T verdi)    // skal ligge i class SBinTre
  {
    Node<T> p = rot, q = null;               // p starter i roten
    int cmp = 0;                             // hjelpevariabel

    while (p != null)       // fortsetter til p er ute av treet
    {
      q = p;                                 // q forelder til p
      cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
      p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
    }

    p = new Node<>(verdi);                   // oppretter en ny node

    if (q == null) rot = p;                  // rotnoden
    else if (cmp < 0) q.venstre = p;         // til venstre for q
    else q.høyre = p;                        // til høyre for q
    
    if(q!= null){
        p.forelder = q;
    } else {
        p.forelder = null;
    }

    antall++;
    return true;
  }
  
    @Override
    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }
  
  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public int fjernAlle(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi)
  {
    if(verdi==null) return 0;

    int a=0; // Oppstartsverdi

    Node<T> p = rot;
    while (p != null) {
        int cmp = comp.compare(verdi,p.verdi);
        if (cmp < 0) p = p.venstre;      // går til venstre
        else if (cmp > 0) p = p.høyre;   // går til høyre
        else {
            a++;
            p = p.høyre;
        }
    }
    return a;
  }

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public void nullstill()
  {
    rot = null;
    antall = 0;
  }
  
    private static <T> Node<T> nesteInorden(Node<T> p) {
        System.out.println("p"+p);
        if (p.venstre != null){                  // p har et venstre subtre
            nesteInorden(p.venstre);              // komma og mellomrom etter
//            s.append(',').append(' ');           // den siste i det venstre
        }                                        // subtreet til p
//        s.append(p.verdi);                       // verdien i p
        
        if (p.høyre != null){                    // p har et høyre subtre
//            s.append(',').append(' ');           // komma og mellomrom etter
            nesteInorden(p.høyre);                // p siden p ikke er den
        }
        return p;
    }
  
    @Override
    public String toString() {                   // hører til SBinTre 
        StringBuilder s = new StringBuilder();   // StringBuilder
        s.append('[');                           // starter med [
        if (!tom()) {
//            s.append(rot);
            Node p = rot;
//                System.out.println("s "+s);
                s.append(",").append(" ");
                s.append(p);
                p = nesteInorden(p);
                for(int i = 0; i<4;i++){
                    s.append(",").append(" ");
                    s.append(p);
                    p = nesteInorden(p);                
                }
                System.out.println("s"+s);
        }
        s.append(']');                           // avslutter med ]
        return s.toString();                     // returnerer
    }
    private static <T> void toString(Node<T> p, StringBuilder s) {
        if (p.venstre != null){                  // p har et venstre subtre

            toString(p.venstre, s);              // komma og mellomrom etter
            s.append(',').append(' ');           // den siste i det venstre
        }                                        // subtreet til p

        s.append(p.verdi);                       // verdien i p
        if (p.høyre != null){                    // p har et høyre subtre
            s.append(',').append(' ');           // komma og mellomrom etter
            toString(p.høyre, s);                // p siden p ikke er den
        }                                        // siste noden i inorden
    }
  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String høyreGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

  } // BladnodeIterator

} // ObligSBinTre
