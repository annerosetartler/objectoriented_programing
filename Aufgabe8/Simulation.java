import java.util.*;

public class Simulation {
    //KOMMENTAR: simuliert Käferpopulationen auf einem Wald (= forest)

    //INV: forest != null
    //     alle Zeilen sind gleich lang & es gibt keine null-Einträge
    private final Forest forest;
    private List<Beetle> theBeetles;
    private boolean running;
    private boolean globalInterrupt;
    private int countBarkThreads;
    private ThreadGroup beetleThreads;

    //VORB: forest != null
    //      alle Zeilen sind gleich lang & es gibt keine null-Einträge
    public Simulation(Forest forest){
        this.forest = forest;
        theBeetles = Collections.synchronizedList(new ArrayList<Beetle>());
        running = false;
        globalInterrupt = false;
        countBarkThreads = 0;//= Anzahl der aktiven Borkenkäfer-Threads
        beetleThreads = new ThreadGroup("TheBeetles");
    }

    //NACHB: beendet alle laufenden Threads & gibt den Zustand aller Käferpopulationen sowie den Zustand des Walds aus
    public void endAll(){
        if (!running){
            return;
        }
        running = false;
        System.out.println("Finaler Zustand der Käferpopulationen:");
        /*
        synchronized (theBeetles) {
            for (Beetle b : theBeetles) {
                if (b != null) b.endThread();
            }
        }
         */
        synchronized (beetleThreads){
            beetleThreads.interrupt();
        }
        stats();
        print("Finaler Zustand des Walds: ");
    }

    //NACHB: gibt den Zustand aller Käferpopulationen aus
    public void stats(){
        System.out.println("Finaler Zustand der Käferpopulationen: ");
        synchronized (theBeetles){
        for(Beetle b : theBeetles){
            System.out.println(b.toString());
        }
        }
    }

    //VORB: change = 1 || change = -1
    //NACHB: verändert countBarkThreads um den Wert change
    public void changeNrOfThread(int change){
        countBarkThreads += change;
    }

    //NACHB: gibt die Anzahl der aktiven Borkenkäferpopulationen zurück
    public int getNrOfBarkThreads(){
        return countBarkThreads;
    }

    //NACHB: gibt den Zustand des Walds aus
    public synchronized void print(String message){
        forest.print(message);
    }

    //VORB: BarkBInfo != null & AntBInfo != null
    //      für alle Zeilen in BarkBInfo gilt: BarkBInfo[x].length = 3 & BarkBInfo[x][0] >= 1 & BarkBInfo[x][0] <= forest[0].length-2
    //      & BarkBInfo[x][1] >= 1 & BarkBInfo[x][1] <= forest.length-2 & BarkBInfo[x][2] = 1
    //      für alle Zeilen in AntBInfo gilt: AntBInfo[x].length = 2 & AntBInfo[x][0] >= 1 & AntBInfo[x][0] <= forest[0].length-2
    //      & AntBInfo[x][1] >= 1 & AntBInfo[x][1] <= forest.length-2
    //      Koordinaten(x-Koordinate = BarkBInfo[x][0] || AntBInfo[x][0], y-Koordniate = BarkBInfo[x][1] || AntBInfo[x][1])
    //      in BarkBInfo & AntBInfo sollen nicht auf eine leere Stelle im Wald zeigen
    //NACHB: fügt BarkBeetles & AntBeetles gemäß den Infos in BarkBInfo & AntBInfo in theBeetles ein
    //       die Käfer werden somit auf die Felder des Walds gesetzt
    public void populate(int[][] BarkBInfo, int[][] AntBInfo){
        for (int[] info : BarkBInfo) {
            theBeetles.add(new BarkBeetle(this, info[0], info[1], info[2], theBeetles));
        }

        for (int[] info : AntBInfo) {
            theBeetles.add(new AntBeetle(this, info[0], info[1], theBeetles));
        }
    }

    //NACHB: startet die Simulation, in dem jeder Käferthread in theBeetles gestartet wird
    public void startSim(){
        running = true;
        synchronized (theBeetles){
            for (Beetle b : theBeetles) {
                new Thread(beetleThreads,b, "Beetle",16).start();
            }
        }
    }

    //NACHB: gibt globalInterrupt
    //KOMMENTAR: dient zur Abfrage, ob bereits global abgebrochen wurde
    public boolean getGlobalInterrupt(){
        return globalInterrupt;
    }

    //NACHB: setzt den globalen Interrupt, in dem globalInterrupt auf true gesetzt wird
    public void interruptGlobally(){
        globalInterrupt = true;
    }

    //NACHB: gibt eine Referenz auf beetleThreads zurück
    public ThreadGroup getBeetleThreads(){return beetleThreads;}

    //VORB: x >= 0 & x <= forest[0].length-1
    //      y >= 0 & y <= forest.length-1
    //NACHB: gibt das Feld an der Stelle x,y im Wald zurück
    public Field getField(int x, int y){
        return forest.getField(x,y);
    }
}
