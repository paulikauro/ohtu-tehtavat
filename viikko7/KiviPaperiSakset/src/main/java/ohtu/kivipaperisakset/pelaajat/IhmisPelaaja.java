package ohtu.kivipaperisakset.pelaajat;

import ohtu.kivipaperisakset.IO;

public class IhmisPelaaja implements Pelaaja {
    private final String kysymys;
    private final IO io;

    public IhmisPelaaja(IO io, String kysymys) {
        this.io = io;
        this.kysymys = kysymys;
    }

    @Override
    public String kysySiirto() {
        io.tulosta(kysymys);
        return io.kysy();
    }
}
