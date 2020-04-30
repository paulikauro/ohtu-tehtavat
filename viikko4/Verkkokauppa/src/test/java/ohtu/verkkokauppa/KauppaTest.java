package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Kauppa k;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        // testivarasto
        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 7));
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "leipä", 2));
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostettaessaTilisiirtoTapahtuuOikeillaParametereilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",
                5);
    }

    @Test
    public void ostettaessaKaksiTuotettaTilisiirtoTapahtuuOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",
                12);
    }

    @Test
    public void ostettaessaKaksiSamaaTuotetttaTilisiirtoTapahtuuOikeillaParametreilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",
                10);
    }

    @Test
    public void tuoteJokaOnLoppuEiVeloiteta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",
                5);
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        when(viite.uusi()).thenReturn(43);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("liisa", "54321");
        verify(pankki).tilisiirto("liisa", 43, "54321", "33333-44455",
                7);
    }

    @Test
    public void kauppaPyytaaUudenViitteenJokaMaksulle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        when(viite.uusi()).thenReturn(43);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("liisa", "54321");
        verify(pankki).tilisiirto("liisa", 43, "54321", "33333-44455",
                7);
    }
}

