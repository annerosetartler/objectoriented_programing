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

    public void addABeetleAndStart(AntBeetle a){
        antBeetles.add(a);
        new Thread(a,"AntBeetle").start();
    }

    public void addBBeetlesAndStart(BarkBeetle b1, BarkBeetle b2){
        barkBeetles.add(b1);
        barkBeetles.add(b2);
        new Thread(b1,"BarkBeetle").start();
        new Thread(b2,"BarkBeetle").start();
    }

    public void endAll(){
        for(BarkBeetle b : barkBeetles){
            b.endThread();
        }
        for(AntBeetle a : antBeetles){
            a.endThread();
        }
    }

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
