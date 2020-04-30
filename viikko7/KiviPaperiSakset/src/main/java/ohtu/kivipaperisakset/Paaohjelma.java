package ohtu.kivipaperisakset;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Paaohjelma {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IO konsoliIO = new IO() {
        @Override
        public void tulosta(String rivi) {
            System.out.println(rivi);
        }

        @Override
        public String kysy() {
            return scanner.nextLine();
        }
    };

    public static void main(String[] args) {
        Map<String, Function<IO, KPSPeli>> pelitehtaat =
                new HashMap<String, Function<IO, KPSPeli>>() {{
            put("a", KPSPeli::pelaajaVsPelaaja);
            put("b", KPSPeli::yksinkertainenTekoaly);
            put("c", KPSPeli::parannettuTekoaly);
        }};

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            // luodaan joka kerta uusi peli, siksi tehdastelu ja .get()
            Function<IO, KPSPeli> pelitehdas =
                    pelitehtaat.get(vastaus);
            if (pelitehdas == null) {
                break;
            }
            System.out.println("peli loppuu kun pelaaja antaa virheellisen"
                    + " siirron eli jonkun muun kuin k, p tai s");
            pelitehdas.apply(konsoliIO).pelaa();
        }
    }
}
