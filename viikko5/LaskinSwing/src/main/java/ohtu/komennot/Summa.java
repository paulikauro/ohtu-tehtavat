package ohtu.komennot;

import ohtu.Sovelluslogiikka;

import javax.swing.*;

public class Summa extends OperandillinenKomento {
    public Summa(
            JTextField tuloskentta, JTextField syotekentta,
            JButton nollaa,
            JButton undo,
            Sovelluslogiikka sovellus
    ) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    protected void tee(int arvo) {
        sovellus.plus(arvo);
    }

    @Override
    public void peru() {
    }
}
