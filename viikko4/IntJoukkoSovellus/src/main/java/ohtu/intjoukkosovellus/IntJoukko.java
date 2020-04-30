package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class IntJoukko {

    public final static int
            OLETUSKAPASITEETTI = 5, OLETUSKASVATUSKOKO = 5;
    private final int kasvatuskoko;
    private int[] alkiot;
    private int koko = 0;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        vaadiEpanegatiivinen(kapasiteetti, "kapasiteetti");
        vaadiEpanegatiivinen(kasvatuskoko, "kasvatuskoko");
        alkiot = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    private void vaadiEpanegatiivinen(int luku, String nimi) {
        if (luku < 0) {
            throw new IllegalArgumentException(
                    nimi + " ei saa olla negatiivinen!"
            );
        }
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        alkiot[koko++] = luku;
        laajennaTarvittaesssa();
        return true;
    }

    private void laajennaTarvittaesssa() {
        if (koko < alkiot.length) {
            return;
        }
        int[] uudetAlkiot = new int[koko + kasvatuskoko];
        System.arraycopy(alkiot, 0, uudetAlkiot, 0, alkiot.length);
        alkiot = uudetAlkiot;
    }

    public boolean kuuluu(int luku) {
        return etsiIndeksi(luku) != -1;
    }

    private int etsiIndeksi(int luku) {
        for (int i = 0; i < koko; i++) {
            if (luku == alkiot[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int indeksi = etsiIndeksi(luku);
        if (indeksi == -1) {
            return false;
        }
        koko--;
        System.arraycopy(
                alkiot, indeksi + 1, alkiot, indeksi, koko - indeksi
        );
        return true;
    }

    public int mahtavuus() {
        return koko;
    }

    @Override
    public String toString() {
        String alkioString = alkioStream()
                .boxed()
                .map(Object::toString)
                .collect(joining(", "));
        return "{" + alkioString + "}";
    }

    private IntStream alkioStream() {
        return Arrays.stream(alkiot, 0, koko);
    }

    public int[] toIntArray() {
        return alkioStream().toArray();
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        IntStream.concat(a.alkioStream(), b.alkioStream())
                .forEach(yhdiste::lisaa);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        a.alkioStream()
                .filter(b::kuuluu)
                .forEach(leikkaus::lisaa);
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        a.alkioStream()
                .filter(i -> !b.kuuluu(i))
                .forEach(erotus::lisaa);
        return erotus;
    }
}
