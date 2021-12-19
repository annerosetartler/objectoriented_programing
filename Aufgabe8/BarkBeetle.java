import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BarkBeetle implements Beetle {

    private Field currentField;
    private int waitingCount;
    private int generation;
    private int maxWaitingTime = 3;
    private final Simulation thisSim;
    public static int countThreads;
    public static Object lock = new Object();
    private static boolean globalInterrupt;
    private List<Beetle> barkBList;

    private Field childField1;
    private Field childField2;
    private Thread currentThread;

    //TODO: könnte sein, dass der Thread bereits im Konstruktor auf das Feld gesetzt werden muss
    public BarkBeetle(Simulation s, int x, int y, int generation, List<Beetle> bB) {
        barkBList = bB;
        thisSim = s;
        currentField = s.getField(x, y);
        currentField.setBeetle(this);
        waitingCount = 0;
        this.generation = generation;
        synchronized (BarkBeetle.class) {
            countThreads++;
        }
        currentThread = Thread.currentThread();
    }

    @Override
    public void run() {
        while (beetleActive()){

            spawnChildren();

            try {
                if (currentField.getLock().tryLock(1, TimeUnit.MILLISECONDS)){
                    currentField.damageTree();
                    currentField.getLock().unlock();
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            thisSim.print("Borkenkäfer haben gewartet: ");

            waitingCount++;
            generation++;

            if (countThreads <= 0 || generation >= 32) {
                globalInterrupt = true;
                thisSim.endAll();
            }
        }
    }

    public boolean isPrey(){
        return true;
    }

    @Override
    public String getCharacter() {
        return "0";
    }

    private boolean beetleActive(){
        if (Thread.currentThread().isInterrupted()){
            return false;
        }
        if (waitingCount >= maxWaitingTime){
            return false;
        }
        if (globalInterrupt){
            return false;
        }
        return true;
    }

    private void spawnChildren(){
        List<Field> childFields = getCFields();
        if (childFields.size() == 2){
            spawnChild(childFields.get(0));
            spawnChild(childFields.get(1));
        }
        for (Field f : childFields) {
            f.getLock().unlock();
        }
    }

    private void spawnChild(Field field){
        BarkBeetle b = new BarkBeetle(thisSim, field.getxPos(), field.getyPos(), generation + 1, barkBList); //toDO: theBeetlesLIST!
        barkBList.add(b);
        new Thread(b, "BarkBeetle").start();
    }

    private List<Field> getCFields(){
        List<Field> neighbours = currentField.getNeighbours();
        List<Field> selection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.hasTree() && f.getBeetle() == null && f.getLock().tryLock()){
                selection.add(f);
            }
            if (selection.size() >= 2){
                return selection;
            }
        }
        return selection;
    }

    public void endThread() {
        if (!currentThread.isInterrupted()) {
            currentThread.interrupt();
            synchronized (BarkBeetle.class) {
                countThreads--;
            }
        }
    }

    public String toString() {
        return "Borkenkäfer: Generationen: " + waitingCount + "; Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
