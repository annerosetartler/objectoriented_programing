import java.util.LinkedList;
import java.util.List;

public class BarkBeetle implements Runnable {

    private Field currentField;
    private int waitingCount;
    private int generation;
    private int maxWaitingTime = 3;
    private Simulation thisSim;

    private Field childField1;
    private Field childField2;
    private Thread bBeetle;

    //TODO: könnte sein, dass der Thread bereits im Konstruktor auf das Feld gesetzt werden muss
    public BarkBeetle(Simulation s, int x, int y, int generation){
        thisSim = s;
        currentField = s.getField(x,y);
        waitingCount = 0;
        this.generation = generation;
        setContent();
    }

    //TODO: bin mir hier generell noch nicht sicher, ob das so funktioniert
    //      weiß auch nicht wie und wann man am besten die Generationen zählen sollte
    @Override
    public void run() {
        bBeetle = Thread.currentThread();
        currentField.setBarkBThread(bBeetle);
        while(!bBeetle.isInterrupted() && waitingCount < maxWaitingTime && generation < 32){
            getChildFields();
            if(childField1 != null && childField2 != null){
                synchronized (childField1){
                    synchronized (childField2){
                        if(Thread.currentThread().isInterrupted()){
                            break;
                        }
                        int newGen = generation + 1;
                        BarkBeetle bChild1 = new BarkBeetle(thisSim,childField1.getxPos(),childField1.getyPos(),newGen);
                        BarkBeetle bChild2 = new BarkBeetle(thisSim,childField2.getxPos(),childField2.getyPos(),newGen);
                        thisSim.addBBeetlesAndStart(bChild1,bChild2);//vll außerhalb des synchronized
                    }
                }
            }
            long waitTime = (long) (Math.random() * 45 + 5);
            try{
                Thread.sleep(waitTime);
            }catch(InterruptedException e){
                return; //TODO: weiß noch nicht, ob das so funktioniert...
            }
            waitingCount++;
            generation++;
            System.out.println("Borkenkäfer haben gewartet: ");
            thisSim.print();
        }
        if(generation >= 32){
            System.out.println("Finaler Zustand der Käferpopulationen:");
            thisSim.endAll();
            thisSim.stats();
            thisSim.print();
        }
        if(waitingCount >= maxWaitingTime){
            currentField.setContent('X');
            currentField.setBarkBThread(null);
            this.endThread();//weiß nicht, ob dann die ganze Methode abgebrochen wird...
            thisSim.checkBarkBeetles();
        }
    }

    private void setContent(){
        currentField.setContent('0');
    }

    private void getChildFields(){
        List<Field> neighbours = currentField.getNeighbours();
        List<Field> firstSelection = new LinkedList<Field>();
        for(Field f : neighbours){
            if(f.getContent() == '*'){
                firstSelection.add(f);
            }
        }
        if(firstSelection.size()>= 2){
            childField1 = firstSelection.get(0);
            childField2 = firstSelection.get(1);
        }else{
            childField1 = null;
            childField2 = null;
        }
    }

    public void endThread(){
        if(!bBeetle.isInterrupted()){
            bBeetle.interrupt();
        }
    }

    public boolean isActive(){
        return !bBeetle.isInterrupted();
    }

    public String toString(){
        return "Borkenkäfer: Generationen: " + waitingCount + "; Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
