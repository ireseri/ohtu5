
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusta(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        alusta(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        alusta(kapasiteetti, kasvatuskoko);
    }
    
    public void alusta(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti negatiivinen");
        }
        if (kasvatuskoko <= 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko negatiivinen");
        }
        this.lukujono = new int[kapasiteetti];
        for (int i = 0; i < this.lukujono.length; i++) {
            this.lukujono[i] = 0;
        }
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }   

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (this.alkioidenLkm == this.lukujono.length) {
                int[] uusiJono = new int[this.lukujono.length + this.kasvatuskoko];
                kopioiTaulukko(this.lukujono, uusiJono, this.lukujono.length);
                this.lukujono = uusiJono;
            }
            this.lukujono[this.alkioidenLkm] = luku;
            this.alkioidenLkm++;
        }
        return false;
    }
    
    public void lisaa(int[] jono) {
        for (int i = 0; i < jono.length; i++)
            lisaa(jono[i]);
    }

    public boolean kuuluu(int luku) {
        return etsi(luku) >= 0;
    }
    
    // palauttaa -1, jos lukua ei löydy
    public int etsi(int luku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (this.lukujono[i] == luku)
                return i;
        }
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = etsi(luku);
        if (kohta >= 0) {
            for (int i = kohta; i < alkioidenLkm - 1; i++) {
                this.lukujono[i] = this.lukujono[i + 1];
            }
            this.alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanhaJono, int[] uusiJono, int koko) {
        for (int i = 0; i < koko; i++)
            uusiJono[i] = vanhaJono[i];
    }

    public int mahtavuus() {
        return this.alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < this.alkioidenLkm; i++) {
            tuotos += this.lukujono[i];
            if (i < this.alkioidenLkm - 1)
                tuotos += ", ";
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[this.alkioidenLkm];
        kopioiTaulukko(this.lukujono, taulu, this.alkioidenLkm);
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        uusiJoukko.lisaa(aTaulu);
        uusiJoukko.lisaa(bTaulu);
        
        return uusiJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i]))
                uusiJoukko.lisaa(aTaulu[i]);
        }
        
        return uusiJoukko;
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        uusiJoukko.lisaa(aTaulu);
        for (int i = 0; i < bTaulu.length; i++) 
            uusiJoukko.poista(i);
        
        return uusiJoukko;
    }
}
