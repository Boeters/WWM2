import java.io.IOException;
import java.util.ArrayList;


public class Quizfrage {
    private String frage;
    private String antwort[] = new String[4];
    private String loesung;
    private int level;
    private int variation;
    private boolean fiftyFiftyJoker = true;
    private boolean neueFrageJoker = true;
    private boolean publikumsJoker = true;

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

    public Quizfrage setAntwort(String[] antwort) {
        this.antwort = antwort;
        return null;
    }

    public Quizfrage antwortenAusgeben(ArrayList<Quizfrage>fragen, int level)
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
        return null;
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

            System.out.println("Folgende Joker stehen zur verfügung");
            if(neueFrageJoker)
            {
                System.out.print(">N< für neue Frage Joker");
            }
            if(fiftyFiftyJoker)
            {
                System.out.print(">F< für fifty-Fifty Joker");
            }
            if(publikumsJoker)
            {
                System.out.print(">P< für Publikumsjoker");
            }
            switch(TextConnector.einlesen())
            {
                case "p", "P": selectPublikumsJoker(fragenkatalog,index);
                    if(antwortPruefen(TextConnector.einlesen(),index,fragenkatalog))
                    {
                        pruefung = true;
                    };
                break;
                case "n","N": selectNeueFrageJoker(index, fragenkatalog);
                    if(antwortPruefen(TextConnector.einlesen(),index,fragenkatalog))
                    {
                        pruefung = true;
                    }
                break;
                case "f","F": selectFiftyFiftyJoker(fragenkatalog,index);
                    if(antwortPruefen(TextConnector.einlesen(),index,fragenkatalog))
                    {
                        pruefung = true;
                    }
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
    public Quizfrage selectFiftyFiftyJoker(ArrayList<Quizfrage>quizfragen, int level)
    {

        /*
        * Logik hier?
        * vom richtigen Quizfrage objekt aus der fragenkatalog arraylist müssen 2 von 4 Antwortmöglichkeiten übrig bleiben.
        * Darunter 1x die Richtige Antwort und eine zufällige falsche.*/

        // TODO diese Methode hier überarbeiten, es funktioniert wirklich beschissen. SON DRECK
        String array[] = new String[2];
        for (int i = 0; i <4 ; i++) {
            //if(quizfragen.get(level-1).getAntwort(i).equals(quizfragen.get(level-1).getLoesung()))
            {
                System.out.println(quizfragen.get(level-1).getAntwort(i));
                System.out.println("Was passiert hier?");
            }
            //else
            {
                array[i%2] = quizfragen.get(level-1).getAntwort(i);
            }
            quizfragen.set(level-1,setAntwort(array));
            System.out.println("hokus pokus shit.");

        }


        return quizfragen.get(level-1);
    }
    public void selectPublikumsJoker() throws IOException {
        String hunderterArray[] = new String[100];
        // das 100 felder array mit 90x der Lösung füllen
        for (int i = 0; i < hunderterArray.length -10; i++) {
          //  hunderterArray[i] = quizfrage.get(level-1).getLoesung();
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
        System.out.println("Das Publikum denkt Antwort:  " + hunderterArray[(int)Math.random()*99+1]+  "   ist richtig.");
    }

        public static Quizfrage selectNeueFrageJoker(int level, ArrayList<Quizfrage> quizfragen) throws IOException {
            quizfragen.add(level-1,new Quizfrage(level,2));
            return quizfragen.get(level-1).antwortenAusgeben();
    }
    public void selectPublikumsJoker(ArrayList<Quizfrage>quizfrage, int level)
    {
        if(publikumsJoker)
        {
            String hunderterArray[] = new String[100];
            for (int i = 0; i <hunderterArray.length-10 ; i++) {
                hunderterArray[i] = quizfrage.get(level-1).getLoesung();

            }
            for (int i = hunderterArray.length-10; i <hunderterArray.length ; i++) {
                if(!quizfrage.get(level-1).getAntwort(i % 4).equals(quizfrage.get(level-1).getLoesung()))
                {
                    hunderterArray[i] = quizfrage.get(level-1).getAntwort(i % 4);
                }
                else
                {
                    hunderterArray[i] = quizfrage.get(level-1).getAntwort(i % 4+1);
                }
            }
            System.out.println("Das Publikum denkt: Antwort   "+hunderterArray[((int)(Math.random()*99)+1)]+"    ist richtig.");
            publikumsJoker = false;
        }
        else
        {
            System.out.println("Leider ist dieser Joker nicht mehr verfügbar");
        }
    }

    // Getter und Setter
    public String getFrage() {
        return frage;
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
