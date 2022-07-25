import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;

public class TextConnector {
    // Funktionen und Methoden
    public static String[] Verbinden(int level, int variation) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader
                ((System.getProperty("user.dir")+"\\Fragen\\frage"+ level + variation + ".txt")));
        String buffer = input.readLine();
        String testArray[] = buffer.split(",", 0);
        return testArray;
    }
    public static String einlesen() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String buffer = input.readLine();
        return buffer;
    }
}
