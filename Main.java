
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // Erstellung einer Klasse Namens "finder", die eine Map beinhaltet
    private static List<Map.Entry<Integer, Integer>> finder(Matcher Match) {
        // Erstellung einer Array List, die pro Eintrag zwei Zahlen beinhaltet
        List<Map.Entry<Integer, Integer>> Finds = new ArrayList<>();

        // while schleife, die über die Match Methode, über den ganzen String iteriert
        // und dann die Fundstellen ausgibt.
        while (Match.find()) {
            int X = Match.start(), Y = Match.end();
            Finds.add(Map.entry(X, Y));
        }
        // Rückgabe der Funde
        return Finds;
    }

    // Erstellen einer Klasse, die aus den vorherigen Funden einen substring
    // erstellt und abspeichert
    private static List<String> Finds_substring(String S, List<Map.Entry<Integer, Integer>> Finds) {
        // Erstellung einer Array Liste, die anschließend die Funde wiedergibt
        List<String> Findings = new ArrayList<>();

        // For-Schleife, die die Stellen aus der Start-Endpunkte nimmt und daraus einen
        // Substring erstellt. (Key-Value sind hierbei die Map-Entry Einträge aus der
        // oberen Klasse)
        for (Map.Entry<Integer, Integer> Find : Finds)
            Findings.add(S.substring(Find.getKey(), Find.getValue()));
        // Rückgabe des Substrings.
        return Findings;
    }

    // Regex
    public static void main(String[] args) {
        // Definieren des zu bearbeitenden Strings, verweisen der Pattern und
        // Matcher-Klassen
        String InputString = "to do or not to do - that is the decision";
        Pattern Patt;
        Matcher Match;

        /*
         * Welche Rückgabemethoden gibt es?
         * Boolsche-Rückgabe siehe Beispiel 1 (minimalistisch ausreichend)
         * While-Rückgabe der Start und Endpunkte & des Strings Beispiel 2 (gängig)
         * For-Rückgabe ermöglicht Ebenso die Rückgabe der End- Startpunkte und eines
         * substrings
         * Direkt aus einer Liste auslesen lassen Bsp3. (einfache schnelle Wiedergabe)
         */

        /*
        Regular Expressions:
        Zu Beachten ist, dass Backslashes in Regex immer doppelt Erfolgen müssen, da
           ansonsten nach dem Zeichen gesucht wird.
        Jeder einzelne Buchstabe/Zeichen/Leerzeichen kann gefiltert werden bspw:
           a-z / A-Z / & / a-zA-Z / [hH]ey
        Die Kombination aus Klammern [] und dem Sternchen * bedeutet, das es nur ein oder
            mehr Vorkommen markiert werden müssen
        Wortgrenzen werden mithilfe des kleinen Buchstabens b gegründet, zu beachten ist, das es
           Anfangs und Endpunkte gibt --> //b --> \\B gibt hingegen das genaue gegenteil aus
        Der Buchstabe \\w hingegen gibt ein Willkürliches alphanumerisches Zeichen wieder.
        Das Fragezeichen besagt, das das vorherige Zeichen optional sein kann.
         */

        /*
        StringBuilder
            Was sind String Builder?
            Der StringBuilder erlaubt es effiziente Änderungen an der Zeichenkette durchzuführen,
            ohne dabei ein neues Objekt zu erstellen.
         
        Beispiele:
         
        StringBuilder sb = new StringBuilder();
        
        Beispiel 1:
            sb.append("Hello");
            sb.append" World!);
            String result = sb.toString();  /"Hello World!" --> Konkatenation von Zeichenketten
         
        Beispiel 2:
            for (int i = 0; i<5; i++) {sb.append(i).append(" ");}
            String result = sb.toString     /"0 1 2 3 4 "   --> Effizientes Aufbauen von Zeichenketten in Schleifen
         
        Beispiel 3 (abcd):
            sb.setCharAt(1, 'X');
            String result = sb.toString();   /"aXcde"        --> Ersetzen von Zeichen in einer
            Zeichenkette
         
        Beispiel 4 (012345):
            sb.delete(0,4);
            String result = sb.toString();   /"45"           --> Löschen von Teilen einer
            Zeichenkette
         
        Beispiel 5 (1234):
            sb.reverse();
            String result = sb.toString();   /"4321"         --> Umkehren von Zeichenketten
         */

        // Beispiel 1
        {
            // Ausgabe aller einzelnen Kleinbuchstaben/-/Leerzeichen des Strings
            Patt = Pattern.compile("[- a-z]*");
            Match = Patt.matcher(InputString);
            // Rückgabe des Fundes in einem boolschen Wert
            boolean F = Match.matches();
            System.out.println("Beispiel 1: " + F);

        }

        System.out.println();

        // Beispiel 2
        {
            // Gibt sämtliche Folgen von d/t/i mit o wieder
            Patt = Pattern.compile("[dti]o"); // do|to|io
            Match = Patt.matcher(InputString);

            System.out.print("Beispiel 2: ");
            // While Schleife, die Start und Endpunkte der Matches wiedergibt
            while (Match.find()) {
                // Suche nach Start- und Endpunkte
                int X = Match.start(), Y = Match.end();
                // Gruppieren des Strings der Funde
                String R = Match.group();
                // Rückgabe des Strings + "@" & Start- und Endpunkt
                System.out.print(R + "@" + X + "-" + Y + "\t");
            }
        }

        System.out.println("\n");

        // Beispiel 3
        {
            Patt = Pattern.compile("\\b\\w?o\\w?\\b"); // Regex: \b\w?o\w?\b
            Match = Patt.matcher(InputString);
            // Erstellen einer Liste, mit Map.Entrys,
            List<Map.Entry<Integer, Integer>> Bsp3Finds = finder(Match);

            // alleinige Rückgabe der genauen Fundstellen in der Liste (Start/End)
            System.out.println("Beispiel 3.1: " + Bsp3Finds);

            System.out.print("Beispiel 3: ");
            // Ausgabe in einer vorher definierten Liste Mit Key/Values
            for (Map.Entry<Integer, Integer> Find : Bsp3Finds) {
                // Deklarieren der Key/Value Werte
                int X = Find.getKey(), Y = Find.getValue();
                // Ausgabe der Werte in einen Substring ausgehen aus dem gesamten Sting
                System.out.print(InputString.substring(X, Y) + "@" + Find + "\t");
            }
            System.out.println();

            // Fragen:
            // Was passiert, wenn man die Boundaries \b oben weglässt?
            // Durch das Weglassen der Boundaried können auch Funde innerhalb von Wörtern
            // gefunden werden. Somit erfolgt die Ausgabe nicht mehr nur in ganzen Wörtern

            // Was passiert, wenn \b durch \B ersetzt wird?
            // Es werden nur Funde angezeigt, die sich genau inmitten des Wortes befinden.
        }

        System.out.print("\n");

        // Beispiel 4
        {
            Patt = Pattern.compile("[DT]O", Pattern.CASE_INSENSITIVE);
            // Patt = Pattern.compile("(?i)[DT]O"); // alternativ per Schalter
            Match = Patt.matcher(InputString);
            List<Map.Entry<Integer, Integer>> Finds = finder(Match);
            System.out.println("Beispiel 4: " + Finds);
        }

        System.out.print("\n");

        // Beispiel 5
        {
            // Regex Filterung für eine Ersetzung bestimmter Zeichen
            Patt = Pattern.compile(" ");
            Match = Patt.matcher(InputString);

            // Erstellung eines Stringbuilders
            StringBuilder SB = new StringBuilder();
            while (Match.find())
                // Ersetzt alle Leerzeichen mit einem neu definierten Zeichen
                Match.appendReplacement(SB, "__");
            Match.appendTail(SB);

            System.out.println("Beispiel 5: " + SB);
        }

        System.out.print("\n");

        // Beispiel 6
        {
        // Überprüfung, ob der String aus den besagten Regex besteht.
        // Die Ausgabe erfolgt über true oder false
            boolean F = InputString.matches("[- a-z]*"); // 'Convenience Function'
            System.out.print("Beispiel 6: " + F + "\t");

        // Der Input Stream wird mithilfe von replaceAll verändert, es wird der angegebene REGEX
        // mit einer Alternativen Stringfolge ersetzt.
            String R = InputString.replaceAll("do", "go"); // 'Convenience Function'
            System.out.println("'" + R + "'");

        // TODO: Aufgabe mit replace und CharSequences
        }

        System.out.print("\n");

        // Spezielle Aufgaben für obige Daten
        {
            // Finden Sie alle zweibuchstabigen Wörter
            // Finden Sie ganze Wörter, die "is" enthalten
            // Finden Sie beliebige Palindrome, die zw. 3 und 5 Zeichen lang sind
        }

        // Allgemeine Aufgaben mit eigenen Beispieldaten
        {
            /*
             Finden aller Varianten von "Meier":
             "Meier", "Maier", "Meyer", "Mayer", "Meir", "Mair", "Meyr", "Mayr"
             */
            /*
             Finden engl. Wörter, die nur aus Buchstaben der 1. bzw. 2. Hälfte
             des engl. Alphabets bestehen (z.B. "alabama" vs. "soupspoon")
             */
            /*
             Finden beliebiger Ganzzahlen, die durch 2 teilbar sind
             (auch 0 selbst und Zahlen mit führenden 0en)
             */

            /*
             Finden von Wörtern, die mit dem selben Segment beginnen und enden,
             das mind. 2 Zeichen lang ist (z.B. "haha", "nennen", "einstein", "enteisent")
             */
            /*
             Finden von Wörtern mit 3 bel. gleichen Buchstaben hintereinander
             (z.B. "Teeei", "Schifffahrt", "XXXVIII", "1999"/"2000")
             */
            /*
             Finden von Wörtern des Schemas XY-YZ mit gemeinsamem Mittelteil,
             der mind. 2 Buchstaben umfasst ("orange-gelb")
             */

            /*
             * String S;
             * Patt = Pattern.compile("");
             * Match = Patt.matcher(S = "");
             * 
             * List<Map.Entry<Integer, Integer>> Finds = finder(Match);
             * List<String> Findings = finds(S, Finds);
             * 
             * System.out.println(Finds);
             * System.out.println(Findings);
             * 
             * //boolean F = Match.find();
             * //boolean F = Match.matches();
             */

        }
    }
}
