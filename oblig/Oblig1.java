/*
    Kristoffer Johansen
    s193370
    HiNGDATA13H2AA
*/

/*
Huskeliste før løsningen sendes inn:

Står navn m.m. på alle i gruppen øverst på filen Oblig1.java?
Står navn m.m. på alle i gruppen også i epostmeldingen?
Er alle utskriftssetninger inne i metodene fjernet?
Er en eventuell main-metode inne i class Oblig1 fjernet?
Er alle metodene testet?
Det vil senere komme et testprogram. Metodene må passere gjennom det programmet 
før løsningen sendes inn. Obs: Testprogrammet vil ikke inneholde en test på 
metoden i Oppgave 8a). Den skal du skal du lage selv. Se Oppgave 8b).
*/
package oblig;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author kristofferjohansen
 */
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
        if(a.length < 1) throw new NoSuchElementException("Du har en tom tabell. Det er ikke bra...");
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
    sortert i stigende rekkefølgen tabellen er jo færre bytter.
    
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
//            System.out.println("Antallbytter:"+ antBytter);            
//            System.out.println("Sortert array; "+Arrays.toString(a));
        }
    }
    public static int[] sortering2(int[] a){
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
                        antBytter++;
                    }
                }
//                System.out.println("Antallbytter:"+ antBytter);
            }
//            System.out.println("Sortert array; "+Arrays.toString(a));
//            System.out.println("Antall bytter: "+antBytter/a.length);
        }
        return a;
    }    
    /*
    Hvilken orden? Je ne sais pas. Det er ca. 
    
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

/*
Lag metoden public static int antallUlikeSortert(int[] a). I metoden skal det 
først sjekkes om a er sortert stigende. Hvis ikke kastes en IllegalStateException 
    (med en passende tekst). Tabellen a skal kunne ha like verdier. Metoden skal 
    returnere antallet forskjellige verdier i a. 
    Hvis f.eks. a inneholder 3, 3, 4, 5, 5, 6, 7, 7, 7 og 8, skal metoden 
    returnere 6 siden det er 6 forskjellige verdier. Metoden skal ikke endre noe
    på tabellens innhold. Pass på at hvis tabellen er tom (har lengde 0), skal 
    metoden returnere 0 siden det er 0 forskjellige verdier i en tom tabell. 
    Med andre ord er ikke en tom tabell en feilsituasjon.
*/

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
                    throw new IllegalStateException("Tallrekken er ikke sortert. Kanskje du bør kjøre den gjennom en sorteringsmetode?");
                }
            }
        }else if(a.length == 1) {
            antallUlike = 1;            
            return antallUlike;
        }
        return antallUlike;
    }
    
    
// Oppgave 4

/*
Lag metoden public static int antallUlikeUsortert(int[] a). Tabellen a kan nå 
    være en hvilken som helst heltallstabell, dvs. den behøver ikke være sortert. 
    Den kan også ha flere like verdier. Metoden skal finne og returnere antallet 
    forskjellige verdier i a. Metoden skal ikke endre noe på tabellens innhold. 
    Hvis a f.eks. inneholder tallene 5, 3, 7, 4, 3, 5, 7, 8, 6 og 7, skal metoden 
    returnere 6 siden det er 6 forskjellige verdier. Pass på at hvis tabellen er 
    tom (har lengde 0), skal metoden returnere 0 siden det er 0 forskjellige 
    verdier i en tom tabell. Du skal ikke bruke noen hjelpetabell eller 
    hjelpestrukturer, dvs. alt arbeidet skal foregå innenfor tabellen a. Du kan 
    selvfølgelig bruke en eller flere hjelpevariabler.
*/
    /*
    public static int antallUlikeUsortert(int[] a){
        int antallUlike = 0;
        boolean antallU = false;
        for(int i = 0; i<a.length; i++){
            for(int j = 0; j<a.length; j++){
                antallU = false;
//                System.out.println("Vi tester: "+a[i]+"=="+a[j]);
                if(a[i] == a[j]){
//                    System.out.println("Score, la oss teste mer!");
                    antallU = true;
                    if(i == 0 && j== 0){
                        antallUlike++;
//                        System.out.println("den første må uansett med");
                        antallU = false;
                    }
                    if(i>0){
                        for(int k = 0; k<j; k++){
//                            System.out.println("j er "+j);
//                            System.out.println("a[i]==a[k] "+a[j]+"=="+a[k]);
                            if(a[j] == a[k]) {    
//                                System.out.println("Denne finnes fra før, stopp galskapen!");
                                antallU = false;
                            }
                        }
//                        System.out.println("antallU er "+antallU);
                        if(antallU == true && a[i]==a[j]){
//                            System.out.println("Da er alt i orden. Vi legger til en til!");
                            antallUlike++;
                        }                    
                    } else {
//                        System.out.println("Den første rada skal bare telles en gang, og det er gjort");
                    }                        
                } else {
//                    System.out.println("Broke it, dette var ikke fulltreffer.");
                }
            }
        }
//            antallU += antallUlike;
        return antallUlike;
    }
*/
    
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
/*
Det kan være aktuelt å «rotere» elementene i en tabell. En rotasjon på én enhet 
    gjøres ved at det siste elementet blir det første og alle de andre forskyves 
    én enhet mot høyre.

A	B	C	D	E	F	G	H	I	J
Tabell 2 : Bokstavene fra A til I
J	A	B	C	D	E	F	G	H	I

Tabell 3 : Elementene i Tabell 2 forskjøvet én enhet
På figuren over har elementene i den første tabellen blitt «rotert» én enhet. 
    Lag metoden public static void rotasjon(char[] a). Den skal «rotere» 
    innholdet i tabellen a én enhet. En rotasjon i en tom tabell eller i en 
    tabell med nøyaktig ett element er ingen feilsituasjon. Men rotasjonen vil 
    da ikke endre noe.
*/

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
/*
Her skal vi gå videre fra Oppgave 5. Hvis vi tenker oss at tabellen er 
«bøyd til en sirkel», er det mer naturlig å se på dette som en rotasjon. 
Dermed kan vi «rotere» et valgfritt antall enheter. Lag metoden public static 
void rotasjon(char[] a, int k) der k er et vilkårlig heltall. Hvis k = 1, skal 
metoden ha samme effekt som metoden i Oppgave 5. Hvis k er negativ, skal 
rotasjonen gå motsatt vei. En rotasjon i en tom tabell eller i en tabell med 
nøyaktig ett element er ingen feilsituasjon. Men rotasjonen vil da ikke endre 
noe. Målet er å gjøre metoden så effektiv som mulig. Følgende programbit viser 
hvordan metoden skal virke:

  char[] a = {'A','B','C','D','E','F','G','H','I','J'};
  System.out.println(Arrays.toString(a));

  rotasjon(a,3); System.out.println(Arrays.toString(a));
  rotasjon(a,-2); System.out.println(Arrays.toString(a));

  // Utskrift:
  [A, B, C, D, E, F, G, H, I, J]   // originaltabellen
  [H, I, J, A, B, C, D, E, F, G]   // en rotasjon på tre enheter mot høyre
  [J, A, B, C, D, E, F, G, H, I]   // en rotasjon to enheter mot venstre
*/

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
/*
a) Lag metoden public static String flett(String s, String t). Den skal «flette» 
    sammen tegnstrengene s og t slik at reultatet blir en tegnstreng der 
    annethvert tegn kommer fra s og annethvert fra t. Hvis s og t har ulik 
    lengde, skal det som er «til overs» legges inn bakerst. Resultatet skal 
    returneres. Flg. eksempel viser hvordan den skal virke:

  String a = flett("ABC","DEFGH");
  String b = flett("IJKLMN","OPQ");
  String b = flett("","AB");
  System.out.println(a + " " + b + " " + c);

  // Utskrift: ADBECFGH IOJPKQLMN AB
    */
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

        /*
    
b) Lag metoden public static String flett(String... s). Den skal «flette» sammen 
    tegnstrengene i s. Husk at s nå er en tabell av tegnstrenger. I koden vil 
    derfor s[0] være første streng i tabellen s, osv. Flettingen skal være slik: 
    Først hentes fortløpende det første tegnet fra hver tegnstreng, deretter 
    fortløpende det andre tegnet, osv. De tegnstrengene som er «brukt opp», dvs. 
    vi er ferdige med alle tegnene der, hoppes over. Resultatet skal returneres. 
    Flg. eksempel viser hvordan den skal virke:

  String a = flett("AM ","L","GEDS","ORATKRR","","R TRTE","IO","TGAUU");
  System.out.println(a);

  // Utskrift: ALGORITMER OG DATASTRUKTURER
    
*/
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
/*
a) Lag metoden public static int[] tredjeMaks(int[] a). Den skal finne 
posisjonene til de tre største verdiene i tabellen a. Den skal returnere en 
tabell med tre verdier der første verdi skal være posisjonen til den største 
verdien i a, andre verdi skal være posisjonen til den nest største verdien i a 
og tredje verdi posisjonen til den tredje største verdien i a. Bruk samme type 
idé som i Programkode 1.2.5 a). Bruk tre hjelpevariabler for verdier og tre 
hjelpevariabler for indekser. Hvis tabellen a har færre enn tre elementer, skal 
det kastes en IllegalArgumentException sammen med en passende tekst. Metoden 
skal ikke endre noe på innholdet i tabellen a.
*/
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

    return new int[] {m,nm, nnm};    // n i posisjon 0, nm i posisjon 1
}

/*
b)  Lag testmetoden public static void tredjeMaksTest(). Den skal teste din 
løsning av Oppgave 8a) og lages på en tilsvarende måte som Programkode 1.1.7 
a). Den skal teste at kastes unntak når det er nødvendig og at det kastes rett 
type unntak. Spesielt er det viktig at metoden tester på alle mulige kombinasjoner 
av tabellens tre første verdier. Husk at det er 6 forskjellige muligheter 
(permutasjoner) for de tre første verdiene i tabellen. Metoden public static 
void tredjeMaksTest() skal være med i løsningen som sendes inn.
*/

public static void tredjeMaksTest(){
    int[] a = {8,3,5,7,9,6,10,2,1,4};  // maksverdien 10 er i posisjon 6

    if (maks(a) != 6)  // kaller maks-metoden
      System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");

    a = new int[0];  // en tom tabell, lengde lik 0
    boolean unntak = false;

    try
    {
      maks(a);  // kaller maks-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
        System.out.println("Kodefeil: Feil unntak for en tom tabell!");
    }

    if (!unntak)
      System.out.println("Kodefeil: Mangler unntak for en tom tabell!");
  }


// Oppgave 9
/*
Lag metoden public static int[] kMinst(int[] a, int k). Den skal returnere en 
tabell som inneholder de k minste verdiene (ikke posisjonene) i tabellen a. 
Hvis k er mindre enn 1 eller større en lengden til a, skal det kastes en 
IllegalArgumentException sammen med en passende tekst. Metoden skal ikke endre 
noe på innholdet i tabellen a. Start metoden med å lage en hjelpetabell med 
lengde k. La den f.eks. hete verdier. Kopiér så inn i den de k første verdiene 
fra a. Deretter sorteres tabellen verdier. Hvis du så videre utover i tabellen 
a finner en verdi som er mindre enn enn den største (bakerste) i tabellen verdier, 
setter du den inn på rett sortert plass. Dermed vil den som opprinnelig var 
bakerst, forsvinne. Osv.
*/

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

/*
public static int[] kMinst(int[] a, int k){
    if(a.length <k) throw new IllegalArgumentException("Antall minsteverdier er større enn selve tabellen");
    if(k <1) throw new IllegalArgumentException("Du må be om minst 1 verdi å vise!");
    
    int[] verdier = new int[k];
    System.arraycopy(a, 0, verdier, 0, k);
    
    kvikksortering(verdier);    
    for(int i = k; i<a.length;i++){
        if(a[i]<verdier[k-1]){
            for(int j=verdier.length-1; j>0; j--){
                if(verdier[j] > a[i]){
                    int temp = verdier[j];
                    
                    verdier[j] = a[i];
                    for(int u = j+1; u<verdier.length-1; u++){
                        int temp2 = verdier[u];
                        verdier[u] = temp;
                        if(u <(verdier.length-1))
                        temp = verdier[u+1];
                    }
                       // Alt fra dette punktet og utover skal flyttes en plass bak
                }
            }
        }
    }
    return verdier;
}
*/
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


// Oppgave 10
/*
Vi sier at et ord er inneholdt i et annet ord hvis hver bokstav i det første 
ordet forekommer minst like mange ganger i det andre ordet som i det første, 
men ikke nødvendigvis i samme rekkefølge. F.eks. er ABBA inneholdt i både 
ABBABBA, BARAB, BARBARER og RABARBRA. ABBA har to A-er og to B-er og minst så 
mange av de to bokstavene har også de fire «ordene». Men ABBA er hverken 
inneholdt i BARBERER eller i AKROBAT. BARBERER har to B-er, men kun én A og 
AKROBAT har to A-er, men kun én B. Lag metoden public static boolean inneholdt
(String a, String b) der a og b er «ord». Du kan ta som gitt at tegnstrengene a 
og b kun har store bokstaver. Metoden skal returnere true hvis a er inneholdt i 
b og false ellers. Vi tenker oss her at et «ord» rett og slett er en oppramsing 
av bokstaver. Et «tomt» ord (en tom tegnstreng) er innholdt i alle andre ord 
(tegnstrenger). Det er ingen grense på hvor lange ordene kan være. Lag metoden 
så effektiv som mulig.
*/    
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
    
}
