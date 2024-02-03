
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    private static List<Map.Entry<Integer, Integer>> finder(Matcher Match)
    {
        List<Map.Entry<Integer, Integer>> Finds = new ArrayList<>();
        
        while (Match.find())
        {
            int X = Match.start(), Y = Match.end();
            Finds.add(Map.entry(X, Y));
        }
        
        return Finds;
    }
    private static List<String> finds(String S, List<Map.Entry<Integer, Integer>> Finds)
    {
        List<String> Findings = new ArrayList<>();

        for (Map.Entry<Integer, Integer> Find : Finds)
            Findings.add(S.substring(Find.getKey(), Find.getValue()));

        return Findings;
    }
    
    // Regex
    public static void main(String[] args)
    {
        String T = "to do or not to do - that is the decision";
        Pattern Patt;
        Matcher Match;
        
        // Beispiel 1
        {
            Patt = Pattern.compile("[- a-z]*");
            Match = Patt.matcher(T);
            boolean F = Match.matches();
            System.out.println("Beispiel 1: "+F);
        }
    
        // Beispiel 2
        {
            Patt = Pattern.compile("[dti]o");    // do|to|io
            Match = Patt.matcher(T);
    
            System.out.print("Beispiel 2: ");
            while (Match.find())
            {
                int X = Match.start(), Y = Match.end();
                String R = Match.group();
                System.out.print(R+"@"+X+"-"+Y+"\t");
            }
            System.out.println();
        }
        
        // Beispiel 3
        {
            Patt = Pattern.compile("\\b\\w?o\\w?\\b");  // Regex: \b\w?o\w?\b
            Match = Patt.matcher(T);
            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            System.out.println(Finds);

            System.out.print("Beispiel 3: ");
            for (Map.Entry<Integer, Integer> Find : Finds)
            {
                int X = Find.getKey(), Y = Find.getValue();
                System.out.print(T.substring(X, Y)+"@"+Find+"\t");
            }
            System.out.println();
            
            // Frage: Was passiert, wenn man die Boundaries \b oben weglässt?
            //        Was passiert, wenn \b durch \B ersetzt wird?
        }
    
        // Beispiel 4
        {
            Patt = Pattern.compile("[DT]O", Pattern.CASE_INSENSITIVE);
            //Patt = Pattern.compile("(?i)[DT]O");  // alternativ per Schalter
            Match = Patt.matcher(T);
            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            System.out.println("Beispiel 4: "+Finds);
        }

        // Beispiel 5
        {
            Patt = Pattern.compile(" ");
            Match = Patt.matcher(T);

            StringBuilder SB = new StringBuilder();
            while (Match.find())
                Match.appendReplacement(SB, "__");
            Match.appendTail(SB);

            System.out.println(SB);
        }

        // Beispiel 6
        {
            boolean F = T.matches("[- a-z]*");  // 'Convenience Function'
            System.out.print("Beispiel 6: "+F+"\t");
        
            String R = T.replaceAll("do", "go");    // 'Convenience Function
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
