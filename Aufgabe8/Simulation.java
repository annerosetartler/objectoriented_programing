import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class Simulation {
    private final Forest forest;
    private List<BarkBeetle> barkBeetles;
    private List<AntBeetle> antBeetles;

    //VORB: forest != null
    //      alle Zeilen sind gleich lang & es gibt keine null-Einträge
    public Simulation(Forest forest){
        this.forest = forest;
        barkBeetles = Collections.synchronizedList(new ArrayList<BarkBeetle>());
        antBeetles = Collections.synchronizedList(new ArrayList<AntBeetle>());
    }

    //NACHB: beendet alle laufenden Threads
    public synchronized void endAll(){ //ToDo: ist hier das Problem, dass die Simulation-Referenz in dem BArkBeetle, von dem aus wir das aufrufen, iwie nicht mehr "funktioniert"
        System.out.println("Finaler Zustand der Käferpopulationen:");
            for(AntBeetle a : antBeetles){
                if(a != null)a.endThread();
            }
            for(BarkBeetle b : barkBeetles){
                if(b != null)b.endThread();
            }
        BarkBeetle.countThreads = 0;
        stats();
        print("Finaler Zustand des Walds: ");
    }

    //NACHB: gibt toString aller BarkBeetles und aller antBeetles aus
    public void stats(){
        System.out.println("Finaler Zustand der Käferpopulationen: ");
        for(BarkBeetle b : barkBeetles){
            System.out.println(b.toString());
        }
        for(AntBeetle a : antBeetles){
            System.out.println(a.toString());
        }
    }

    public synchronized void print(String message){
        forest.print(message);
    }

    public void populate(int[][] BarkBInfo, int[][] AntBInfo){
        for (int[] info : BarkBInfo) {
            barkBeetles.add(new BarkBeetle(this, info[0], info[1], info[2], barkBeetles));
        }
        for (int[] info : AntBInfo) {
            antBeetles.add(new AntBeetle(this, info[0], info[1], antBeetles));
        }
    }

    //VORB: bB != null & aB != null & alle Einträge von bB != null & alle Einträge von aB != null
    //      Objekte in bB & aB dürfen sich icht überlappen und dürfen nicht auf einer Stelle im Wald
    //      positioniert sein, die mit 'X' markiert ist.
    //TODO: gehört hier schon hin, aber anders formuliert. Es geht um die erlaubten Feldkoordinaten der Käferpopulationen, die sollten passen
    //      erlaubte x- & y-Werte für die Positionen der Käfer sind: x >= 1 & x <= forest[0].length-2
    //                                                               y >= 1 & y <= forest.length-2
    //NACHB: startet die Simulation mit den in bB enthaltenen Borkenkäferpopulationen und den in
    //       aB enthaltenen Ameisenbuntkäferpopulationen auf dem Wald(=Forest) der Simulation
    public void startSim(){
        synchronized (barkBeetles){
            for (BarkBeetle b : barkBeetles) {
                new Thread(b, "BarkBeetle").start();
            }
        }
        synchronized (antBeetles){
            for (AntBeetle a : antBeetles) {
                new Thread(a, "AntBeetle").start();
            }
        }
    }

    //NACHB: gibt das Feld and Stelle x,y zurück
    public Field getField(int x, int y){
        return forest.getField(x,y);
    }
}
