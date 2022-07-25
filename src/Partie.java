import java.io.IOException;
import java.util.ArrayList;

public class Partie extends Quizfrage
{
    private boolean fiftyFiftyJoker = true;
    private boolean neueFrageJoker = true;
    private boolean publikumsJoker = true;
    private int preisgeld = 100;
    ArrayList<Quizfrage> fragenkatalog = new ArrayList<>();
    public Partie(int level, int variation) throws IOException {
        super(level, variation);
    }


    public Quizfrage partieStarten(int level) throws IOException {
        System.out.println("Wer wird Millionär?");
        System.out.println("Runde: "+level);
        System.out.println("Aktuelles Preisgeld: "+ getPreisgeld(level));
        fragenkatalog.add((level-1),new Quizfrage(level,1));
        System.out.println("DEBUGAUSGABE: "+fragenkatalog.size());
        return fragenkatalog.get(level-1).antwortenAusgeben();

    }
    public int getPreisgeld(int level)
    {
        int preisgeld1 = this.preisgeld;
        for (int i = 0; i < level; i++) {
            preisgeld1 = preisgeld1 + (preisgeld1*2);
        }
        return preisgeld1;
    }

}
