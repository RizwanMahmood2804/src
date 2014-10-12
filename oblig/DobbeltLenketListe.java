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

public class DobbeltLenketListe<T> implements Liste<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    // instansvariabler
    private T verdi;
    private Node<T> forrige, neste;

    // konstruktør
    private Node(T verdi, Node<T> forrige, Node<T> neste)
    {
      this.verdi = verdi;
      this.forrige = forrige;
      this.neste = neste;
    }
  }

  // instansvariabler
  private Node<T> hode;          // peker til den første i listen
  private Node<T> hale;          // peker til den siste i listen
  private int antall;            // antall noder i listen
  private int antallEndringer;   // antall endringer i listen

  // hjelpemetode
  private void indeksKontroll(int indeks)
  {
    if (indeks < 0)
    {
      throw new IndexOutOfBoundsException("Indeks " +
        indeks + " er negativ!");
    }
    else if (indeks >= antall)
    {
      throw new IndexOutOfBoundsException("Indeks " +
        indeks + " >= antall(" + antall + ") noder!");
    }
  }

  // hjelpemetode
  private Node<T> finnNode(int indeks)
  {
      
      Node<T> peker = null;
      if(indeks == 0)
          return hode;
      if(indeks < antall && indeks >0){
        if(indeks < antall/2){
            peker = hode;
            for (int i = 0; i < indeks; i++) peker = peker.neste;        
        } else {
            peker = hale;
            for(int i = antall-1; i > indeks; i--) peker = peker.forrige;
        }
      }
    return peker;
  }

  // konstruktør
  public DobbeltLenketListe()
  {
    hode = hale = null;
    antall = 0;
    antallEndringer = 0;
  }

  @Override
  public int antall()
  {
      return antall;
  }
 
  public int antallEndringer(){
      return antallEndringer;
  }
  
  @Override
  public boolean tom()
  {
    return  antall == 0;
  }

  @Override
  public boolean leggInn(T verdi)
  {
    if (verdi == null)
    {
      throw new NullPointerException("Ikke tillatt med null-verdier!");
    }

    if (antall == 0)           // sjekker om listen er tom
    {
      hode = hale = new Node<>(verdi,null, null);
    }
    else
    {
      hale = hale.neste = new Node<>(verdi,hale, null);  // legges bakerst
    }

    antallEndringer++;         // en ny endring
    antall++;                  // en mer i listen

    return true;               // vellykket innlegging
  }

  @Override
  public void leggInn(int indeks, T verdi)
  {
    if (verdi == null)
    {
      throw new NullPointerException
        ("Ikke tillatt med null-verdier!");
    }  
    if (indeks < 0 || indeks > antall)
    {
      throw new IndexOutOfBoundsException
        ("Indeks(" + indeks + ") er ulovlig!");
    }
        Node hp = null;
        
        if(indeks == 0){

            if(antall == 0)
                hale=hode=new Node<>(verdi, null, null);
            else{
                hp=hode;
                hode = hp.forrige = new Node<>(verdi, null, hode.neste);
                hode.neste = hp;
            }
        }else if(indeks==antall){
            hale = hale.neste = new Node<>(verdi,hale, null); 
        } else {
            Node<T> p = hode;    // må telle fra hode og til indeks - 1
            for (int i = 1; i < indeks; i++)
            {
              p = p.neste;
            }
            p.neste = new Node<>(verdi,p, p.neste);  // ny node settes på rett plass
            p.neste.neste.forrige = p.neste;
        }
        antall++;
        antallEndringer++;
  }

  @Override
  public boolean inneholder(T verdi)
  {
      if(indeksTil(verdi) != -1){
          return true;
      }
      else return false;
  }

  @Override
  public T hent(int indeks)
  {
      indeksKontroll(indeks);
      return finnNode(indeks).verdi;
      
  }

  @Override
  public int indeksTil(T verdi)
  {
      Node peker = hode;
      int indeks = -1;
      for(int i=0; i<antall;i++){
          if(peker.verdi.equals(verdi)){
              indeks = i;
              break;
          }
          peker = peker.neste;
      }
      return indeks;    
  }

  @Override
  public T oppdater(int indeks, T nyverdi)
  {
      indeksKontroll(indeks);
      if(nyverdi == null)throw new NullPointerException("Ikke tillatt med nullverdier");
      Node<T> peker = finnNode(indeks);
      T gammelverdi = peker.verdi;
      peker.verdi = nyverdi;
      antallEndringer++;
      return gammelverdi;
  }

  @Override
  public boolean fjern(T verdi)
  {
      if(antall == 0) throw new IndexOutOfBoundsException("Tabellen er tom");
      if(inneholder(verdi)) {
        Node<T> peker = hode;
        for(int i = 0; i<antall-1; i++){
            if(peker.verdi.equals(verdi)){
                fjern(peker.verdi);
                return true;
            }
            peker = peker.neste;
        }
      }
    return false;
  }

    @Override
    public T fjern(int indeks) {
//      indeksKontroll(indeks);
        T returverdi;
        if(antall == 0) throw new IndexOutOfBoundsException("Tabellen er tom");
        if(indeks >antall-1 || indeks <0) throw new IndexOutOfBoundsException("Indeksen du vil fjerne finnes ikke");

        if (indeks == 0) {
            returverdi = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                  // hode flyttes til neste node
            hode.forrige = null;
            if (antall == 1) hale = null;      // det var kun en verdi i listen
        }else if((antall-1)==indeks){
            System.out.println("Bever");
            returverdi = hode.verdi;
        } else {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            returverdi = q.verdi;                    // tar vare på verdien som skal fjernes
            if (q == hale) hale = p;           // q er siste node
            p.neste = q.neste;                 // "hopper over" q
        }
        antallEndringer++;                   // en ny endring
        antall--;                            // reduserer antallet
        return returverdi;
    }

  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public String toString()
  {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      Node peker = hode;
      if(peker != null)
          sb.append(peker.verdi);
      while(peker !=null && peker.neste !=null){
//          System.out.println("Peker.neste.verdi"+peker.neste.verdi);
          sb.append(", "+peker.neste.verdi);
          peker = peker.neste;
      }
      sb.append("]");
      return sb.toString();
  }

  public String omvendtString()
  {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      Node peker = hale;
      if(peker!=null)
          sb.append(peker.verdi);
      while(peker!=null && peker.forrige!=null){
        sb.append(", "+peker.forrige.verdi);
        peker = peker.forrige;
      }
      sb.append("]");
      return sb.toString();
  }

  @Override
  public Iterator<T> iterator()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  public Iterator<T> iterator(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  private class DobbeltLenketListeIterator implements Iterator<T>
  {
    private Node<T> denne;
    private boolean fjernOK;
    private int forventetAntallEndringer;

    private DobbeltLenketListeIterator()
    {
      denne = hode;     // denne starter på den første i listen
      fjernOK = false;  // blir sann når next() kalles
      forventetAntallEndringer = antallEndringer;  // teller endringer
    }

    @Override
    public boolean hasNext()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

  } // DobbeltLenketListeIterator  

} // DobbeltLenketListe  