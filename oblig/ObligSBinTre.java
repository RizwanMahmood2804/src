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
    public boolean fjern(T verdi) {
       
        if(verdi == null) return false;
           
        Node<T> p = rot;
       
        while(p!=null){
            int cmp = comp.compare(verdi,p.verdi);
           
            if(cmp < 0) p=p.venstre;
            else if(cmp > 0) p=p.høyre;
            else break;
        }
       
        if (p==null) return false;
       
        if (p.venstre==null || p.høyre==null) {
           
            Node<T> b = (p.venstre!=null) ? p.venstre : p.høyre;
           
            if (p == rot) {
                rot =  b;
                if(b!=null) b.forelder=null;
            }
            else if (p==p.forelder.venstre) {
                if(b!=null)b.forelder = p.forelder;
                p.forelder.venstre = b;
            } else {
               
                if(b!=null)b.forelder = p.forelder;
                p.forelder.høyre = b;
            }
        }
        else {
           
            Node<T> r = p.høyre;
            while (r.venstre != null) r = r.venstre;
            p.verdi = r.verdi;
           
            if(r.forelder!=p) {
                Node<T> q = r.forelder;
                q.venstre = r.høyre;
                if(q.venstre!=null)q.venstre.forelder = q;
            }
            else{    
                p.høyre =  r.høyre;              
                if(p.høyre !=null) p.høyre.forelder = p;
 
            }
        }
       
        antall--;
        return true;      
    }
  
  public int fjernAlle(T verdi)
  {
      int i = 0;
      boolean fjernet = true;
      while(fjernet!=false){
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
        while(p.høyre != null || p.venstre!=null ){
            if(p.høyre != null)
                p = p.høyre;
            else p = p.venstre;
            s.append(",").append(" ").append(p);            
        }      
      }
      s.append("]");
      return s.toString();
  }
  
  /*
  7. Lag metoden public String[] grener(). Den skal returnere en 
  String-tabell som inneholder (som tabellelementer) alle grenene i 
  treet i rekkefølge fra venstre mot høyre. Hvis treet er tomt skal det 
  returneres en tom tabell. Flg. kodebit viser hvordan det skal fungere:

  ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());

  char[] verdier = "GSAHQNRBFCJDOEPKIML".toCharArray();
  for (char c : verdier) tre.leggInn(c);

  String[] s = tre.grener();

  for (String gren : s) System.out.println(gren);

  // Utskrift:
  // [G, A, B, F, C, D, E]
  // [G, S, H, Q, N, J, I]
  // [G, S, H, Q, N, J, K, M, L]
  // [G, S, H, Q, N, O, P]
  // [G, S, H, Q, R]
  
  */
  
   public String[] grener() {
        if(tom())return new String[0];
        String[] stringTabell = new String[1];
        StringJoiner s;
        ArrayDeque<Node<T>> que = new ArrayDeque();
        ArrayDeque<Node<T>> nodegrenque = new ArrayDeque();

        boolean tomListe = false;
        
        Node<T> p = rot;
        int i=0;
        
        while(!tomListe){
            s = new StringJoiner(", ","[","]");
    
            while(p.venstre!=null || p.høyre!=null) {
                if(p.venstre!=null) {
                    if(p.høyre!=null) que.add(p.høyre);
                    p = p.venstre;
                }else if(p.høyre!=null){
                    p = p.høyre; 
                }
            }

            while(p!=null) {nodegrenque.add(p);p=p.forelder;}
            
            while(!nodegrenque.isEmpty()) 
                s.add(nodegrenque.pollLast().toString());
            
            if(stringTabell[stringTabell.length-1]!=null) 
                stringTabell = Arrays.copyOf(stringTabell, stringTabell.length+1);
            stringTabell[i++] = s.toString();

            if(!que.isEmpty()) p = que.pollLast();
            else tomListe = true;            
        }

        return stringTabell;
    }
  
   /*
8. Lag metoden public String bladnodeverdier(). Den skal returnere en 
   tegnstreng med verdiene i bladnodene. Tegnstrengen skal se ut som 
   vanlig (med [ og ] og med komma og mellomrom). Her skal du bruke en 
   rekursiv hjelpemetode som traverserer treet. Bladnodeverdiene skal i 
   tegnstrengen stå i rekkefølge fra venstre mot høyre.   
   */
    public String bladnodeverdier() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node p = rot;
        finnBladnode(p, sb);
        sb.append("]");
        return sb.toString();
    }
    private void finnBladnode(Node p, StringBuilder sb) {
        if (p == null) {
	    return;
        }
        if(p.venstre == null && p.høyre == null){
            if(!sb.toString().equals("[")){
                sb.append(",").append(" ").append(p);
            } else {
                sb.append(p);
            }
        }        
        finnBladnode(p.venstre, sb);
        finnBladnode(p.høyre, sb);
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
