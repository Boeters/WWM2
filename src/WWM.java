import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WWM {
    public static void main(String[] args) throws IOException {
        BufferedReader buffreader = new BufferedReader(new InputStreamReader(System.in));
        int level, variation;
        String antwort;
        boolean antwortKorrekt = false;
        level = 1;
        variation = 1;
        Partie partie = new Partie();

        do {
            Quizfrage quizfrage = new Quizfrage(level,variation);
            quizfrage.antwortenAusgeben();
            antwortKorrekt= quizfrage.antwortPruefen(partie.einlesen());
        }while(antwortKorrekt);

    }
}
}