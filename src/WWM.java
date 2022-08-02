import java.io.IOException;
public class WWM {
    public static void main(String[] args) throws IOException {

        int level = 0;
        Partie runde1 = new Partie(1,1);
        boolean korrekteAntwort = false;

        System.out.println((int)(Math.random()*99)+1);
        do {
            level++;
            runde1.partieStarten(level);
            String buffer = TextConnector.einlesen();
            korrekteAntwort=runde1.antwortPruefen(buffer,level, runde1.getFragenkatalog());
        }while(korrekteAntwort);
        System.out.println(runde1.spielVerloren());



    }
}