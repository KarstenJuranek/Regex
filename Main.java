
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    // Erstellung einer Klasse Namens "finder", die eine Map beinhaltet
    private static List<Map.Entry<Integer, Integer>> finder(Matcher Match)
    {
        // Erstellung einer Array List, die pro Eintrag zwei Zahlen beinhaltet
        List<Map.Entry<Integer, Integer>> Finds = new ArrayList<>();
        
        // while schleife, die über die Match Methode, über den ganzen String iteriert
        // und dann die Fundstellen ausgibt.
        while (Match.find())
        {
            int X = Match.start(), Y = Match.end();
            Finds.add(Map.entry(X, Y));
        }
        // Rückgabe der Funde
        return Finds;
    }

    // Erstellen einer Klasse, die aus den vorherigen Funden einen substring erstellt und abspeichert
    private static List<String> Finds_substring(String S, List<Map.Entry<Integer, Integer>> Finds)
    {
        // Erstellung einer Array Liste, die anschließend die Funde wiedergibt
        List<String> Findings = new ArrayList<>();

        // For-Schleife, die die Stellen aus der Start-Endpunkte nimmt und daraus einen
        // Substring erstellt. (Key-Value sind hierbei die Map-Entry Einträge aus der oberen Klasse)
        for (Map.Entry<Integer, Integer> Find : Finds)
            Findings.add(S.substring(Find.getKey(), Find.getValue()));
        // Rückgabe des Substrings.
        return Findings;
    }
    
    // Regex
    public static void main(String[] args)
    {
        // Definieren des zu bearbeitenden Strings, verweisen der Pattern und Matcher-Klassen
        String InputString = "to do or not to do - that is the decision";
        Pattern Patt;
        Matcher Match;
        
        /*
        Welche Rückgabemethoden gibt es?
        * Boolsche-Rückgabe siehe Beispiel 1 (minimalistisch ausreichend)
        * While-Rückgabe der Start und Endpunkte & des Strings Beispiel 2 (gängig)
         */

        /*
        Regular Expressions:
        * Jeder einzelne Buchstabe/Zeichen/Leerzeichen kann gefiltert werden bspw:
            a-z /   A-Z /   &   /   a-zA-Z  /   [hH]ey
        * Die Kombination aus Klammern [] und dem Sternchen * bedeutet, das es nur ein oder
            mehr Vorkommen markiert werden müssen.
         */
        // Beispiel 1
        {
            // Ausgabe aller einzelnen Kleinbuchstaben/-/Leerzeichen des Strings
            Patt = Pattern.compile("[- a-z]*");
            Match = Patt.matcher(InputString);
            // Rückgabe des Fundes in einem boolschen Wert
            boolean F = Match.matches();
            System.out.println("Beispiel 1: "+F);
            
        }
    
        System.out.println();

        // Beispiel 2
        {
            // Gibt sämtliche Folgen von d/t/i mit o wieder
            Patt = Pattern.compile("[dti]o");    // do|to|io
            Match = Patt.matcher(InputString);
    
            System.out.print("Beispiel 2: ");
            // While Schleife, die Start und Endpunkte der Matches wiedergibt
            while (Match.find())
            {
                // Suche nach Start- und Endpunkte
                int X = Match.start(), Y = Match.end();
                // Gruppieren des Strings der Funde
                String R = Match.group();
                // Rückgabe des Strings + "@" & Start- und Endpunkt
                System.out.print(R+"@"+X+"-"+Y+"\t");
            }
        }

        System.out.println("\n");
        
        // Beispiel 3
        {
            Patt = Pattern.compile("\\b\\w?o\\w?\\b");  // Regex: \b\w?o\w?\b
            Match = Patt.matcher(InputString);
            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            System.out.println(Finds);

            System.out.print("Beispiel 3: ");
            for (Map.Entry<Integer, Integer> Find : Finds)
            {
                int X = Find.getKey(), Y = Find.getValue();
                System.out.print(InputString.substring(X, Y)+"@"+Find+"\t");
            }
            System.out.println();
            
            // Frage: Was passiert, wenn man die Boundaries \b oben weglässt?
            //        Was passiert, wenn \b durch \B ersetzt wird?
        }
    
        // Beispiel 4
        {
            Patt = Pattern.compile("[DT]O", Pattern.CASE_INSENSITIVE);
            //Patt = Pattern.compile("(?i)[DT]O");  // alternativ per Schalter
            Match = Patt.matcher(InputString);
            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            System.out.println("Beispiel 4: "+Finds);
        }

        // Beispiel 5
        {
            Patt = Pattern.compile(" ");
            Match = Patt.matcher(InputString);

            StringBuilder SB = new StringBuilder();
            while (Match.find())
                Match.appendReplacement(SB, "__");
            Match.appendTail(SB);

            System.out.println(SB);
        }

        // Beispiel 6
        {
            boolean F = InputString.matches("[- a-z]*");  // 'Convenience Function'
            System.out.print("Beispiel 6: "+F+"\t");
        
            String R = InputString.replaceAll("do", "go");    // 'Convenience Function
            System.out.println("'"+R+"'");
        }
        
        // Spezielle Aufgaben für obige Daten
        {
            // Finden Sie alle zweibuchstabigen Wörter
            // Finden Sie ganze Wörter, die "is" enthalten
            // Finden Sie beliebige Palindrome, die zw. 3 und 5 Zeichen lang sind
        }
        
        // Allgemeine Aufgaben mit eigenen Beispieldaten
        {
            /* Finden aller Varianten von "Meier":
               "Meier", "Maier", "Meyer", "Mayer", "Meir", "Mair", "Meyr", "Mayr" */
            /* Finden engl. Wörter, die nur aus Buchstaben der 1. bzw. 2. Hälfte
               des engl. Alphabets bestehen (z.B. "alabama" vs. "soupspoon") */
            /* Finden beliebiger Ganzzahlen, die durch 2 teilbar sind
               (auch 0 selbst und Zahlen mit führenden 0en) */

            /* Finden von Wörtern, die mit dem selben Segment beginnen und enden,
               das mind. 2 Zeichen lang ist (z.B. "haha", "nennen", "einstein", "enteisent") */
            /* Finden von Wörtern mit 3 bel. gleichen Buchstaben hintereinander
               (z.B. "Teeei", "Schifffahrt", "XXXVIII", "1999"/"2000") */
            /* Finden von Wörtern des Schemas XY-YZ mit gemeinsamem Mittelteil,
               der mind. 2 Buchstaben umfasst ("orange-gelb") */

            /*String S;
            Patt = Pattern.compile("");
            Match = Patt.matcher(S = "");

            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            List<String> Findings = finds(S, Finds);

            System.out.println(Finds);
            System.out.println(Findings);

            //boolean F = Match.find();
            //boolean F = Match.matches();*/
        }
    }
}
