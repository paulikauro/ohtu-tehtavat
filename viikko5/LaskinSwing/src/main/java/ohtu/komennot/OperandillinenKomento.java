package ohtu.komennot;

import ohtu.Sovelluslogiikka;

import javax.swing.*;

public abstract class OperandillinenKomento extends Komento {
    protected JButton nollaa;
    protected JButton undo;
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected Sovelluslogiikka sovellus;

    public OperandillinenKomento(
            JTextField tuloskentta, JTextField syotekentta, JButton nollaa,
            JButton undo,
            Sovelluslogiikka sovellus
    ) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        tee(arvo);

        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        nollaa.setEnabled(laskunTulos != 0);
        undo.setEnabled(true);
    }
    protected abstract void tee(int arvo);
    public abstract void peru();
}
