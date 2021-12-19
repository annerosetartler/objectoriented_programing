import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class BarkBeetle implements Runnable {

    private Field currentField;
    private int waitingCount;
    private int generation;
    private int maxWaitingTime = 3;
    private final Simulation thisSim;
    public static int countThreads;
    public static Object lock = new Object();
    private static boolean interrupted;
    private List<BarkBeetle> barkBQueue;

    private Field childField1;
    private Field childField2;
    private Thread bBeetle;

    //TODO: könnte sein, dass der Thread bereits im Konstruktor auf das Feld gesetzt werden muss
    public BarkBeetle(Simulation s, int x, int y, int generation, List<BarkBeetle> bB) {
        barkBQueue = bB;
        thisSim = s;
        currentField = s.getField(x, y);
        waitingCount = 0;
        this.generation = generation;
        setContent();
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                countThreads++;
            }
            bBeetle = Thread.currentThread();
            currentField.setBarkBThread(Thread.currentThread());
            while (!bBeetle.isInterrupted() && waitingCount < maxWaitingTime && generation < 32 && countThreads > 0 && !interrupted) {
                synchronized (currentField) {
                    getChildFields();
                    if (childField1 != null && childField2 != null) {
                        synchronized (childField1) {
                            synchronized (childField2) {
                                if (Thread.currentThread().isInterrupted() || interrupted) {
                                    break;
                                }
                                int newGen = generation + 1;
                                BarkBeetle bChild1 = new BarkBeetle(thisSim, childField1.getxPos(), childField1.getyPos(), newGen,barkBQueue);
                                BarkBeetle bChild2 = new BarkBeetle(thisSim, childField2.getxPos(), childField2.getyPos(), newGen,barkBQueue);
                                addToBarkBQueue(bChild1,bChild2);
                            }
                        }
                    }
                }
                long waitTime = (long) (Math.random() * 45 + 5);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    return;
                }
                waitingCount++;
                generation++;
                thisSim.print("Borkenkäfer haben gewartet: ");
                if (waitingCount >= maxWaitingTime) {
                    currentField.setContent('X');
                    currentField.setBarkBThread(null);
                    this.endThread();
                    synchronized (this) {
                        countThreads--;
                    }
                }
            }
            if (countThreads <= 0 || generation >= 32) {
                interrupted = true;
                thisSim.endAll();
            }
        }catch (InterruptedException e){
            System.out.println(e + "Borkenkäferalarm!");
        }
    }

    private void addToBarkBQueue(BarkBeetle b1, BarkBeetle b2){
        barkBQueue.add(b1);
        barkBQueue.add(b2);
        new Thread(b1, "BarkBeetle").start();
        new Thread(b2, "BarkBeetle").start();
    }


    private void setContent() {
        currentField.setContent('0');
    }

    private void getChildFields() throws InterruptedException{
        List<Field> neighbours = currentField.getNeighbours();
        List<Field> firstSelection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.getContent() == '*') {
                firstSelection.add(f);
            }
        }
        if (firstSelection.size() >= 2) {
            childField1 = firstSelection.get(0);
            childField2 = firstSelection.get(1);
        } else {
            childField1 = null;
            childField2 = null;
        }
    }

    public void endThread() {
        if (!bBeetle.isInterrupted()) {
            bBeetle.interrupt();
        }
    }

    public String toString() {
        return "Borkenkäfer: Generationen: " + waitingCount + "; Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
