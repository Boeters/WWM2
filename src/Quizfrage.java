import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Quizfrage {
    private String frage;
    private String antwort[] = new String[4];
    private String loesung;
    private int level;
    private int variation;
    private Quizfrage fragenKatalog[] = new Quizfrage[15];

    // Konstruktor

    public Quizfrage(int level, int variation) throws IOException {
        String buffer[] = TextConnector.Verbinden(level, variation);
        this.frage = buffer[0];
        this.antwort[0] = buffer[1];
        this.antwort[1] = buffer[2];
        this.antwort[2] = buffer[3];
        this.antwort[3] = buffer[4];
        this.loesung = buffer[5];
        this.level = level;
        this.variation = variation;
    }
    // Funktionen und Methoden
    public Quizfrage antwortenAusgeben() {
        System.out.println(frage);
        for (int i = 0; i < antwort.length; i++) {
            System.out.println(antwort[i]);
        }
        return null;
    }
    public void antwortenAusgeben(ArrayList<Quizfrage>fragen)
    {
        for (int i = 0; i <fragen.size() ; i++) {
            System.out.println(fragen.get(0));

        }
    }
    public boolean antwortPruefen(String eingabe) throws IOException {
        // die Loesung muss mit der Eingabe verglichen werden
        // dafür den Markierbuchstaben (A,B,C,D) aus dem Loesungsstring auslesen
        boolean pruefung = false;
        String loesungsBuchstabe = String.valueOf(this.loesung.charAt(1)).toLowerCase();
        if(eingabe.equals(loesungsBuchstabe))
        {
            pruefung = true;
        }
        else if(eingabe.equals("j"))
        {
            System.out.println("Wählen Sie zwischen >P< für Publikumsjoker(90%), >N< für neue Frage Joker oder >f< für fifty-Fifty joker");
            switch(Partie.einlesen(1))
            {
                case "p": selectPublikumsJoker();
                break;
                case "n": selectNeueFrageJoker(level);
                break;
                case "f": selectFiftyFiftyJoker();
            }

        }
        else
        {
            pruefung = false;
        }
        return pruefung;
    }

    public boolean selectFiftyFiftyJoker() {
        String antwortNachJoker[] = new String[2];

        int zufallszahl = (int) (Math.random() * 2) + 0;
        antwortNachJoker[zufallszahl] = loesung;

        for (int i = 0; i < antwort.length && !antwort[i].equals(loesung); i++) {
            switch (zufallszahl) {
                case 0:
                    zufallszahl = 1;
                    break;
                case 1:
                    zufallszahl = 0;
                    break;
            }
            antwortNachJoker[zufallszahl] = antwort[i];
        }
        this.antwort = antwortNachJoker;
        return false;
    }

    public void selectPublikumsJoker() throws IOException {
        String hunderterArray[] = new String[100];
        // das 100 felder array mit 90x der Lösung füllen
        for (int i = 0; i < hunderterArray.length -10; i++) {
            hunderterArray[i] = loesung;
        }
        for (int i = hunderterArray.length-10; i < hunderterArray.length ; i++) {
            if(!antwort[i%4].equals(loesung))
            {
                hunderterArray[i] = antwort[i%4];
            }
            else
            {
                hunderterArray[i] = antwort[i%4+1];
            }
        }
        System.out.println("Das Publikum denkt Antwort:  " + hunderterArray[(int)Math.random()*99+0]+"   ist richtig.");
        Partie.einlesen(1);
    }

        public Quizfrage selectNeueFrageJoker(int level) throws IOException {
            Quizfrage quizfrage = new Quizfrage(level,2);
            return quizfrage.antwortenAusgeben();
    }

    // Getter und Setter
    public String getFrage() {
        return frage;
    }

    public String[] getAntwort() {
        return antwort;
    }

    public String getAntwort(int index) {
        return antwort[index];
    }

    public String getLoesung() {
        return loesung;
    }

    public int getLevel() {
        return level;
    }

    public int getVariation() {
        return variation;
    }

}
