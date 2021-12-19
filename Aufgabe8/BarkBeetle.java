import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BarkBeetle implements Beetle {

    private Field currentField;
    private int generation;
    private final Simulation thisSim;
    private static int countThreads;
    private List<Beetle> barkBList;
    private boolean running;
    private Thread currentThread;

    //TODO: könnte sein, dass der Thread bereits im Konstruktor auf das Feld gesetzt werden muss
    public BarkBeetle(Simulation s, int x, int y, int generation, List<Beetle> bB) {
        barkBList = bB;
        thisSim = s;
        currentField = s.getField(x, y);
        currentField.setBeetle(this);
        this.generation = generation;
        synchronized (BarkBeetle.class) {
            countThreads++;
        }
        currentThread = Thread.currentThread();
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (beetleActive()){

            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {
            }
            thisSim.print("Borkenkäfer haben gewartet: ");

            spawnChildren();

            try {
                if (currentField.getLock().tryLock(100, TimeUnit.MILLISECONDS)){
                    if (running) {
                        currentField.damageTree();
                    }
                    currentField.getLock().unlock();
                }
            } catch (InterruptedException ignored) {}

            generation++;

            synchronized (BarkBeetle.class) {
                if (countThreads <= 0 || generation >= 32) {
                    thisSim.interruptGlobally();
                    thisSim.endAll();
                }
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
        if (thisSim.getGlobalInterrupt()){
            return false;
        }
        if (!running){
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
        if (!running){
            return;
        }
        running = false;
        currentThread.interrupt();
        synchronized (BarkBeetle.class) {
            countThreads--;
        }

        if (currentField.getLock().tryLock()) {
            currentField.setBeetle(null);
            currentField.getLock().unlock();
        }
    }

    public static void resetCountThreads(){
        countThreads = 0;
    }

    public String toString() {
        return "Borkenkäfer: Generationen: " + generation + "; Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
