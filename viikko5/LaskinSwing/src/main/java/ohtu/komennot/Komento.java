package ohtu.komennot;

public abstract class Komento {
    private Komento edellinen = null;
    public abstract void suorita();
    public abstract void peru();
}
