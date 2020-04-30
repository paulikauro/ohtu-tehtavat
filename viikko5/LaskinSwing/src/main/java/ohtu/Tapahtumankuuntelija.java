package ohtu;

import ohtu.komennot.OperandillinenKomento;
import ohtu.komennot.Summa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private Map<JButton, OperandillinenKomento> komennot;
    private OperandillinenKomento edellinen = null;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<JButton, OperandillinenKomento>() {{
            put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        }};
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == undo && edellinen != null) {
            edellinen.peru();
            edellinen = null;
            return;
        }

        OperandillinenKomento komento = komennot.get(ae.getSource());
        komento.suorita();
        edellinen = komento;
//        if (ae.getSource() == plus) {
//            sovellus.plus(arvo);
//        } else if (ae.getSource() == miinus) {
//            sovellus.miinus(arvo);
//        } else if (ae.getSource() == nollaa) {
//            sovellus.nollaa();
//        } else {
//            System.out.println("undo pressed");
//        }
        

    }
 
}