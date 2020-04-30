package ohtu.kivipaperisakset.pelaajat.tekoaly;

public class YksinkertainenTekoaly implements Tekoaly {
    private int siirto = 0;

    @Override
    public String laskeSiirto() {
        siirto++;
        siirto %= 3;
        return Character.toString("kps".charAt(siirto));
    }
}
