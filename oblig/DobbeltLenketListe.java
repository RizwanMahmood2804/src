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
    throw new UnsupportedOperationException("Ikke laget ennå!");
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
      hode = hale = new Node<>(verdi, null);
    }
    else
    {
      hale = hale.neste = new Node<>(verdi, null);  // legges bakerst
    }

    antallEndringer++;         // en ny endring
    antall++;                  // en mer i listen

    return true;               // vellykket innlegging
  }

  @Override
  public void leggInn(int indeks, T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public boolean inneholder(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T hent(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public int indeksTil(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T oppdater(int indeks, T nyverdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T fjern(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public String toString()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
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
