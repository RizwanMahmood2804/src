package oblig;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
    
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
    if (verdi == null) return false;  // treet har ingen nullverdier

    Node<T> p = rot, q = null;   // q skal være forelder til p

    while (p != null)            // leter etter verdi
    {
      int cmp = comp.compare(verdi,p.verdi);      // sammenligner
      if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
      else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
      else break;    // den søkte verdien ligger i p
    }
    if (p == null) return false;   // finner ikke verdi

    if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
    {
      Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
        System.out.println("SATAN");
      if (p == rot){
          b.forelder = null;
          rot = b;
      } 
      else if (p == q.venstre) {
          q.venstre = b;
      }
      else{
          q.høyre = b;
          b.forelder = q;
      } 
    }
    else  // Tilfelle 3)
    {
      Node<T> s = p, r = p.høyre;   // finner neste i inorden
      if(p == rot){
          System.out.println("ROT");
                    if(p.venstre==null){
                            if(p.høyre==null){
                                rot = null;
                            } else{
                                p.høyre.forelder = null;
                                rot = p.høyre;
                            }
                    }
                    else if(p.høyre==null){
                            p.venstre.forelder = null;
                            rot = p.venstre;
                    }else{
                        Node u = p;
                            u = p.venstre;
                            if(u.høyre != null){
                                while(u.høyre != null){
                                    u = u.høyre;
                                }                            
                            }
                            
//                            String minTitle = minTitle(root);
                        Node temp = p.høyre;
                        
                        p.venstre.forelder = null;
                        rot = p.venstre;
                        u.høyre = temp;
                    }
        }else {
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
                r.forelder = s;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p){

                s.venstre = r.høyre;
                s.forelder = r;
                System.out.println("shit luck");
            }else {
                s.høyre = r.høyre;

                s.forelder = q;
                s.høyre.forelder = s;

            }      
        }
      
    }

    antall--;   // det er nå én node mindre i treet
    return true;
  }
  
  public int fjernAlle(T verdi)
  {
      int i = 0;
      boolean fjernet = true;
      while(fjernet!=false){
          System.out.println("verdi"+verdi);
          if(fjern(verdi))
            i++;
          else 
              fjernet = false;
      }
      return i;
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
    public void nullstill() {
        rot = null;
        antall = 0;
    }
  
    private static <T> Node<T> nesteInorden(Node<T> temp) {
        if (temp == null)
            return null;
        if (temp.høyre != null)
            return finnLaveste(temp.høyre);
        Node y = temp.forelder;
        Node x = temp;
        while (y != null && x == y.høyre) {
            x = y;
            y = y.forelder;
        }
        return y;
    }
    
    
    private static<T> Node<T> finnLaveste(Node<T> p){
            if (p == null)
                return null;
            if (p.venstre != null)
                return finnLaveste(p.venstre);
            return p;
    }

    
    @Override
    public String toString() {                   // hører til SBinTre 
        StringBuilder s = new StringBuilder();   // StringBuilder
        s.append('[');                           // starter med [
        Node p = rot;
        if(p!=null){
            while(p.venstre != null)
                p= finnLaveste(p);
            s.append(p);
            while(nesteInorden(p)!=null){
                p = nesteInorden(p);
                s.append(",").append(" ").append(p);            
            }        
        }
        s.append(']');
        return s.toString();
    }

    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Stack stakk = new Stack();
        Node p = finnLaveste(rot);
        if(p != null){
            stakk.push (p);
            while (nesteInorden(p) != null) { 
                p = nesteInorden(p);
                stakk.push (p);
            }                
        }

        while ( !stakk.empty() )
        {
                s.append( stakk.pop() );
                if(!stakk.empty())
                    s.append(",").append(" ");
        }
        s.append(']');
        return s.toString();
    }
  
  public String høyreGren()
  {
      StringBuilder s = new StringBuilder();
      s.append("[");
      if(rot != null){
        Node p = rot;
        s.append(p);
        while(p.høyre != null){
            p = p.høyre;
            s.append(",").append(" ").append(p);            
        }      
      }
      s.append("]");
      return s.toString();
  }
  
    public String[] grener() {
        Queue<Object> q = new LinkedList<Object>();
        String[] st = new String[0];
        q.add(rot);
        q.add(rot.verdi + "");
        int size =0;
        while(!q.isEmpty()){

            Node p = (Node) q.remove();
            String headPath = (String) q.remove();

            if(p.venstre==null && p.høyre==null){
                String[] tempArray = new String[size+1];
                    System.arraycopy(st, 0, tempArray, 0, size);
                    tempArray[size] = headPath;
                    st = tempArray;
                    size++;         
//                System.out.println(headPath);
                continue;
            }

            if(p.venstre!=null){
                String leftStr =  headPath + ", " + p.venstre.verdi;
                q.add(p.venstre);
                q.add(leftStr);
                System.out.println("leftStr "+leftStr);
            }

            if(p.høyre!=null){
                String rightStr =  headPath + ", " + p.høyre.verdi;
                q.add(p.høyre);
                q.add(rightStr);
            }
        }
        return st;
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
