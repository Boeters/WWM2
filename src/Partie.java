import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Partie extends Quizfrage
{

    private int preisgeld = 100;
    private ArrayList<Quizfrage> fragenkatalog = new ArrayList<>();
    public Partie(int level, int variation) throws IOException {
        super(level, variation);
    }

    public ArrayList<Quizfrage> getFragenkatalog() {
        return fragenkatalog;
    }

    public Quizfrage partieStarten(int level) throws IOException {
        System.out.println("Wer wird Millionär?");
        System.out.println("Runde: "+level);
        System.out.println("Aktuelles Preisgeld: "+ getPreisgeld(level));
        fragenkatalog.add((level-1),new Quizfrage(level,1));
        return fragenkatalog.get(level-1).antwortenAusgeben(fragenkatalog,level);

    }
    public int getPreisgeld(int level)
    {
        int preisgeld1 = this.preisgeld;
        for (int i = 0; i < level; i++) {
            preisgeld1 = preisgeld1 + (preisgeld1*2);
        }
        return preisgeld1;
    }
    public String spielVerloren() {
        String rueckgabe;
        if(getLevel()>1)
        {
           rueckgabe =  String.format("Sie haben bis Runde "+ getLevel()+ "gespielt" + "und ein theoretisches Preisgeld von " + getPreisgeld(getLevel()) +  "€ erhalten! ");
        }
        else
        {
            rueckgabe = String.format("In der ersten Runde verloren? Respekt!");
        }
        return  rueckgabe;
    }
    public void jokerPruefung(){

    }


}
