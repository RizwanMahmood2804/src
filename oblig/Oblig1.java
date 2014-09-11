/*
    Kristoffer Johansen
    s193370
    HiNGDATA13H2AA
*/

package oblig;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

// Oppgave 1
/*
Lag metoden public static int maks(int[] a). Den skal finne og returnere den 
    største verdien (ikke posisjonen) i parametertabellen a og den skal bruke 
    flg. teknikk: Sammenlign først a[0] og a[1]. Hvis a[0] > a[1], så bytter de 
    to verdiene plass. Sammenlign så a[1] og a[2]. Hvis a[1] > a[2], bytter de 
    to plass. Osv. Når denne prosessen er ferdig vil tabellens største verdi 
    ligge bakerst (sjekk at det stemmer). Dermed kan den verdien returneres. 
    Det skal kastes en NoSuchElementException (med en passende tekst) hvis 
    tabellen a er tom.

La tabellen ha lengde n. Hvor mange sammenligninger av tabellverdier blir det?

Antall ombyttinger vil være avhengig av hvordan verdiene ligger. Når blir det 
    færrest ombyttinger? Når blir det flest ombyttinger? Finn hvor mange 
    ombyttinger det blir i gjennomsnitt for en tabell med n forskjellige verdier.
    Her kan du lage kode som teller opp ombyttingene. Det kan gi deg en 
    indikasjon på hvor mange det blir i gjennomsnitt. Er dette en god metode for 
    å finne den største verdien i en tabell?
*/

    public static int maks(int[] a){
        if(a.length < 1) throw new NoSuchElementException("Du har en tom tabell. Det er ikke noe å sortere.");
        int maks = 0;
        int temp = 0;
        int antBytter = 0;
        if(a.length>1){
            for(int i = 0; i<a.length-1; i++){
                if(a[i] > a[i+1]){
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    antBytter++;
                }
                maks=a[i+1];
            }
        }else if(a.length==1){
            maks = a[0];
        }
        return maks;
    }

    /*
    Det er bare en tabellaksess, og det er jo forsåidt bra for hurtighet. Jo mer
    sortert i stigende rekkefølgen tabellen er jo færre bytter, men er den lite 
    sortert vil den kreve mange bytter.
    */
    
// Oppgave 2
/*
Lag metoden public static void sortering(int[] a). Den skal sortere 
    paramtertabellen a ved å bruke idéen i Oppgave 1 gjentatte ganger. Idéen 
    gjør at den største verdien havner bakerst. Så gjentas dette slik at nest 
    største verdi (dvs. den største av de gjenværende) havner nest bakerst. Osv. 
    Unngå unødvendige sammenligninger. OBS: En tabell som er tom eller har kun 
    ett element, er allerede sortert. Med andre ord utgjør ikke det noen 
    feilsituasjoner.

La tabellen ha lengde n. Hvor mange sammenligninger av tabellverdier blir det? 
    Oppgi det som en funksjon av n. Hvilken orden har metoden?
*/
    public static void sortering(int[] a){
        if(a.length >1){
        int maks = 0;
        int temp = 0;
        int antBytter = 0;
            for(int i = a.length; i>0;i--){
                for(int j= 0; j< i-1; j++){
                    if(a[j] > a[j+1]){
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
                antBytter++;
                }
            }
        }
    }

    /*
    Antall sorteringer; 
    F.eks. er det 10 tall som skal gås gjennom, vil det være 9 
    sorteringer/sammenlikninger første gang, 8 andre gang, 7 tredje gang osv.. 
    Så da kan man bruke formelen for aritemtisk rekke.
    n = hvor mange tall i rekken
    n-1 = antall sorteringer 
    (synkende tall, færre tall å sortere gir færre sorteringer)
    ((n-1)(n))/2
    */
    
// Oppgave 3
    public static int antallUlikeSortert(int[] a){
        int antallUlike = 0;   
        if(a.length > 1){
            antallUlike = 1;
            for(int i = 0; i<a.length-1; i++){
                if(a[i] <= a[i+1]){
                    if(a[i] != a[i+1]){
                        antallUlike++;
                    }
                } else {
                    throw new IllegalStateException("Tallrekken er ikke sortert. Kanskje du bør kjøre den gjennom en sorteringsmetode først?");
                }
            }
        }else if(a.length == 1) {
            antallUlike = 1;            
            return antallUlike;
        }
        return antallUlike;
    }
        
// Oppgave 4

    
    public static int antallUlikeUsortert(int[] a){
        int antallUlike = 0;
        
        if(a.length >1){
            boolean sjekk = false;
            antallUlike++;
            for(int i = 1; i<a.length; i++){
                for(int j = 0; j<i; j++){
                    if(a[i] == a[j]){
                        sjekk =true;
                    }
                }
                if(sjekk != true){
                    antallUlike++;
                    sjekk=false;
                } else{
                    sjekk=false;
                }
            }        
        }else{
            antallUlike = a.length;
        }
        return antallUlike;
    }

// Oppgave 5

    public static void rotasjon(char[] a){
        char temp;
        if(a.length >1){
            temp = a[a.length-1];
            for(int i=a.length-1; i>0; i--){
                a[i] = a[i-1];
            }
            a[0] = temp;
        }
    }

    
// Oppgave 6

    public static void rotasjon(char[] a, int k){
        char[] temp = new char[a.length]; // Opprett hjelpetabell
        if(a.length >1){ // Større enn null
            for(int i = 0; i < a.length; i++){ // Gå gjennom tabellen
                if(k < 0){// Er i+k mindre enn null (aka  negativ
                    if(i+(k+(a.length)) >0 && i+(k+(a.length-1)) < a.length-1) {
                        temp[i+(k+(a.length))] = a[i];
                    } else{
                        temp[(i+(k+(a.length)))%a.length] = a[i];
                    }
                }else if(i+k > (a.length-1)){
                    int u = (i+k)%(a.length);
                    temp[u] = a[i];
                }else {
                    temp[i+k] = a[i];
                }
            }
            for(int j=0; j<a.length; j++){
                a[j] = temp[j];
            }
        }
    }
    
    
    
// Oppgave 7
    public static String flett(String s, String t){
        String nyString = "";
        int lengde;
        if(s.length() <= t.length()){
            lengde = s.length();
            for(int i=0; i<lengde;i++){
                nyString +=s.substring(i, i+1);
                nyString +=t.substring(i, i+1);
            }
            nyString +=t.substring(s.length(), t.length());
        } else {
            lengde = t.length();
            for(int i=0; i<lengde;i++){
                nyString +=s.substring(i, i+1);
                nyString +=t.substring(i, i+1);
            }
            nyString +=s.substring(t.length(), s.length());
        }
        return nyString;
    }


    public static String flett(String... s){
        String output = "";
        int maksLengde = 0;
        for (String item : s) {
            if (item.length() > maksLengde) {
                maksLengde = item.length();
            }
        }
        for(int j = 0; j <maksLengde; j++){
            for (String item : s) {
                if (j < item.length() && item.length() > 0) {
                    output += item.substring(j, j+1);
                }
            }
        }
        return output;
    }

// Oppgave 8

    public static int[] tredjeMaks(int[] a){
        if(a.length < 3)throw new IllegalArgumentException("Her er det noe muffens. "
                + "Skal du ha de tre største tallene? Så må må du ha MINST tre tall!");    
        int n = a.length;  

        int m = 0;
        int nm = 1;
        int nnm = 2;
        if(a[2] >a[1] && a[2] > a[0]) {
            m=2;
            if(a[1]>a[0]){
                nnm=0;
                nm=1;        
            } else{
                nm=0;
                nnm=1;
            }
        }else if(a[1]>a[2]&& a[1]>a[0]){
            m=1;
            if(a[2]>a[0]){
                nnm=0;
                nm=2;        
            } else{
                nm=0;
                nnm=2;
            }    
        }else if(a[0]>a[1]&& a[0]>a[2]){
            m=0;
            if(a[2]>a[1]){
                nm=2;
                nnm=1;
            }else{
                nm=1;
                nnm=2;        
            }
        }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi
        int nestnestmaksverdi = a[nnm];     // 3. største verdi

        for (int i = 3; i < n; i++) {
            if (a[i] > maksverdi && a[i] > nestmaksverdi) {
              nnm = nm;
              nm = m;
              nestnestmaksverdi = nestmaksverdi; // ny nest nest størst
              nestmaksverdi = maksverdi;     // ny nest størst

              m = i;
              maksverdi = a[m];              // ny størst
            } else if(a[i] > nestmaksverdi) {
              nnm = nm;
              nestnestmaksverdi = a[nnm];         // ny nest størst
              nm = i;
              nestmaksverdi = a[nm];         // ny nest størst
            } else if(a[i] > nestnestmaksverdi){
              nnm = i;
              nestnestmaksverdi = a[nnm];         // ny nest størst          
            }
        } // for

        return new int[] {m,nm, nnm};    // returnerer posisjonene til maks, nestmaks og nestnestmaks
    }


    public static void tredjeMaksTest(){
        int[] a = {8,3,5,7,9,6,10,2,1,4};  // maksverdien 10 er i posisjon 6
        int[] ariktig={6,4,0};
        int[] b={1,2,3};
        int[] b2={1,3,2};
        int[] c={2,3,1};
        int[] c2={2,1,3};
        int[] d={3,1,2};
        int[] d2={3,2,1};
        int[] treRiktig ={3,2,1};
        int[] tom={};
        int[] nestenTom={3,2};

        boolean unntak = false;
        try {
            tredjeMaks(tom);
            tredjeMaks(nestenTom);
        }
        catch (Exception e) {
          unntak = true;
          if (!(e instanceof IllegalArgumentException))
            System.out.println("Oppgave 8: Kodefeil: Feil unntak for en tom tabell eller tabell med for få verdier!");
        }

        if (!unntak)
          System.out.println("Oppgave 8: Kodefeil: Mangler unntak for få verdier!");

        if(!Arrays.equals(ariktig,tredjeMaks(a))|| 
            !((treRiktig[0]== b[(tredjeMaks(b)[0])])
                &&(treRiktig[1]== b[tredjeMaks(b)[1]])
                &&(treRiktig[2]== b[tredjeMaks(b)[2]]))||
            !((treRiktig[0]== b2[(tredjeMaks(b2)[0])])
                &&(treRiktig[1]== b2[tredjeMaks(b2)[1]])
                &&(treRiktig[2]== b2[tredjeMaks(b2)[2]]))|| 
            !((treRiktig[0]== c[(tredjeMaks(c)[0])])
                &&(treRiktig[1]== c[tredjeMaks(c)[1]])
                &&(treRiktig[2]== c[tredjeMaks(c)[2]]))||
            !((treRiktig[0]== c2[(tredjeMaks(c2)[0])])
                &&(treRiktig[1]== c2[tredjeMaks(c2)[1]])
                &&(treRiktig[2]== c2[tredjeMaks(c2)[2]]))||
            !((treRiktig[0]== d[(tredjeMaks(d)[0])])
                &&(treRiktig[1]== d[tredjeMaks(d)[1]])
                &&(treRiktig[2]== d[tredjeMaks(d)[2]]))||
            !((treRiktig[0]== d2[(tredjeMaks(d2)[0])])
                &&(treRiktig[1]== d2[tredjeMaks(d2)[1]])
                &&(treRiktig[2]== d2[tredjeMaks(d2)[2]]))) {
            System.out.println("Den returnerer feil data!");
        }
    }


// Oppgave 9

    public static int[] kMinst(int[] a, int k){
 
        int arrayAlengde = a.length;
        if(k > arrayAlengde||k < 1 )
            throw new IllegalArgumentException("antall minsteverdi må være færre enn lengden på array og større enn 0");
 
        int[] m = new int[k];
 
        System.arraycopy(a, 0, m, 0, k);
        kvikksortering(m);
 
        if(arrayAlengde == k)
            return m;
       int laveste = m[k-1];
        for(int i = k; i < arrayAlengde; i++){
            int verdi = a[i];
            
            if(a[i]< laveste){
                for(int j = 0; j < k; j++){
                    if(verdi < m[j]){
                        int temp = m[j];
                        m[j] = verdi;
                        verdi = temp;
                    }
                    laveste = m[k-1];
                }                
            }
        }
        return m;
    }

// Oppgave 10
    public static boolean inneholdt(String a, String b){

        int[] aL = new int[29];
        int[] bL = new int[29];
        lagBokstav(a,aL);
        lagBokstav(b,bL);        
        boolean ordOk = true;
        
        if(a.equals(b) || a.length() == 0){
            return ordOk;
        } else {
            for(int i = 0; i <aL.length-1; i++){
                if(aL[i] > bL[i]){
                    ordOk = false;
                }
            }
            return ordOk;
        }
        
    }
    
    public static void lagBokstav(String ord, int[] listen){
        char[] bokstaver = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','Æ','Ø','Å'};
        
        for(int i = 0; i<ord.length(); i++){
            for(int j = 0; j<bokstaver.length-1;j++){
                char temp = (char) ord.charAt(i);
                if(bokstaver[j] == temp){
                listen[j]++;
                }
            }
        }    
    }

    // Metoder fra tabell-klassen
    

  public static int parter(int[] a, int v, int h, int skilleverdi)
  {
    while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
    while (v <= h && skilleverdi <= a[h]) h--;  // v er stoppverdi for h

    while (true)
    {
      if (v < h) bytt(a,v++,h--);   // bytter om a[v] og a[h]
      else  return v;                      // partisjoneringen er ferdig
      while (a[v] < skilleverdi) v++;      // flytter v mot høyre
      while (skilleverdi <= a[h]) h--;     // flytter h mot venstre
    }
  }

  public static int parter(int[] a, int skilleverdi)
  {
    return parter(a,0,a.length-1,skilleverdi);  // kaller metoden over
  }    
    
  public static int sParter(int[] a, int v, int h, int indeks)
  {
    if (indeks < v || indeks > h) throw new IllegalArgumentException();

    bytt(a,h,indeks);
    int k = parter(a,v,h-1,a[h]);
    bytt(a,h,k);
    return k;
  }

  public static int sParter(int[] a, int k)   // bruker hele tabellen
  {
    return sParter(a,0,a.length-1,k);   // v = 0 og h = a.lenght - 1
  }

  public static void kvikksortering(int[] a)
  {
    kvikksortering(a, 0, a.length - 1);
  }

  private static void kvikksortering(int[] a, int v, int h)
  {
    if (v >= h) return;

    int m = (v + h)/2;

    int p = sParter(a, v, h, m);

    kvikksortering(a, v, p - 1);
    kvikksortering(a, p + 1, h);
  }

  public static void bytt(int[] a, int i, int j)
  {
    int temp = a[i]; a[i] = a[j]; a[j] = temp;
  }    
    
}
