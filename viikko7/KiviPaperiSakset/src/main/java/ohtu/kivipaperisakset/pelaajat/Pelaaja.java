package ohtu.kivipaperisakset.pelaajat;

public interface Pelaaja {
    String kysySiirto();
    default void asetaSiirto(String siirto) {}
}
