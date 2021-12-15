import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private Forest forest;
    private List<BarkBeetle> barkBeetles;
    private List<AntBeetle> antBeetles;

    //VORB: forest != null
    //      alle Zeilen sind gleich lang & es gibt keine null-Einträge
    public Simulation(Forest forest){
        this.forest = forest;
    }

    //TODO: - Methode, die alle threads löscht, (passiert wenn eine Borkenkäferpopulation Generation 32 erreicht oder alle Borkenkäfer ausgestorben sind)
    //      - Methode, die neue Threads einmal für Borkenkäfer und einmal für Ameisenbuntkäfer in die Listen einfügt
    //      - Methode, die Ausgabe der Werte aller Populationen generiert
    //      - Methode, die Ausgabe des Waldes generiert


    //VORB: bB != null & aB != null & alle Einträge von bB != null & alle Einträge von aB != null
    //      Objekte in bB & aB dürfen sich icht überlappen und dürfen nicht auf einer Stelle im Wald
    //      positioniert sein, die mit 'X' markiert ist.
    //TODO: der folgende Abschnitt der Vorbedingung gehört hier nicht hin, aber er steht jetzt einfach mal hier, damit wir das nicht vergessen!!
    //      erlaubte x- & y-Werte für die Positionen der Käfer sind: x >= 1 & x <= forest[0].length-2
    //                                                               y >= 1 & y <= forest.length-2
    //NACHB: startet die Simulation mit den in bB enthaltenen Borkenkäferpopulationen und den in
    //       aB enthaltenen Ameisenbuntkäferpopulationen auf dem Wald(=Forest) der Simulation
    public void startSim(LinkedList<BarkBeetle> bB, LinkedList<AntBeetle> aB){
        this.barkBeetles = bB;
        this.antBeetles = aB;
        for(BarkBeetle b : barkBeetles){
            new Thread(b, "BarkBeetle").start();
        }
        for(AntBeetle a : antBeetles){
            new Thread(a, "AntBeetle").start();
        }
    }

    public Field getField(int x, int y){
        return forest.getField(x,y);
    }
}
