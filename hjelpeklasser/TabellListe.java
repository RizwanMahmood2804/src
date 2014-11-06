////////////////// class TabellListe  //////////////////////////////

package hjelpeklasser;

import java.util.*;

public class TabellListe<T> implements Liste<T>
{
  private T[] a;
  private int antall;
  private int antallEndringer;    // ny variabel


  private void indeksKontroll(int indeks)
  {
    if (indeks < 0)
      throw new IndexOutOfBoundsException("Indeks " +
         indeks + " er negativ!");
    else if (indeks >= antall)
      throw new IndexOutOfBoundsException("Indeks " +
          indeks + " >= antall(" + antall + ") noder!");
  }

  public TabellListe(int størrelse)
  {
    a = (T[])new Object[størrelse];
    antall = 0;
    antallEndringer = 0;
  }

  public TabellListe()
  {
    this(10);
  }

  public TabellListe(Iterable<T> itererbar)    // konstruktør
  {
    this();   // bruker standardkonstruktøren

    Iterator<T> i = itererbar.iterator();

    while (i.hasNext())
    {
      if (antall >= a.length)
        a = Arrays.copyOf(a,(3*antall)/2);  // utvider 50%

      a[antall++] = i.next();
    }
  }

  @Override
  public void leggInn(int indeks, T t)
  {
    if (t == null) throw new
      NullPointerException("Ulovlig å legge inn null-verdier!");

    if (indeks < 0 || indeks > antall) throw new
      IndexOutOfBoundsException("Indeks " + indeks + " er ulovlig!");

    // En full tabell utvides med 50%
    if (antall == a.length)
      a = Arrays.copyOf(a,(3*antall)/2 + 1);

    // rydder plass til den nye verdien
    System.arraycopy(a, indeks, a, indeks + 1, antall - indeks);

    //for (int i = antall; i > indeks; i--) a[i] = a[i-1];

    a[indeks] = t;     // setter inn ny verdi

    antall++;
    antallEndringer++;
  }

  @Override
  public boolean leggInn(T t)  // inn bakerst
  {
    if (t == null) throw
      new NullPointerException("Ulovlig å legge inn null-verdier!");

    // En full tabell utvides med 50%
    if (antall == a.length)
    {
      a = Arrays.copyOf(a,(3*antall)/2 + 1);
    }

    a[antall++] = t;   // setter inn ny verdi
    antallEndringer++;

    return true;
  }

  @Override
  public int indeksTil(T t)
  {
    for (int i = 0; i < antall; i++)
    {
      if (a[i].equals(t)) return i;    // funnet!
    }
    return -1;  // ikke funnet!
  }

  @Override
  public boolean inneholder(T t)
  {
    return indeksTil(t) != -1;
  }

  @Override
  public T hent(int indeks)
  {
    indeksKontroll(indeks);      // tester indeksen
    return a[indeks];            // returnerer er tabellelement
  }

  @Override
  public T oppdater(int indeks, T t)
  {
    if (t == null) throw new
      NullPointerException("Ulovlig å legge inn null-verdier!");

    indeksKontroll(indeks);      // tester indeksen

    T gammelverdi = a[indeks];   // tar vare på den gamle verdien
    a[indeks] = t;         // oppdaterer

    antallEndringer++;

    return gammelverdi;          // returnerer den gamle verdien
  }

  @Override
  public T fjern(int indeks)
  {
    indeksKontroll(indeks);
    T verdi = a[indeks];

    antall--; // sletter ved å flytte verdier forover
    System.arraycopy(a, indeks + 1, a, indeks, antall - indeks);
    a[antall] = null;

    antallEndringer++;

    return verdi;
  }

  @Override
  public boolean fjern(T t)
  {
    if (t == null) return false;  // listen har ikke null-verdier

    for (int i = 0; i < antall; i++)
    {
      if (a[i].equals(t))
      {
        antall--;
        System.arraycopy(a, i + 1, a, i, antall - i);

        a[antall] = null;
        antallEndringer++;

        return true;
      }
    }
    return false;
  }

  @Override
  public int antall()
  {
    return antall;          // returnerer antallet
  }

  @Override
  public boolean tom()
  {
    return antall == 0;     // listen er tom hvis antall er 0
  }

  @Override
  public void nullstill()
  {
    if (a.length > 10)
      a = (T[])new Object[10];
    else
      for (int i = 0; i < antall; i++) a[i] = null;

    antall = 0;
    antallEndringer++;
  }

  @Override
  public Iterator<T> iterator()
  {
    return new TabellListeIterator();
  }

  @Override
  public String toString()
  {
    if (antall == 0) return "[]";

    StringBuilder s = new StringBuilder();
    s.append('['); s.append(a[0]);

    for (int i = 1; i < antall; i++)
    {
      s.append(','); s.append(' '); s.append(a[i]);
    }
    s.append(']');

    return s.toString();
  }

  private class TabellListeIterator implements Iterator<T>
  {
    private int denne = 0;              // instansvariabel
    private boolean removeOK = false;   // instansvariabel
    private int forventetAntallEndringer = antallEndringer;  // ny variabel

    @Override
    public boolean hasNext()     // sjekker om det er flere igjen
    {
      return denne < antall;     // sjekker verdien til denne
    }

    @Override
    public T next()
    {
      if (forventetAntallEndringer != antallEndringer) throw new
        ConcurrentModificationException("Listen er endret!");

      if (!hasNext())
        throw new NoSuchElementException("Tomt eller ingen verdier igjen!");

      T denneVerdi = a[denne];   // henter aktuell verdi
      denne++;                   // flytter indeksen

      removeOK = true;           // nå kan remove() kalles

      return denneVerdi;         // returnerer verdien
    }

    @Override
    public void remove()
    {
      if (forventetAntallEndringer != antallEndringer) throw new
        ConcurrentModificationException("Listen er endret!");

      if (!removeOK) throw
        new IllegalStateException("Ulovlig tilstand!");

      removeOK = false;          // remove() kan ikke kalles på nytt

      // verdien i denne - 1 skal fjernes da den ble returnert i siste kall
      // på next(), verdiene fra og med denne flyttes derfor en mot venstre

      antall--;           // en verdi vil bli fjernet
      denne--;            // denne må flyttes til venstre

      // tetter igjen
      System.arraycopy(a, denne + 1, a, denne, antall - denne);

      a[antall] = null;   // verdien som lå lengst til høyre nulles

      antallEndringer++;
      forventetAntallEndringer++;
    }

  }  // class TabellListeIterator

} // class TabellListe

