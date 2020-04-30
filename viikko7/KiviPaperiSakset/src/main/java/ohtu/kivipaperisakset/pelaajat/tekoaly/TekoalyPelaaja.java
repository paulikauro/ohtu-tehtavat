package ohtu.kivipaperisakset.pelaajat.tekoaly;

import ohtu.kivipaperisakset.IO;
import ohtu.kivipaperisakset.pelaajat.Pelaaja;

public class TekoalyPelaaja implements Pelaaja {
    private final IO io;
    private final Tekoaly tekoaly;

    public TekoalyPelaaja(IO io, Tekoaly tekoaly) {
        this.io = io;
        this.tekoaly = tekoaly;
    }

    @Override
    public String kysySiirto() {
        String siirto = tekoaly.laskeSiirto();
        io.tulosta("Tietokone valitsi: " + siirto);
        return siirto;
    }

    @Override
    public void asetaSiirto(String siirto) {
        tekoaly.asetaVastustajanSiirto(siirto);
    }
}
