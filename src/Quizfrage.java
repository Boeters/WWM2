import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class Quizfrage {
    private String frage;
    private String antwort[] = new String[4];
    private String loesung;
    private int level;
    private int variation;

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
    public void antwortenAusgeben(ArrayList<Quizfrage>fragen, int level)
    {
        /*LOGIK:
        * Hier muss aus der ArrayList <Quizfragen> die Frage + Antworten ausgegeben werden. Dabei ist das
        * richtige Level zu beachten*/
        for (int i = 0; i <fragen.size() ; i++) {
            if(fragen.get(i).level==level)
            {
                System.out.println(fragen.get(i).frage);
                for (int j = 0; j <4 ; j++) {
                    System.out.println(fragen.get(i).antwort[j]);

                }
            }

        }
    }
    public boolean antwortPruefen(String eingabe, int index, ArrayList<Quizfrage> fragenkatalog) throws IOException {
        // die Loesung muss mit der Eingabe verglichen werden
        //dafür den Markierbuchstaben (A,B,C,D) aus dem Loesungsstring auslesen

        boolean pruefung = false;
        /*
        * Hier muss jetzt der Index von dem richtigen Quizfrage Objekt abgefragt werden*/
        String loesungsBuchstabe = String.valueOf(fragenkatalog.get(index-1).loesung.charAt(1));
        if(eingabe.equals(loesungsBuchstabe.toLowerCase())|| eingabe.equals(loesungsBuchstabe.toUpperCase()))
        {
            pruefung = true;
        }
        else if(eingabe.equals("j") || eingabe.equals("J"))
        {
            System.out.println("Wählen Sie zwischen >P< für Publikumsjoker(90%), >N< für neue Frage Joker oder >f< für fifty-Fifty joker");
            switch(TextConnector.einlesen())
            {
                case "p", "P": selectPublikumsJoker();
                    if(antwortPruefen(TextConnector.einlesen(),level,fragenkatalog))
                    {
                        pruefung = true;
                    };
                break;
                case "n","N": selectNeueFrageJoker(level);
                    if(antwortPruefen(TextConnector.einlesen(),level,fragenkatalog))
                    {
                        pruefung = true;
                    };
                break;
                case "f","F": selectFiftyFiftyJoker();
                    if(antwortPruefen(TextConnector.einlesen(),level,fragenkatalog))
                    {
                        pruefung = true;
                    };;
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
    }

        public static Quizfrage selectNeueFrageJoker(int level) throws IOException {
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
