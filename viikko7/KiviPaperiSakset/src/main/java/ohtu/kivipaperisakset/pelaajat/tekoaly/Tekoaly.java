package ohtu.kivipaperisakset.pelaajat.tekoaly;

public interface Tekoaly {
    String laskeSiirto();
    default void asetaVastustajanSiirto(String vastustajanSiirto) {}
}
