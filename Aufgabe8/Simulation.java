import java.util.*;

public class Simulation {
    private final Forest forest;
    private List<Beetle> theBeetles;
    private boolean running;
    private static boolean globalInterrupt;

    //VORB: forest != null
    //      alle Zeilen sind gleich lang & es gibt keine null-Einträge
    public Simulation(Forest forest){
        this.forest = forest;
        theBeetles = Collections.synchronizedList(new ArrayList<Beetle>());
        running = false;
        globalInterrupt = false;
        BarkBeetle.resetCountThreads();
    }

    //NACHB: beendet alle laufenden Threads
    public void endAll(){
        if (!running){
            return;
        }
        running = false;
        System.out.println("Finaler Zustand der Käferpopulationen:");
        synchronized (theBeetles) {
            for (Beetle b : theBeetles) {
                if (b != null) b.endThread();
            }
        }
        stats();
        print("Finaler Zustand des Walds: ");
    }

    //NACHB: gibt toString aller BarkBeetles und aller antBeetles aus
    public void stats(){
        System.out.println("Finaler Zustand der Käferpopulationen: ");
        synchronized (theBeetles){
        for(Beetle b : theBeetles){
            System.out.println(b.toString());
        }
        }
    }

    public synchronized void print(String message){
        forest.print(message);
    }

    public void populate(int[][] BarkBInfo, int[][] AntBInfo){
        for (int[] info : BarkBInfo) {
            theBeetles.add(new BarkBeetle(this, info[0], info[1], info[2], theBeetles));
        }

        for (int[] info : AntBInfo) {
            theBeetles.add(new AntBeetle(this, info[0], info[1], theBeetles));
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
        running = true;
        synchronized (theBeetles){
            for (Beetle b : theBeetles) {
                new Thread(b, "Beetle").start();
            }
        }
    }

    public boolean getGlobalInterrupt(){
        return globalInterrupt;
    }

    public void interruptGlobally(){
        globalInterrupt = true;
    }

    //NACHB: gibt das Feld and Stelle x,y zurück
    public Field getField(int x, int y){
        return forest.getField(x,y);
    }
}
