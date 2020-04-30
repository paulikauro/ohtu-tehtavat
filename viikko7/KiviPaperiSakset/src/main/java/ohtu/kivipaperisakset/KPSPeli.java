package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.pelaajat.IhmisPelaaja;
import ohtu.kivipaperisakset.pelaajat.tekoaly.ParannettuTekoaly;
import ohtu.kivipaperisakset.pelaajat.Pelaaja;
import ohtu.kivipaperisakset.pelaajat.tekoaly.TekoalyPelaaja;
import ohtu.kivipaperisakset.pelaajat.tekoaly.YksinkertainenTekoaly;

public class KPSPeli {
    private final Pelaaja pelaaja, vastustaja;
    private final IO io;

    public KPSPeli(
            IO io, Pelaaja pelaaja, Pelaaja vastustaja
    ) {
        this.io = io;
        this.pelaaja = pelaaja;
        this.vastustaja = vastustaja;
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        while (true) {
            String pelaajanSiirto = pelaaja.kysySiirto();
            String vastustajanSiirto = vastustaja.kysySiirto();
            if (!(onkoOkSiirto(pelaajanSiirto) && onkoOkSiirto(vastustajanSiirto))) {
                break;
            }

            pelaaja.asetaSiirto(vastustajanSiirto);
            vastustaja.asetaSiirto(pelaajanSiirto);

            tuomari.kirjaaSiirto(pelaajanSiirto, vastustajanSiirto);
            io.tulosta(tuomari.toString());
            io.tulosta("");
        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari.toString());
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    // tehdasmetodit
    public static KPSPeli pelaajaVsPelaaja(IO io) {
        return new KPSPeli(
                io, ekaIhminen(io),
                new IhmisPelaaja(io, "Toisen pelaajan siirto: ")
        );
    }

    public static KPSPeli yksinkertainenTekoaly(IO io) {
        return new KPSPeli(
                io, ekaIhminen(io),
                new TekoalyPelaaja(io, new YksinkertainenTekoaly())
        );
    }

    public static KPSPeli parannettuTekoaly(IO io) {
        return new KPSPeli(
                io, ekaIhminen(io),
                new TekoalyPelaaja(io, new ParannettuTekoaly(20))
        );
    }

    private static Pelaaja ekaIhminen(IO io) {
        return new IhmisPelaaja(io, "Ensimmäisen pelaajan siirto: ");
    }
}
