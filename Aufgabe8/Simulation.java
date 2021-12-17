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

    //NACHB: Checkt ob es noch BarkBettle Populationen gibt, wenn nicht werden alle Threads beendet
    public void checkBarkBeetles(){
        int count = 0;
        for(BarkBeetle b : barkBeetles){
            if(b.isActive()){
                count++;
            }
        }
        if(count == 0){
            endAll();
        }
    }

    //VORB: a != null
    //NACHB: fügt eine AntBeetle zur List antBeetles hinzu und startet einen neuen Thread
    public synchronized void addABeetleAndStart(AntBeetle a){
        antBeetles.add(a);
        new Thread(a,"AntBeetle").start();
    }

    //VORB: b1 != null & b2 != null
    //NACHB: fügt zwei BarkBeetles zur List barkBeetles hinzu und startet jweils einen neuen Thread
    public synchronized void addBBeetlesAndStart(BarkBeetle b1, BarkBeetle b2){
        barkBeetles.add(b1);
        barkBeetles.add(b2);
        new Thread(b1,"BarkBeetle").start();
        new Thread(b2,"BarkBeetle").start();
    }

    //NACHB: beendet alle laufenden Threads
    public void endAll(){
        System.out.println("Finaler Zustand der Käferpopulationen:");
        for(AntBeetle a : antBeetles){
            a.endThread();
        }
        for(BarkBeetle b : barkBeetles){
            b.endThread();
        }
        stats();
        print();
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

    public void print(){
        forest.print();
    }

    //VORB: bB != null & aB != null & alle Einträge von bB != null & alle Einträge von aB != null
    //      Objekte in bB & aB dürfen sich icht überlappen und dürfen nicht auf einer Stelle im Wald
    //      positioniert sein, die mit 'X' markiert ist.
    //TODO: gehört hier schon hin, aber anders formuliert. Es geht um die erlaubten Feldkoordinaten der Käferpopulationen, die sollten passen
    //      erlaubte x- & y-Werte für die Positionen der Käfer sind: x >= 1 & x <= forest[0].length-2
    //                                                               y >= 1 & y <= forest.length-2
    //NACHB: startet die Simulation mit den in bB enthaltenen Borkenkäferpopulationen und den in
    //       aB enthaltenen Ameisenbuntkäferpopulationen auf dem Wald(=Forest) der Simulation
    public void startSim(LinkedList<BarkBeetle> bB, LinkedList<AntBeetle> aB){
        this.barkBeetles = bB;
        this.antBeetles = aB;
        synchronized (barkBeetles) {
            for(BarkBeetle b : barkBeetles){
                new Thread(b, "BarkBeetle").start();
            }
        }
        synchronized (antBeetles) {
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
