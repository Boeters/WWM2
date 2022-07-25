import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class WWM {
    public static void main(String[] args) throws IOException {

        int level = 0, variation = 1;
        Partie runde1 = new Partie(1,1);
        boolean korrekteAntwort = false;

        do {
            level++;
            runde1.partieStarten(level);
            System.out.println(runde1.fragenkatalog.size());
            korrekteAntwort=runde1.antwortPruefen(TextConnector.einlesen(),level, runde1.fragenkatalog);
        }while(korrekteAntwort);







        // TODO seit der umstellung auf ArrayList funktioniert antwortPruefen nicht mehr, muss vermutlich der Index mitgegeben werden (level-1)
        // TODO Verknüpfung der Joker ODER eine Eingabeaufforderung nach dem Joker

    }
}
